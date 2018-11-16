package sistemaCine.clases;

import java.util.HashMap;
import java.util.Map;

public class Establecimiento {
	private int cuit;
	private String nombre;
	private String domicilio;
	private Map<String, Sala> salas;
	private Map<Integer, Pelicula> peliculas;
	private int capacidadTotal;

	public String getNombre() {
		return nombre;
	}

	public Establecimiento(int cuit, String nombre, String domicilio, int capacidadTotal) {
		super();
		this.cuit = cuit;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.salas = new HashMap<>();
		this.setCapacidadTotal(capacidadTotal);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCuit() {
		return cuit;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public Map<String, Sala> getSalas() {
		return salas;
	}

	public void addSala(Sala sala) {
		if (!salas.containsKey(sala.getNombre())) {
			salas.put(sala.getNombre(), sala);
		}
	}

	public boolean removeSala(String nombre) {
		return null == salas.remove(nombre);
	}

	public Map<Integer, Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Map<Integer, Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public void addPelicula(Pelicula pelicula) {
		peliculas.put(pelicula.getId(), pelicula);
	}

	public int getCapacidadTotal() {
		return capacidadTotal;
	}

	public void setCapacidadTotal(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}
}
