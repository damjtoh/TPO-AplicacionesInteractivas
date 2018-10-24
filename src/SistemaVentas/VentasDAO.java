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
import sistemaCine.clases.Entrada;

public class VentasDAO {

	public Venta insert(Object object) {
		try {
			Venta venta = (Venta) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into Venta values (?,?,?,?,?,?)");

			query.setArray(1, (Array) venta.getEntradas());
			query.setString(2, venta.getFechaYHora().toString());
			query.setObject(3, venta.getTipoDePago());
			query.setLong(4, venta.getNumeroDeTarjeta());
			query.setDouble(5, venta.getImporte());
			query.setArray(6, (Array) venta.getObservers());
			query.execute();
			// query = coneccion.prepareStatement("insert into establecimientos_salas values
			// (?,?)");
			// for (String sala : establecimiento.getSalas().keySet()) {
			// query.setInt(1, establecimiento.getCuit());
			// query.setString(2, sala);
			// query.execute();
			// }

			ResultSet res = query.getResultSet();
			venta.setId(res.getInt("id"));
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "//insert fail");
		}
		return null;

	}

	public Venta update(Object object) {
		try {
			Venta venta = (Venta) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update establecimientos set entradas = ?,fechaYHora = ?, tipoDePago = ?,numeroDeTarjeta = ?, importe = ?,observers = ? where cuit = ?");

			query.setArray(1, (Array) venta.getEntradas());
			query.setString(2, venta.getFechaYHora().toString());
			query.setObject(3, venta.getTipoDePago());
			query.setLong(4, venta.getNumeroDeTarjeta());
			query.setDouble(5, venta.getImporte());
			query.setArray(6, (Array) venta.getObservers());
			query.setInt(7, venta.getId());
			query.execute();
			// query = coneccion.prepareStatement("delete from establecimientos_salas where
			// cuit = ?");
			// query.setInt(1, establecimiento.getCuit());
			// query.execute();
			// for (String sala : establecimiento.getSalas().keySet()) {
			// query.setInt(1, establecimiento.getCuit());
			// query.setString(2, sala);
			// query.execute();
			// }
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Update fail");
		}
		return null;
	}

	public boolean delete(Object object) {

		try {
			Venta venta = (Venta) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("delete from establecimientos where id = ?");
			query.setInt(1, venta.getId());
			query.execute();
			// query = coneccion.prepareStatement("delete from establecimientos_salas where
			// cuit = ?");
			// query.setInt(1, establecimiento.getCuit());
			// query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Delete fail");
		}
		return false;
	}

	public Venta getById(int id) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("select * from venta where id = ?");
			query.setInt(1, id);
			ResultSet res = query.executeQuery();
			Venta venta = null;
			
			
			
			if (res.next()) {
				
				List<Entrada> entradas = new ArrayList<Entrada>();
				
				Array auxiliar = res.getArray("entradas");
				
				for (Entrada entrada : auxiliar) {
					entradas.add(entrada);
				}
				
				List<ObserverVenta> observers = new ArrayList<ObserverVenta>();
				
				Array aux = res.getArray("observers");
				
				for (ObserverVenta observer : aux) {
					observers.add(observer);
				}
				
				venta = new Venta(entradas, LocalDate.parse(res.getString("fechaYHora")),
						(ITipoDePago)res.getObject("tipoDePago"), res.getLong("numeroDeTarjeta"), res.getDouble("importe"), 
						observers, res.getInt("id"));
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return venta;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Select fail");
		}
		return null;
	}
}
