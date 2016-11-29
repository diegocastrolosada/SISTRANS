package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class AeronaveCarga
{
	@JsonProperty(value="numSerie")
	private int numSerie;
	
	@JsonProperty(value="capacidadCarga")
	private double capacidadCarga;
	
	public AeronaveCarga(@JsonProperty(value="numSerie")int numSerie, @JsonProperty(value="capacidadCarga")double capacidad ) {
		this.numSerie = numSerie;
		this.capacidadCarga = capacidad;
		// TODO Auto-generated constructor stub
	}
	
	public double getCapacidadCarga()
	{
		return capacidadCarga;
	}
	public void setCapacidadCarga(Double pCapacidadCarga)
	{
		this.capacidadCarga = pCapacidadCarga;
	}

	public int getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(int numSerie) {
		this.numSerie = numSerie;
	}

}

