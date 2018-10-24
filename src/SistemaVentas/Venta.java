package SistemaVentas;

import java.sql.Array;
import java.time.LocalDate;
import java.util.List;

import sistemaCine.clases.Entrada;

import sistemaCine.cinesClases.Entrada;

public class Venta {
	private int id;
	private List<Entrada> entradas;
	private LocalDate fechaYHora;
	private ITipoDePago tipoDePago;
	private long numeroDeTarjeta;
	private double importe;
	private List<ObserverVenta> observers;

	
	
	public Venta(List<Entrada> entradas, LocalDate fechaYHora, ITipoDePago tipoDePago, long numeroDeTarjeta,
			double importe) {
		super();
		this.entradas = entradas;
		this.fechaYHora = fechaYHora;
		this.tipoDePago = tipoDePago;
		this.numeroDeTarjeta = numeroDeTarjeta;
		this.importe = importe;
	}

	public Venta(List<Entrada> entradas, LocalDate fechaYHora, ITipoDePago tipoDePago, long numeroDeTarjeta,
			double importe, List<ObserverVenta> observers,int id) {
		super();
		this.entradas = entradas;
		this.fechaYHora = fechaYHora;
		this.tipoDePago = tipoDePago;
		this.numeroDeTarjeta = numeroDeTarjeta;
		this.importe = importe;
		this.observers = observers;
		this.id = id;
	}

	public void addObserver(ObserverVenta observer) {
		observers.add(observer);
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public LocalDate getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(LocalDate fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public ITipoDePago getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(ITipoDePago tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public long getNumeroDeTarjeta() {
		return numeroDeTarjeta;
	}

	public void setNumeroDeTarjeta(long numeroDeTarjeta) {
		this.numeroDeTarjeta = numeroDeTarjeta;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public List<ObserverVenta> getObservers() {
		return observers;
	}

	public void setObservers(List<ObserverVenta> observers) {
		this.observers = observers;
	}

	public void removeObserver(ObserverVenta observer) {
		observers.remove(observer);
	}

	public void updateVentas() {
		for (ObserverVenta observer : observers) {
			observer.NotifyTerminal();
		}
	}
}
