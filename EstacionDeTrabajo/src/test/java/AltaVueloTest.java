package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import logica.*;

@TestMethodOrder(OrderAnnotation.class)
class AltaVueloTest {
	private static IControladorVuelos ICV;
	private static IControladorUsuarios ICU;
	private static IControladorRutas ICR;
    
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICV = f.getIVuelos();
		ICU = f.getIUsuarios();
		ICR = f.getIRutas();
		ICU.crearAerolinea("SpideyAir", "Spider Airlines", "spiderline@air.mcu", "www.spideyairlines.mcu", "Aerolinea con gran responsabilidad", null, null);
		Set<String> grado = new HashSet<String>();
		ICR.altaRutaVuelo("SpideyAir", "CruzarWeb", "Moverte por la red", LocalTime.of(5, 2), 250, 600, 100, "Boston", "Manhatan", LocalDate.now(), grado, "", "", EstadoRuta.Ingresada,"");
	}
	
	@Test
	@Order(1)
	void testCreacion() {
		assertTrue(ICV.altaVuelo("CruzarWeb", "SpideyFlight", LocalDate.of(2024, 11, 20), LocalTime.of(15, 5), 10, 1, LocalDate.of(2024, 9, 7), null));
	}
	
	@Test
	@Order(2)
	void testRegistradoEnAerolinea() {
		Set<String> vuelos = ICR.vuelosDeRuta("CruzarWeb");
		assertTrue(vuelos.contains("SpideyFlight"));
	}
	
	@Test
	@Order(3)
	void testNombreEnUso() {
		assertFalse(ICV.altaVuelo("CruzarWeb", "SpideyFlight", LocalDate.of(2024, 11, 20), LocalTime.of(15, 5), 10, 1, LocalDate.of(2024, 9, 7), null));
	}
	
	@Test
	@Order(4)
	void testListarVuelos() {
		DataVuelo dv = ICV.listarDatosVuelo("SpideyFlight");
		assertEquals("SpideyFlight",dv.getNombre());
		assertEquals(LocalDate.of(2024, 11, 20),LocalDate.parse(dv.getFecha()));
		assertEquals(LocalTime.of(15, 5),LocalTime.parse(dv.getDuracion()));
		assertEquals(10,(int) dv.getAsientosTurista());
		assertEquals(1,(int) dv.getAsientosEjecutivo());
		assertEquals(LocalDate.of(2024, 9, 7),LocalDate.parse(dv.getFechaAlta()));
	}
}
