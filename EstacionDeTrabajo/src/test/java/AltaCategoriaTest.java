package test.java;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import logica.*;

public class AltaCategoriaTest {
	@Test
	void testIngresarNoRepetido() {
		ControladorRutas CRutas = new ControladorRutas();
	    boolean res = CRutas.ingresarNombre("Categoria");
	    //devuelve true si no existe
	    //assertFalse(res);
	    assertTrue(res);
	}
	
	@Test
	void testIngresarRepetido() {
		ControladorRutas CRutas = new ControladorRutas();
	    CRutas.ingresarNombre("CategoriaExistente");
	    Boolean res = CRutas.ingresarNombre("CategoriaExistente");
	    //devuelve false si ya existe el nombre ingresado
	    assertFalse(res);
	}
	
	@Test
	void testMismoNombreConEspacio() {
		ControladorRutas CRutas = new ControladorRutas();
	    CRutas.ingresarNombre("Categoria2");
	    Boolean res = CRutas.ingresarNombre("Categoria2 ");
	    //devuelve false si ya existe el nombre ingresado
	    assertFalse(res);
	}
	
	@Test
	void testIngresarVacio() {
		ControladorRutas CRutas = new ControladorRutas();
		Boolean res = CRutas.ingresarNombre("");
		assertNull(res);
	}
}
