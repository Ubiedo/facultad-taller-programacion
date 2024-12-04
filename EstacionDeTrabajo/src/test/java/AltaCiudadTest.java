package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import logica.*;
public class AltaCiudadTest {
	@Test
	void testingresarNorepetido() {
		ControladorRutas Cr = new ControladorRutas();
		LocalDate fecha =  LocalDate.of(2024,8,5);
		Boolean res = Cr.altaCiudad("Canelones","Uruguay", "aeropuerto", "ciudad", "www.canelones.com", fecha);
		assertTrue(res);
	}
	
	@Test
	void testUsuarioRepetido() {
		ControladorRutas Cr = new ControladorRutas();
		LocalDate fecha1 =  LocalDate.of(2024,8,5);
		LocalDate fecha2 =  LocalDate.of(2024,8,5);
		Cr.altaCiudad("Montevideo","Uruguay", "aeropuerto", "ciudad", "www.mdeo.com", fecha1);
		Boolean res2 = Cr.altaCiudad("Montevideo","Uruguay", "otro aeropuerto", "otra ciudad", "www.montevideo.com", fecha2);
		assertFalse(res2);
	}
	
	@Test
	void testListarCiudades() {
		ControladorRutas Cr = new ControladorRutas();
		LocalDate fecha1 =  LocalDate.of(2024,8,5);
		Cr.altaCiudad("Montevideo","Uruguay", "aeropuerto", "ciudad", "www.mdeo.com", fecha1);
		Set<String> comparacionA = Set.of("Montevideo","Uruguay");
		DataCiudad c = Cr.listarDatosCiudad("Montevideo"+"Uruguay");
		Set<String> listaResultado = Set.of(c.getNombre(),c.getPais());
		assertEquals(listaResultado, comparacionA);
	}
}
