package sistemaCine.utils;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import sistemaCine.clases.AsientoFisico;
import sistemaCine.clases.Funcion;

public class MapaAsientosUtils {
	public static void setMapaAsientosFuncion(Funcion funcion, JPanel asientosPane, Map<Integer, Map<Integer, AsientoFisico>> asientos) {

		asientosPane.setLayout(new GridLayout(funcion.getSala().getCantFilas(), funcion.getSala().getCantColumnas()));
		asientos = new HashMap<Integer, Map<Integer, AsientoFisico>>();
		for (AsientoFisico asiento : funcion.getSala().getMapaDeAsientos().values()) {
			if (!asientos.containsKey(asiento.getNroFila())) {
				asientos.put(asiento.getNroFila(), new HashMap<Integer, AsientoFisico>());
			}
			asientos.get(asiento.getNroFila()).put(asiento.getNroColumna(), asiento);
		}
		for (int nroFila = 1; nroFila <= funcion.getSala().getCantFilas(); nroFila++) {
			for (int nroColumna = 1; nroColumna <= funcion.getSala().getCantColumnas(); nroColumna++) {
				AsientoFisico asiento = asientos.get(nroFila).get(nroColumna);
				JButton btnAsiento = new JButton(asientos.get(nroFila).get(nroColumna).toString());
				if (!asiento.isUsable() || funcion.getMapaDeAsientos()
						.get(new FilaColumna(asiento.getFila(), asiento.getColumna())).isOcupado()) {
					btnAsiento.setBackground(Color.RED);
				} else {
					btnAsiento.setBackground(Color.GREEN);
				}
				asientosPane.add(btnAsiento);
			}
		}
	}

}
