package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Persistencas.PoolConnection;
import sistemaCine.clases.Pelicula;

public class PeliculaDAO {

	private PeliculaDAO() {

	}

	public static void insertPelicula(Object object) throws SQLException {

		Pelicula p = (Pelicula) object;
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = conection.prepareStatement("insert into peliculas values (?,?,?,?,?,?,?,?,?,1)");
		query.setInt(1, getLastId(conection));
		query.setString(2, p.getNombre());
		query.setString(3, p.getDirector());
		query.setString(4, p.getGenero());
		query.setInt(5, p.getDuracion());
		query.setString(6, p.getIdioma());
		query.setBoolean(7, p.isSubtitulos());
		query.setDouble(8, p.getClaificacion());
		query.setString(9, p.getObservaciones());
		query.execute();
		PoolConnection.getPoolConnection().realeaseConnection(conection);

	}

	private static int getLastId(Connection conection) throws SQLException {
		PreparedStatement query = conection.prepareStatement("(select max(id)+1 from peliculas)");
		ResultSet rs = query.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	public static void updatePelicula(Pelicula pelicula) throws SQLException {
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		// cambiar por nombre de la base nuestra
		// (A_Interactivas_01.dbo.peliculas)
		PreparedStatement query = conection.prepareStatement(
				"update peliculas set nombre = ?, director = ?,genero = ?,duracion = ?,idioma = ?,subtitulos = ?, calificacion = ?,observaciones = ? where id = ?");
		query.setString(1, pelicula.getNombre());
		query.setString(2, pelicula.getDirector());
		query.setString(3, pelicula.getGenero());
		query.setInt(4, pelicula.getDuracion());
		query.setString(5, pelicula.getIdioma());
		query.setBoolean(6, pelicula.isSubtitulos());
		query.setDouble(7, pelicula.getClaificacion());
		query.setString(8, pelicula.getObservaciones());
		query.setInt(9, pelicula.getId());
		query.execute();
		PoolConnection.getPoolConnection().realeaseConnection(conection);

	}

	public static void deletePelicula(Pelicula pelicula) throws SQLException {
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = conection.prepareStatement("update peliculas set activa = 0 where id = ?");
		query.setInt(1, pelicula.getId());
		query.execute();
		PoolConnection.getPoolConnection().realeaseConnection(conection);

	}

	public static List<Pelicula> selectAllPeliculas() throws SQLException {

		List<Pelicula> peliculas = new ArrayList<>();
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		// cambiar por nombre de la base nuestra
		// (A_Interactivas_01.dbo.peliculas)
		PreparedStatement query = conection.prepareStatement("Select * from peliculas where activa = 1");
		ResultSet result = query.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			String nombre = result.getString(2);
			String director = result.getString(3);
			String genero = result.getString(4);
			int duracion = result.getInt(5);
			String idioma = result.getString(6);
			boolean subtitulos = result.getBoolean(7);
			double calificacion = result.getDouble(8);
			String observaciones = result.getString(9);

			Pelicula dis = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion,
					observaciones);
			dis.setId(id);
			peliculas.add(dis);

		}
		PoolConnection.getPoolConnection().realeaseConnection(conection);
		return peliculas;
	}

	public static List<Pelicula> buscarPeliculas(Pelicula pelicula) throws SQLException {
		boolean cambiado = false;
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query;
		String stringConsulta = "select * from peliculas where ";
		if (pelicula.getId() == null) {
			if (pelicula.getGenero() != null) {
				stringConsulta = stringConsulta.concat(" genero = ? ");
				cambiado = true;
			}
			if (pelicula.getNombre() != null) {
				if (cambiado) {
					stringConsulta = stringConsulta.concat(" and nombre = ? ");
				} else {
					stringConsulta = stringConsulta.concat(" nombre = ? ");
					cambiado = true;
				}
			}
			if (pelicula.getIdioma() != null) {
				if (cambiado) {
					stringConsulta = stringConsulta.concat(" and idioma = ? ");
				} else {
					stringConsulta = stringConsulta.concat(" idioma = ? ");
					cambiado = true;
				}
			}
			if (pelicula.isSubtitulos() != null) {
				if (cambiado) {
					stringConsulta = stringConsulta.concat(" and subtitulos = ? ");
				} else {
					stringConsulta = stringConsulta.concat(" subtitulos = ? ");
				}
			}
			stringConsulta = stringConsulta.concat(" and activa = 1 ");
			query = conection.prepareStatement(stringConsulta);
			int pos = 1;
			if (pelicula.getGenero() != null) {
				query.setString(pos, pelicula.getGenero());
				pos++;
			}
			if (pelicula.getNombre() != null) {
				query.setString(pos, pelicula.getNombre());
				pos++;
			}
			if (pelicula.getIdioma() != null) {
				query.setString(pos, pelicula.getIdioma());
				pos++;
			}
			if (pelicula.isSubtitulos() != null) {
				query.setBoolean(pos, pelicula.isSubtitulos());
			}
		} else {
			stringConsulta = stringConsulta.concat(" id = ? ");
			query = conection.prepareStatement(stringConsulta);
			query.setInt(1, pelicula.getId());
		}

		List<Pelicula> peliculas = new ArrayList<>();
		ResultSet result = query.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			String nombre = result.getString(2);
			String director = result.getString(3);
			String genero = result.getString(4);
			int duracion = result.getInt(5);
			String idioma = result.getString(6);
			boolean subtitulos = result.getBoolean(7);
			double calificacion = result.getDouble(8);
			String observaciones = result.getString(9);
			pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion,
					observaciones);
			pelicula.setId(id);
			peliculas.add(pelicula);
		}
		PoolConnection.getPoolConnection().realeaseConnection(conection);
		return peliculas;
	}

	public static List<Pelicula> getPeliculas(List<Integer> idsPeliculas) throws SQLException {
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		String stringConsulta = "select * from peliculas where id in (?) and activa = 1";
		PreparedStatement query;
		query = conection.prepareStatement(stringConsulta);
		ResultSet result = query.executeQuery();
		List<Pelicula> peliculas = new ArrayList<>();
		while (result.next()) {
			String nombre = result.getString(2);
			String director = result.getString(3);
			String genero = result.getString(4);
			int duracion = result.getInt(5);
			String idioma = result.getString(6);
			boolean subtitulos = result.getBoolean(7);
			double calificacion = result.getDouble(8);
			String observaciones = result.getString(9);
			Pelicula pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion,
					observaciones);
			pelicula.setId(result.getInt(1));
			peliculas.add(pelicula);
		}
		PoolConnection.getPoolConnection().realeaseConnection(conection);
		return peliculas;
	}

	public static Pelicula getById(int id) throws SQLException {
		Connection conection = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement query = conection.prepareStatement("select * from peliculas where id = ?");

		query.setInt(1, id);

		ResultSet result = query.executeQuery();

		Pelicula pelicula = null;
		if (result.next()) {
			pelicula = new Pelicula(result.getInt("id"), result.getString("nombre"), result.getString("director"),
					result.getString("genero"), result.getInt("duracion"), result.getString("idioma"),
					result.getBoolean("subtitulos"), result.getDouble("calificacion"),
					result.getString("observaciones"));
		}

		PoolConnection.getPoolConnection().realeaseConnection(conection);
		return pelicula;

	}

}