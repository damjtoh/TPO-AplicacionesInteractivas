package sistemaCine.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistemaCine.CineDAO.FuncionDAO;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.Sala;
import sistemaCine.utils.DateUtils;

public class FuncionServices {

	public static void crearFuncion(Funcion funcion, int cuitEstablecimiento) throws SQLException {
		FuncionDAO.insertFuncion(funcion, cuitEstablecimiento);
		EntradaService.crearMapaEntradas(funcion);
	}

	public static void updateFuncion(Funcion funcion, int cuitEstablecimiento) throws SQLException {
		FuncionDAO.updateFuncion(funcion, cuitEstablecimiento);
	}

	public static void eliminarFuncion(Funcion funcion) throws SQLException {
		if (EntradaService.getEntradasVendidas(funcion).isEmpty()) {
			FuncionDAO.deleteFuncion(funcion);
			EntradaService.eliminarEntradasFuncion(funcion);
		}
		else {
			throw new SQLException("Hay entradas Vendidas");
		}
	}

	public static Map<String, Map<String, Funcion>>  getFuncionesMap(Pelicula pelicula, Date fecha, Integer cuitEstablecimiento) {
		List<Funcion> funcionesObtenidas = FuncionDAO.selectFunciones(pelicula, fecha, cuitEstablecimiento);
		Map<String, Map<String, Funcion>>  funcionesMap = new HashMap<>();
		for (Funcion funcion : funcionesObtenidas) {
			if (!funcionesMap.containsKey(DateUtils.getDateSinHora(funcion.getFechaYHora()).toString())) {
				funcionesMap.put(DateUtils.getDateSinHora(funcion.getFechaYHora()).toString(),
						new HashMap<String, Funcion>());
			}
			funcionesMap.get(DateUtils.getDateSinHora(funcion.getFechaYHora()).toString())
					.put(DateUtils.getHoraString(funcion.getFechaYHora()), funcion);
		}
		return funcionesMap;
	}

	public static List<Pelicula> getPeliculasEstablecimientoIDS(int cuit, Date fecha) throws SQLException {
		return FuncionDAO.selectPeliculasEstablecimiento(cuit, fecha);
	}

	public static void eliminarFuncionesSala(Sala sala, int cuit) throws SQLException {
		for (Funcion funcion : getFuncionesSala(sala, cuit)) {
			FuncionServices.eliminarFuncion(funcion);
		}
	}
	public static List<Funcion> getFuncionesSala(Sala sala, int cuit) throws SQLException{
		List<Funcion> funciones = FuncionDAO.selectFuncionesSala(sala, cuit);
		for (Funcion funcion : funciones) {
			funcion.setPelicula(PeliculaServices.getPelicula(funcion.getPelicula().getId()));
		}
		return funciones;
	}

	public static List<Funcion> getFunciones(Pelicula pelicula) throws SQLException {
		return FuncionDAO.selectFuncionesPelicula(pelicula);
	}
	


}

