package it.synclab.sms.persistent;

/************************************************************************************************
CLASSE UTILIZZATA ESCLUSIVAMENTE PER AVERE UN OGGETTO AD HOC PER L' ACTION LISTACOMPETENZEPERSONA
*************************************************************************************************/
public class CompetenzeForID {
	private String nome_competenza;
	private String livello;

	public CompetenzeForID() {}

	public CompetenzeForID(String nome_competenza, String livello) {
		super();
		this.nome_competenza = nome_competenza;
		this.livello = livello;
	}

	public String getNome_competenza() {
		return nome_competenza;
	}

	public void setNome_competenza(String nome_competenza) {
		this.nome_competenza = nome_competenza;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

}
