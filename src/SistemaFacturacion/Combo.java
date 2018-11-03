package SistemaFacturacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import SistemaVentas.Venta;
import Usuarios.AgenteComercial;
import Usuarios.Usuario;
import presentacion.ComboPresentacion;
import presentacion.DescuentoPresentacion;
import presentacion.xPorcentajePrecioVentaPresentacion;

public class Combo extends Descuento{
	
	private Collection<Descuento> descuentos;
	public Combo(int id, Usuario creadoPor, LocalDate fechaInicio, LocalDate fechaFin, String nombre,
			int establecimientoCuit, int activo, int estaCombo, Collection<Descuento> descuentos) {
		super(id, creadoPor, fechaInicio, fechaFin, nombre, establecimientoCuit, activo, estaCombo);
		this.descuentos = descuentos;
		this.tipo = ETipoDescuento.COMBO;
	}

	public Collection<Descuento> getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(Collection<Descuento> descuentos) {
		this.descuentos = descuentos;
	}

	public void AgregarDescuento(Descuento descuento) {
		descuentos.add(descuento);
	}
	
	public void QuitarDescuento(int id) {
		for(Descuento descuento : descuentos) {
			if(descuento.TieneId(id)) {
				descuentos.remove(descuento);
				break;
			}
		}
	}
	
	@Override
	public void EfectuarDescuento(Venta venta) {
		for(Descuento descuento : descuentos)
			descuento.EfectuarDescuento(venta);
	}

	@Override
	public DescuentoPresentacion ToPresentacion() {
		Collection<DescuentoPresentacion> descuentosPresentacion = new ArrayList<DescuentoPresentacion>();
		
		for(Descuento descuento : descuentos)
			descuentosPresentacion.add(descuento.ToPresentacion());
		
		return new ComboPresentacion(id,fechaInicio,fechaFin,descuentosPresentacion,nombre);
	}
	
}
