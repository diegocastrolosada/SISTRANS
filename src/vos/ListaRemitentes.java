package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaRemitentes {
	@JsonProperty(value="remitentes")
	private List<Remitente> remitentes;
	
	public ListaRemitentes(@JsonProperty(value="remitentes")List<Remitente> remitentes) {
		this.remitentes = remitentes;
	}

	public List<Remitente> getRemitentes() {
		return remitentes;
	}

	public void setRemitentes(List<Remitente> remitentes) {
		this.remitentes = remitentes;
	}
}
