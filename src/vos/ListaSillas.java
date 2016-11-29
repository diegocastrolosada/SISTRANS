package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaSillas {
	@JsonProperty(value="sillas")
	private List<Silla> sillas;
	
	public ListaSillas(@JsonProperty(value="sillas")List<Silla> sillas) {
		this.sillas = sillas;
	}

	public List<Silla> getSillas() {
		return sillas;
	}

	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}
}
