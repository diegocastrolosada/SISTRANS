package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Silla 
{
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="numero")
	private int numero;

	public Silla(@JsonProperty(value="tipo") String pTipo,@JsonProperty(value="numero") int pNumero) 
	{
		super();
		this.tipo = pTipo;
		this.numero = pNumero;
	}

	public int getNumero() 
	{
		return numero;
	}
	public void setNumero(int pNumero)
	{
		this.numero = pNumero;
	}
	public String getTipo()
	{
		return tipo;
	}
	public void setTipo(String pTipo)
	{
		this.tipo = pTipo;
	}


}
