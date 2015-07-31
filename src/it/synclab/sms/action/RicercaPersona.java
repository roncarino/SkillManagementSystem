package it.synclab.sms.action;

import it.synclab.sms.dbfactory.HibernateUtil;
import it.synclab.sms.persistent.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

public class RicercaPersona extends ActionSupport {
	private static final long serialVersionUID = -1943873928424459465L;
	private List<Persona> listaElementi; 
	
	private List<String> competenze;
	private List<Integer> livello;
	
	private List<String> competenzeSeconda;
	private List<Integer> livelloSecondo;
	
	
	private String scegliLivello;
	private String scegliCompetenza;

	private String scegliLivelloSecondo;
	private String scegliCompetenzaSeconda;
		
	public RicercaPersona() {
	}

	public List<String> getCompetenze() {
		return competenze;
	}

	public void setCompetenze(List<String> competenze) {
		this.competenze = competenze;
	}

	public String getScegliCompetenza() {
		return scegliCompetenza;
	}

	public void setScegliCompetenza(String scegliCompetenza) {
		this.scegliCompetenza = scegliCompetenza;
	}

	public List<Integer> getLivello() {
		return livello;
	}

	public void setLivello(List<Integer> livello) {
		this.livello = livello;
	}

	public String getScegliLivello() {
		return scegliLivello;
	}

	public void setScegliLivello(String scegliLivello) {
		this.scegliLivello = scegliLivello;
	}

	public List<Persona> getListaElementi() {
		return listaElementi;
	}

	public void setListaElementi(List<Persona> listaElementi) {
		this.listaElementi = listaElementi;
	}

	public List<String> getCompetenzeSeconda() {
		return competenzeSeconda;
	}

	public void setCompetenzeSeconda(List<String> competenzeSeconda) {
		this.competenzeSeconda = competenzeSeconda;
	}

	public List<Integer> getLivelloSecondo() {
		return livelloSecondo;
	}

	public void setLivelloSecondo(List<Integer> livelloSecondo) {
		this.livelloSecondo = livelloSecondo;
	}
	

	public String getScegliLivelloSecondo() {
		return scegliLivelloSecondo;
	}

	public void setScegliLivelloSecondo(String scegliLivelloSecondo) {
		this.scegliLivelloSecondo = scegliLivelloSecondo;
	}

	public String getScegliCompetenzaSeconda() {
		return scegliCompetenzaSeconda;
	}

	public void setScegliCompetenzaSeconda(String scegliCompetenzaSeconda) {
		this.scegliCompetenzaSeconda = scegliCompetenzaSeconda;
	}

