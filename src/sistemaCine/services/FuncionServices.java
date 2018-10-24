package sistemaCine.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
import sistemaCine.CineDAO.FuncionDAO;
import sistemaCine.clases.AsinentoVirtual;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.Sala;
import sistemaCine.utils.DateUtils;
import sistemaCine.utils.FilaColumna;

public class FuncionServices {

	public static void crearFuncion(Funcion funcion, int cuitEstablecimiento) {
		FuncionDAO.insertFuncion(funcion, cuitEstablecimiento);
	}

	public static void updateFuncion(Funcion funcion, int cuitEstablecimiento) {
		FuncionDAO.updateFuncion(funcion, cuitEstablecimiento);
	}

	public static void eliminarFuncion(Funcion funcion) {
		FuncionDAO.deleteFuncion(funcion);
		EntradaService.eliminarEntradasFuncion(funcion);
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
					.put(funcion.getFechaYHora().toString(), funcion);
		}
		return funcionesMap;
	}

	public static List<Integer> getPeliculasEstablecimientoIDS(int cuit, Date fecha) {
		return FuncionDAO.selectPeliculasEstablecimiento(cuit, fecha);
	}

	public static void eliminarFuncionesSala(Sala sala, int cuit) {
		for (Funcion funcion : getFuncionesSala(sala, cuit)) {
			FuncionServices.eliminarFuncion(funcion);
		}
	}
	public static List<Funcion> getFuncionesSala(Sala sala, int cuit){
		return FuncionDAO.selectFuncionesSala(sala, cuit);
	}
	


}
