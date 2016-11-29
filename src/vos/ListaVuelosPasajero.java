package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaVuelosPasajero {
	@JsonProperty(value="vuelosPasajero")
	private List<VueloPasajero> vuelosPasajero;
	
	public ListaVuelosPasajero(@JsonProperty(value="vuelosPasajero")List<VueloPasajero> vuelosPasajero) {
		this.vuelosPasajero = vuelosPasajero;
	}

	public List<VueloPasajero> getVuelosPasajero() {
		return vuelosPasajero;
	}

	public void setVuelosPasajero(List<VueloPasajero> vuelosPasajero) {
		this.vuelosPasajero = vuelosPasajero;
	}
}
