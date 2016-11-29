package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaReservas {
	@JsonProperty(value="reservas")
	private List<Reserva> reservas;
	
	public ListaReservas(@JsonProperty(value="reservas")List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}
