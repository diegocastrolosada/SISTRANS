package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva {
	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="vuelo")
	private String vuelo;
	
	@JsonProperty(value="cliente")
	private int cliente;
	
	public Reserva(@JsonProperty(value="id") int id,@JsonProperty(value="vuelo") String vuelo, @JsonProperty(value="cliente")int cliente) {
		super();
		this.id = id;
		this.vuelo = vuelo;
		this.cliente = cliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVuelo() {
		return vuelo;
	}
	public void setVuelo(String vuelo) {
		this.vuelo = vuelo;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	
}
