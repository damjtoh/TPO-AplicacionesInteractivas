package sistemaCine.services;

import java.util.Map;

import sistemaCine.CineDAO.EstablecimientoDAO;
import sistemaCine.cinesClases.Establecimiento;

public class EstablecimientoService {
	public static void crearEstablecimiento(Establecimiento establecimiento) {
		EstablecimientoDAO.insert(establecimiento);
	}

	public static void updateEstablecimiento(Establecimiento establecimiento) {
		EstablecimientoDAO.update(establecimiento);
	}

	public static void eliminarEstablecimiento(Establecimiento establecimiento) {
		EstablecimientoDAO.delete(establecimiento);
	}

	public static Establecimiento getEstablecimieto(int cuit) {
		return EstablecimientoDAO.selectEstablecimieto(cuit);
	}
	public static Map<String,Establecimiento> getAllEstablecimietos() {
		return EstablecimientoDAO.selectAllEstablecimietos();
	}
	
}
