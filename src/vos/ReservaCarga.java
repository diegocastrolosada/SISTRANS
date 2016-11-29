package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaCarga{
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="pesoCarga")
	private double pesoCarga;
	
	@JsonProperty(value="volumen")
	private double volumen;
	
	@JsonProperty(value="contenido")
	private String contenido;
	
	@JsonProperty(value="vuelo")
	private String vuelo;
	
	@JsonProperty(value="precio")
	private double precio;
	
	public ReservaCarga(@JsonProperty(value="id")int id,@JsonProperty(value="pesoCarga") double pesoCarga ,@JsonProperty(value="volumen") double volumen,@JsonProperty(value="contenido") String contenido,@JsonProperty(value="vuelo")String vuelo,@JsonProperty(value="precio")double precio) {
		this.id = id;
		this.pesoCarga =pesoCarga;
		this.volumen = volumen;
		this.contenido = contenido;
		this.vuelo = vuelo;
		this.precio= precio;
		// TODO Auto-generated constructor stub
	}
	public double getPesoCarga() {
		return pesoCarga;
	}
	public void setPesoCarga(double pesoCarga) {
		this.pesoCarga = pesoCarga;
	}
	public double getVolumen() {
		return volumen;
	}
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getVuelo() {
		return vuelo;
	}
	public void setVuelo(String vuelo) {
		this.vuelo = vuelo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
