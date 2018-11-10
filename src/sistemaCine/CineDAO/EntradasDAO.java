package sistemaCine.CineDAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.clases.AsientoFisico;
import sistemaCine.clases.AsientoVirtual;
import sistemaCine.clases.Entrada;
import sistemaCine.clases.Funcion;
import sistemaCine.utils.FilaColumna;

public class EntradasDAO {
	public static Entrada insertEntrada(Entrada entrada) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into entradas values (?,?,?,?,null)");
			query.setInt(1, entrada.getFuncion().getId());
			query.setString(2, entrada.getAsiento().getColumna());
			query.setString(3, entrada.getAsiento().getFila());
			query.setBoolean(4, entrada.getAsiento().isOcupado());
			query.execute();
			
			ResultSet res = query.getResultSet();
			entrada.setId(res.getInt("id"));
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return entrada;
	}

	public static void insertMapaEntradas(Funcion funcion) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			for (AsientoFisico asiento : funcion.getSala().getMapaDeAsientos().values()) {
				PreparedStatement query = coneccion.prepareStatement("insert into entradas values (?,?,?,?,null)");
				query.setInt(1, funcion.getId());
				query.setString(2, asiento.getColumna());
				query.setString(3, asiento.getFila());
				query.setBoolean(4, false);
				query.execute();
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
	}

	public static Entrada updateEntrada(Entrada entrada) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement(
					"update entradas set ocupado = ?,ventaId = ? where id_funcion = ? and columna = ? and fila = ?");
			query.setBoolean(1, entrada.getAsiento().isOcupado());
			query.setInt(2, entrada.getVentaId());
			query.setInt(3, entrada.getFuncion().getId());
			query.setString(4, entrada.getAsiento().getColumna());
			query.setString(5, entrada.getAsiento().getFila());
			query.execute();

			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return entrada;
	}

	public static void deleteEntrada(Entrada entrada) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("delete from entradas where id_funcion = ? and columna = ? and fila = ?");
			query.setInt(1, entrada.getFuncion().getId());
			query.setString(2, entrada.getAsiento().getColumna());
			query.setString(3, entrada.getAsiento().getFila());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}

	public static Entrada selectEntrada(Entrada entrada) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from entradas where id_funcion = ? and columna = ? and fila = ? ";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, entrada.getFuncion().getId());
			query.setString(2, entrada.getAsiento().getColumna());
			query.setString(3, entrada.getAsiento().getFila());
			ResultSet rs = query.executeQuery();
			rs.next();
			AsientoVirtual asinento = new AsientoVirtual(rs.getString(2), rs.getString(3));
			asinento.setOcupado(rs.getBoolean(4));
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return new Entrada(asinento, null);
	}

	public static List<Entrada> getByVentaId(int ventaId) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from entradas where ventaId = ?";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, ventaId);
			
			
			ResultSet rs = query.executeQuery();
			
			List<Entrada> entradas = new ArrayList<Entrada>();
			
			while(rs.next()) {
				entradas.add(new Entrada(new AsientoVirtual(rs.getString(2), rs.getString(3)), FuncionDAO.getById(rs.getInt("id_funcion"))));
			}
			
			
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return entradas;
	}
	
	
	public static Map<FilaColumna, AsientoVirtual> selectMapaEntradas(Funcion funcion) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from entradas where id_funcion = ?" /*and columna in (?) */;
			PreparedStatement query = coneccion.prepareStatement(statementSql);
//			List<String> columnas = new ArrayList<>();
//			for (FilaColumna columna : funcion.getSala().getMapaDeAsientos().keySet()) {
//				columnas.add(columna.getColumna());
//			}
			query.setInt(1, funcion.getId());
//			query.setArray(2, coneccion.createArrayOf("VARCHAR", columnas.toArray()));
			ResultSet rs = query.executeQuery();
			Map<FilaColumna, AsientoVirtual> asientos = new HashMap<>();
			while (rs.next()) {
				AsientoVirtual asiento;
				asiento = new AsientoVirtual(rs.getString(2), rs.getString(3));
				asiento.setOcupado(rs.getBoolean(4));
				asientos.put(new FilaColumna(asiento.getFila(), asiento.getColumna()), asiento);
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return asientos;
	}

	public static void deleteEntradas(int idFuncion) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("delete from entradas where id_funcion = ?");
			query.setInt(1, idFuncion);
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
	
	}

	public static List<Entrada> getEntradasVendidas(Funcion funcion) throws SQLException {
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		String statementSql = "select * from entradas where id_funcion = ? and ocupado = 1" /*and columna in (?) */;
		PreparedStatement query = coneccion.prepareStatement(statementSql);
//		List<String> columnas = new ArrayList<>();
//		for (FilaColumna columna : funcion.getSala().getMapaDeAsientos().keySet()) {
//			columnas.add(columna.getColumna());
//		}
		query.setInt(1, funcion.getId());
//		query.setArray(2, coneccion.createArrayOf("VARCHAR", columnas.toArray()));
		ResultSet rs = query.executeQuery();
		List<Entrada> asientos = new ArrayList<>();
		while (rs.next()) {
			AsientoVirtual asiento;
			asiento = new AsientoVirtual(rs.getString(2), rs.getString(3));
			asiento.setOcupado(rs.getBoolean(4));
			asientos.add(new Entrada(asiento, funcion));
		}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return asientos;
	}

}
