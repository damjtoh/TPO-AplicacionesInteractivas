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
import sistemaCine.clases.AsientoVirtual;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.Sala;
import sistemaCine.utils.FilaColumna;

public class FuncionDAO {
	public static void insertFuncion(Funcion funcion, int cuitEstablecimiento) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion
					.prepareStatement("insert into funciones values (?,?,?,?,?,?)");
			query.setInt(1, getLastId(coneccion));
			query.setInt(2, funcion.getPelicula().getId());
			query.setString(3, funcion.getSala().getNombre());
			query.setInt(4, cuitEstablecimiento);
			query.setDate(5, funcion.getFechaYHora());
			query.setDouble(6, funcion.getValor());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}
	private static int getLastId(Connection conection) throws SQLException {
		PreparedStatement query = conection.prepareStatement("(select max(id)+1 from funciones)");
		ResultSet rs = query.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	public static void updateFuncion(Funcion funcion, int cuitEstablecimiento) throws SQLException {
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
			String consultaSql = "select fecha_hora,nombre_sala,valor,id from funciones where  cuit_establecimiento = ? and id_pelicula = ? and fecha_hora >= ? order by fecha_hora ASC";
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
	
	public static Funcion getById(int id) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String consultaSql = "select * where  id = ?";

			PreparedStatement query = coneccion.prepareStatement(consultaSql);
			query.setInt(1, id);
			
			Funcion funcion = null;
			
			ResultSet resFuncion = query.executeQuery();
			
			List<Funcion> funciones = new ArrayList<>();
			if (resFuncion.next()) {
				funcion = new Funcion(PeliculaDAO.getById(resFuncion.getInt("id_pelicula")), SalaDAO.getById(resFuncion.getInt("salaId")), resFuncion.getDate("fecha_hora"));
				funcion.setId(resFuncion.getInt(4));
				funciones.add(funcion);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return funcion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	public static Map<FilaColumna, AsientoVirtual> selectAsientos(Funcion funcion) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from entradas where id_funcion = ?";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, funcion.getId());

			ResultSet rs = query.executeQuery();
			Map<FilaColumna, AsientoVirtual> mapaDeAsientos = new HashMap<>();
			while (rs.next()) {
				AsientoVirtual asiento = new AsientoVirtual(rs.getString(2), rs.getString(3));
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

	public static List<Pelicula> selectPeliculasEstablecimiento(int cuit, Date fecha) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String statementSql = "select * from peliculas where id in (select distinct id_pelicula from funciones where cuit_establecimiento = ? and fecha_hora >= ? order by fecha_hora)";
			PreparedStatement query = coneccion.prepareStatement(statementSql);
			query.setInt(1, cuit);
			query.setDate(2, fecha);
			ResultSet result = query.executeQuery();
			
			List<Pelicula> peliculas = new ArrayList<>();
			while (result.next()) {
				String nombre = result.getString(2);
				String director = result.getString(3);
				String genero = result.getString(4);
				int duracion = result.getInt(5);
				String idioma = result.getString(6);
				boolean subtitilos = result.getBoolean(7);
				double calificacion = result.getDouble(8);
				String observaciones = result.getString(9);
				Pelicula pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitilos, calificacion,
						observaciones);
				pelicula.setId(result.getInt(1));
				peliculas.add(pelicula);
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return peliculas;

	}

	public static List<Funcion> selectFuncionesSala(Sala sala, int cuit) throws SQLException {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			String consultaSql = "select fecha_hora,nombre_sala,valor,id,id_pelicula from funciones where  cuit_establecimiento = ? and nombre_sala = ?";		
			PreparedStatement query = coneccion.prepareStatement(consultaSql);
			query.setInt(1, cuit);
			query.setString(2, sala.getNombre());
			ResultSet rs = query.executeQuery();
			List<Funcion> funciones = new ArrayList<>();
			while (rs.next()) {
				Funcion funcion = new Funcion(rs.getDate(1), new Pelicula(null, null, null, 0, null, null, 0, null), new Sala(rs.getString(2)), rs.getDouble(3));
				funcion.setId(rs.getInt(4));
				funcion.getPelicula().setId(rs.getInt(5));
				funciones.add(funcion);
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return funciones;
	}

}
