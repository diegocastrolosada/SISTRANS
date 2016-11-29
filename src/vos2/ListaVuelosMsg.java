package vos2;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.List;

public class ListaVuelosMsg 
{

	@JsonProperty(value="vuelosMsg")
	private List<VueloMsg> vuelos;
	
	public ListaVuelosMsg( @JsonProperty(value="vuelosMsg")List<VueloMsg> vuelos)
	{
		this.vuelos = vuelos;
	}

	public List<VueloMsg> getVuelos() 
	{
		return vuelos;
	}

	public void setVuelo(List<VueloMsg> vuelos) 
	{
		this.vuelos = vuelos;
	}
	
}