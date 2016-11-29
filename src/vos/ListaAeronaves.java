package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaAeronaves {
	@JsonProperty(value="aeronaves")
	private List<Aeronave> aernaves;
	
	
	public ListaAeronaves( @JsonProperty(value="aeronaves")List<Aeronave> aeronaves){
		this.aernaves = aeronaves;
	}

	
	public List<Aeronave> getAeronaves() {
		return aernaves;
	}

	public void setAeronaves(List<Aeronave> aeronaves) {
		this.aernaves = aeronaves;
	}
}
