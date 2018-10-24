package SistemaFacturacion;

import java.time.LocalDate;

public abstract class VentaTarjeta extends Venta {
	private String tipo;
	private Long numero;
	private LocalDate fechaVencimiento;
	private int codigoDeSeguridad;
	
	public VentaTarjeta(String tipo, Long numero, LocalDate fechaVencimiento,
			int codigoDeSeguridad) {
		super(fechaVencimiento, numero);
		this.tipo = tipo;
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		this.codigoDeSeguridad = codigoDeSeguridad;
	}

}
