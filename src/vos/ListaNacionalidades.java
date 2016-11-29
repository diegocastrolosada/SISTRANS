package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaNacionalidades {
	
	@JsonProperty(value="nacionalidades")
	private List<Nacionalidad> nacionalidades;
	
	public ListaNacionalidades(@JsonProperty(value="nacionalidades")List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}
}
