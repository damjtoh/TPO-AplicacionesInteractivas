package SistemaVentas;

public class TarjetaDebitoXPortal extends Portal {

	private int fechaVencimientoDia;
	private int fechaVencimientoMes;
	
	
	
	public TarjetaDebitoXPortal(int fechaVencimientoDia, int fechaVencimientoMes) {
		super();
		this.fechaVencimientoDia = fechaVencimientoDia;
		this.fechaVencimientoMes = fechaVencimientoMes;
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
	
	
}
