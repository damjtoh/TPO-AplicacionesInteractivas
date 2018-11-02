package SistemaVentas;

public class TarjetaCreditoXPortal extends Portal {
	private double interes;
	private int coutas;
	private int fechaVencimientoDia;
	private int FechaVencimientoMes;
	
	
	
	public TarjetaCreditoXPortal(double interes, int coutas, int fechaVencimientoDia, int fechaVencimientoMes) {
		super();
		this.interes = interes;
		this.coutas = coutas;
		this.fechaVencimientoDia = fechaVencimientoDia;
		FechaVencimientoMes = fechaVencimientoMes;
	}

	public TarjetaCreditoXPortal() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Tarjeta de Credito";
	}

	public int getFechaVencimientoDia() {
		return fechaVencimientoDia;
	}

	public void setFechaVencimientoDia(int fechaVencimientoDia) {
		this.fechaVencimientoDia = fechaVencimientoDia;
	}

	public int getFechaVencimientoMes() {
		return FechaVencimientoMes;
	}

	public void setFechaVencimientoMes(int fechaVencimientoMes) {
		FechaVencimientoMes = fechaVencimientoMes;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public int getCoutas() {
		return coutas;
	}

	public void setCoutas(int coutas) {
		this.coutas = coutas;
	}

	@Override
	public int getNro() {
		return 1;
	}

}
