package it.synclab.sms.action;

import it.synclab.sms.dbfactory.HibernateUtil;
import it.synclab.sms.persistent.LabelCompetenze;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

public class InserisciLabelCompetenza extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String id_label_competenza;
	private String nome_competenza;
	private String nome_tipologia;

	public InserisciLabelCompetenza() {}

	public InserisciLabelCompetenza(String id_label_competenza, String nome_competenza, String nome_tipologia) {
		super();
		this.id_label_competenza = id_label_competenza;
		this.nome_competenza = nome_competenza;
		this.nome_tipologia = nome_tipologia;
	}

	public String getId_label_competenza() {
		return id_label_competenza;
	}

	public void setId_label_competenza(String id_label_competenza) {
		this.id_label_competenza = id_label_competenza;
	}

	public String getNome_competenza() {
		return nome_competenza;
	}

	public void setNome_competenza(String nome_competenza) {
		this.nome_competenza = nome_competenza;
	}

	public String getNome_tipologia() {
		return nome_tipologia;
	}

	public void setNome_tipologia(String nome_tipologia) {
		this.nome_tipologia = nome_tipologia;
	}

	/********************************************************************************
					 	METODO INSERISCI COMPETENZA MANUALE
	 ********************************************************************************/
	public boolean inserisciCompetenza(String id_label_competenza, String nome_competenza, String nome_tipologia) {
		LabelCompetenze tmp_label_competenza = new LabelCompetenze(id_label_competenza, nome_competenza, nome_tipologia);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			Connection con = (Connection) session.connection();
			String call = "{call skillManagementSystem.Insert_Label_Competenze(?,?,?)}";
			CallableStatement cs = con.prepareCall(call);
			cs.setString(1, id_label_competenza);
			cs.setString(2, nome_competenza);
			cs.setString(3, nome_tipologia);
			tx = session.beginTransaction();
			session.saveOrUpdate(tmp_label_competenza);
			tx.commit();
		} 
		catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.flush();
				session.close();
		}
		return true;
	}

	public String execute() {
		if (inserisciCompetenza(id_label_competenza, nome_competenza, nome_tipologia)) {
			return "SUCCESS";
		}
		else
			return "ERRORE";
	}

	public void validate() {
		if(getFieldErrors().get("id_label_competenza") == null) 
		if(id_label_competenza.equals("")) {	
			addFieldError("id_label_competenza", "CAMPO OBBLIGATORIO");
		}

		if(getFieldErrors().get("nome_competenza") == null) 
		if(nome_competenza.equals("")) {	
			addFieldError("nome_competenza", "CAMPO OBBLIGATORIO");
		}
		
		if(getFieldErrors().get("nome_tipologia") == null) 
		if(nome_tipologia.equals("")) {	
			addFieldError("nome_tipologia", "CAMPO OBBLIGATORIO");
		}
	}
	
}
