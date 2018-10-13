package sistemacine.cinesClases;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Establecimiento {
	private int cuit;
	private String nombre;
	private String domicilio;
	private Map<String,Sala> salas;
	
	public String getNombre() {
		return nombre;
	}
	public Establecimiento(int cuit, String nombre, String domicilio) {
		super();
		this.cuit = cuit;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.salas = new HashMap<>();
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
	public Map<String,Sala> getSalas() {
		return salas;
	}
	public void addSala(Sala sala) {
		if (!salas.containsKey(sala.getNombre())) {
			salas.put(sala.getNombre(), sala);
		}
	}
	public boolean removeSala(String nombre) {
		return null==salas.remove(nombre);
	}
}
