package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaCiudades {
	@JsonProperty(value="ciudades")
	private List<Ciudad> ciudades;
	
	
	public ListaCiudades( @JsonProperty(value="ciudades")List<Ciudad> ciudades){
		this.ciudades = ciudades;
	}


	public List<Ciudad> getCiudades() {
		return ciudades;
	}


	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

}
