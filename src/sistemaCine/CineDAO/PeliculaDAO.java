package sistemaCine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Persistencas.PoolConnection;
import sistemaCine.cinesClases.Pelicula;

public class PeliculaDAO {

	private static PeliculaDAO instancia;

	private PeliculaDAO() {

	}

	public static PeliculaDAO getInstancia() {
		if (instancia == null)
			instancia = new PeliculaDAO();
		return instancia;
	}

	public static void insertPelicula(Object object) {
		try {
			Pelicula p = (Pelicula) object;
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = conection
					.prepareStatement("insert into peliculas values ((select max(id)+1 from peliculas),?,?,?,?,?,?,?,?)");
			query.setString(1, p.getNombre());
			query.setString(2, p.getDirector());
			query.setString(3, p.getGenero());
			query.setInt(4, p.getDuracion());
			query.setString(5, p.getIdioma());
			query.setBoolean(6, p.isSubtitulos());
			query.setDouble(7, p.getClaificacion());
			query.setString(8, p.getObservaciones());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(conection);
		} catch (Exception e) {
			System.out.println();
		}

	}

	public static void updatePelicula(Pelicula pelicula) {
		try {
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			// cambiar por nombre de la base nuestra
			// (A_Interactivas_01.dbo.peliculas)
			PreparedStatement query = conection.prepareStatement(
					"update peliculas set nombre = ?, director = ?,genero = ?,duracion = ?,idioma = ?,subtitilos = ?, calificacion = ?,observaciones = ? where id = ?)");
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
		} catch (Exception e) {
			System.out.println();
		}

	}

	public static void deletePelicula(Pelicula pelicula) {
		try {
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = conection.prepareStatement("delete from peliculas where id = ?");
			query.setInt(1, pelicula.getId());
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(conection);
		} catch (Exception e) {
			System.out.println();
		}

	}

	public static List<Pelicula> selectAllPeliculas() {
		try {

			List<Pelicula> peliculas = new ArrayList<>();
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			// cambiar por nombre de la base nuestra
			// (A_Interactivas_01.dbo.peliculas)
			PreparedStatement query = conection.prepareStatement("Select * from peliculas");
			ResultSet result = query.executeQuery();
			while (result.next()) {
				int id = result.getInt(1);
				String nombre = result.getString(2);
				String director = result.getString(3);
				String genero = result.getString(4);
				int duracion = result.getInt(5);
				String idioma = result.getString(6);
				boolean subtitilos = result.getBoolean(7);
				double calificacion = result.getDouble(8);
				String observaciones = result.getString(9);

				Pelicula dis = new Pelicula(nombre, director, genero, duracion, idioma, subtitilos, calificacion,
						observaciones);
				dis.setId(id);
				peliculas.add(dis);

			}
			PoolConnection.getPoolConnection().realeaseConnection(conection);
			return peliculas;
		} catch (Exception e) {

		}
		return new ArrayList<>();
	}

	public static List<Pelicula> buscarPeliculas(Pelicula pelicula) {
		try {
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
			}else {
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
				boolean subtitilos = result.getBoolean(7);
				double calificacion = result.getDouble(8);
				String observaciones = result.getString(9);
				pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitilos, calificacion,
						observaciones);
				pelicula.setId(id);
				peliculas.add(pelicula);
			}
			PoolConnection.getPoolConnection().realeaseConnection(conection);
			return peliculas;
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

}
