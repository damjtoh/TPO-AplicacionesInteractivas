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
import sistemaCine.clases.AsinentoFisico;
import sistemaCine.clases.Sala;
import sistemaCine.utils.FilaColumna;

public class SalaDAO {

	private static final String cuit_establecimiento = "cuit_establecimiento";

	public static void insertSala(Object object, int cuitEstablecimiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into salas values (?,?)");
			query.setInt(1, cuitEstablecimiento);
			query.setString(2, sala.getNombre());
			query.execute();
			query = coneccion.prepareStatement("insert into sala_asientos values (?,?,?,?,?,?,?)");
			for (AsinentoFisico asiento : sala.getMapaDeAsientos().values()) {
				query.setInt(1, cuitEstablecimiento);
				query.setString(2, sala.getNombre());
				query.setString(3, asiento.getFila());
				query.setString(4, asiento.getColumna());
				query.setInt(5, asiento.getNroFila());
				query.setInt(6, asiento.getNroColumna());
				query.setBoolean(7, asiento.isUsable());
				query.execute();
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void updateSala(Object object, int cuitEstablecimiento, String salaOldName) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement(
					"update into salas set nombre = ? where " + cuit_establecimiento + " = ? and nombre like ?");
			query.setString(1, sala.getNombre());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, salaOldName);
			query.execute();
			query = coneccion.prepareStatement("update into sala_asientos set nombre = ? where " + cuit_establecimiento
					+ " = ? and nombre like ?");
			query.setString(1, sala.getNombre());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, salaOldName);
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void updateAsientosSala(Object object, int cuitEstablecimiento) throws SQLException {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement(
					"delete from sala_asientos where " + cuit_establecimiento + " = ? and nombre like ?");
			query.setString(2, sala.getNombre());
			query.setInt(1, cuitEstablecimiento);
			query.execute();
			query = coneccion.prepareStatement("insert into sala_asientos values (?,?,?,?,?,?,?)");
			for (AsinentoFisico asiento : sala.getMapaDeAsientos().values()) {
				query.setInt(1, cuitEstablecimiento);
				query.setString(2, sala.getNombre());
				query.setString(3, asiento.getFila());
				query.setString(4, asiento.getColumna());
				query.setInt(5, asiento.getNroFila());
				query.setInt(6, asiento.getNroColumna());
				query.setBoolean(7, asiento.isUsable());
				query.execute();
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}

	public static void deleteSala(Object object, int cuitEstablecimiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("deleta from salas where " + cuit_establecimiento + " = ? and nombre like ?");
			query.setInt(1, cuitEstablecimiento);
			query.setString(2, sala.getNombre());
			query.execute();
			query = coneccion.prepareStatement(
					"deleta from sala_asientos where " + cuit_establecimiento + " = ? and nombre like ?");
			query.setInt(1, cuitEstablecimiento);
			query.setString(2, sala.getNombre());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}

	}

	public static List<Sala> selectSalas(int cuitEstablecimiento) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("select nombre from salas where " + cuit_establecimiento + " = ?");
			query.setInt(1, cuitEstablecimiento);
			ResultSet rs = query.executeQuery();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			List<Sala> salas = new ArrayList<>();
			while (rs.next()) {
				salas.add(new Sala(rs.getString(1)));
			}
			return salas;
		} catch (Exception e) {
			System.out.println();
		}
		return new ArrayList<>();
	}

	public static Sala getById(int id) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("select nombre from salas where id = ?");
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			
			Sala sala = new Sala(rs.getString("nombre"), rs.getInt("id"));
			
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			
			return sala;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}
	
	
	public static Map<FilaColumna, AsinentoFisico> selectAsientosSala(Sala sala, int cuitEstablecimiento) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement(
					"select fila,columna,nro_fila,nro_columna,usable from sala_asientos where cuit = ? and nombre like ?");
			query.setInt(1, cuitEstablecimiento);
			query.setString(2, sala.getNombre());
			ResultSet rs = query.executeQuery();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			Map<FilaColumna, AsinentoFisico> asientos = new HashMap<>();
			while (rs.next()) {
				asientos.put(new FilaColumna(rs.getString(1), rs.getString(2)), new AsinentoFisico(rs.getString(1),
						rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5)));
			}
			return asientos;
		} catch (Exception e) {
			System.out.println();
		}
		return new HashMap<>();
	}

}
