package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Aeronave
{
	@JsonProperty(value="numSerie")
	private int numSerie;
	
	@JsonProperty(value="marca")
	private String marca;
	
	@JsonProperty(value="modelo")
	private String modelo;
	
	@JsonProperty(value="anioFabricacion")
	private Date anioFabricacion;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="aerolinea")
	private String aerolinea;
	
	@JsonProperty(value="posesion")
	private String posesion;
	

	public Aeronave(@JsonProperty(value="numSerie") int pNumSerie,@JsonProperty(value="marca") String pMarca,@JsonProperty(value="modelo") String pModelo,@JsonProperty(value="anioFabricacion") Date pAnioFabricacion,@JsonProperty(value="tipo") String pTipo,@JsonProperty(value="aerolinea") String pAerolinea,@JsonProperty(value="posesion") String pPosesion) 
	{
		super();
		this.numSerie = pNumSerie;
		this.marca = pMarca;
		this.modelo = pModelo;
		this.anioFabricacion = pAnioFabricacion;
		this.posesion = pPosesion;
		this.tipo = pTipo;
		this.aerolinea = pAerolinea;
	}

	public int getNumSerie() 
	{
		return numSerie;
	}

	public void setNumSerie(int pNumSerie) 
	{
		this.numSerie = pNumSerie;
	}

	public String getMarca() 
	{
		return marca;
	}

	public void setMarca(String pMarca) 
	{
		this.marca = pMarca;
	}

	public String getModelo() 
	{
		return modelo;
	}

	public void setModelo(String pModelo) 
	{
		this.modelo = pModelo;
	}
	public Date getAnioFabricacion() 
	{
		return anioFabricacion;
	}

	public void setAnioFabricacion(Date pAnioFabricacion) 
	{
		this.anioFabricacion = pAnioFabricacion;
	}
	public String getTipo()
	{
		return tipo;
	}
	public void setTipo(String pTipo)
	{
		this.tipo = pTipo;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	public String getPosesion() {
		return posesion;
	}

	public void setPosesion(String posesion) {
		this.posesion = posesion;
	}
}
