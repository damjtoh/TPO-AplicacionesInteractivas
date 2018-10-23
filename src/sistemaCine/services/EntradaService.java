package sistemaCine.services;

import java.util.Map;

import sistemaCine.CineDAO.EntradasDAO;
import sistemaCine.clases.AsinentoVirtual;
import sistemaCine.clases.Entrada;
import sistemaCine.clases.Funcion;
import sistemaCine.utils.FilaColumna;

public class EntradaService {
	public static void crearEntrada(Entrada entrada) {
		EntradasDAO.insertEntrada(entrada);
	}

	public static void crearMapaEntradas(Funcion funcion) {
		EntradasDAO.insertMapaEntradas(funcion);
	}

	public static void updateEntrada(Entrada entrada) {
		EntradasDAO.updateEntrada(entrada);
	}

	public static void eliminarEntrada(Entrada entrada) {
		EntradasDAO.deleteEntrada(entrada);
	}
	public static void eliminarEntradas(int idFuncion) {
		EntradasDAO.deleteEntradas(idFuncion);
	}

	public static Entrada getEntrada(Entrada entrada) {
		return EntradasDAO.selectEntrada(entrada);
	}

	public static Map<FilaColumna, AsinentoVirtual> getMapaAsientosFuncion(Funcion funcion) {
		return EntradasDAO.selectMapaEntradas(funcion);
	}

	public static void eliminarEntradasFuncion(Funcion funcion) {
		EntradasDAO.deleteEntradas(funcion.getId());	
	}

}
