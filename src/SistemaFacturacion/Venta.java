package SistemaFacturacion;

import java.time.LocalDate;

public class Venta {
	
	private LocalDate fechaHora;
	private ITipoPago tipoDePago;
	private Long numeroTarjetaClub;
	private float importe;

	public Venta(LocalDate fechaHora, ITipoPago tipoDePago,
			Long numeroTarjetaClub, float importe) {
		super();
		this.fechaHora = fechaHora;
		this.tipoDePago = tipoDePago;
		this.numeroTarjetaClub = numeroTarjetaClub;
		this.importe = importe;
	}

}
