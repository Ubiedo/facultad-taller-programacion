package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import logica.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class ConsultarPaqueteDeRutasTest {
	private static IControladorVuelos ICV;
	private static IControladorRutas ICR;
	private static LocalDate fecha;
	@BeforeAll
	static void ingresarDatos() {
		Fabrica f = Fabrica.getInstance();
		ICV = f.getIVuelos();
		ICR = f.getIRutas();
		fecha = LocalDate.of(2024, 9, 7);
		ICV.crearPaquete("Vuelos a España", "Aproveche la oportunidad", 20, 25f, fecha,"");
		ICV.crearPaquete("Vuelos a Estados Unidos", "Oportunidad única", 30, 20f, fecha,"");
	}
	@Test
	@Order(1)
	void testListar() {
		Set<String> lista = ICV.listasPaquetesRegistrados();
        assertTrue(lista.contains("Vuelos a España"));
        assertTrue(lista.contains("Vuelos a Estados Unidos"));
	}
	
	@Test
	@Order(2)
	void testMostrarInfo() {
		ParPaqueteRutas datos = ICV.mostrarInfoPaquete("Vuelos a España");
		assertEquals(datos.getPaquete().getNombre(),"Vuelos a España");
		assertEquals(datos.getPaquete().getDescripcion(),"Aproveche la oportunidad");
		assertEquals(datos.getPaquete().getDiasValidez(),Integer.valueOf(20));
		assertEquals(LocalDate.parse(datos.getPaquete().getFechaAlta()),fecha);
		assertEquals(datos.getPaquete().getDescuento(),Float.valueOf(25f));
		assertEquals(datos.getPaquete().getCosto(),Float.valueOf(0f));
		assertEquals(datos.getCantRutas(), Integer.valueOf(0));
		assertTrue(datos.getNombImgRutas().isEmpty());
	}
}
