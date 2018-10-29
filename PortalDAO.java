package SistemaVentas;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Persistencas.PoolConnection;

public class PortalDAO {
	
	public static Portal insert(Portal portal) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into TIPO_DE_PAGO values (?,?,?,?)");
			query.setInt(1, (int)portal.getNumero());
			query.setString(2, portal.getTipoDeTarjeta());
			query.setDate(3, java.sql.Date.valueOf(portal.getFechaDeVencimiento()));
			query.setInt(4, portal.getCodigoDeSeguridad());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return portal;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "//insert fail");
		}
		return null;

	}

	
	public boolean delete(Portal portal) {

		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("delete from TIPO_DE_PAGO where numero = ?");
			query.setLong(1, portal.getNumero());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Delete fail");
		}
		return false;
	}
	
	
}
