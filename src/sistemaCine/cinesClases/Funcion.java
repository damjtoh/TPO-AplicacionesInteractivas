package sistemaCine.cinesClases;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import sistemaCine.utils.FilaColumna;

public class Funcion {
	private Date fechaYHora;
	private Pelicula pelicula;
	private Sala sala;
	private Map<FilaColumna, AsinentoVirtual> mapaDeAsientos;

	public Funcion(Date date, Pelicula pelicula, Sala sala) {
		super();
		this.fechaYHora = date;
		this.pelicula = pelicula;
		this.sala = sala;
		setMapaDeAsientos(sala.getMapaDeAsientos());
	}

	private void setMapaDeAsientos(Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisico) {
		for (Entry<FilaColumna, AsinentoFisico> entry : mapaDeAsientosFisico.entrySet()) {
			this.mapaDeAsientos.put(entry.getKey(),
					new AsinentoVirtual(entry.getValue().getFila(), entry.getValue().getColumna()));
		}
	}

	public Date getFechaYHora() {
		return fechaYHora;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public Sala getSala() {
		return sala;
	}

	public Map<FilaColumna, AsinentoVirtual> getMapaDeAsientos() {
		return mapaDeAsientos;
	}
}
