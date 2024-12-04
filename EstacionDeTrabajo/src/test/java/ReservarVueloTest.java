package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logica.*;
public class ReservarVueloTest {
	private static IControladorUsuarios ICU;
	private static IControladorRutas ICR;
	private static IControladorVuelos ICV;
    
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICU = f.getIUsuarios();
		ICR = f.getIRutas();
		ICV = f.getIVuelos();
		ICU.crearCliente("cliente_test_ote", "Juan Pablo", "clientetest@gmail.com", "Otero", LocalDate.of(2003, 1, 13), "Uruguay", Documento.CI, "52801723", "", "");
		ICU.crearCliente("cliente_test_ote2", "Juan Pablo II", "clientetest2@gmail.com", "Otero2", LocalDate.of(2004, 1, 13), "Argentina", Documento.CI, "52801734", "", "");
		ICU.crearAerolinea("aero_test_ote", "Aerolinea de Testeo", "aerotest@gmail.com", "www.aerotest.com.uy", "hola",  "",  "");
		ICR.altaCiudad("San Diego","Estados Unidos", "aeropuerto", "ciudad", "www.la.com", LocalDate.of(2024,9,8));
		ICR.altaCiudad("Nueva York","Estados Unidos", "aeropuerto2", "ciudad2", "www.ny.com", LocalDate.of(2024,9,8));
		ICR.ingresarNombre("Categoria 1");
		ICR.ingresarNombre("Categoria 2");
		Set<String> categorias = new HashSet<>();
		categorias.add("Categoria 1");
		categorias.add("Categoria 2");
		ICR.altaRutaVuelo("aero_test_ote", "Ruta de AeroTest", "hola!", LocalTime.of(12, 30), 120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), categorias, "", "",EstadoRuta.Ingresada,"");
		ICV.altaVuelo("Ruta de AeroTest", "vuelo de testeo", LocalDate.of(2024, 9, 9), LocalTime.of(2,30), 100, 2, LocalDate.of(2024,9,8),  "");
	}

	@Test
	@Order(1)
	void testAltaRuta() {
		//reservar vuelo: asientos suficientes, no existe reserva previa
		Set<DataPasaje> pasajes = new HashSet<>();
		DataPasaje dp1 = new DataPasaje("Sazu", "Otero");
		pasajes.add(dp1);
		DataPasaje dp2 = new DataPasaje("Miles", "Morales");
		pasajes.add(dp2);
		int rv1 = ICV.reservaDeVuelo("vuelo de testeo", "cliente_test_ote", LocalDate.of(2024,9,8), Asiento.turista, 3, 2, pasajes);
		assertEquals(rv1, 0);
		//no hay asientos suficientes
		int rv2 = ICV.reservaDeVuelo("vuelo de testeo", "cliente_test_ote2", LocalDate.of(2024,9,8), Asiento.ejecutivo, 3, 2, pasajes);
		assertEquals(rv2, 2);
		//reserva existente por cliente
		int rv3 = ICV.reservaDeVuelo("vuelo de testeo", "cliente_test_ote", LocalDate.of(2024,9,8), Asiento.turista, 3, 2, pasajes);
		assertEquals(rv3, 1);
	}
}
