package it.synclab.sms.persistent;

import java.io.Serializable;

/********************************************************************************
						CLASSE PERSISTENTE "COMPETENZE"
********************************************************************************/
public class Competenze implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id_competenza;
	private String livello;

	public Competenze() {}

	public Competenze(String id_competenza, String livello) {
		super();
		this.id_competenza = id_competenza;
		this.livello = livello;
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
	
}