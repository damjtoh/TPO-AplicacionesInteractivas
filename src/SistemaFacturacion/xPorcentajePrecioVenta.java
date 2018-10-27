package SistemaFacturacion;

import java.time.LocalDate;

import SistemaVentas.Venta;
import Usuarios.AgenteComercial;
import presentacion.DescuentoPresentacion;
import presentacion.xPorcentajePrecioVentaPresentacion;

public class xPorcentajePrecioVenta extends Descuento{
	
	private float porcentaje;

	public xPorcentajePrecioVenta(AgenteComercial creadoPor, LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String nombre) {
		super(creadoPor, fechaInicio, fechaFin,nombre);
		this.porcentaje = porcentaje;
		this.tipo = ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA;
	}

	public void EfectuarDescuento(Venta venta) {
		venta.setImporte(venta.getImporte()*(1-(porcentaje/100)));
	}

	public DescuentoPresentacion ToPresentacion() {
		return new xPorcentajePrecioVentaPresentacion(id,fechaInicio,fechaFin,porcentaje,nombre);
	}

}
