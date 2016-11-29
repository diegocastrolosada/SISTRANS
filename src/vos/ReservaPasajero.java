package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaPasajero{
	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="vuelo")
	private String vuelo;
	
	@JsonProperty(value="silla")
	private int silla;
	
	@JsonProperty(value="avion")
	private int avion;
	
	@JsonProperty(value="precio")
	private double precio;
	
	public ReservaPasajero(@JsonProperty(value="id")int id,@JsonProperty(value="vuelo")String vuelo,@JsonProperty(value="silla") int silla,@JsonProperty(value="avion") int avion,@JsonProperty(value="precio") double precio) {
		this.id = id;
		this.vuelo = vuelo;
		this.silla = silla;
		this.avion = avion;
		this.precio = precio;
		// TODO Auto-generated constructor stub
	}
	public String getVuelo() {
		return vuelo;
	}
	public void setVuelo(String vuelo) {
		this.vuelo = vuelo;
	}
	public int getSilla() {
		return silla;
	}
	public void setSilla(int silla) {
		this.silla = silla;
	}
	public int getAvion() {
		return avion;
	}
	public void setAvion(int avion) {
		this.avion = avion;
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
