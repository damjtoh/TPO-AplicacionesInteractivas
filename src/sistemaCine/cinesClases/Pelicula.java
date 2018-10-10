package cinesClases;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
	private String nombre;
	private String director;
	private String genero;
	private int duracion;
	private String idioma;
	private boolean subtitilos;
	private double claificacion;
	private String observaciones;
	private List<Funcion> funciones;

	public Pelicula(String nombre, String director, String genero, int duracion, String idioma, boolean subtitilos,
			double claificacion, String observaciones) {
		super();
		this.nombre = nombre;
		this.director = director;
		this.genero = genero;
		this.duracion = duracion;
		this.idioma = idioma;
		this.subtitilos = subtitilos;
		this.claificacion = claificacion;
		this.observaciones = observaciones;
		this.funciones = new ArrayList<>();
	}

	public double getClaificacion() {
		return claificacion;
	}

	public void setClaificacion(double claificacion) {
		this.claificacion = claificacion;
	}

	public List<Funcion> getFunciones() {
		return funciones;
	}

	public void addFuncion(Funcion funcion) {
		funciones.add(getPosicion(funcion, funciones.size() / 2, funciones.size()), funcion);
	}

	private int getPosicion(Funcion funcion, int pivot, int size) {
		if (funciones.get(pivot) == null || size < 2) {
			return pivot;
		} else {
			if (funciones.get(pivot).getFechaYHora().after(funcion.getFechaYHora())) {
				return getPosicion(funcion, pivot + (pivot - size) / 2, size / 2);
			} else {
				return getPosicion(funcion, pivot - (pivot - size) / 2, size / 2);
			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirector() {
		return director;
	}

	public String getGenero() {
		return genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getIdioma() {
		return idioma;
	}

	public boolean isSubtitilos() {
		return subtitilos;
	}

	public String getObservaciones() {
		return observaciones;
	}
}
