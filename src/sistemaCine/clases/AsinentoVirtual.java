package sistemaCine.clases;

public class AsinentoVirtual {
	private String columna;
	private String fila;
	private Boolean ocupado;

	public AsinentoVirtual(String columna, String fila) {
		super();
		this.columna = columna;
		this.fila = fila;
		this.ocupado = false;
	}

	public Boolean isOcupado() {
		return ocupado;
	}

	public String getColumna() {
		return columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getFila() {
		return fila;
	}

	@Override
	public String toString() {
		return fila + "-" + columna;
	}
}
