package sistemaCine.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistemaCine.CineDAO.SalaDAO;
import sistemaCine.clases.AsinentoFisico;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Sala;
import sistemaCine.utils.FilaColumna;

public class SalaServices {
	public static void crearSala(Sala sala, int cuitEstablecimiento) {
		SalaDAO.insertSala(sala, cuitEstablecimiento);
	}

	public static void updateSala(Sala sala, int cuitEstablecimiento, String salaOldName) throws SQLException {
		SalaDAO.updateSala(sala, cuitEstablecimiento, salaOldName);
		SalaDAO.updateAsientosSala(sala, cuitEstablecimiento);
		for (Funcion funcion : FuncionServices.getFuncionesSala(sala, cuitEstablecimiento)) {
			funcion.setSala(new Sala(sala.getNombre()));
			FuncionServices.updateFuncion(funcion, cuitEstablecimiento);
		}
	}


	public static void eliminarSala(Sala sala, int cuitEstablecimiento) throws SQLException {
		SalaDAO.deleteSala(sala, cuitEstablecimiento);
		FuncionServices.eliminarFuncionesSala(sala, cuitEstablecimiento);
	}

	public static List<Sala> getSalas(int cuitEstablecimiento) {
		return SalaDAO.selectSalas(cuitEstablecimiento);
	}

	public static Map<FilaColumna, AsinentoFisico> getAsientosSala(Sala sala, int cuitEstablecimiento) {
		return SalaDAO.selectAsientosSala(sala, cuitEstablecimiento);
	}

	public static void eliminarSalasEstablecimiento(Establecimiento establecimiento) throws SQLException {
		for (Sala sala : getSalas(establecimiento.getCuit())) {
			eliminarSala(sala, establecimiento.getCuit());
		}
	}

	public static Map<String, Sala> getSalasMap(Integer cuit) {
		Map<String, Sala> mapa = new HashMap<>();
		for (Sala sala : getSalas(cuit)) {
			mapa.put(sala.getNombre(), sala);
		}
		return mapa;
	}

}
