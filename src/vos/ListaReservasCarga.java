package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaReservasCarga {
	@JsonProperty(value="reservasCarga")
	private List<ReservaCarga> reservasCarga;
	
	public ListaReservasCarga(@JsonProperty(value="reservasCarga")List<ReservaCarga> reservasCarga) {
		this.reservasCarga = reservasCarga;
	}

	public List<ReservaCarga> getReservasCarga() {
		return reservasCarga;
	}

	public void setReservasCarga(List<ReservaCarga> reservasCarga) {
		this.reservasCarga = reservasCarga;
	}
}
