package it.synclab.sms.action;

import it.synclab.sms.dbfactory.HibernateUtil;
import it.synclab.sms.persistent.CompetenzeForID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

public class ListaCompetenzePersona extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<CompetenzeForID> listaCompetenze;
	private Hashtable<String, List<CompetenzeForID>> lista;
	private int id_protocollo;
	
	public ListaCompetenzePersona() {}

	public List<CompetenzeForID> getListaCompetenze() {
		return listaCompetenze;
	}

	public void setListaCompetenze(List<CompetenzeForID> listaCompetenze) {
		this.listaCompetenze = listaCompetenze;
	}

 	public Hashtable<String, List<CompetenzeForID>> getLista() {
		return lista;
	}

	public void setLista(Hashtable<String, List<CompetenzeForID>> lista) {
		this.lista = lista;
	}

	public int getId_protocollo() {
		return id_protocollo;
	}

	public void setId_protocollo(int id_protocollo) {
		this.id_protocollo = id_protocollo;
	}

	/********************************************************************************
	 				METODO RICERCA COMPETENZE PER ID PROTOCOLLO
	 ********************************************************************************/
	public Hashtable<String, List<CompetenzeForID>> competenzeIdProtocollo(int id_protocollo) throws SQLException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Connection conn = session.connection();
		try {
			String s = "SELECT comp.livello, label.nome_competenza, label.nome_tipologia FROM Competenze comp, LabelCompetenze label WHERE label.id_label_competenza = comp.id_competenza AND id_protocollo='" + id_protocollo + "' ORDER BY label.nome_tipologia";
			PreparedStatement stmt = conn.prepareStatement(s);
			ResultSet rs = stmt.executeQuery(s);
			this.lista = new Hashtable<String, List<CompetenzeForID>>();
			String tmpTipologia = "";
			while (rs.next()) {
				String nome_tipologia = rs.getString("nome_tipologia");
				String nome_competenza = rs.getString("nome_competenza");
				String livello = rs.getString("livello");
				
				if(!tmpTipologia.equals(nome_tipologia)) {
					CompetenzeForID competenze = new CompetenzeForID(nome_competenza, livello);
					listaCompetenze = new ArrayList<CompetenzeForID>();
					this.listaCompetenze.add(competenze);
					this.lista.put(nome_tipologia, listaCompetenze);
					tmpTipologia = nome_tipologia;
				}
				else {
					CompetenzeForID competenza = new CompetenzeForID(nome_competenza, livello);
					lista.get(nome_tipologia).add(competenza);
				}
			}
			
		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		} finally {
			if (session.isOpen())
				session.flush();
				session.close();
			if (conn != null)
				conn.close();
		}
		return lista;
	}

	public String execute() throws SQLException {
		this.lista = competenzeIdProtocollo(id_protocollo);
		return "SUCCESS";
	}
	
}
