package test.java;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import logica.*;
public class CrearPaqueteTest {
	@Test
	void crearNoRepetido() {
		ControladorVuelos CtrlVuelo = new ControladorVuelos();
		LocalDate fecha = LocalDate.of(2024, 4, 6);
		Boolean res = CtrlVuelo.crearPaquete("Paquete1", "es un paquete", 2, 30f, fecha, null);
		assertTrue(res);
		CtrlVuelo.borrarPaquetes();
	}

	@Test
	void crearRepetido() {
		ControladorVuelos CtrlVuelo = new ControladorVuelos();
		LocalDate fecha1 = LocalDate.of(2024, 4, 6);
		CtrlVuelo.crearPaquete("Paquete2", "es un paquete", 2, 30f, fecha1, null);
	
		LocalDate fecha2 = LocalDate.of(2024, 9, 6);
		Boolean res2 = CtrlVuelo.crearPaquete("Paquete2", "es otro paquete", 6, 3f, fecha2, null);
		assertFalse(res2);
		CtrlVuelo.borrarPaquetes();
	}
}
