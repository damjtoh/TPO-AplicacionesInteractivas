package SistemaFacturacion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import SistemaVentas.Venta;
import Usuarios.AgenteComercial;
import Usuarios.Usuario;
import presentacion.DescuentoPresentacion;

public abstract class Descuento {

	protected int id;
	protected Date fechaInicio;
	protected Date fechaFin;
	protected String nombre;
	protected ETipoDescuento tipo;
	private int establecimientoCuit;
	private int activo;
	private int estaCombo;
	public Descuento(int id, Usuario creadoPor, Date fechaInicio, Date fechaFin, String nombre, int establecimientoCuit, int activo, int estaCombo) {
		this.id = id;
		this.creadoPor = creadoPor;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.establecimientoCuit = establecimientoCuit;
		this.activo = activo;
		this.estaCombo = estaCombo;
	}
	
	public Descuento(Date fechaInicio, Date fechaFin, String nombre, ETipoDescuento tipo, int establecimientoCuit,
			int activo, int estaCombo, Usuario creadoPor) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.tipo = tipo;
		this.establecimientoCuit = establecimientoCuit;
		this.activo = activo;
		this.estaCombo = estaCombo;
		this.creadoPor = creadoPor;
	}

	public Descuento(Usuario creadoPor2, Date fechaInicioLocalDate, Date fechaFinLocalDate, String nombre2,
			int establecimientoCuit2, int activo2, int estaCombo2) {
		// TODO Auto-generated constructor stub
		this.creadoPor=creadoPor2;
		this.fechaInicio = fechaInicioLocalDate;
		this.fechaFin = fechaFinLocalDate;
		this.nombre = nombre2;
		this.establecimientoCuit = establecimientoCuit2;
		this.activo=activo2;
		this.estaCombo = estaCombo2;
	}

	public Descuento(Usuario usuarioLogueado, Date fechaInicio2, Date fechaFin2,
			Collection<DescuentoPresentacion> descuentosPresentacion, String nombre2) {
		// TODO Auto-generated constructor stub
		this.creadoPor = usuarioLogueado;
		this.fechaInicio = fechaInicio2;
		this.fechaFin = fechaFin2;
		this.nombre = nombre2;
	}
	
	

	public Descuento() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Usuario creadoPor) {
		this.creadoPor = creadoPor;
	}

	protected Usuario creadoPor;

	
	public int getEstaCombo() {
		return estaCombo;
	}

	public void setEstaCombo(int esCombo) {
		this.estaCombo = esCombo;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getEstablecimientoCuit() {
		return establecimientoCuit;
	}

	public void setEstablecimientoCuit(int establecimientoCuit) {
		this.establecimientoCuit = establecimientoCuit;
	}

	public abstract DescuentoPresentacion ToPresentacion();
	
	public abstract void EfectuarDescuento(Venta venta);
	
	public ETipoDescuento GetTipo() {
		return tipo;
	}
	
	public void ModificarFechaInicio(Date fechaInicio) {
		this.fechaInicio =  fechaInicio;
	}
	
	public void ModificarFechaFin(Date fechaFin) {
		this.fechaFin =  fechaFin;
	}
	
	public int GetId() {
		return id;
	}
	
	public boolean TieneId(int id) {
		return this.id == id;
	}
	
	public Date GetFechaInicio() {
		return fechaInicio;
	}
	
	public Date GetFechaFin() {
		return fechaFin;
	}
	
	public Usuario GetAgenteComercial() {
		return creadoPor;
	}
	
	public void SetFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void SetFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
