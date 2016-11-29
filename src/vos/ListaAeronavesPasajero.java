package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAeronavesPasajero {
	@JsonProperty(value="aeronavesPasajero")
	private List<AeronavePasajero> aeronavesPasajero;
	
	
	public ListaAeronavesPasajero( @JsonProperty(value="aeronavesPasajero")List<AeronavePasajero> aeronavesPasajero){
		this.aeronavesPasajero = aeronavesPasajero;
	}

	
	public List<AeronavePasajero> getAeronavesPasajero() {
		return aeronavesPasajero;
	}

	public void setAeronavesPasajero(List<AeronavePasajero> aeronavesPasajero) {
		this.aeronavesPasajero = aeronavesPasajero;
	}
}
