package SistemaFacturacion;

import java.sql.Date;
import java.time.LocalDate;

import SistemaVentas.Venta;
import Usuarios.AgenteComercial;
import Usuarios.Usuario;
import presentacion.DescuentoPresentacion;
import presentacion.xPorcentajePrecioVentaPresentacion;

public class xPorcentajePrecioVenta extends Descuento {

	private float porcentaje;

	public xPorcentajePrecioVenta(Integer id, Usuario creadoPor, Date fechaInicio, Date fechaFin,
			Float porcentaje, String nombre, int establecimientoCuit, int activo, int esCombo) {
		super(id, creadoPor, fechaInicio, fechaFin, nombre, establecimientoCuit, activo, esCombo);
		this.porcentaje = porcentaje;
		this.tipo = ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void EfectuarDescuento(Venta venta) {
		venta.setImporte(venta.getImporte() * (1 - (porcentaje / 100)));
	}

	public DescuentoPresentacion ToPresentacion() {
		return new xPorcentajePrecioVentaPresentacion(id, fechaInicio, fechaFin, porcentaje, nombre);
	}

}
