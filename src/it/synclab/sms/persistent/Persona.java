package it.synclab.sms.persistent;

import java.io.Serializable;

/********************************************************************************
						CLASSE PERSISTENTE "PERSONA"
********************************************************************************/
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_protocollo;
	private String nome;
	private String cognome;
	private String paese;
	private String indirizzo;
	private String data_nascita;
	private int eta;
	private String telefono;
	private String email;
	private String notePersona;
	private String status_lavorativo;
	private String profilo;
	private String da_considerare;

	public Persona() {}

	public Persona(int id_protocollo, String nome, String cognome,
			String paese, String indirizzo, String data_nascita, int eta,
			String telefono, String email, String notePersona,
			String status_lavorativo, String profilo, String da_considerare) {
		super();
		this.id_protocollo = id_protocollo;
		this.nome = nome;
		this.cognome = cognome;
		this.paese = paese;
		this.indirizzo = indirizzo;
		this.data_nascita = data_nascita;
		this.eta = eta;
		this.telefono = telefono;
		this.email = email;
		this.notePersona = notePersona;
		this.status_lavorativo = status_lavorativo;
		this.profilo = profilo;
		this.da_considerare = da_considerare;
	}

	public int getId_protocollo() {
		return id_protocollo;
	}

	public void setId_protocollo(int id_protocollo) {
		this.id_protocollo = id_protocollo;
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

}