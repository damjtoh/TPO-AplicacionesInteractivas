package sistemaCine.clases;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import sistemaCine.utils.DateUtils;
import sistemaCine.utils.FilaColumna;

public class Funcion {
	private int id;
	private Pelicula pelicula;
	private Sala sala;
	private Date fechaYHora;
	private Map<FilaColumna, AsientoVirtual> mapaDeAsientos;
	private double valor;

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

	public void setMapaDeAsientos(Map<FilaColumna, AsientoVirtual> mapaDeAsientos) {
		this.mapaDeAsientos = mapaDeAsientos;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Funcion(Date date, Pelicula pelicula, Sala sala, double valor) {
		super();
		this.fechaYHora = date;
		this.pelicula = pelicula;
		this.sala = sala;
		this.valor = valor;
		this.mapaDeAsientos = new HashMap<>();
		setMapaDeAsientosIni(sala.getMapaDeAsientos());
	}

	private void setMapaDeAsientosIni(Map<FilaColumna, AsientoFisico> mapaDeAsientosFisico) {
		for (Entry<FilaColumna, AsientoFisico> entry : mapaDeAsientosFisico.entrySet()) {
			this.mapaDeAsientos.put(entry.getKey(),
					new AsientoVirtual(entry.getValue().getFila(), entry.getValue().getColumna()));
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

	public Map<FilaColumna, AsientoVirtual> getMapaDeAsientos() {
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
//		return pelicula.toString() + "-" + fechaYHora.toString() + "-"
//				+ ((DateUtils.get(fechaYHora, Calendar.HOUR_OF_DAY) < 10)
//						? "0" + DateUtils.get(fechaYHora, Calendar.HOUR_OF_DAY)
//						: DateUtils.get(fechaYHora, Calendar.HOUR_OF_DAY))
//				+ ":"
//				+ ((DateUtils.get(fechaYHora, Calendar.MINUTE) < 10) ? "0" + DateUtils.get(fechaYHora, Calendar.MINUTE)
//						: DateUtils.get(fechaYHora, Calendar.MINUTE));
		return pelicula.toString() + "-" + DateUtils.getDateSinHoraString(fechaYHora) + "-"
				+ DateUtils.getHoraString(fechaYHora);
	}

	public void generateMapaAsientos() {
		setMapaDeAsientosIni(this.sala.getMapaDeAsientos());
	}
}
