package SistemaFacturacion;

import java.time.LocalDate;

public abstract class VentaPortal extends VentaTarjeta {

	public VentaPortal(String tipo, Long numero, LocalDate fechaVencimiento,
			int codigoDeSeguridad) {
		super(tipo, numero, fechaVencimiento, codigoDeSeguridad);
		// TODO Auto-generated constructor stub
	}

}
