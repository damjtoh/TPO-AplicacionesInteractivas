package sistemaCine.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Persistencas.PoolConnection;
import sistemaCine.CineDAO.PeliculaDAO;
import sistemaCine.clases.Pelicula;

public class PeliculaServices {

	public static void crearPelicula(Pelicula pelicula) {
		PeliculaDAO.insertPelicula(pelicula);
	}

	public static void updatePelicula(Pelicula pelicula) {
		PeliculaDAO.updatePelicula(pelicula);
	}

	public static void deletePelicula(Pelicula pelicula) {
		PeliculaDAO.deletePelicula(pelicula);
	}

	public static List<Pelicula> getAllPeliculas() {
		return PeliculaDAO.selectAllPeliculas();
	}

	public static List<Pelicula> buscarPeliculas(Pelicula pelicula) {
		return PeliculaDAO.buscarPeliculas(pelicula);
	}
	public static List<Pelicula> getPeliculas(List<Integer> idsPeliculas) {
		return PeliculaDAO.getPeliculas(idsPeliculas);
	}

}
