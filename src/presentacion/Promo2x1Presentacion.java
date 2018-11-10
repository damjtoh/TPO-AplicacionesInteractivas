package presentacion;

import java.sql.Date;
import java.time.LocalDate;

import SistemaFacturacion.ETipoDescuento;

public class Promo2x1Presentacion extends DescuentoPresentacion{

	public Promo2x1Presentacion(int id, Date fechaInicio, Date fechaFin, String nombre) {
		super(id, fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.PROMO_2x1;
	}
	
	public Promo2x1Presentacion(Date fechaInicio, Date fechaFin, String nombre) {
		super(fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.PROMO_2x1;
	}
	
}
