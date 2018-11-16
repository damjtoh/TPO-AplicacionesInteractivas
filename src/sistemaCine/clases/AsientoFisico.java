package sistemaCine.clases;

public class AsientoFisico {
	private String columna;
	private String fila;
	private int nroFila;
	private int nroColumna;
	private boolean usable;
	public AsientoFisico(String fila,String columna,int nroFila,int nroColumna) {
		super();
		this.columna = columna;
		this.fila = fila;
		this.nroFila = nroFila;
		this.nroColumna = nroColumna;
		this.usable = true;
	}
	public AsientoFisico( String fila,String columna,int nroFila,int nroColumna,boolean usable) {
		super();
		this.columna = columna;
		this.fila = fila;
		this.usable = usable;
		this.nroFila = nroFila;
		this.nroColumna = nroColumna;
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
	public int getNroColumna() {
		return nroColumna;
	}
	public void setNroColumna(int nroColumna) {
		this.nroColumna = nroColumna;
	}
	public int getNroFila() {
		return nroFila;
	}
	public void setNroFila(int nroFila) {
		this.nroFila = nroFila;
	}
	@Override
	public String toString() {
		return fila + "-" + columna;
	}
	public void setColumna(String columna) {
		this.columna = columna;
	}
	public void setFila(String fila) {
		this.fila = fila;
	}
	
}
