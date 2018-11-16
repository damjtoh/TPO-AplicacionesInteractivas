package SistemaFacturacion;

import java.sql.Date;
import java.time.LocalDate;

import SistemaVentas.Venta;
import Usuarios.AgenteComercial;
import Usuarios.Usuario;
import presentacion.DescuentoPresentacion;
import presentacion.Promo2x1Presentacion;

public class Promo2x1 extends Descuento {

	public Promo2x1(int id, Usuario creadoPor, Date fechaInicio, Date fechaFin, String nombre,
			int establecimientoCuit, int activo, int esCombo) {
		super(id, creadoPor, fechaInicio, fechaFin, nombre, establecimientoCuit, activo, esCombo);
		this.tipo = ETipoDescuento.PROMO_2x1;
	}

	public Promo2x1() {
		super();
	}
	
	@Override
	public void EfectuarDescuento(Venta venta) {
		venta.setImporte(venta.getImporte() / 2);
	}

	@Override
	public DescuentoPresentacion ToPresentacion() {
		return new Promo2x1Presentacion(id, fechaInicio, fechaFin, nombre, Integer.toString(establecimientoCuit), activo);
	}

}
