package SistemaVentas;

public class TarjetaDebitoXPortal extends Portal {

	private int fechaVencimientoDia;
	private int fechaVencimientoMes;
	
	
	
	public TarjetaDebitoXPortal(int fechaVencimientoDia, int fechaVencimientoMes) {
		super();
		this.fechaVencimientoDia = fechaVencimientoDia;
		this.fechaVencimientoMes = fechaVencimientoMes;
	}
	
	@Override
	public String toString() {
		return "Tarjeta de Debito";
	}
	public TarjetaDebitoXPortal() {
		// TODO Auto-generated constructor stub
	}


	public int getFechaVencimientoDia() {
		return fechaVencimientoDia;
	}
	public void setFechaVencimientoDia(int fechaVencimientoDia) {
		this.fechaVencimientoDia = fechaVencimientoDia;
	}
	public int getFechaVencimientoMes() {
		return fechaVencimientoMes;
	}
	public void setFechaVencimientoMes(int fechaVencimientoMes) {
		this.fechaVencimientoMes = fechaVencimientoMes;
	}

	@Override
	public int getNro() {
		return 0;
	}
	
	
}
