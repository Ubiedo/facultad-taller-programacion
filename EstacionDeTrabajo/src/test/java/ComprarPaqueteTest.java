package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import logica.*;
@TestMethodOrder(OrderAnnotation.class)
class ComprarPaqueteTest {
	private static IControladorVuelos ICV;
	private static IControladorUsuarios ICU;
	private static IControladorRutas ICR;
    
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICV = f.getIVuelos();
		ICU = f.getIUsuarios();
		ICR = f.getIRutas();
		ICU.crearCliente("FefoManya", "Federico", "fefo@cap.uy", "Gonzalez", LocalDate.of(2003, 4, 16), "Uruguayo", Documento.CI, "5312045","","");
		ICU.crearAerolinea("MiAerolinea", "My Airlines", "air@fly.com", "www.airfly.com", "breve intro...","","");
		Set<String> grados = new HashSet<String>();
		ICR.altaRutaVuelo("MiAerolinea", "Ruta Uruguay", "Conoce uruguay", LocalTime.of(3, 1), 250.12f, 350.12f, 15.12f, "Montevideo", "Salto", LocalDate.now(), grados, "", "",EstadoRuta.Ingresada,"");
		ICV.altaVuelo("Ruta Uruguay", "SpideyFlight", LocalDate.of(2024, 11, 20), LocalTime.of(15, 5), 10, 1, LocalDate.of(2024, 9, 7), "");
		ControladorVuelos CV = new ControladorVuelos();
		CV.borrarPaquetes();
		
		ICV.crearPaquete("Vuelos Gratis", "Vuelos con descuentos", 30, 100.0f, LocalDate.now(),"");
		ICR.agregarRutaPaquete("Ruta Uruguay", "Vuelos Gratis", 5, Asiento.turista);
	}
	
	@Test
    @Order(1)
	void testCompraPaquete() {
		assertTrue(ICV.comprarPaquete("Vuelos Gratis", "FefoManya", LocalDate.now(), ICV.getCostoPaquete("Vuelos Gratis"), ICV.calcularVencimientoCompra(LocalDate.now(), "Vuelos Gratis")));
	}
	
	@Test
    @Order(2)
	void testCompraPaqueteAnuladaExistenciaCopmraPrevia() {
		assertFalse(ICV.comprarPaquete("Vuelos Gratis", "FefoManya", LocalDate.now(), ICV.getCostoPaquete("Vuelos Gratis"), ICV.calcularVencimientoCompra(LocalDate.now(), "Vuelos Gratis")));
	}
	
	@Test
	@Order(3)
	void testListarPaquetesConRutas() {
		Set<String> paquetes = ICV.listarPaquetesConRutas();
		assertEquals(paquetes.size(), 1);
	}
	
	@Test
	@Order(4)
	void yaComproPaqueteTest() {
		ICU.crearCliente("FefoManyaa", "Federicoo", "fefo2@cap.uy", "Gonzalezz", LocalDate.of(2003, 4, 16), "Uruguayo", Documento.CI, "5312044","","");
		assertFalse(ICV.yaComproPaquete("FefoManyaa", "Vuasdasdasdos Gratis"));
		assertFalse(ICV.yaComproPaquete("FefoManyaa", "Vuelos Gratis"));
		ICV.comprarPaquete("Vuelos Gratis", "FefoManyaa",LocalDate.now(),ICV.getCostoPaquete("Vuelos Gratis"), ICV.calcularVencimientoCompra(LocalDate.now(), "Vuelos Gratis"));
		assertTrue(ICV.yaComproPaquete("FefoManyaa", "Vuelos Gratis"));
	} 
	
	@Test
	@Order(5)
	void reservaDeVueloPaqueteTest() {
		ICV.crearPaquete("Vuelos Caros", "Vuelos con descuentos", 30, 100.0f, LocalDate.now().plusYears(2),"");
		DataPasaje pasaje = new DataPasaje("Federicoo", "Gonzalezz");
		Set<DataPasaje> pasajes = new HashSet<>();
		pasajes.add(pasaje);
		ICR.altaRutaVuelo("MiAerolinea", "Ruta Uruguay2", "Conoce uruguay", LocalTime.of(3, 1), 250.12f, 350.12f, 15.12f, "Montevideo", "Salto", LocalDate.now(), new HashSet<String>(), "", "",EstadoRuta.Ingresada,"");
		ICV.altaVuelo("Ruta Uruguay2", "SpideyFlight2", LocalDate.of(2024, 11, 20), LocalTime.of(15, 5), 10, 1, LocalDate.of(2024, 9, 7), "");
		ICR.agregarRutaPaquete("Ruta Uruguay2", "Vuelos Caros", 5, Asiento.turista);
		assertEquals(ICV.reservaDeVueloPaquete("SpideyFlight", "FefoManyaa", LocalDate.now(), logica.Asiento.ejecutivo, 1, 2, pasajes, "Vuelos Gratis"),3);
		ICV.comprarPaquete("Vuelos Caros", "FefoManyaa",LocalDate.now(),ICV.getCostoPaquete("Vuelos Gratis"), ICV.calcularVencimientoCompra(LocalDate.now(), "Vuelos Gratis"));
		assertEquals(ICV.reservaDeVueloPaquete("SpideyFlight2", "FefoManyaa", LocalDate.now(), logica.Asiento.turista, 1, 2, pasajes, "Vuelos Caros"),0);
	}
}
