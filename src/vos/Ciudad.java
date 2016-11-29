package vos;

import org.codehaus.jackson.annotate.JsonProperty;


public class Ciudad {
	
	@JsonProperty(value="id")
	private int id;

	@JsonProperty(value="idPais")
	private int idPais;

	@JsonProperty(value="nombre")
	private String nombre;
	
public Ciudad(int id, int idPais, String nombre) {
		super();
		this.id = id;
		this.idPais = idPais;
		this.nombre = nombre;
	}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIdPais() {
	return idPais;
}
public void setIdPais(int idPais) {
	this.idPais = idPais;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

}
