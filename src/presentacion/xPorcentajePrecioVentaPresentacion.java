package presentacion;

import java.time.LocalDate;

import SistemaFacturacion.ETipoDescuento;

public class xPorcentajePrecioVentaPresentacion extends DescuentoPresentacion{

	private float porcentaje;
	
	public xPorcentajePrecioVentaPresentacion(int id, LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String nombre) {
		super(id, fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA;
		this.porcentaje = porcentaje;
	}
	
	public xPorcentajePrecioVentaPresentacion(LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String nombre) {
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
