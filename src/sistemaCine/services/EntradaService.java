package sistemaCine.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import sistemaCine.CineDAO.EntradasDAO;
import sistemaCine.clases.AsientoVirtual;
import sistemaCine.clases.Entrada;
import sistemaCine.clases.Funcion;
import sistemaCine.utils.FilaColumna;

public class EntradaService {
	public static void crearEntrada(Entrada entrada) throws SQLException {
		EntradasDAO.insertEntrada(entrada);
	}

	public static void crearMapaEntradas(Funcion funcion) throws SQLException {
		EntradasDAO.insertMapaEntradas(funcion);
	}

	public static void updateEntrada(Entrada entrada) throws SQLException {
		EntradasDAO.updateEntrada(entrada);
	}

	public static void eliminarEntrada(Entrada entrada) throws SQLException {
		EntradasDAO.deleteEntrada(entrada);
	}
	public static void eliminarEntradas(int idFuncion) throws SQLException {
		EntradasDAO.deleteEntradas(idFuncion);
	}

	public static Entrada getEntrada(Entrada entrada) throws SQLException {
		return EntradasDAO.selectEntrada(entrada);
	}

	public static Map<FilaColumna, AsientoVirtual> getMapaAsientosFuncion(Funcion funcion) throws SQLException {
		return EntradasDAO.selectMapaEntradas(funcion);
	}

	public static void eliminarEntradasFuncion(Funcion funcion) throws SQLException {
		EntradasDAO.deleteEntradas(funcion.getId());	
	}

	public static List<Entrada> getEntradasVendidas(Funcion funcion) throws SQLException {
		return EntradasDAO.getEntradasVendidas(funcion);
	}

}
