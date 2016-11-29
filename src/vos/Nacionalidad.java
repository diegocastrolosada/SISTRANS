package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Nacionalidad {
	
	@JsonProperty(value="idPersona")
	private int idPersona;

	@JsonProperty(value="idPais")
	private int idPais;
	
public Nacionalidad(int idPersona, int idPais) {
		super();
		this.idPersona = idPersona;
		this.idPais = idPais;
	}

public int getIdPersona() {
	return idPersona;
}
public void setIdPersona(int idPersona) {
	this.idPersona = idPersona;
}
public int getIdPais() {
	return idPais;
}
public void setIdPais(int idPais) {
	this.idPais = idPais;
}

}
