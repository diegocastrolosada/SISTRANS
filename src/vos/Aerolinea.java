package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Aerolinea 
{
    @JsonProperty(value="iata")
	private String iata;
    
    @JsonProperty(value="nombre")
	private String nombre;
    
    @JsonProperty(value="codigo")
	private String codigo;
    
    @JsonProperty("pais")
	private String pais;

	public Aerolinea(@JsonProperty(value="iata") String pIata,@JsonProperty(value="nombre") String pNombre,@JsonProperty(value="codigo") String pCodigo,@JsonProperty("pais") String pPais) 
	{
		super();
		this.iata = pIata;
		this.nombre = pNombre;
		this.codigo = pCodigo;
		this.pais = pPais;
	}

	public String getIata() 
	{
		return iata;
	}

	public void setIata(String pIata) 
	{
		this.iata = pIata;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String pNombre) 
	{
		this.nombre = pNombre;
	}

	public String getCodigo() 
	{
		return codigo;
	}

	public void setCodigo(String pCodigo) 
	{
		this.codigo = pCodigo;
	}
	public String getPais() 
	{
		return pais;
	}

	public void setPais(String pPais) 
	{
		this.pais = pPais;
	}
}
