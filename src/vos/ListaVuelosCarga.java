package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaVuelosCarga {
	@JsonProperty(value="vuelosCarga")
	private List<VueloCarga> vuelosCarga;
	
	public ListaVuelosCarga(@JsonProperty(value="vuelosCarga")List<VueloCarga> vuelosCarga) {
		this.vuelosCarga = vuelosCarga;
	}

	public List<VueloCarga> getVuelosCarga() {
		return vuelosCarga;
	}

	public void setVuelosCarga(List<VueloCarga> vuelosCarga) {
		this.vuelosCarga = vuelosCarga;
	}
}
