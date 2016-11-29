package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Viajero extends Cliente {
	
	@JsonProperty(value="frecuente")
	private String frecuente;
	
	public Viajero(int id, String tipoId, String nombre) {
		super(id, tipoId, nombre);
		// TODO Auto-generated constructor stub
	}

	public String getFrecuente() {
		return frecuente;
	}

	public void setFrecuente(String frecuente) {
		this.frecuente = frecuente;
	}
	
}
