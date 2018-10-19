package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.cinesClases.AsinentoFisico;
import sistemaCine.cinesClases.Sala;
import sistemaCine.utils.FilaColumna;

public class SalaDAO {

	public static void insert(Object object, int cuitEstablecimiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into sala_asientos values (?,?,?,?,?)");
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

	public static void updateSala(Object object, int cuitEstablecimiento, String salaOldName) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update into sala_asientos set nombre = ? where cuit = ? and nombre = ?");
			query.setString(1, sala.getNombre());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, salaOldName);
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void updateAsientosSala(Object object, int cuitEstablecimiento, AsinentoFisico asiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("update into sala_asientos set usable = ? where cuit = ? and nombre = ?");
			query.setBoolean(1, asiento.isUsable());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, sala.getNombre());

			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void delete(Object object, int cuitEstablecimiento) {
		try {
			Sala sala = (Sala) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("deleta from sala_asientos where cuit = ? and nombre = ?");
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
					.prepareStatement("select nombre from sala_asientos where cuit = ? group by nombre");
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

	public static void selectAsientosSala(Sala sala, int cuitEstablecimiento) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("select fila,comlumna,usable from sala_asientos where cuit = ? and nombre = ?");
			query.setInt(1, cuitEstablecimiento);
			query.setString(2, sala.getNombre());
			ResultSet rs = query.executeQuery();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			Map<FilaColumna, AsinentoFisico> asientos = new HashMap<>();
			while (rs.next()) {
				asientos.put(new FilaColumna(rs.getString(1), rs.getString(2)),
						new AsinentoFisico(rs.getString(1), rs.getString(2), rs.getBoolean(3)));
			}
			sala.setMapaDeAsientos(asientos);
		} catch (Exception e) {
			System.out.println();
		}
	}

}