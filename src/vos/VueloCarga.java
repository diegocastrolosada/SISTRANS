package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class VueloCarga extends Vuelo
{
	@JsonProperty(value = "precioDensidad")
	private double precioDensidad;
	
	@JsonProperty(value="capacidadActual")
	private double capacidadActual;

	public VueloCarga(String id, Date horaSalida, Date horaLlegada, Double duracion, Double distancia,int frecuencia, String tipo, int aeronave, String aerolinea, String origen, String destino)
	{
		super(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino);
	}
	
	public double getPrecioDensidad()
	{
		return precioDensidad;
	}
	public void setPrecioDensidad(double pPrecioD)
	{
		this.precioDensidad = pPrecioD;
	}

	public double getCapacidadActual()
	{
		return capacidadActual;
	}
	public void setCapacidadActual(double pCapacidad)
	{
		this.capacidadActual = pCapacidad;
	}
	
}
