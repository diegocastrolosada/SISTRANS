package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAerolineas {
	@JsonProperty(value="aerolineas")
	private List<Aerolinea> aerolineas;
	
	
	public ListaAerolineas( @JsonProperty(value="aerolineas")List<Aerolinea> aerolineas){
		this.aerolineas = aerolineas;
	}

	
	public List<Aerolinea> getAerolineas() {
		return aerolineas;
	}

	public void setAerolineas(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
}