	/********************************************************************************
	 * MENU' A TENDINA CON LE COMPETENZE
	 ********************************************************************************/
	public List<String> listaCompetenze() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<String> competenze = session.createQuery("SELECT nome_competenza FROM LabelCompetenze ORDER BY nome_competenza").list();
			this.competenze = competenze;
			return competenze;
		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		} finally {
			if (session != null)
				session.flush();
			session.close();
		}
	}

	/********************************************************************************
	 * SECONDO MENU' A TENDINA CON LE COMPETENZE
	 ********************************************************************************/
	public List<String> listaCompetenzeSeconda() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<String> competenzeSeconda = session.createQuery("SELECT nome_competenza FROM LabelCompetenze ORDER BY nome_competenza").list();
			this.competenzeSeconda = competenzeSeconda;
			return competenzeSeconda;
		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		} finally {
			if (session != null)
				session.flush();
				session.close();
		}
	}

	/********************************************************************************
	 * MENU' A TENDINA CON IL LIVELLO
	 ********************************************************************************/
	public void listaLivello() {
		livello = new ArrayList<Integer>();
		livello.add(1);
		livello.add(2);
		livello.add(3);
		livello.add(4);
	}

	/********************************************************************************
	 * SECONDO MENU' A TENDINA CON IL LIVELLO
	 ********************************************************************************/
	public void listaLivelloSecondo() {
		livelloSecondo = new ArrayList<Integer>();
		livelloSecondo.add(1);
		livelloSecondo.add(2);
		livelloSecondo.add(3);
		livelloSecondo.add(4);
	}	

	/********************************************************************************
	 * METODO RICERCA PERSONA ATTRAVERSO COMBO
	 ********************************************************************************/
	public List<Persona> ricercaPersona(String nome_competenza, String livello, String nome_competenza2, String livello2) throws SQLException, ParseException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Connection conn = session.connection();
		try {
			String s;
			if(nome_competenza2.equals("-1")) {
				s = "SELECT Persona.id_protocollo, Persona.nome, Persona.cognome, Persona.paese, Persona.telefono, Persona.data_nascita, Persona.email, Persona.notePersona FROM Persona, LabelCompetenze, Competenze WHERE LabelCompetenze.id_label_competenza = Competenze.id_competenza AND Persona.id_protocollo = Competenze.id_protocollo AND nome_competenza='" + nome_competenza + "' AND livello > '" + livello + "' ORDER BY Persona.id_protocollo DESC";
			} else {
				s = " SELECT Persona.id_protocollo, Persona.nome, Persona.cognome, Persona.paese, Persona.telefono, Persona.data_nascita, Persona.email, Persona.notePersona FROM Persona, LabelCompetenze, Competenze WHERE LabelCompetenze.id_label_competenza = Competenze.id_competenza AND Persona.id_protocollo = Competenze.id_protocollo AND nome_competenza='" + nome_competenza + "' AND livello > '" + livello + "' AND Persona.id_protocollo IN (SELECT Persona.id_protocollo FROM Persona, LabelCompetenze, Competenze WHERE LabelCompetenze.id_label_competenza = Competenze.id_competenza AND Persona.id_protocollo = Competenze.id_protocollo AND nome_competenza= '" + nome_competenza2 + "' AND livello > '" + livello2 + "') ";
			}
			PreparedStatement stmt = conn.prepareStatement(s);
			ResultSet rs = stmt.executeQuery(s);
			this.listaElementi = new ArrayList<Persona>();
			while (rs.next()) {
				int id_protocollo = rs.getInt("id_protocollo");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String paese = rs.getString("paese");
				String data_nascita = rs.getString("data_nascita");
				int eta = calcolaEta(stringToDateConversion(data_nascita));
				String telefono = rs.getString("telefono");
				String email = rs.getString("email");
				String notePersona = rs.getString("notePersona");
				Persona persona = new Persona(id_protocollo, nome, cognome, paese, null, null, eta, telefono, email, notePersona, null, null, null);
				this.listaElementi.add(persona);
			}
			return listaElementi;
		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		} finally {
			if (session != null)
				session.flush();
			session.close();
			if (conn != null)
				conn.close();
		}

	}

	/********************************************************************************
	 * EFFETTUA CAST DA STRING A DATA
	 ********************************************************************************/
	public Date stringToDateConversion(String data_nascita) throws ParseException {
		DateFormat formatter;
		Date data;
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		data = (Date) formatter.parse(data_nascita);
		return data;
	}

	/********************************************************************************
	 * CALCOLA ETà DELLA PERSONA CON ANNO CORRENTE E DATA DI NASCITA
	 ********************************************************************************/
	public int calcolaEta(Date data_nascita) throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data_nascita);
		int annoNascita = cal.get(Calendar.YEAR);
		Calendar now = new GregorianCalendar();
		int annoCorrente = now.get(Calendar.YEAR);
		int eta = annoCorrente - annoNascita;
		return eta;
	}

	public String execute() {
		listaCompetenze();
		listaCompetenzeSeconda();
		listaLivello();
		listaLivelloSecondo();
		if (scegliCompetenza != null && scegliLivello != null && scegliCompetenzaSeconda != null && scegliLivelloSecondo != null)
			try {
				this.listaElementi = ricercaPersona(scegliCompetenza, scegliLivello, scegliCompetenzaSeconda, scegliLivelloSecondo);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return "SUCCESS";
	}

}
