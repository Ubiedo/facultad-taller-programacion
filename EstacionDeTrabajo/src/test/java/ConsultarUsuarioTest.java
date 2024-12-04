package test.java;
/*
 * Consultar Usuario
 * 
 * Implementacion de un test para el caso de uso: ConsultarUsuario
 *
 * 03/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import logica.*;

@TestMethodOrder(OrderAnnotation.class)
public class ConsultarUsuarioTest {
	private static IControladorUsuarios ICU;
	private static IControladorRutas ICR;
	private static IControladorVuelos ICV;
	Set<DataPasaje> pasajes;
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICU = f.getIUsuarios();
		ICR = f.getIRutas();
		ICV = f.getIVuelos();
		ICU.crearCliente("Spiderman", "Peter", "TheRealSpidey@gmail.com", "Parker", LocalDate.parse("2001-09-11"), "Estados Unidos", Documento.CI, "12345678", "", "");
		ICU.crearAerolinea("SpiderLover", "Aerolineas Aracnidas", "ArañaConAlas@gmail.com", "www.TheSpiderWeb.com", "A pesar de lo que la mayoria asume, no habran arañas en sus vuelos no hay de que preocuparse", "", "");
		ICR.altaCiudad("Montevideo", "Uruguay", "Aeropuerto Internacional de Carrasco", "Uruguay noma", "www.sitioweb.com", LocalDate.of(2024, 9, 4));
		ICR.altaCiudad("Sydney", "Australia", "Aeropuerto Internacional Kingsford Smith", "Canguros", "www.sitioweb2.com", LocalDate.of(2024, 9, 3));
		ICR.ingresarNombre("categoria1");
		ICR.ingresarNombre("categoria2");
		Set<String> categorias = new HashSet<>(Arrays.asList("categoria1","categoria2"));
		ICR.altaRutaVuelo("SpiderLover", "oteNombreDeRuta", "ruta de vuelo de ote", LocalTime.of(8, 30),(float) 100, (float)150, (float)111, "Montevideo"+"Uruguay", "Sydney"+"Australia", LocalDate.of(2024, 10, 7), categorias, "", "", EstadoRuta.Ingresada,"");
		ICV.altaVuelo("oteNombreDeRuta", "vuelo de ote", LocalDate.of(2024, 10, 10), LocalTime.of(8, 30), 50, 20, LocalDate.of(2024,10, 5), null);
		DataPasaje pasaje = new DataPasaje("nombreCliente", "apellidoCliente");
		ICV.crearPaquete("package", "paquetePrueba", 40, 0.4f, LocalDate.of(2025, 10, 10), null);
		ICR.agregarRutaPaquete("oteNombreDeRuta", "package", 1, Asiento.ejecutivo);
		ICV.comprarPaquete("package", "Spiderman", LocalDate.of(2025, 11, 10), 170f, LocalDate.of(2026, 11, 10));
		Set<DataPasaje> pasajes = new HashSet<>();
		pasajes.add(pasaje);
		//DataPasaje pasaje2 = new DataPasaje("nombreCliente2", "apellidoCliente2");
		//Set<DataPasaje> pasajes2 = new HashSet<>();
		//pasajes2.add(pasaje2);
		ICV.reservaDeVuelo("vuelo de ote", "Spiderman", LocalDate.of(2024, 9, 4), Asiento.ejecutivo, 1, 20, pasajes );
		//ICV.reservaDeVuelo("vuelo de ote", "joaquito", LocalDate.of(2024, 9, 4), tipoAsiento.turista, 1, 20, pasajes2 );
	}
	
	@Test
	@Order(1)
	void testListarUsuarios() {
		Set<String> listaResultado = ICU.listarUsuarios();
		// no encuentro porque falla cuando se lo hace con demas archivos pero no solo
		assertNotEquals(listaResultado, null);
		assertFalse(listaResultado.isEmpty());
		assertNotEquals(ICU.listarDatosUsuarioNickname("Spiderman"), null);
		assertNotEquals(ICU.listarDatosUsuarioNickname("SpiderLover"), null);
		assertTrue(ICU.listarClientes().contains("Spiderman"));
		assertTrue(ICU.listarAerolineas().contains("SpiderLover"));
		// las de abajo tienen error, no tiene sentido
		assertTrue(listaResultado.contains("Spiderman"));
		assertTrue(listaResultado.contains("SpiderLover"));
	}
	
	@Test
	@Order(2)
	void testListarDatosUsuarioNickname() {
		Set<String> comparacionC = Set.of("Spiderman", "Peter", "TheRealSpidey@gmail.com", "Parker", "Estados Unidos", "12345678");
		DataCliente c = (DataCliente) ICU.listarDatosUsuarioNickname("Spiderman");
		Set<String> listaResultadoC = Set.of(c.getNickname(),c.getNombre(),c.getEmail(),c.getApellido(),c.getNacionalidad(),c.getNroDocumento());
		assertEquals(listaResultadoC, comparacionC);
		Set<String> comparacionA = Set.of("SpiderLover", "Aerolineas Aracnidas", "ArañaConAlas@gmail.com", "www.TheSpiderWeb.com", "A pesar de lo que la mayoria asume, no habran arañas en sus vuelos no hay de que preocuparse");
		DataAerolinea a = (DataAerolinea) ICU.listarDatosUsuarioNickname("SpiderLover");
		Set<String> listaResultadoA = Set.of(a.getNickname(),a.getNombre(),a.getEmail(),a.getWeb(),a.getDescripcion());
		assertEquals(listaResultadoA, comparacionA);
	}
	@Test
	@Order(3)
	void testListarReservasUsuario() {
		Set<DataClienteVuelo> reservas = ICU.listarReservasUsuario("Spiderman");
		for (DataClienteVuelo reserva:reservas) {
			if(reserva.getVuelo() == "vuelo de ote") {
				assertEquals(reserva.getVuelo(), "vuelo de ote");
				assertEquals(reserva.getCantEquipajeExtra(), 20);
				assertEquals(reserva.getCantPasajes(), 1);
				assertEquals(reserva.getTipoAsiento(), Asiento.ejecutivo);
				assertEquals(LocalDate.parse(reserva.getFechaReserva()), LocalDate.of(2024, 9, 4));
				assertEquals(0, reserva.getCosto(), 1 * 150 + 20 * 111);
				for(DataPasaje pasaje : reserva.getPasajes()) {
					if(pasaje.getNombre()=="nombreCliente") {
						assertEquals(pasaje.getNombre(), "nombreCliente");
					    assertEquals(pasaje.getApellido(), "apellidoCliente");
					}
				}
			}
		}
	    
		
	}
	@Test
	@Order(4)
	void testListarPaquetesUsuario() {
		Set<DataClientePaquete> paquetes = ICU.listarPaquetesUsuario("Spiderman");
		for (DataClientePaquete paquete : paquetes) {
			if(paquete.getCliente() == "Spiderman") {
				assertEquals(paquete.getCliente(), "Spiderman");
				assertEquals(0, paquete.getCosto(), 170f);
				assertEquals(paquete.getFecha(), LocalDate.of(2025, 11, 10).toString());
				assertEquals(paquete.getVencimiento(),LocalDate.of(2026, 11, 10).toString());
				assertEquals(paquete.getPaquete(), "package");
			}
		}
	    
		
	}
	@Test
	@Order(5)
	void testListarRutasDeAerolinea() {
		Set<String> rutas =  ICU.listarRutasDeAerolinea("SpiderLover");
	    assertTrue(rutas.contains("oteNombreDeRuta"));
	}

@Test
@Order(6)
void testListarReservasAsociadasVuelo() {
	Set<String> reservas =  ICV.listarReservasAsociadasVuelo("vuelo de ote");
    assertTrue(reservas.contains("Spiderman"));
}
@Test
@Order(7)
void testGetDataPaquete() {
	DataPaquete paquete =  ICV.getDataPaquete("package");
			assertEquals(paquete.getNombre(), "package");
			assertEquals(0, paquete.getCosto(), 170f);
			assertEquals(LocalDate.parse(paquete.getFechaAlta()), LocalDate.of(2025, 10, 10));
			assertEquals((int) paquete.getDiasValidez(), 40);
			assertEquals(paquete.getDescripcion(), "paquetePrueba");
			assertEquals((int) paquete.getCantRutas(), 1);
	}
@Test
@Order(8)
void testGetDataPaqueteRuta() {
	Set<DataPaqueteRuta> paqueteRuta =  ICV.getDataPaqueteRuta("package");
		for(DataPaqueteRuta PR : paqueteRuta) {
			if (PR.getRuta() == "oteNombreDeRuta") {
				assertEquals(PR.getRuta(), "oteNombreDeRuta");
				assertEquals((int) PR.getCantidad(), 1);
				assertEquals(PR.getAsiento(), Asiento.ejecutivo);
			}
		}
			
	}
@Test
@Order(9)
void testListarDatosRuta() {
	DataRuta ruta =  ICR.listarDatosRuta("oteNombreDeRuta");
				assertEquals(ruta.getNombre(), "oteNombreDeRuta");
				assertEquals(ruta.getDescripcion(), "ruta de vuelo de ote");
				assertEquals(0, ruta.getTuristaBase(), 100);
				assertEquals(0, ruta.getEjecutivoBase(), 150);
				assertEquals(ruta.getFechaAlta(), LocalDate.of(2024, 10, 7).toString());
				assertEquals(ruta.getHora(), LocalTime.of(8, 30).toString());
				assertEquals(0, ruta.getUnidadEquipajeExtra(), 111);
			
	}

@Test
@Order(10)
void testOrigenYDestinoRuta() {
	DataCiudad origen = ICR.getOrigenRuta("oteNombreDeRuta");
	DataCiudad destino = ICR.getDestinoRuta("oteNombreDeRuta");
	assertEquals(origen.getNombre(), "Montevideo");
	assertEquals(origen.getPais(), "Uruguay");
	assertEquals(destino.getNombre(), "Sydney");
	assertEquals(destino.getPais(), "Australia");
			
	}

@Test
@Order(11)
void nombreReservasUsuarioTest() {
	assertEquals("vuelo de ote", ICU.nombresReservasUsuario("Spiderman").toArray()[0]);
	}
@Test
@Order(12)
void listarAerolineasConRutasTest() {
	Set<String> aerolineas = ICU.listarAerolineasConRutas();
	boolean res = false;
	for (String aerolinea : aerolineas) {
		if (aerolinea.equals("SpiderLover")) {res=true;}
	}
	assertTrue(res);

}
@Test
@Order(13)
void verReservaTest() {
	DataClienteVuelo reserva = ICU.verReserva("Spiderman", "vuelo de ote");
	assertEquals(reserva.getTipoAsiento().toString(), "ejecutivo");
	assertEquals(reserva.getCantPasajes(), 1);
	assertEquals(reserva.getCantEquipajeExtra(), 20);
	assertEquals(0, reserva.getCosto(), 2370);
	assertEquals(reserva.getFechaReserva(), "2024-09-04");
	assertEquals(reserva.getVuelo(), "vuelo de ote");
	assertEquals(reserva.getRuta(), "oteNombreDeRuta");
	assertEquals(reserva.getAerolinea(), "Aerolineas Aracnidas");
	assertEquals(reserva.getNickAerolinea(), "SpiderLover");
}
@Test
@Order(13)
void seguirUsuarioTest() {
assertEquals(ICU.seguirUsuario("Spiderman", "SpiderLover"),0);

assertEquals(ICU.seguirUsuario("Spidermann", "SpiderLover"),1);

assertEquals(ICU.seguirUsuario("Spiderman", "SpiderLoverr"),2);

assertEquals(ICU.seguirUsuario("Spiderman", "Spiderman"),3);

assertEquals(ICU.seguirUsuario("Spiderman", "SpiderLover"),4);

assertTrue(ICU.seguidoresDeUsuario("SpiderLover").toArray()[0].equals("Spiderman"));

assertTrue(ICU.seguidosDeUsuario("Spiderman").toArray()[0].equals("SpiderLover"));
assertTrue(ICU.esSeguidor("Spiderman", "SpiderLover"));
assertEquals(ICU.dejarDeSeguirUsuario("Spiderman", "SpiderLover"),0);

assertEquals(ICU.dejarDeSeguirUsuario("Spidermann", "SpiderLover"),1);

assertEquals(ICU.dejarDeSeguirUsuario("Spiderman", "SpiderLoverr"),2);

assertEquals(ICU.dejarDeSeguirUsuario("Spiderman", "Spiderman"),3);

assertEquals(ICU.dejarDeSeguirUsuario("Spiderman", "SpiderLover"),4);
}
//
}



