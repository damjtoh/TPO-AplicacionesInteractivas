package cinesClases;

public class AsinentoFisico {
	private String columna;
	private String fila;
	private boolean usable;
	public AsinentoFisico(String numero, String fila) {
		super();
		this.columna = numero;
		this.fila = fila;
		this.usable = true;
	}
	public boolean isUsable() {
		return usable;
	}
	public void setUsable(boolean esUsable) {
		this.usable = esUsable;
	}

	public String getColumna() {
		return columna;
	}
	public String getFila() {
		return fila;
	}
	
	
}
