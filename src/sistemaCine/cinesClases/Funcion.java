package sistemaCine.cinesClases;

import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;

import sistemaCine.utils.FilaColumna;

public class Funcion {
	private int id;
	private Pelicula pelicula;
	private Sala sala;
	private Date fechaYHora;
	private Map<FilaColumna, AsinentoVirtual> mapaDeAsientos;
	private double valor;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Funcion(Date date, Pelicula pelicula, Sala sala,double valor) {
		super();
		this.fechaYHora = date;
		this.pelicula = pelicula;
		this.sala = sala;
		this.valor = valor;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
