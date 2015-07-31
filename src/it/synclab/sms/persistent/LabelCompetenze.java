package it.synclab.sms.persistent;

/********************************************************************************
					CLASSE PERSISTENTE "LABELCOMPETENZE"
********************************************************************************/
public class LabelCompetenze {
	private String id_label_competenza;
	private String nome_tipologia;
	private String nome_competenza;

	public LabelCompetenze() {}

	public LabelCompetenze(String id_label_competenza, String nome_competenza, String nome_tipologia) {
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
}
