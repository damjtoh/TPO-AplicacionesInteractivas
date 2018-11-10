package sistemaCine.clases;

public class Entrada {

	private AsientoVirtual asiento;
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
	public AsientoVirtual getAsiento() {
		return asiento;
	}
	public void setAsiento(AsientoVirtual asiento) {
		this.asiento = asiento;
	}
	public Funcion getFuncion() {
		return funcion;
	}
	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Entrada(AsientoVirtual asiento, Funcion funcion) {
		super();
		this.asiento = asiento;
		this.funcion = funcion;
	}
	public Entrada(AsientoVirtual asiento, Funcion funcion, int ventaId, int id) {
		super();
		this.asiento = asiento;
		this.funcion = funcion;
		this.ventaId = ventaId;
		Id = id;
	}
	
	
	
}
