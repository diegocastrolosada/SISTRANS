package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaReservasPasajero {
	@JsonProperty(value="reservasPasajero")
	private List<ReservaPasajero> reservasPasajero;
	
	public ListaReservasPasajero(@JsonProperty(value="reservasPasajero")List<ReservaPasajero> reservasPasajero) {
		this.reservasPasajero = reservasPasajero;
	}

	public List<ReservaPasajero> getReservasPasajero() {
		return reservasPasajero;
	}

	public void setReservasPasajero(List<ReservaPasajero> reservasPasajero) {
		this.reservasPasajero = reservasPasajero;
	}
}
