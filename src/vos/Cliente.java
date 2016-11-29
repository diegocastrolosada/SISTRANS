package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente {
	
	@JsonProperty(value="id")
	private int id;

	@JsonProperty(value="tipoId")
	private String tipoId;

	@JsonProperty(value="nombre")
	private String nombre;
	
public Cliente(@JsonProperty(value="id")int id,@JsonProperty(value="tipoId") String tipoId,@JsonProperty(value="nombre") String nombre) {
		super();
		this.id = id;
		this.tipoId = tipoId;
		this.nombre = nombre;
	}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTipoId() {
	return tipoId;
}
public void setTipoId(String tipoId) {
	this.tipoId = tipoId;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

}
