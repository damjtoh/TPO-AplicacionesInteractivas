package SistemaVentas;

public abstract class Portal implements ITipoDePago {
	private String tipoDeTarjeta;
	private long numero;
	private String fechaDeVencimiento;
	private int codigoDeSeguridad;

	public String getTipoDeTarjeta() {
		return tipoDeTarjeta;
	}

	public void setTipoDeTarjeta(String tipoDeTarjeta) {
		this.tipoDeTarjeta = tipoDeTarjeta;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public int getCodigoDeSeguridad() {
		return codigoDeSeguridad;
	}

	public void setCodigoDeSeguridad(int codigoDeSeguridad) {
		this.codigoDeSeguridad = codigoDeSeguridad;
	}

}
