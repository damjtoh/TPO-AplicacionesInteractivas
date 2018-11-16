package presentacion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import SistemaFacturacion.ETipoDescuento;

public class ComboPresentacion extends DescuentoPresentacion{
	
	private Collection<DescuentoPresentacion> descuentos;

	public ComboPresentacion(int id, Date fechaInicio, Date fechaFin, Collection<DescuentoPresentacion> descuentos, String nombre, String establecimientoCuit, int activo) {
		super(id, fechaInicio, fechaFin,nombre, establecimientoCuit, activo);
		this.tipo = ETipoDescuento.COMBO;
		this.descuentos = descuentos;
	}
	
	public ComboPresentacion(Date fechaInicio, Date fechaFin, Collection<DescuentoPresentacion> descuentos, String nombre) {
		super(fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.COMBO;
		this.descuentos = descuentos;
	}
	
	public Collection<DescuentoPresentacion> GetDescuentos(){
		return descuentos;
	}

}
