package SistemaVentas;

import java.time.LocalDate;
import java.util.List;

import sistemaCine.clases.Entrada;


public class ConfirmarVenta {
	private VentasDAO ventasDao;
	
	
	
	public ConfirmarVenta() {
		super();
		ventasDao = new VentasDAO();
	}



	public Venta CrearVenta(List<Entrada> entradas, LocalDate fechaYHora, ITipoDePago tipoDePago, long numeroTarjetaClub, double importe) {
		return ventasDao.insert(new Venta(entradas,fechaYHora, tipoDePago,numeroTarjetaClub, importe));
	}
}
