package SistemaVentas;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Persistencas.PoolConnection;
import sistemaCine.CineDAO.EntradasDAO;
import sistemaCine.clases.Entrada;

public class VentasDAO {

	public static Venta insert(Venta venta) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into Venta values (?,?,?,?)");

			for (Entrada entrada : venta.getEntradas()) {
				venta.getEntradas().add(EntradasDAO.insertEntrada(entrada));
			}	
			
			
			query.setString(2, venta.getFechaYHora().toString());
			query.setObject(3, venta.getTipoDePago());
			query.setLong(4, venta.getNumeroDeTarjeta());
			query.setDouble(5, venta.getImporte());
			query.execute();
	
			ResultSet res = query.getResultSet();
			venta.setId(res.getInt("id"));
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "//insert fail");
		}
		return null;

	}

	public static Venta update(Venta venta) {
		try {			
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update Venta set fechaYHora = ?, tipoDePago = ?,numeroDeTarjeta = ?, importe = ? where id = ?");

			venta.getEntradas().clear();
			
			for (Entrada entrada : venta.getEntradas()) {
				venta.getEntradas().add(EntradasDAO.updateEntrada(entrada));
			}	
			
			query.setString(2, venta.getFechaYHora().toString());
			query.setObject(3, venta.getTipoDePago());
			query.setLong(4, venta.getNumeroDeTarjeta());
			query.setDouble(5, venta.getImporte());
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
			PreparedStatement query = coneccion.prepareStatement("delete from Venta where id = ?");
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
			PreparedStatement query = coneccion.prepareStatement("select * from venta where id = ?");
			query.setInt(1, id);
			ResultSet res = query.executeQuery();
			Venta venta = null;
			
			if (res.next()) {
				
				venta = new Venta(EntradasDAO.getByVentaId(id), LocalDate.parse(res.getString("fechaYHora")),
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
