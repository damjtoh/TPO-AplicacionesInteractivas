package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import Persistencas.PoolConnection;
import sistemaCine.cinesClases.AsinentoFisico;
import sistemaCine.cinesClases.Sala;

public class SalaDAO {

	public void insert(Object object, int cuitEstablecimiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into salas values (?,?,?,?,?)");
			for (AsinentoFisico asiento : sala.getMapaDeAsientos().values()) {
				query.setInt(1, cuitEstablecimiento);
				query.setString(2, sala.getNombre());
				query.setString(3, asiento.getFila());
				query.setString(4, asiento.getColumna());
				query.setBoolean(5, asiento.isUsable());
				query.execute();
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public void updateSala(Object object, int cuitEstablecimiento, String salaOldName) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update into salas set nombre = ? where cuit = ? and nombre = ?");
			query.setString(1, sala.getNombre());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, salaOldName);
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public void updateAsientosSala(Object object, int cuitEstablecimiento, AsinentoFisico asiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update into salas set usable = ? where cuit = ? and nombre = ?");
			query.setBoolean(1, asiento.isUsable());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, sala.getNombre());

			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public void delete(Object object, int cuitEstablecimiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("deleta from salas where cuit = ? and nombre = ?");
			query.setInt(1, cuitEstablecimiento);
			query.setString(2, sala.getNombre());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}

	}

	public List<Object> select(Object o) {
		return null;
	}

	public void insert(Object o) {
		// TODO Auto-generated method stub

	}

}
