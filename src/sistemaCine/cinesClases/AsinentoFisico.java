package sistemaCine.cinesClases;

public class AsinentoFisico {
	private String columna;
	private String fila;
	private boolean usable;
	public AsinentoFisico(String fila,String columna) {
		super();
		this.columna = columna;
		this.fila = fila;
		this.usable = true;
	}
	public AsinentoFisico(String columna, String fila,boolean usable) {
		super();
		this.columna = columna;
		this.fila = fila;
		this.usable = usable;
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
