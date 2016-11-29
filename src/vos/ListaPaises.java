package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaPaises {
	@JsonProperty(value="paises")
	private List<Pais> paises;
	
	public ListaPaises(@JsonProperty(value="paises")List<Pais> paises) {
		this.paises = paises;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}
}
