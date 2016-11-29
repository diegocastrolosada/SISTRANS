package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.*;

public class AeronavePasajero
{
	@JsonProperty(value="numSerie")
	private int numSerie;
	
	@JsonProperty(value="capacidadPasajero")
	private int capacidadPasajero;
	
	public AeronavePasajero(@JsonProperty(value="numSerie")int pNumSerie,@JsonProperty(value="capacidadPasajero")int capacidadPasajero) {
		this.numSerie = pNumSerie;
		this.capacidadPasajero = capacidadPasajero;
		// TODO Auto-generated constructor stub
	}
	
	public int getCapacidadPasajero()
	{
		return capacidadPasajero;
	}
	public void setCapacidadPasajero(int pCapacidadPasajero)
	{
		this.capacidadPasajero = pCapacidadPasajero;
	}
	public int getNumSerie()
	{
		return numSerie;
	}
	public void setNumSerie(int pNumSerie)
	{
		this.numSerie = pNumSerie;
	}
}
