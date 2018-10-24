package sistemaCine.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sistemaCine.clases.AsinentoFisico;
import sistemaCine.clases.AsinentoVirtual;
import sistemaCine.clases.Establecimiento;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Sala;
import sistemaCine.utils.FilaColumna;
import sistemaCine.view.cliente.SeleccionarAsientosView;

public class SeleccionarAsientosViewTest {

	@Test
	public void testGetInstance() {
		Funcion funcion = new Funcion(new Date(new java.util.Date().getTime()) , null, new Sala("The first"), 3);
		Map<FilaColumna, AsinentoVirtual> mapaDeAsientosVirtuales = new HashMap<FilaColumna, AsinentoVirtual>();
		Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisicos = new HashMap<>();
		for (int i = 1; i < 15; i++) {
			for (int j = 1; j < 10; j++) {
				AsinentoFisico asinentoFisico = new AsinentoFisico(Integer.toString(i), Integer.toString(j), i, j);
				AsinentoVirtual asinentoVirtual = new AsinentoVirtual(Integer.toString(j), Integer.toString(i));
				mapaDeAsientosVirtuales.put(new FilaColumna(Integer.toString(i), Integer.toString(j)), asinentoVirtual);
				mapaDeAsientosFisicos.put(new FilaColumna(Integer.toString(i), Integer.toString(j)), asinentoFisico);
				asinentoFisico.setUsable(i==j);
				asinentoVirtual.setOcupado(i>j);
			}
		}
		funcion.getSala().setMapaDeAsientos(mapaDeAsientosFisicos);
		funcion.setMapaDeAsientos(mapaDeAsientosVirtuales);
		SeleccionarAsientosView.getInstance(funcion,new Establecimiento(0, null, null, 0)).setLocationRelativeTo(null);
		SeleccionarAsientosView.getInstance(funcion,new Establecimiento(0, null, null, 0)).setVisible(true);

	}

}
