package presentacion;

import java.sql.Date;
import java.time.LocalDate;

import SistemaFacturacion.ETipoDescuento;

public abstract class DescuentoPresentacion {
	
	private int id;
	private Date fechaInicio;
	private Date fechaFin;
	protected ETipoDescuento tipo;
	private String nombre;
	
	public DescuentoPresentacion(int id, Date fechaInicio2, Date fechaFin2, String nombre) {
		this.id = id;
		this.fechaInicio = fechaInicio2;
		this.fechaFin = fechaFin2;
		this.nombre = nombre;
	}
	
	public DescuentoPresentacion(Date fechaInicio, Date fechaFin, String nombre) {
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
