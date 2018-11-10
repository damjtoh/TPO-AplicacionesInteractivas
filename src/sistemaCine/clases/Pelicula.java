package sistemaCine.clases;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
	private Integer id;
	private String nombre;
	private String director;
	private String genero;
	private int duracion;
	private String idioma;
	private Boolean subtitulos;
	private double clasificacion;
	private String observaciones;
	private List<Funcion> funciones;

	public Pelicula(String nombre, String director, String genero, int duracion, String idioma, Boolean subtitulos,
			double claificacion, String observaciones) {
		super();
		this.nombre = nombre;
		this.director = director;
		this.genero = genero;
		this.duracion = duracion;
		this.idioma = idioma;
		this.subtitulos = subtitulos;
		this.clasificacion = claificacion;
		this.observaciones = observaciones;
		this.funciones = new ArrayList<>();
	}

	
	
	public Pelicula(Integer id, String nombre, String director, String genero, int duracion, String idioma,
			Boolean subtitulos, double claificacion, String observaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.director = director;
		this.genero = genero;
		this.duracion = duracion;
		this.idioma = idioma;
		this.subtitulos = subtitulos;
		this.clasificacion = claificacion;
		this.observaciones = observaciones;
	}



	public double getClaificacion() {
		return clasificacion;
	}

	public void setClaificacion(double claificacion) {
		this.clasificacion = claificacion;
	}

	public List<Funcion> getFunciones() {
		return funciones;
	}

	public void addFuncion(Funcion funcion) {
		funciones.add(getPosicion(funcion), funcion);
	}

	private int getPosicion(Funcion funcion) {
		int inicio = 0;
		int fin = funciones.size() - 1;
		int pos;
		while (inicio <= fin) {
			pos = (inicio + fin) / 2;
			if (funciones.get(pos).getFechaYHora().equals(funcion.getFechaYHora()))
				return pos;
			else if (funciones.get(pos).getFechaYHora().before(funcion.getFechaYHora())) {
				inicio = pos + 1;
			} else {
				fin = pos - 1;
			}
		}
		if (inicio >= funciones.size()) {
			return funciones.size();
		} else {
			return 0;
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

	public Boolean isSubtitulos() {
		return subtitulos;
	}

	public void setSubtitulos(Boolean subtitilos) {
		this.subtitulos = subtitilos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return nombre + " - " + idioma + ((subtitulos)?" - subtitulada":"");
	}
}
