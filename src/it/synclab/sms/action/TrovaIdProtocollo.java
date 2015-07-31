package it.synclab.sms.action;

import it.synclab.sms.dbfactory.HibernateUtil;
import it.synclab.sms.persistent.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

public class TrovaIdProtocollo extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<Persona> listaElementi;
	private String nomePersona;
	private String cognomePersona;
	private String paese;
	private String profilo;
	private String da_considerare;

	public TrovaIdProtocollo() {}

	public List<Persona> getListaElementi() {
		return listaElementi;
	}

	public void setListaElementi(List<Persona> listaElementi) {
		this.listaElementi = listaElementi;
	}
	
	public String getNomePersona() {
		return nomePersona;
	}

	public void setNomePersona(String nomePersona) {
		this.nomePersona = nomePersona;
	}

	public String getCognomePersona() {
		return cognomePersona;
	}

	public void setCognomePersona(String cognomePersona) {
		this.cognomePersona = cognomePersona;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
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

	/********************************************************************************
						METODO RICERCA ID PROTOCOLLO ATTRAVERSO QUERY
	 ********************************************************************************/
	public List<Persona> ricercaIdProtocollo(String nomePersona, String cognomePersona) throws SQLException, ParseException {
		super.validate();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Connection conn = session.connection();
		try {
			nomePersona = nomePersona.toUpperCase();
			cognomePersona = cognomePersona.toUpperCase();
			String s = "SELECT Persona.id_protocollo, Persona.nome, Persona.cognome, Persona.paese, Persona.profilo, Persona.da_considerare FROM Persona WHERE UPPER(nome) like '" + nomePersona + "%'" + "AND UPPER(cognome) like '" + cognomePersona + "%'" + "ORDER BY Persona.id_protocollo DESC";
			PreparedStatement stmt = conn.prepareStatement(s);
			ResultSet rs = stmt.executeQuery(s);
			this.listaElementi = new ArrayList<Persona>();
			while (rs.next()) {
				  int id_protocollo = rs.getInt("id_protocollo");
				  String nome = rs.getString("nome");
				  String cognome = rs.getString("cognome");
				  String paese = rs.getString("paese");
				  String profilo = rs.getString("profilo");
				  String da_considerare = rs.getString("da_considerare");
				  Persona persona = new Persona(id_protocollo, nome, cognome, paese, null, null, 0, null, null, null, null, profilo, da_considerare);
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
	
	public String execute() {
		try {
			this.listaElementi = ricercaIdProtocollo(nomePersona, cognomePersona);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e){
			e.printStackTrace();
		}
		return "SUCCESS";
	}
	
}
