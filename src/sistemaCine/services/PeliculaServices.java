package sistemaCine.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.CineDAO.PeliculaDAO;
import sistemaCine.clases.Pelicula;

public class PeliculaServices {

	public static void crearPelicula(Pelicula pelicula) throws SQLException {
		PeliculaDAO.insertPelicula(pelicula);
	}

	public static void updatePelicula(Pelicula pelicula) throws SQLException {
		PeliculaDAO.updatePelicula(pelicula);
	}

	public static void deletePelicula(Pelicula pelicula) throws SQLException {
		if (FuncionServices.getFunciones(pelicula).isEmpty()) {
			PeliculaDAO.deletePelicula(pelicula);
		}else {
			throw new SQLException("La Pelicula Tiene Funciones Activas");
		}
	}

	public static Map<String, Pelicula> getAllPeliculasMap() throws SQLException {
		Map<String, Pelicula> mapaPeliculas= new HashMap<>();
		for (Pelicula pelicula : PeliculaDAO.selectAllPeliculas()) {
			mapaPeliculas.put(pelicula.toString(), pelicula);
		}
		return mapaPeliculas;
	}

	public static List<Pelicula> buscarPeliculas(Pelicula pelicula) throws SQLException {
		return PeliculaDAO.buscarPeliculas(pelicula);
	}
	public static List<Pelicula> getPeliculas(List<Integer> idsPeliculas) throws SQLException {
		return PeliculaDAO.getPeliculas(idsPeliculas);
	}

	public static Pelicula getPelicula(Integer id) throws SQLException {
		return PeliculaDAO.getById(id);
	}

}
