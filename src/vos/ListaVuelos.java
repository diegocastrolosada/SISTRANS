package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaVuelos {
	@JsonProperty(value="vuelos")
	private List<Vuelo> vuelos;
	
	public ListaVuelos(@JsonProperty(value="vuelos")List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
}
