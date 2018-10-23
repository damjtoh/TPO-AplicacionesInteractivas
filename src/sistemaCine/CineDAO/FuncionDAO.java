package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.cinesClases.AsinentoVirtual;
import sistemaCine.cinesClases.Funcion;
import sistemaCine.cinesClases.Pelicula;
import sistemaCine.cinesClases.Sala;
import sistemaCine.utils.FilaColumna;

public class FuncionDAO {
	public static void insertFuncion(Funcion funcion, int cuitEstablecimiento) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("insert into funciones values ((select max(id)+1 from peliculas),?,?,?,?,?)");
			query.setInt(1, funcion.getPelicula().getId());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, funcion.getSala().getNombre());
			query.setDate(4, funcion.getFechaYHora());
			query.setDouble(5, funcion.getValor());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void updateFuncion(Funcion funcion, int cuitEstablecimiento) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement(
					"update funciones set id_pelicula = ?,cuit_establecimiento = ?, nombre_sala = ?,fecha_hora = ?,valor = ? where id = ?");
			query.setInt(1, funcion.getPelicula().getId());
			query.setInt(2, cuitEstablecimiento);
			query.setString(3, funcion.getSala().getNombre());
			query.setDate(4, funcion.getFechaYHora());
			query.setDouble(5, funcion.getValor());
			query.setInt(6, funcion.getId());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void deleteFuncion(Funcion funcion) {

		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("delete from funciones where id = ?");
			query.setInt(1, funcion.getId());
			query.execute();

			PoolConnection.getPoolConnection().realeaseConnection(coneccion);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Funcion> selectFunciones(Pelicula pelicula, Date fecha, Integer cuitEstablecimiento) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String consultaSql = "select fecha_hora,nombre_sala,valor,id from funciones where  cuit_establecimiento = ? and id_pelicula = ?, fecha_hora >= ? order by fecha_hora ASC";
//			if (cuitEstablecimiento !=null) {
//				consultaSql = consultaSql.replace("cuit"," cuit_establecimiento = ? and ");
//			}else {
//				consultaSql = consultaSql.replace("cuit","");
//			}
//			
			PreparedStatement query = coneccion.prepareStatement(consultaSql);
//			if (cuitEstablecimiento !=null) {
			query.setInt(1, cuitEstablecimiento);
			query.setInt(2, pelicula.getId());
			query.setDate(3, fecha);
//			}else {
//				query.setInt(1, funcion.getPelicula().getId());
//				query.setDate(2, funcion.getFechaYHora());
//			}
			ResultSet rs = query.executeQuery();
			List<Funcion> funciones = new ArrayList<>();
			if (rs.next()) {
				Funcion funcion = new Funcion(rs.getDate(1), pelicula, new Sala(rs.getString(2)), rs.getDouble(3));
				funcion.setId(rs.getInt(4));
				funciones.add(funcion);
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return funciones;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static Map<FilaColumna, AsinentoVirtual> selectAsientos(Funcion funcion) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from entradas where id_funcion = ?";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, funcion.getId());

			ResultSet rs = query.executeQuery();
			Map<FilaColumna, AsinentoVirtual> mapaDeAsientos = new HashMap<>();
			while (rs.next()) {
				AsinentoVirtual asiento = new AsinentoVirtual(rs.getString(2), rs.getString(3));
				asiento.setOcupado(rs.getBoolean(4));
				mapaDeAsientos.put(new FilaColumna(rs.getString(2), rs.getString(3)), asiento);
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return mapaDeAsientos;
		} catch (Exception e) {
			System.out.println();
		}
		return new HashMap<>();
	}
	public static List<Integer> selectPeliculasEstablecimiento(int cuit,Date fecha){
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select distinct id_pelicula from funciones where cuit_establecimiento = ? and fecha_hora > ? order_by fecha_hora";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, cuit);
			query.setDate(2, fecha);
			ResultSet rs = query.executeQuery();
			List<Integer> idsPelicula= new ArrayList<>();
			while (rs.next()) {
				idsPelicula.add(rs.getInt(1));
			}
			return idsPelicula;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
		
	}
}
