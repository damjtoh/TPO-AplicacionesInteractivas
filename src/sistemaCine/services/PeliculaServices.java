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

	public static void updatePelicula(Pelicula pelicula) {
		PeliculaDAO.updatePelicula(pelicula);
	}

	public static void deletePelicula(Pelicula pelicula) {
		PeliculaDAO.deletePelicula(pelicula);
	}

	public static Map<String, Pelicula> getAllPeliculasMap() {
		Map<String, Pelicula> mapaPeliculas= new HashMap<>();
		for (Pelicula pelicula : PeliculaDAO.selectAllPeliculas()) {
			mapaPeliculas.put(pelicula.toString(), pelicula);
		}
		return mapaPeliculas;
	}

	public static List<Pelicula> buscarPeliculas(Pelicula pelicula) {
		return PeliculaDAO.buscarPeliculas(pelicula);
	}
	public static List<Pelicula> getPeliculas(List<Integer> idsPeliculas) {
		return PeliculaDAO.getPeliculas(idsPeliculas);
	}

}
