package sistemaCine.cinesClases;

public class Entrada {

	AsinentoVirtual asiento;
	Funcion funcion;
	public AsinentoVirtual getAsiento() {
		return asiento;
	}
	public void setAsiento(AsinentoVirtual asiento) {
		this.asiento = asiento;
	}
	public Funcion getFuncion() {
		return funcion;
	}
	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Entrada(AsinentoVirtual asiento, Funcion funcion) {
		super();
		this.asiento = asiento;
		this.funcion = funcion;
	}
	
	
}
