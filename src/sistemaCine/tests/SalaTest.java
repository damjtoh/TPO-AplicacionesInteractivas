package sistemaCine.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import sistemaCine.cinesClases.AsinentoFisico;
import sistemaCine.cinesClases.Funcion;
import sistemaCine.cinesClases.Pelicula;
import sistemaCine.cinesClases.Sala;

public class SalaTest {

	@Test
	public void testAddAsiento() {
		Sala sala = new Sala("sala 1");
		assertTrue(sala.addAsiento(new AsinentoFisico("1", "a")));
		assertFalse(sala.addAsiento(new AsinentoFisico("1", "a")));
		assertTrue(sala.addAsiento(new AsinentoFisico("a", "1")));
	}

	@Test
	public void testAddFuncion() {
		Sala sala = new Sala("sala 1");
		for (int i = 1; i < 10; i++) {
			Calendar date = Calendar.getInstance();
			date.set(i, i, i);
			//sala.addFuncion(
				//	new Funcion((Date) date.getTime() , new Pelicula(null, null, null, i, null, true, i, null), sala));
		}
		for (int i = 0; i < sala.getFunciones().size() - 1; i++) {
			assertTrue(sala.getFunciones().get(i).getFechaYHora().before(sala.getFunciones().get(i+1).getFechaYHora()));
		}
	}

}
