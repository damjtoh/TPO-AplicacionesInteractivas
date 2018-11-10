package sistemaCine.services;

import java.sql.SQLException;
import java.util.Map;

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.clases.Establecimiento;

public class EstablecimientoService {
	public static void crearEstablecimiento(Establecimiento establecimiento) throws SQLException {
		EstablecimientoDAO.insert(establecimiento);
	}

	public static void updateEstablecimiento(Establecimiento establecimiento) throws SQLException {
		EstablecimientoDAO.update(establecimiento);
	}

	public static void eliminarEstablecimiento(Establecimiento establecimiento) throws SQLException {
		EstablecimientoDAO.delete(establecimiento);
		SalaServices.eliminarSalasEstablecimiento(establecimiento);
	}

	public static Establecimiento getEstablecimieto(int cuit) throws SQLException {
		return EstablecimientoDAO.selectEstablecimieto(cuit);
	}
	public static Map<String,Establecimiento> getAllEstablecimietosMap() throws SQLException {
		return EstablecimientoDAO.selectAllEstablecimietos();
	}
	
}
