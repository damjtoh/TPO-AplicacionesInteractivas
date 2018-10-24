package SistemaFacturacion;

import java.time.LocalDate;

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

	@Override
	public void EfectuarDescuento(Venta venta) {
		venta.SetImporte(venta.GetImporte()*(1-(porcentaje/100)));
	}

	@Override
	public DescuentoPresentacion ToPresentacion() {
		return new xPorcentajePrecioVentaPresentacion(id,fechaInicio,fechaFin,porcentaje,nombre);
	}
}
