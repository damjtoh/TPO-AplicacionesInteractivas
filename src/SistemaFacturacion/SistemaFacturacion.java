package SistemaFacturacion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import SistemaVentas.Venta;
import SistemaVentas.VentasDAO;
import Usuarios.Rol;
import Usuarios.SistemaUsuarios;
import Usuarios.Usuario;
import presentacion.DescuentoPresentacion;

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
		ventas = new ArrayList<Venta>();         //Traer ventas de la base de datos TODO
		descuentos = new ArrayList<Descuento>(); //Traer descuentos de la base de datos
		descuentos = FacturacionMapper.listDescuentos();
		
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
		
		return VentasDAO.getById(idVenta);
	}
	
//	public void EfectuarDescuento(int idVenta, int idDescuento) {
//		Venta venta = BuscarVenta(idVenta);
//		
//		Descuento descuento = BuscarDescuento(idDescuento);
//		descuento.EfectuarDescuento(venta);
//	}
	
	public Collection<DescuentoPresentacion> GetDescuentosPresentacion(){
		Collection<DescuentoPresentacion> descuentosPresentacion = new ArrayList<DescuentoPresentacion>();
		
		for(Descuento descuento : descuentos) {
			if (descuento != null) {				
				descuentosPresentacion.add(descuento.ToPresentacion());
			}
		}
		
		return descuentosPresentacion;
	}
	
	public void AltaDescuentoPorcentaje(Date fechaInicio, Date fechaFin, float porcentaje, String nombre) {
		Usuario usuarioLogueado = SistemaUsuarios.getInstancia().getUsuarioLogueado();
		if(usuarioLogueado.tieneRol(Rol.AGENTE_COMERCIAL_ID)) {
			Descuento descuento = new xPorcentajePrecioVenta(null, usuarioLogueado,fechaInicio,fechaFin,porcentaje,nombre, 0, 0, 0);
			descuentos.add(descuento);
			//Guardar en la base de datos
			DescuentoDAO.Save(descuento);
		}
	}
	
	public void AltaDescuento2x1(Date fechaInicio, Date fechaFin, String nombre, Integer establecimientoCuit) {
		Usuario usuarioLogueado = SistemaUsuarios.getInstancia().getUsuarioLogueado();
		if(usuarioLogueado.tieneRol(Rol.AGENTE_COMERCIAL_ID)) {
			Descuento descuento = new Promo2x1(0, usuarioLogueado,fechaInicio,fechaFin,nombre, establecimientoCuit, 1, 0);
			descuentos.add(descuento);
			//Guardar en la base de datos
			DescuentoDAO.Save(descuento);
		}
	}
	
	public void AltaDescuentoCombo(Date fechaInicio, Date fechaFin, Collection<DescuentoPresentacion> descuentosPresentacion, String nombre) {
		Usuario usuarioLogueado = SistemaUsuarios.getInstancia().getUsuarioLogueado();
		if(usuarioLogueado.tieneRol(Rol.AGENTE_COMERCIAL_ID)) {
			Descuento combo = new Combo(usuarioLogueado,fechaInicio,fechaFin,descuentosPresentacion,nombre);
			descuentos.add(combo);
			//Guardar en la base de datos
			DescuentoDAO.Save(combo);
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
		Usuario usuarioLogueado = SistemaUsuarios.getInstancia().getUsuarioLogueado();
		if(usuarioLogueado.tieneRol(Rol.AGENTE_COMERCIAL_ID)) {
			Descuento descuento = BuscarDescuento(idDescuento);
			// check if null;
			descuentos.remove(descuento);
			DescuentoDAO.getInstancia().deleteDescuento(idDescuento);
		}
	}

	public void ModificarDescuento(int idDescuento, Date fechaInicio, Date fechaFin) {
		Usuario usuarioLogueado = SistemaUsuarios.getInstancia().getUsuarioLogueado();
		if(usuarioLogueado.tieneRol(Rol.AGENTE_COMERCIAL_ID)) {
			Descuento descuento = BuscarDescuento(idDescuento);
			descuento.SetFechaInicio(fechaInicio);
			descuento.SetFechaFin(fechaFin);	
			//Modificar en la base de datos
		}
	}

}
