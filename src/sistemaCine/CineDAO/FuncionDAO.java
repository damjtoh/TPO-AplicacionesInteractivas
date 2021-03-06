package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		PreparedStatement query = coneccion.prepareStatement("insert into funciones values (?,?,?,?,?,?,1)");
		funcion.setId(getLastId(coneccion));
		query.setInt(1, funcion.getId());
		query.setInt(2, funcion.getPelicula().getId());
		query.setString(3, funcion.getSala().getNombre());
		query.setInt(4, cuitEstablecimiento);
		query.setTimestamp(5, new Timestamp(funcion.getFechaYHora().getTime()));
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
		query.setTimestamp(4, new Timestamp(funcion.getFechaYHora().getTime()));
		query.setDouble(5, funcion.getValor());
		query.setInt(6, funcion.getId());
		query.execute();
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}

	public static void deleteFuncion(Funcion funcion) throws SQLException {

		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = coneccion.prepareStatement("update funciones set activa = 0 where id = ?");
		query.setInt(1, funcion.getId());
		query.execute();

		PoolConnection.getPoolConnection().realeaseConnection(coneccion);

	}

	public static List<Funcion> selectFunciones(Pelicula pelicula, Date fecha, Integer cuitEstablecimiento)
			throws SQLException {

		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		String consultaSql = "select fecha_hora,nombre_sala,valor,id from funciones where  cuit_establecimiento = ? and id_pelicula = ? and fecha_hora >= ? and activa = 1 order by fecha_hora ASC";
//			if (cuitEstablecimiento !=null) {
//				consultaSql = consultaSql.replace("cuit"," cuit_establecimiento = ? and ");
//			}else {
//				consultaSql = consultaSql.replace("cuit","");
//			}
//			
		PreparedStatement query = coneccion.prepareStatement(consultaSql);

		query.setInt(1, cuitEstablecimiento);
		query.setInt(2, pelicula.getId());
		query.setTimestamp(3, new Timestamp(fecha.getTime()));

		ResultSet rs = query.executeQuery();
		List<Funcion> funciones = new ArrayList<>();
		if (rs.next()) {
			Funcion funcion = new Funcion(new Date(rs.getTimestamp(1).getTime()), pelicula, new Sala(rs.getString(2)),
					rs.getDouble(3));
			funcion.setId(rs.getInt(4));
			funciones.add(funcion);
		}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return funciones;
	}

	public static Funcion getById(int id) throws SQLException {
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		String consultaSql = "select * where  id = ?";

		PreparedStatement query = coneccion.prepareStatement(consultaSql);
		query.setInt(1, id);

		Funcion funcion = null;

		ResultSet resFuncion = query.executeQuery();

		if (resFuncion.next()) {
			funcion = new Funcion(PeliculaDAO.getById(resFuncion.getInt("id_pelicula")),
					SalaDAO.selectSala(resFuncion.getString("nombre_sala"), resFuncion.getInt("cuit_establecimiento")),
					new Date(resFuncion.getTimestamp("fecha_hora").getTime()));
			funcion.setId(resFuncion.getInt(4));
		}

		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return funcion;
	}

	public static Map<FilaColumna, AsientoVirtual> selectAsientos(Funcion funcion) throws SQLException {
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
	}

	public static List<Pelicula> selectPeliculasEstablecimiento(int cuit, Date fecha) throws SQLException {
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		String statementSql = "select * from peliculas where id in (select distinct id_pelicula from funciones where cuit_establecimiento = ? and fecha_hora >= ? and activa = 1 order by fecha_hora)";
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
		String consultaSql = "select fecha_hora,nombre_sala,valor,id,id_pelicula from funciones where  cuit_establecimiento = ? and nombre_sala = ? and activa = 1";
		PreparedStatement query = coneccion.prepareStatement(consultaSql);
		query.setInt(1, cuit);
		query.setString(2, sala.getNombre());
		ResultSet rs = query.executeQuery();
		List<Funcion> funciones = new ArrayList<>();
		while (rs.next()) {
			Funcion funcion = new Funcion(new Date(rs.getTimestamp(1).getTime()),
					new Pelicula(null, null, null, 0, null, null, 0, null), new Sala(rs.getString(2)), rs.getDouble(3));
			funcion.setId(rs.getInt(4));
			funcion.getPelicula().setId(rs.getInt(5));
			funciones.add(funcion);
		}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return funciones;
	}

	public static List<Funcion> selectFuncionesPelicula(Pelicula pelicula) throws SQLException {
		Connection coneccion = PoolConnection.getPoolConnection().getConnection();
		String consultaSql = "select fecha_hora,nombre_sala,valor,id,id_pelicula from funciones where id_pelicula = ? and activa = 1";
		PreparedStatement query = coneccion.prepareStatement(consultaSql);
		query.setInt(1, pelicula.getId());
		ResultSet rs = query.executeQuery();
		List<Funcion> funciones = new ArrayList<>();
		while (rs.next()) {
			Funcion funcion = new Funcion(new Date(rs.getTimestamp(1).getTime()),
					new Pelicula(null, null, null, 0, null, null, 0, null), new Sala(rs.getString(2)), rs.getDouble(3));
			funcion.setId(rs.getInt(4));
			funcion.getPelicula().setId(rs.getInt(5));
			funciones.add(funcion);
		}
		PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		return funciones;
	}

}
