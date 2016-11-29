package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAeropuertos {
	@JsonProperty(value="aeropuertos")
	private List<Aeropuerto> aeropuertos;
	
	
	public ListaAeropuertos( @JsonProperty(value="aeropuertos")List<Aeropuerto> aeropuertos){
		this.aeropuertos = aeropuertos;
	}

	
	public List<Aeropuerto> getAeropuertos() {
		return aeropuertos;
	}

	public void setAeropuertos(List<Aeropuerto> aeropuertos) {
		this.aeropuertos = aeropuertos;
	}
}
