package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vuelo {
	
	@JsonProperty(value="id")
	private String id;
	
	@JsonProperty(value="horaSalida")
	private Date horaSalida;
	
	@JsonProperty(value="horaLlegada")
	private Date horaLlegada;
	
	@JsonProperty(value="duracion")
	private Double duracion;
	
	@JsonProperty(value="distancia")
	private Double distancia;
	
	@JsonProperty(value="frecuencia")
	private int frecuencia;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="aeronave")
	private int aeronave;
	
	@JsonProperty(value="aerolinea")
	private String aerolinea;
	
	@JsonProperty(value="origen")
	private String origen;
	
	@JsonProperty(value="destino")
	private String destino;
	public Vuelo(@JsonProperty(value="id") String id,@JsonProperty(value="horaSalida") Date horaSalida,@JsonProperty(value="horaLlegada") Date horaLlegada,@JsonProperty(value="duracion") Double duracion,@JsonProperty(value="distancia") Double distancia,@JsonProperty(value="frecuencia") int frecuencia,@JsonProperty(value="tipo")
			String tipo,@JsonProperty(value="aeronave") int aeronave,@JsonProperty(value="aerolinea") String aerolinea,@JsonProperty(value="origen") String origen,@JsonProperty(value="destino") String destino) {
		super();
		this.id = id;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.duracion = duracion;
		this.distancia = distancia;
		this.frecuencia = frecuencia;
		this.tipo = tipo;
		this.aeronave = aeronave;
		this.aerolinea = aerolinea;
		this.origen = origen;
		this.destino = destino;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	public Double getDuracion() {
		return duracion;
	}
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public int getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getAeronave() {
		return aeronave;
	}
	public void setAeronave(int aeronave) {
		this.aeronave = aeronave;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
}
