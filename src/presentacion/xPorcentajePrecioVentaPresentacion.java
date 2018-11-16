package presentacion;

import java.sql.Date;
import java.time.LocalDate;

import SistemaFacturacion.ETipoDescuento;

public class xPorcentajePrecioVentaPresentacion extends DescuentoPresentacion{

	private float porcentaje;
	
	public xPorcentajePrecioVentaPresentacion(int id, Date fechaInicio, Date fechaFin, float porcentaje, String nombre, String establecimientoCuit, int activo) {
		super(id, fechaInicio, fechaFin,nombre, establecimientoCuit, activo);
		this.tipo = ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA;
		this.porcentaje = porcentaje;
	}
	
	public xPorcentajePrecioVentaPresentacion(Date fechaInicio, Date fechaFin, float porcentaje, String nombre) {
		super(fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA;
		this.porcentaje = porcentaje;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}
