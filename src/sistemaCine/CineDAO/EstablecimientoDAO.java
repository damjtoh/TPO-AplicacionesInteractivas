package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.clases.Establecimiento;

public class EstablecimientoDAO {

	public static void insert(Object object) throws SQLException {

		Establecimiento establecimiento = (Establecimiento) object;
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = coneccion.prepareStatement("insert into establecimientos values (?,?,?,?,1)");

		query.setInt(1, establecimiento.getCuit());
		query.setString(2, establecimiento.getNombre());
		query.setString(3, establecimiento.getDomicilio());
		query.setInt(4, establecimiento.getCapacidadTotal());
		query.execute();
//			query = coneccion.prepareStatement("insert into establecimientos_salas values (?,?)");
//			for (String sala : establecimiento.getSalas().keySet()) {
//				query.setInt(1, establecimiento.getCuit());
//				query.setString(2, sala);
//				query.execute();
//			}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
	}

	public static void update(Object object) throws SQLException {
		Establecimiento establecimiento = (Establecimiento) object;
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = coneccion
				.prepareStatement("update establecimientos set nombre = ?,direccion = ?,capacidad = ? where cuit = ?");
		query.setString(1, establecimiento.getNombre());
		query.setString(2, establecimiento.getDomicilio());
		query.setInt(3, establecimiento.getCapacidadTotal());
		query.setInt(4, establecimiento.getCuit());
		query.execute();
//			query = coneccion.prepareStatement("delete from establecimientos_salas where cuit = ?");
//			query.setInt(1, establecimiento.getCuit());
//			query.execute();
//			for (String sala : establecimiento.getSalas().keySet()) {
//				query.setInt(1, establecimiento.getCuit());
//				query.setString(2, sala);
//				query.execute();
//			}
//			
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}

	public static void delete(Object object) throws SQLException {

		Establecimiento establecimiento = (Establecimiento) object;
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = coneccion.prepareStatement("update establecimientos set activa = 0 where cuit = ?");
		query.setInt(1, establecimiento.getCuit());
		query.execute();
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}

	public static Establecimiento selectEstablecimieto(int cuit) throws SQLException {

		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = coneccion.prepareStatement("select * from establecimientos where cuit = ?");
		query.setInt(1, cuit);
		ResultSet rs = query.executeQuery();
		Establecimiento establecimiento = null;
		if (rs.next()) {
			establecimiento = new Establecimiento(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return establecimiento;

	}

	public static Map<String, Establecimiento> selectAllEstablecimietos() throws SQLException {

		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = coneccion.prepareStatement("select * from establecimientos where activa = 1");
		ResultSet rs = query.executeQuery();
		Map<String, Establecimiento> establecimientos = new HashMap<>();
		while (rs.next()) {
			establecimientos.put(rs.getString(2),
					new Establecimiento(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return establecimientos;

	}
}
