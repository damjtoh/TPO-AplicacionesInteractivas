package SistemaVentas;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import Persistencas.PoolConnection;
import sistemaCine.CineDAO.EntradasDAO;
import sistemaCine.clases.Entrada;
import sistemaCine.services.EntradaService;

public class VentasDAO {

	public static Venta insert(Venta venta) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			
			
			int id = getId();

			PreparedStatement query = coneccion.prepareStatement("insert into VENTA values (?,?,?,?,?,?)");

			query.setInt(1, id);
			query.setDate(2, java.sql.Date.valueOf(venta.getFechaYHora()));
			query.setLong(3, venta.getNumeroDeTarjeta());
			query.setDouble(4, venta.getImporte());
			query.setInt(5, venta.getTipoDePago().getNro());
			query.setBoolean(6, venta.isEsPorPortal());
			query.execute();
			venta.setId(id);
			for (Entrada entrada : venta.getEntradas()) {
				entrada.setId(id);
				entrada.getAsiento().setOcupado(true);
				EntradaService.updateEntrada(entrada);
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	private static int getId() throws SQLException {

			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("select max(id)+1 from VENTA");
			ResultSet res =query.executeQuery();

			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			if (res.next()) {
				return res.getInt(1);
			}
			return 1;

	}

	public static Venta update(Venta venta) {
		try {			
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update VENTA set fechaYHora = ?, tipoDePago = ?,numeroDeTarjeta = ?, importe = ?,esPorPortal = ? where id = ?");

			venta.getEntradas().clear();
			
			for (Entrada entrada : venta.getEntradas()) {
				venta.getEntradas().add(EntradasDAO.updateEntrada(entrada));
			}	
			
			query.setString(2, venta.getFechaYHora().toString());
			query.setObject(3, venta.getTipoDePago());
			query.setLong(4, venta.getNumeroDeTarjeta());
			query.setDouble(5, venta.getImporte());
			query.setBoolean(6, venta.isEsPorPortal());
			query.setInt(7, venta.getId());
			query.execute();
	
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Update fail");
		}
		return null;
	}

	public boolean delete(Venta venta) {

		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("delete from VENTA where id = ?");
			query.setInt(1, venta.getId());
			query.execute();
	
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Delete fail");
		}
		return false;
	}

	public static Venta getById(int id) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("select * from VENTA where id = ?");
			query.setInt(1, id);
			ResultSet res = query.executeQuery();
			Venta venta = null;
			
			if (res.last()) {
				venta = new Venta(EntradasDAO.getByVentaId(id), res.getDate("fechaYHora").toLocalDate(),
						(ITipoDePago)res.getObject("tipoDePago"), res.getLong("numeroDeTarjeta"), res.getDouble("importe"), res.getInt("id"));
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Select fail");
		}
		return null;
	}
}
