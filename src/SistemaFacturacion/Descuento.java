package SistemaFacturacion;

import java.time.LocalDate;

import SistemaVentas.Venta;
import Usuarios.AgenteComercial;
import Usuarios.Usuario;
import presentacion.DescuentoPresentacion;

public abstract class Descuento {

	protected int id;
	protected LocalDate fechaInicio;
	protected LocalDate fechaFin;
	protected String nombre;
	protected ETipoDescuento tipo;
	private int establecimientoCuit;
	private int activo;
	private int estaCombo;
	public Descuento(int id, Usuario creadoPor, LocalDate fechaInicio, LocalDate fechaFin, String nombre, int establecimientoCuit, int activo, int estaCombo) {
		this.id = id;
		this.creadoPor = creadoPor;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.establecimientoCuit = establecimientoCuit;
		this.activo = activo;
		this.estaCombo = estaCombo;
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
	
	public void ModificarFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio =  fechaInicio;
	}
	
	public void ModificarFechaFin(LocalDate fechaFin) {
		this.fechaFin =  fechaFin;
	}
	
	public int GetId() {
		return id;
	}
	
	public boolean TieneId(int id) {
		return this.id == id;
	}
	
	public LocalDate GetFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate GetFechaFin() {
		return fechaFin;
	}
	
	public Usuario GetAgenteComercial() {
		return creadoPor;
	}
	
	public void SetFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void SetFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
