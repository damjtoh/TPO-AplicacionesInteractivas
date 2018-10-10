package sistemaCine.cinesClases;

public class AsinentoVirtual {
	private String numero;
	private String fila;
	private boolean ocupado;
	
	public AsinentoVirtual(String numero, String fila) {
		super();
		this.numero = numero;
		this.fila = fila;
		this.ocupado = false;
	}
	public boolean isOcupado() {
		return ocupado;
	}
	public void setUsable(boolean ocupado) {
		this.ocupado = ocupado;
	}
	public String getNumero() {
		return numero;
	}
	public String getFila() {
		return fila;
	}
	
}
