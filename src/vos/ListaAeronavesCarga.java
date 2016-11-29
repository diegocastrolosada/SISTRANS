package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAeronavesCarga {
	@JsonProperty(value="aeronavesCarga")
	private List<AeronaveCarga> aeronavesCarga;
	
	
	public ListaAeronavesCarga( @JsonProperty(value="aeronavesCarga")List<AeronaveCarga> aeronavesCarga){
		this.aeronavesCarga = aeronavesCarga;
	}

	
	public List<AeronaveCarga> getAeronavesCarga() {
		return aeronavesCarga;
	}

	public void setAeronavesCargas(List<AeronaveCarga> aeronavesCarga) {
		this.aeronavesCarga = aeronavesCarga;
	}
}
