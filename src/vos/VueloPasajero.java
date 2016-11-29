package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VueloPasajero extends Vuelo
{
	@JsonProperty(value="precioEconomica")
	private double precioEconomica;
	
	@JsonProperty(value="precioEjecutiva")
	private double precioEjecutiva;
	
	@JsonProperty(value="capacidadActual")
	private int capacidadActual;

	public VueloPasajero(String id, Date horaSalida, Date horaLlegada, Double duracion, Double distancia,int frecuencia, String tipo, int aeronave, String aerolinea, String origen, String destino)
	{
		super(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino);
	}
	
	public double getPrecioEconomica()
	{
		return precioEconomica;
	}
	public void setPrecioEconomica(double pPrecioE)
	{
		this.precioEconomica = pPrecioE;
	}
	public double getPrecioEjecutiva()
	{
		return precioEjecutiva;
	}
	public void setPrecioEjecutiva(double pPrecioE)
	{
		this.precioEjecutiva = pPrecioE;
	}
	public int getCapacidadActual()
	{
		return capacidadActual;
	}
	public void setCapacidadActual(int pCapacidad)
	{
		this.capacidadActual = pCapacidad;
	}
	
}
