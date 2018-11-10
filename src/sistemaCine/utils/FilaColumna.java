package sistemaCine.utils;

public class FilaColumna {
	String fila;
	String columna;

	public FilaColumna(String fila, String columna) {
		super();
		this.fila = fila;
		this.columna = columna;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getColumna() {
		return columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this.getClass())) {
			return fila.equals(((FilaColumna) obj).getFila()) && columna.equals(((FilaColumna) obj).getColumna());
		}
		return false;
	}
	@Override
	public int hashCode() {
		return fila.hashCode() + columna.hashCode();
	}
}
