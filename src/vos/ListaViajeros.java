package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaViajeros {
	@JsonProperty(value="viajeros")
	private List<Viajero> viajeros;
	
	public ListaViajeros(@JsonProperty(value="viajeros")List<Viajero> viajeros) {
		this.viajeros = viajeros;
	}

	public List<Viajero> getViajeros() {
		return viajeros;
	}

	public void setViajeros(List<Viajero> viajeros) {
		this.viajeros = viajeros;
	}

}
