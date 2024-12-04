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
import logica.Fabrica;
import logica.IControladorRutas;
import logica.IControladorUsuarios;
import logica.IControladorVuelos;
import logica.Asiento;
import logica.DataCheckIn;
import logica.DataClienteVuelo;
import logica.Documento;
import logica.EstadoRuta;
import logica.DataPasaje;
import logica.DataReserva;

public class RealizarCheckInTest {
	private static IControladorUsuarios controladorUsuarios;
	private static IControladorVuelos controladorVuelos;
	private static IControladorRutas controladorRutas;
	private static LocalDate fecha;
	private static Set<DataPasaje> pasajes;
	private static LocalTime hora;
	private static LocalTime hora2;
	@BeforeAll
	static void ingresarDatos() {
		Fabrica f = Fabrica.getInstance();
		controladorUsuarios = f.getIUsuarios();
		controladorVuelos = f.getIVuelos();
		controladorRutas = f.getIRutas();
		fecha = LocalDate.now();
		controladorRutas.altaCiudad("Montevideo", "Uruguay", "llll", "llll", "llll", fecha);
		controladorRutas.altaCiudad("Buenos Aires", "Argentina", "llll", "llll", "llll", fecha);
		controladorUsuarios.crearAerolinea("aereo", "aereo1", "llll", "llll", "llll", "llll", "llll");
		controladorUsuarios.crearCliente("cliente1000", "cliente1", "lllll", "llll", fecha, "lll", Documento.CI, "lll", "lll", "lll");
		hora = LocalTime.of(15, 30, 00);
		hora2 = LocalTime.of(00, 20, 00);
		Set<String> categorias = new HashSet<>();
		//con hora
		controladorRutas.altaRutaVuelo("aereo", "ruta", "llll", hora, 0, 0, 0, "Montevideo", "Buenos Aires", fecha, categorias, "llll", "llll", EstadoRuta.Ingresada,"");
		controladorVuelos.altaVuelo("ruta", "vuelo", fecha, hora, 3, 3, fecha, "llll");
		//con hora2
		controladorRutas.altaRutaVuelo("aereo", "ruta2", "llll", hora2, 0, 0, 0, "Montevideo", "Buenos Aires", fecha, categorias, "llll", "llll", EstadoRuta.Ingresada,"");
		controladorVuelos.altaVuelo("ruta2", "vuelo2", fecha, hora2, 3, 3, fecha, "llll");
		//sin check-in
		controladorVuelos.altaVuelo("ruta2", "vuelo3", fecha, hora2, 3, 3, fecha, "llll");
		pasajes = new HashSet<>();
		DataPasaje data = new DataPasaje("Manuel", "Martinez"); 
		DataPasaje data2 = new DataPasaje("Valentina", "Beron");
		pasajes.add(data);
		pasajes.add(data2);
		controladorVuelos.reservaDeVuelo("vuelo", "cliente1000", fecha, Asiento.turista, 2, 0, pasajes);
		controladorVuelos.reservaDeVuelo("vuelo2", "cliente1000", fecha, Asiento.ejecutivo, 2, 0, pasajes);
		controladorVuelos.reservaDeVuelo("vuelo3", "cliente1000", fecha, Asiento.turista, 2, 0, pasajes);
	}
	
	@Test
	@Order(1)
	void test1() {
	    Boolean res = controladorUsuarios.realizarCheckIn("cliente1000", "vuelo", LocalDate.now());
	    //devuelve true porque no habia un chechIn realizado
	    assertTrue(res);
	    
	}
	
	@Test
	@Order(2)
	void test2() {
	    Boolean res = controladorUsuarios.realizarCheckIn("cliente1000", "vuelo", LocalDate.now());
	    //devuelve false porque ya se realizó en el primer test
	    assertFalse(res);
	}
	
	@Test
	@Order(3)
	void test3() {
	    DataCheckIn res = controladorUsuarios.consultaCheckIn("cliente1000", "vuelo");
	    Set<DataPasaje> pasajesRes = res.getPasajesCheckIn();
	    assertEquals(pasajes, pasajesRes);
	    LocalTime horaEmbarque = hora.minusMinutes(30);
	    assertEquals(res.gethoraEmbarque().toString(),horaEmbarque.toString());
	    assertTrue(hora.isAfter(horaEmbarque));
	    // como horaEmbarque es anterior a hora
	    assertEquals(res.getfechaEmbarque().toString(),fecha.toString());
	}
	
	@Test
	@Order(4)
	void test4() {
		Boolean res1 = controladorUsuarios.realizarCheckIn("cliente1000", "vuelo2", LocalDate.now());
		assertTrue(res1);
	    DataCheckIn res = controladorUsuarios.consultaCheckIn("cliente1000", "vuelo2");
	    Set<DataPasaje> pasajesRes = res.getPasajesCheckIn();
	    assertEquals(pasajes, pasajesRes);
	    LocalTime horaEmbarque = hora2.minusMinutes(30);
	    assertEquals(res.gethoraEmbarque().toString(),horaEmbarque.toString());
	    assertTrue(horaEmbarque.isAfter(hora2));
	    // como horaEmbarque es posterior a hora
	    assertEquals(res.getfechaEmbarque().toString(),fecha.minusDays(1).toString());
	}
	
	@Test
	@Order(5) 
	void test5() {
		DataClienteVuelo datosReserva = controladorUsuarios.verReserva("cliente1000", "vuelo3");
		assertEquals(datosReserva.getAerolinea(),"aereo1");
		assertEquals(datosReserva.getCantEquipajeExtra(),0);
		assertEquals(datosReserva.getCantPasajes(),2);
		assertEquals(datosReserva.getCosto(),0);
		assertEquals(datosReserva.getFechaReserva(),LocalDate.now().toString());
		assertEquals(datosReserva.getNickAerolinea(),"aereo");
		assertEquals(datosReserva.getPasajes(),pasajes);
		assertEquals(datosReserva.getRuta(),"ruta2");
		assertEquals(datosReserva.getTipoAsiento(),Asiento.turista);
		assertEquals(datosReserva.getVuelo(),"vuelo3");
	}
	
	@Test
	@Order(6) 
	void test6() {
		Set<DataReserva> reservas = controladorUsuarios.reservasSinCheckIn("cliente1000");
		assertEquals(reservas.size(),1);
	}
	
	@Test
	@Order(7) 
	void test7() {
		Set<DataReserva> reservas = controladorUsuarios.reservasConCheckIn("cliente1000");
		assertEquals(reservas.size(),2);
	}
	
	@Test
	@Order(8) 
	void test8() {
		DataCheckIn checkIn = controladorUsuarios.consultaCheckIn("cliente1000", "vuelo2");
		assertEquals(checkIn.getfechaEmbarque(),fecha.minusDays(1).toString());
		assertEquals(checkIn.gethoraEmbarque(),hora2.minusMinutes(30).toString());
		assertEquals(checkIn.getPasajesCheckIn(), pasajes);
		assertEquals(checkIn.getFechaCheckIn(), LocalDate.now().toString());
	}
}
