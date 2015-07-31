package it.synclab.sms.action;

import it.synclab.sms.dbfactory.HibernateUtil;
import it.synclab.sms.persistent.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateDatiPersona extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int id_protocollo;
	private String paese;
	private String indirizzo;
	private String telefono;
	private String email;
	private String notePersona;
	private String status_lavorativo;
	private String profilo;
	private String da_considerare;
	private Persona persona;

	public UpdateDatiPersona() {}

	public int getId_protocollo() {
		return id_protocollo;
	}

	public void setId_protocollo(int id_protocollo) {
		this.id_protocollo = id_protocollo;
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

	public String getStatus_lavorativo() {
		return status_lavorativo;
	}

	public void setStatus_lavorativo(String status_lavorativo) {
		this.status_lavorativo = status_lavorativo;
	}

	public String getProfilo() {
		return profilo;
	}

	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}

	public String getDa_considerare() {
		return da_considerare;
	}

	public void setDa_considerare(String da_considerare) {
		this.da_considerare = da_considerare;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/********************************************************************************
			METODO CHE EFFETTUA LA SELECT SULLA BASE DELL'ID PROTOCOLLO 
	 ********************************************************************************/
	public Persona selectPersona(int id_protocollo) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Connection conn = session.connection();
		try {
			String s = "SELECT nome, cognome, paese, indirizzo, telefono, email, notePersona, status_lavorativo, profilo, da_considerare FROM Persona WHERE id_protocollo=" + id_protocollo;
			PreparedStatement stmt = conn.prepareStatement(s);
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String paese = rs.getString("paese");
				String indirizzo = rs.getString("indirizzo");
				String telefono = rs.getString("telefono");
				String email = rs.getString("email");
				String notePersona = rs.getString("notePersona");
				String status_lavorativo = rs.getString("status_lavorativo");
				String profilo = rs.getString("profilo");
				String da_considerare = rs.getString("da_considerare");
				this.persona = new Persona(id_protocollo, nome, cognome, paese, indirizzo, null, 0, telefono, email, notePersona, status_lavorativo, profilo, da_considerare);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.flush();
			session.close();
			if (conn != null)
				conn.close();
		}
		return this.persona;
	}

	/********************************************************************************
				METODO MODIFICA DATI DI UNA PERSONA ATTRAVERSO QUERY UPDATE
	 ********************************************************************************/
	public String updateDati() throws SQLException {
		Persona tmp_persona = new Persona();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Connection conn = session.connection();
		Transaction tx = null;
		try {			
			tx = session.beginTransaction();
			this.paese = paese.replace("'", "''");
			this.indirizzo = indirizzo.replace("'", "''");
			this.notePersona = notePersona.replace("'", "''");
			this.status_lavorativo = status_lavorativo.replace("'", "''");
			this.profilo = profilo.replace("'", "''");
			this.da_considerare = da_considerare.replace("'", "''");
			String s = "UPDATE Persona SET paese='"+paese+"', indirizzo='"+indirizzo+"', telefono='"+telefono+"', email='"+email+"', notePersona='"+notePersona+"', status_lavorativo='"+status_lavorativo+"', profilo='"+profilo+"', da_considerare='"+da_considerare+"' WHERE id_protocollo='"+id_protocollo+"'";
			Query query = session.createQuery(s);
			query.executeUpdate();
			tmp_persona.setPaese(paese);
			tmp_persona.setIndirizzo(indirizzo);
			tmp_persona.setTelefono(telefono);
			tmp_persona.setEmail(email);
			tmp_persona.setNotePersona(notePersona);
			tmp_persona.setStatus_lavorativo(status_lavorativo);
			tmp_persona.setProfilo(profilo);
			tmp_persona.setDa_considerare(da_considerare);
			session.update(tmp_persona);
			tx.commit();
			return "SUCCESSUPDATE";
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

	public String execute() {
		try {
			this.persona = selectPersona(id_protocollo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}

}
