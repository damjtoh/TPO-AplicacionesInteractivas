package SistemaFacturacion;

import java.time.LocalDate;

public class Venta {
	
	private static int contador = 1;
	private int id;
	private LocalDate fechaHora;
	private ITipoPago tipoDePago;
	private Long numeroTarjetaClub;
	private float importe;

	public Venta(LocalDate fechaHora, ITipoPago tipoDePago, Long numeroTarjetaClub, float importe) {
		super();
		this.fechaHora = fechaHora;
		this.tipoDePago = tipoDePago;
		this.numeroTarjetaClub = numeroTarjetaClub;
		this.importe = importe;
		this.id = contador;
		contador++;
	}
	
	public Venta(LocalDate fechaHora, Long numeroTarjetaClub) {
		super();
		this.fechaHora = fechaHora;
		this.tipoDePago = tipoDePago;
		this.numeroTarjetaClub = numeroTarjetaClub;
		this.importe = importe;
		this.id = contador;
		contador++;
	}
	
	public float GetImporte() {
		return importe;
	}
	
	public void SetImporte(float importe) {
		this.importe = importe;
	}
	
	public int GetId() {
		return id;
	}
	
	public boolean TieneId(int id) {
		return this.id == id;
	}

}
