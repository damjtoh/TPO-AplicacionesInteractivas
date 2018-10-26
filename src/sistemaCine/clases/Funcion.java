package sistemaCine.clases;

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
	private int salaId;
	

	

	public Funcion(Pelicula pelicula, Sala sala, Date fechaYHora) {
		super();
		this.pelicula = pelicula;
		this.sala = sala;
		this.fechaYHora = fechaYHora;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public void setMapaDeAsientos(Map<FilaColumna, AsinentoVirtual> mapaDeAsientos) {
		this.mapaDeAsientos = mapaDeAsientos;
	}

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
		setMapaDeAsientosIni(sala.getMapaDeAsientos());
	}

	private void setMapaDeAsientosIni(Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisico) {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pelicula.toString() + "-" + fechaYHora.toString();
	}
}
