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
import sistemaCine.cinesClases.AsinentoFisico;
import sistemaCine.cinesClases.Sala;
import sistemaCine.utils.FilaColumna;

public class SalaServices {
	public static void crearSala(Sala sala, int cuitEstablecimiento) {
		SalaDAO.insertSala(sala, cuitEstablecimiento);
	}

	public static void updateSala(Sala sala, int cuitEstablecimiento, String salaOldName) {
		SalaDAO.updateSala(sala, cuitEstablecimiento, salaOldName);
	}

	public static void updateAsientosSala(Sala sala, int cuitEstablecimiento, AsinentoFisico asiento) {
		SalaDAO.updateAsientoSala(sala, cuitEstablecimiento, asiento);

	}

	public static List<Sala> getSalas(int cuitEstablecimiento) {
		return SalaDAO.selectSalas(cuitEstablecimiento);
	}

	public static Map<FilaColumna, AsinentoFisico> getAsientosSala(Sala sala, int cuitEstablecimiento) {
		return SalaDAO.selectAsientosSala(sala, cuitEstablecimiento);
	}

}
