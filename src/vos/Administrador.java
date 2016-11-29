package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Administrador 
{
    @JsonProperty(value="idAdmin")	
	private int idAdmin;
    
    @JsonProperty(value="nombre")
	private String nombre;
    
    @JsonProperty(value="correo")
	private String correo;
    
    @JsonProperty(value="rol")
	private String rol;

	public Administrador(@JsonProperty(value="idAdmin")int pIdAdmin,@JsonProperty(value="nombre")String pNombre,@JsonProperty(value="correo")String pCorreo,@JsonProperty(value="rol")String pRol) 
	{
		super();
		this.idAdmin = pIdAdmin;
		this.nombre = pNombre;
		this.correo = pCorreo;
		this.rol = pRol;
	}

	public int getIdAdmin() 
	{
		return idAdmin;
	}

	public void setIdAdmin(int pIdAdmin) 
	{
		this.idAdmin = pIdAdmin;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String pNombre) 
	{
		this.nombre = pNombre;
	}

	public String getCorreo() 
	{
		return correo;
	}

	public void setCorreo(String pCorreo) 
	{
		this.correo = pCorreo;
	}
	public String getRol() 
	{
		return rol;
	}

	public void setRol(String pRol) 
	{
		this.rol = pRol;
	}

}
