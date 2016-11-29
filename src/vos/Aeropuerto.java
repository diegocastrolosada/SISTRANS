package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Aeropuerto 
{
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="codigo")
	private String codigo;

	@JsonProperty(value="ciudad")
	private String ciudad;

	public Aeropuerto(@JsonProperty(value="nombre")String pNombre,@JsonProperty(value="codigo") String pCodigo,@JsonProperty(value="ciudad") String pCiudad) 
	{
		super();
		this.nombre = pNombre;
		this.codigo = pCodigo;
		this.ciudad = pCiudad;
	}

	public String getCiudad() 
	{
		return ciudad;
	}

	public void setCiudad(String pCiudad) 
	{
		this.ciudad = pCiudad;
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

}
