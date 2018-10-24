package SistemaVentas;

import java.time.LocalDate;
import java.util.List;

import sistemaCine.clases.Entrada;


public class ConfirmarVenta {
	public Venta CrearVenta(List<Entrada> entradas, LocalDate fechaYHora, ITipoDePago tipoDePago, long numeroTarjetaClub, double importe) {
		return new Venta(entradas,fechaYHora, tipoDePago,numeroTarjetaClub, importe);
	}
}
