package SistemaFacturacion;

import java.time.LocalDate;

import Usuarios.AgenteComercial;
import presentacion.DescuentoPresentacion;

public abstract class Descuento {

	private static int contador = 1;
	protected int id;
	protected AgenteComercial creadoPor;
	protected LocalDate fechaInicio;
	protected LocalDate fechaFin;
	protected ETipoDescuento tipo;
	protected String nombre;
	
	public Descuento(AgenteComercial creadoPor, LocalDate fechaInicio, LocalDate fechaFin, String nombre) {
		this.creadoPor = creadoPor;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.id = contador;
		contador++;
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
	
	public AgenteComercial GetAgenteComercial() {
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
