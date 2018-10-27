package sistemaCine.clases;

public class Entrada {

	private AsinentoVirtual asiento;
	private Funcion funcion;
	private int ventaId;
	private int Id;
	

	public int getVentaId() {
		return ventaId;
	}
	public void setVentaId(int ventaId) {
		this.ventaId = ventaId;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
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

	public Entrada(AsinentoVirtual asiento, Entrada entrada) {
		super();
		this.asiento = asiento;
		this.funcion = entrada;
	}
	public Entrada(AsinentoVirtual asiento, Funcion funcion, int ventaId, int id) {
		super();
		this.asiento = asiento;
		this.funcion = funcion;
		this.ventaId = ventaId;
		Id = id;
	}
	
	
	
}
