package sistemaCine.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sistemaCine.cinesClases.AsinentoFisico;
import sistemaCine.cinesClases.AsinentoVirtual;
import sistemaCine.cinesClases.Funcion;
import sistemaCine.cinesClases.Sala;
import sistemaCine.utils.FilaColumna;
import sistemaCine.view.SeleccionarAsientosView;

public class SeleccionarAsientosViewTest {

	@Test
	public void testGetInstance() {
		Funcion funcion = new Funcion(new Date(new java.util.Date().getTime()) , null, new Sala("The first"), 3);
		Map<FilaColumna, AsinentoVirtual> mapaDeAsientosVirtuales = new HashMap<FilaColumna, AsinentoVirtual>();
		Map<FilaColumna, AsinentoFisico> mapaDeAsientosFisicos = new HashMap<>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 10; j++) {
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
		SeleccionarAsientosView.getInstance(funcion).setLocationRelativeTo(null);
		SeleccionarAsientosView.getInstance(funcion).setVisible(true);

	}

}
