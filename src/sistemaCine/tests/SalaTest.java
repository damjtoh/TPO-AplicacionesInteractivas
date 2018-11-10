package sistemaCine.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import sistemaCine.clases.AsientoFisico;
import sistemaCine.clases.Funcion;
import sistemaCine.clases.Pelicula;
import sistemaCine.clases.Sala;

public class SalaTest {

	@Test
	public void testAddAsiento() {
		Sala sala = new Sala("sala 1");
		assertTrue(sala.addAsiento(new AsientoFisico("1", "a",0,0)));
		assertFalse(sala.addAsiento(new AsientoFisico("1", "a",0,0)));
		assertTrue(sala.addAsiento(new AsientoFisico("a", "1",0,0)));
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
