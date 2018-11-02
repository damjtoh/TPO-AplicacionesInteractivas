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

	public Combo(Usuario creadoPor, LocalDate fechaInicio, LocalDate fechaFin, String nombre) {
		super(creadoPor, fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.COMBO;
		descuentos = new ArrayList<Descuento>();
	}
	
	public Combo(Usuario creadoPor, LocalDate fechaInicio, LocalDate fechaFin, ArrayList<Descuento> descuentos, String nombre) {
		super(creadoPor, fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.COMBO;
		this.descuentos = descuentos;
	}
	
	public Combo(Usuario creadoPor, LocalDate fechaInicio, LocalDate fechaFin, Collection<DescuentoPresentacion> descuentosPresentacion, String nombre) {
		super(creadoPor, fechaInicio, fechaFin,nombre);
		this.tipo = ETipoDescuento.COMBO;
		descuentos = new ArrayList<Descuento>();
		
		for(DescuentoPresentacion descuentoPresentacion : descuentosPresentacion) {
			Descuento descuento = null;
			if(descuentoPresentacion.GetTipoDescuento() == ETipoDescuento.PROMO_2x1) {
				descuento = new Promo2x1(creadoPor,descuentoPresentacion.getFechaInicio(),descuentoPresentacion.getFechaFin(),nombre);
			} else if(descuentoPresentacion.GetTipoDescuento() == ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA) {
				xPorcentajePrecioVentaPresentacion descuentoPorcentaje = (xPorcentajePrecioVentaPresentacion) descuentoPresentacion;
				descuento = new xPorcentajePrecioVenta(creadoPor,descuentoPorcentaje.getFechaInicio(),descuentoPorcentaje.getFechaFin(),descuentoPorcentaje.getPorcentaje(),nombre);
			}
			
			if(descuento!=null)
				descuentos.add(descuento);
			
		}
		
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
