package presentacion;

import java.time.LocalDate;

import SistemaFacturacion.ETipoDescuento;

public abstract class DescuentoPresentacion {
	
	private int id;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	protected ETipoDescuento tipo;
	private String nombre;
	
	public DescuentoPresentacion(int id, LocalDate fechaInicio, LocalDate fechaFin, String nombre) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
	}
	
	public DescuentoPresentacion(LocalDate fechaInicio, LocalDate fechaFin, String nombre) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
	}
	
	public ETipoDescuento GetTipoDescuento() {
		return tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
