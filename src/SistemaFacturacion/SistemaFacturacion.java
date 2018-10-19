package SistemaFacturacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import Usuarios.AgenteComercial;
import Usuarios.SistemaUsuarios;
import presentacion.DescuentoPresentacion;
import presentacion.Promo2x1Presentacion;
import presentacion.xPorcentajePrecioVentaPresentacion;

public class SistemaFacturacion {
	
	private static SistemaFacturacion instancia = null;
	private Collection<Venta> ventas;
	private Collection<Descuento> descuentos;
	
	public static SistemaFacturacion GetInstancia() {
		if(instancia==null)
			instancia = new SistemaFacturacion();
		return instancia;
	}
	
	private SistemaFacturacion() {
		ventas = new ArrayList<Venta>();         //Traer ventas de la base de datos
		descuentos = new ArrayList<Descuento>(); //Traer descuentos de la base de datos
		
		
		/* CARGA DE DATOS MANUAL PARA TESTING SIN BASE DE DATOS
		
		AltaDescuentoPorcentaje(LocalDate.now(),LocalDate.now(),20,"Desc Porc 1");
		AltaDescuentoPorcentaje(LocalDate.now(),LocalDate.now(),10,"Desc Porc 2");
		
		Collection<DescuentoPresentacion> descuentosPresentacion = new ArrayList<DescuentoPresentacion>();
		DescuentoPresentacion descuentoPresentacion = new Promo2x1Presentacion(1,LocalDate.now(),LocalDate.now(),"Desc 2x1 1");
		descuentosPresentacion.add(descuentoPresentacion);
		descuentoPresentacion = new Promo2x1Presentacion(2,LocalDate.now(),LocalDate.now(),"Desc 2x1 2");
		descuentosPresentacion.add(descuentoPresentacion);
		descuentoPresentacion = new xPorcentajePrecioVentaPresentacion(2,LocalDate.now(),LocalDate.now(),32,"Desc Porc 3");
		descuentosPresentacion.add(descuentoPresentacion);
		
		AltaDescuentoCombo(LocalDate.now(),LocalDate.now(),descuentosPresentacion,"Desc Combo 1");
		AltaDescuentoCombo(LocalDate.now(),LocalDate.now(),descuentosPresentacion,"Desc Combo 2");
		AltaDescuentoCombo(LocalDate.now(),LocalDate.now(),descuentosPresentacion,"Desc Combo 3");
		
		AltaDescuento2x1(LocalDate.now(),LocalDate.now(),"Desc 2x1 3");
		
		AltaDescuentoPorcentaje(LocalDate.now(),LocalDate.now(),3,"Desc Porc 4");
		
		*/
	}
	
	public Venta BuscarVenta(int idVenta) {
		for(Venta venta : ventas) {
			if(venta.TieneId(idVenta))
				return venta;
		}
		return null;
	}
	
	public void EfectuarDescuento(int idVenta, int idDescuento) {
		Venta venta = BuscarVenta(idVenta);
		
		Descuento descuento = BuscarDescuento(idDescuento);
		descuento.EfectuarDescuento(venta);
	}
	
	public Collection<DescuentoPresentacion> GetDescuentosPresentacion(){
		Collection<DescuentoPresentacion> descuentosPresentacion = new ArrayList<DescuentoPresentacion>();
		
		for(Descuento descuento : descuentos)
			descuentosPresentacion.add(descuento.ToPresentacion());
		
		return descuentosPresentacion;
	}
	
	public void AltaDescuentoPorcentaje(LocalDate fechaInicio, LocalDate fechaFin, float porcentaje, String nombre) {
		if(SistemaUsuarios.GetInstancia().UsuarioLogueadoEsAgenteComercial()) {
			AgenteComercial agente = SistemaUsuarios.GetInstancia().GetAgenteComercial();
			Descuento descuento = new xPorcentajePrecioVenta(agente,fechaInicio,fechaFin,porcentaje,nombre);
			descuentos.add(descuento);
			//Guardar en la base de datos
		}
	}
	
	public void AltaDescuento2x1(LocalDate fechaInicio, LocalDate fechaFin, String nombre) {
		if(SistemaUsuarios.GetInstancia().UsuarioLogueadoEsAgenteComercial()) {
			AgenteComercial agente = SistemaUsuarios.GetInstancia().GetAgenteComercial();
			Descuento descuento = new Promo2x1(agente,fechaInicio,fechaFin,nombre);
			descuentos.add(descuento);
			//Guardar en la base de datos
		}
	}
	
	public void AltaDescuentoCombo(LocalDate fechaInicio, LocalDate fechaFin, Collection<DescuentoPresentacion> descuentosPresentacion, String nombre) {
		if(SistemaUsuarios.GetInstancia().UsuarioLogueadoEsAgenteComercial()) {
			AgenteComercial agente = SistemaUsuarios.GetInstancia().GetAgenteComercial();
			Descuento combo = new Combo(agente,fechaInicio,fechaFin,descuentosPresentacion,nombre);
			descuentos.add(combo);
			//Guardar en la base de datos
		}
	}
	
	public Descuento BuscarDescuento(int id) {
		for(Descuento descuento : descuentos) {
			if(descuento.TieneId(id))
				return descuento;
		}
		return null;
	}
	
	
	public void BajaDescuento(int idDescuento) {
		if(SistemaUsuarios.GetInstancia().UsuarioLogueadoEsAgenteComercial()) {
			Descuento descuento = BuscarDescuento(idDescuento);
			descuentos.remove(descuento);
			//Borrar de la base de datos
		}
	}

	public void ModificarDescuento(int idDescuento, LocalDate fechaInicio, LocalDate fechaFin) {
		if(SistemaUsuarios.GetInstancia().UsuarioLogueadoEsAgenteComercial()) {
			Descuento descuento = BuscarDescuento(idDescuento);
			descuento.SetFechaInicio(fechaInicio);
			descuento.SetFechaFin(fechaFin);	
			//Modificar en la base de datos
		}
	}

}
