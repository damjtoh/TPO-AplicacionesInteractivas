package sistemaCine.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Persistencas.PoolConnection;
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

	public static void updateSala(Sala sala, int cuitEstablecimiento, String salaOldName) {
		SalaDAO.updateSala(sala, cuitEstablecimiento, salaOldName);
		for (Funcion funcion : FuncionServices.getFuncionesSala(sala, cuitEstablecimiento)) {
			funcion.setSala(new Sala(sala.getNombre()));
			FuncionServices.updateFuncion(funcion, cuitEstablecimiento);
		}
	}

	public static void updateAsientosSala(Sala sala, int cuitEstablecimiento, AsinentoFisico asiento) {
		SalaDAO.updateAsientoSala(sala, cuitEstablecimiento, asiento);

	}

	public static void eliminarSala(Sala sala, int cuitEstablecimiento) {
		SalaDAO.deleteSala(sala, cuitEstablecimiento);
		FuncionServices.eliminarFuncionesSala(sala, cuitEstablecimiento);
	}

	public static List<Sala> getSalas(int cuitEstablecimiento) {
		return SalaDAO.selectSalas(cuitEstablecimiento);
	}

	public static Map<FilaColumna, AsinentoFisico> getAsientosSala(Sala sala, int cuitEstablecimiento) {
		return SalaDAO.selectAsientosSala(sala, cuitEstablecimiento);
	}

	public static void eliminarSalasEstablecimiento(Establecimiento establecimiento) {
		for (Sala sala : getSalas(establecimiento.getCuit())) {
			eliminarSala(sala, establecimiento.getCuit());
		}
	}

}
