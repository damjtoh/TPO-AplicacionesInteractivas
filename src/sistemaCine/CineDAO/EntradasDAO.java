package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.cinesClases.AsinentoVirtual;
import sistemaCine.cinesClases.Entrada;
import sistemaCine.cinesClases.Funcion;
import sistemaCine.utils.FilaColumna;

public class EntradasDAO {
	public static void insertEntrada(Entrada entrada) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into entradas values (?,?,?,?)");
			query.setInt(1, entrada.getFuncion().getId());
			query.setString(2, entrada.getAsiento().getColumna());
			query.setString(3, entrada.getAsiento().getFila());
			query.setBoolean(4, entrada.getAsiento().isOcupado());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void updateEntrada(Entrada entrada) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement(
					"update entradas set ocupado = ? where id_funcion = ? and columna = ? and fila = ?");
			query.setBoolean(1, entrada.getAsiento().isOcupado());
			query.setInt(2, entrada.getFuncion().getId());
			query.setString(3, entrada.getAsiento().getColumna());
			query.setString(4, entrada.getAsiento().getFila());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void deleteEntrada(Entrada entrada) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("delete from entradas where id_funcion = ? and columna = ? and fila = ?");
			query.setInt(1, entrada.getFuncion().getId());
			query.setString(2, entrada.getAsiento().getColumna());
			query.setString(3, entrada.getAsiento().getFila());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static Entrada selectEntrada(Entrada entrada) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from entradas where id_funcion = ? and columna = ? and fila = ? ";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, entrada.getFuncion().getId());
			query.setString(2, entrada.getAsiento().getColumna());
			query.setString(3, entrada.getAsiento().getFila());
			ResultSet rs = query.executeQuery();
			rs.next();
			AsinentoVirtual asinento = new AsinentoVirtual(rs.getString(2), rs.getString(3));
			asinento.setOcupado(rs.getBoolean(4));
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return new Entrada(asinento, null);
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}

}
