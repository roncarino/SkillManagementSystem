package it.synclab.sms.action;

import it.synclab.sms.dbfactory.HibernateUtil;
import it.synclab.sms.excel.ReadExcelFile;
import it.synclab.sms.persistent.Persona;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

public class InsertAutomaticCompetenze extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id_protocollo;
	private String id_competenza;
	private String livello;
	
	private String nome;
	private String cognome;
	private String paese;
	private String indirizzo;
	private String data_nascita;
	private int eta;
	private String telefono;
	private String email;
	private String notePersona;

	public InsertAutomaticCompetenze() {}

	public int getId_protocollo() {
		return id_protocollo;
	}

	public void setId_protocollo(int id_protocollo) {
		this.id_protocollo = id_protocollo;
	}

	public String getId_competenza() {
		return id_competenza;
	}

	public void setId_competenza(String id_competenza) {
		this.id_competenza = id_competenza;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotePersona() {
		return notePersona;
	}

	public void setNotePersona(String notePersona) {
		this.notePersona = notePersona;
	}

	/********************************************************************************
	 					METODO INSERIMENTO COMPETENZE AUTOMATICO
	 ********************************************************************************/
	/* All'interno del metodo è compreso anche l'inserimento nella tabella Label Competenze */
	public boolean automaticInsertCompetenza(int id_protocollo, String id_competenza, String livello) throws IOException {
		Object array[][] = new Object[91][11];
		String path = System.getProperty("java.io.tmpdir") + "/skill_matrix.xls";
		array = ReadExcelFile.readExcelFile(path);
		System.out.println("*****************************FILE LETTO*************************************");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			for(int cont = 0; cont <= 1; cont++) 
				for (int rig = (7 - (4*cont)); rig < 91; rig++) {
					for (int col = (1 + (7*cont)); col < (4 + (7*cont)); col++) {			
						if (array[rig][col] != null) {
							String nome_competenza = (String) array[rig][col - 1];
							System.out.println(nome_competenza);
							col++;
							String nome_tipologia = (String) array[rig][1 + (7*cont)];
							System.out.println(nome_tipologia);
							String id_label_competenza = nome_tipologia + "||" + nome_competenza;
							setLivello(String.valueOf(array[rig][col - 1]));
							try {
								if(nome_competenza != null) {
//INSERIMENTO IN LABEL_COMPETENZE, ATTENZIONE: SE BISOGNA RIINSERIRE ANDARE IN ReadExcelFile E INCLUDERE LE RIGHE CON LIVELLO PARI A 0 
//									InserisciLabelCompetenza insertLabel = new InserisciLabelCompetenza(id_label_competenza, nome_competenza, nome_tipologia);
//									insertLabel.inserisciCompetenza(id_label_competenza, nome_competenza, nome_tipologia);					
	
									Connection con = (Connection) session.connection();
									String call = "{call skillManagementSystem.Insert_Competenze(?,?,?)}";
									CallableStatement cs = con.prepareCall(call);
									cs.setInt(1, getId_protocollo()); // ID_PROTOCOLLO
									cs.setString(2, id_label_competenza); // ID_LABEL_COMPETENZA
									cs.setString(3, getLivello()); // LIVELLO
									cs.execute();
								}
							} catch (SQLException errsql) {
							}
							tx = session.beginTransaction();
							tx.commit();
						}
					}
				}
			System.out.println("Competenze automatiche inserite correttamente");
			return true;

		} catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			if (session != null)
				session.flush();			
				session.close();
		}
	}
	
	
	/********************************************************************************
					METODO INSERIMENTO DATI DELLA PERSONA
	 ********************************************************************************/	
	public boolean inserisciDatiPersona(int id_protocollo, String nome, String cognome, String paese, String indirizzo, String data_nascita, int eta, String telefono, String email, String notePersona) {
		Persona tmp_persona = new Persona(id_protocollo, nome, cognome, paese, indirizzo, data_nascita, eta, telefono, email, notePersona, null, null, null);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Connection con = (Connection) session.connection();
			String call = "{call skillManagementSystem.Insert_Dati_Persona(?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = con.prepareCall(call);
			cs.setInt(1, getId_protocollo()); // ID_PROTOCOLLO
			cs.setString(2, nome); // NOME
			cs.setString(3, cognome); // COGNOME
			cs.setString(4, paese); // PAESE
			cs.setString(5, indirizzo); // INDIRIZZO
			cs.setString(6, data_nascita); // DATA DI NASCITA 
			cs.setString(7, telefono); // TELEFONO
			cs.setString(8, email); // E-MAIL
			cs.setString(9, notePersona); // NOTE DELLA PERSONA
			tx = session.beginTransaction();
			session.saveOrUpdate(tmp_persona);
			tx.commit();
			return true;
		} 
		catch (SQLException errsql) {
				return false;
		}
		catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			if (session != null)
				session.flush();
				session.close();
		}
	}
	
	public String execute() throws IOException {
		if (automaticInsertCompetenza(id_protocollo, id_competenza, livello) && inserisciDatiPersona(id_protocollo, nome, cognome, paese, indirizzo, data_nascita, eta, telefono, email, notePersona)) {
			UploadFile.deleteFile();
			return "SUCCESS";
		} else
			return "ERRORE";
	}

	public void validate() {
		//DEVE ESSERCI ID PROTOCOLLO
		if(getFieldErrors().get("id_protocollo") == null)
		if(id_protocollo == 0) {	
			addFieldError("id_protocollo", "CAMPO OBBLIGATORIO");
		}
		
		//DEVE ESSERCI NOME		
		if(getFieldErrors().get("nome") == null)
		if(nome.equals("")) {	
			addFieldError("nome", "CAMPO OBBLIGATORIO");
		}
		
		//DEVE ESSERCI COGNOME
		if(getFieldErrors().get("cognome") == null)
		if(cognome.equals("")) {	
			addFieldError("cognome", "CAMPO OBBLIGATORIO");
		}
		
		//IL NUMERO DI TELEFONO, SE C'E', DEVE ESSERE UN NUMERO
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(telefono);
		if(getFieldErrors().get("telefono") == null)
		if(m.find())
			try {
				Integer.parseInt(telefono);
			} catch (NumberFormatException e) {
				addFieldError("telefono", "DEVI INSERIRE UN NUMERO");
			}
		
		//DEVE ESSERCI LA DATA DI NASCITA
		if(getFieldErrors().get("data_nascita") == null)
		if(data_nascita.equals("")) {	
			addFieldError("data_nascita", "CAMPO OBBLIGATORIO");
		}
		
		//LA DATA DI NASCITA DEVE ESSERE NEL FORMATO dd/MM/yyyy
		Pattern p2 = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
		Matcher m2 = p2.matcher(data_nascita);
		if(getFieldErrors().get("data_nascita") == null)
		if(!m2.find())
			try {
				Integer.parseInt(data_nascita);
			} catch (NumberFormatException e) {
				addFieldError("data_nascita", "DATA NEL FORMATO dd/MM/yyyy");
			}
	}
}
