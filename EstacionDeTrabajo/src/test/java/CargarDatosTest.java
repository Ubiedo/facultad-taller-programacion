package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import logica.*;

@TestMethodOrder(OrderAnnotation.class)
public class CargarDatosTest {
	private static IControladorVuelos ICV;
	private static IControladorRutas ICR;
	private static IControladorUsuarios ICU;
	
	@BeforeAll
	static void ingresarDatos() {
		Fabrica f = Fabrica.getInstance();
		ICV = f.getIVuelos();
		ICR = f.getIRutas();
		ICU = f.getIUsuarios();
		ICR.leerDatosCategorias();
		ICR.leerDatosCiudades();
		ICU.leerDatosUsuarios();
		ICR.leerDatosRutasVuelo();
		ICV.leerDatosVuelo();
		ICV.leerDatosPaquetes();
		ICR.leerDatosPaquetesRutas();
		ICV.leerDatosReservas();
		ICV.leerDatosCompraPaquetes();
		ICU.leerDatosCheckIn();
		ICU.leerDatosSeguidos();
	}
	
	@Test
	@Order(1)
	void testCategoria(){
		Boolean cat = ICR.ingresarNombre("Europa");
		//Europa ya estaba en los datos cargados
		assertFalse(cat);
	}
	
	@Test
	@Order(2)
	void testCiudad(){
		LocalDate fecha = LocalDate.parse("1/4/2024", DateTimeFormatter.ofPattern("d/M/yyyy")); 
		Boolean ciud = ICR.altaCiudad("Montevideo", "Uruguay", "Carrasco", "Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.", "https://montevideo.gub.uy", fecha);
		//ciudad ya cargada
		assertFalse(ciud);
	}
	
	@Test
	@Order(3)
	void testUsuario() {
		Boolean c = ICU.esCliente("anarod87");
		assertTrue(c);
		Boolean a = ICU.esCliente("aerolineas");
		assertFalse(a);
		int aereo = ICU.crearAerolinea("aireuropa", "Air Europa", "reservas@aireuropa.com.uy", "", "", "", "");
		//mismo nickname y email que una aerolinea ingresada
		assertEquals(1,aereo);
	}
	
	@Test
	@Order(4)
	void testRutas() {
		int ruta = ICR.altaRutaVuelo(null, "UX46", null, null, 0, 0, 0, null, null, null, null, "", "",EstadoRuta.Ingresada,"");
		//con el mismo nombre que una ya ingresada
		assertEquals(1,ruta);
	}
	
	@Test
	@Order(5)
	void testVuelos() {
		boolean vuelo = ICV.altaVuelo(null, "IB6012377", null, null, 0, 0, null, null);
		assertFalse(vuelo);
	}

	@Test
	@Order(6)
	void testPaquetes() {
		boolean paquete = ICV.crearPaquete("Madrid ida y vuelta", null, null, null, null, null);
		// nombre existente
		assertFalse(paquete);
	}
	
	@Test
	@Order(7)
	void TestRutaPaquetes(){
		Set<String> p = ICV.mostrarInfoPaquete("Madrid ida y vuelta").getNombImgRutas().keySet();
		boolean pertenece = p.contains("IB6012");
		assertTrue(pertenece);
	}
	
	
	@Test
	@Order(8)
	void testReservas(){
		Set<String> res = ICV.listarReservasAsociadasVuelo("IB6012272");
		boolean Vacio = res.isEmpty();
		assertFalse(Vacio);
	}
	
	@Test
	@Order(9)
	void testCompra() {
		boolean compra = ICV.comprarPaquete("Cruzar el Charco", "sofi89", null, 0, null);		
		assertFalse(compra);
	}
	
	@Test
	@Order(10)
	void testCheckIn() {
		DataClienteVuelo reserva = ICU.verReserva("hernacar", "IB6012272");
		assertEquals(reserva.getAerolinea(), "Iberia");
		assertEquals(reserva.getCantEquipajeExtra(), 2);
		assertEquals(reserva.getCantPasajes(), 1);
		assertEquals(0,reserva.getCosto(), 470);
		assertEquals(reserva.getFechaReserva(), "2024-08-28");
		assertEquals(reserva.getVuelo(),"IB6012272");
		assertEquals(reserva.getRuta(),"IB6012");
		assertEquals(reserva.getNickAerolinea(),"iberia");
		
	}
	
	@Test
	@Order(11)
	void testSeguidos() {
		Set<String> seguidores = ICU.seguidoresDeUsuario("jackwil");
		assertTrue(seguidores.contains("csexto"));
	}
}
