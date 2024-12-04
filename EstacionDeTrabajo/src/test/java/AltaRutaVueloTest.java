package test.java;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logica.*;

public class AltaRutaVueloTest {
	private static IControladorUsuarios ICU;
	private static IControladorRutas ICR;
    
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICU = f.getIUsuarios();
		ICR = f.getIRutas();
		ICU.crearAerolinea("aero_test", "Aerolinea de Testeo", "aerotest@gmail.com", "www.aerotest.com.uy", "hola", "","");
		ICR.altaCiudad("San Diego","Estados Unidos", "aeropuerto", "ciudad", "www.la.com", LocalDate.of(2024,9,8));
		ICR.altaCiudad("Nueva York","Estados Unidos", "aeropuerto2", "ciudad2", "www.ny.com", LocalDate.of(2024,9,8));
		ICR.ingresarNombre("Categoria 1");
		ICR.ingresarNombre("Categoria 2");
	}

	@Test
	@Order(1)
	void testAltaRuta() {
		//crear rutas
		//sin categorias
		Set<String> categorias = new HashSet<>();
		int rv1 = ICR.altaRutaVuelo("aero_test", "Ruta de AeroTest", "hola!", LocalTime.of(12, 30), 
				120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
				categorias, "", "", EstadoRuta.Ingresada,"");
		assertEquals(rv1, 0);
		//con categorias
		Set<String> categorias2 = new HashSet<>();
		categorias2.add("Categoria 1");
		categorias2.add("Categoria 2");
		int rv2 = ICR.altaRutaVuelo("aero_test", "Ruta de AeroTest II", "hola!", LocalTime.of(12, 30), 
				120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
				categorias2, "", "", EstadoRuta.Ingresada,"");
		assertEquals(rv2, 0);
		//nombre ya utilizado
		Set<String> categorias3 = new HashSet<>();
		categorias3.add("Categoria 2");
		int rv3 = ICR.altaRutaVuelo("aero_test", "Ruta de AeroTest", "hola!", LocalTime.of(12, 30), 
				120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
				categorias3, "", "", EstadoRuta.Ingresada,"");
		assertEquals(rv3, 1);
		assertTrue(ICR.existeRuta("Ruta de AeroTest"));
		assertEquals(ICR.nicknameAerolineaDeRuta("Ruta de AeroTest"), "aero_test");
		assertEquals(ICR.nombreAerolineaDeRuta("Ruta de AeroTest"), "Aerolinea de Testeo");
		assertEquals(ICR.nombreCiudadOrigenRuta("Ruta de AeroTest"), "San Diego, Estados Unidos");
		assertEquals(ICR.nombreCiudadDestinoRuta("Ruta de AeroTest"), "Nueva York, Estados Unidos");
	}
	@Test
	@Order(2)
	void testEstados() {
		Set<String> categorias2 = new HashSet<>();
		categorias2.add("Categoria 1");
		categorias2.add("Categoria 2");
		ICR.altaRutaVuelo("aero_test", "RutaConfirmar", "hola!", LocalTime.of(12, 30), 
				120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
				categorias2, "", "", EstadoRuta.Ingresada,"");
		ICR.altaRutaVuelo("aero_test", "RutaRechazar", "hola!", LocalTime.of(12, 30), 
				120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
				categorias2, "", "", EstadoRuta.Ingresada,"");
		ICR.altaRutaVuelo("aero_test", "RutaFinalizar", "hola!", LocalTime.of(12, 30), 
				120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
				categorias2, "", "", EstadoRuta.Ingresada,"");
		ICR.finalizarRuta("RutaFinalizar");
		ICR.rechazarRuta("RutaRechazar");
		ICR.confirmarRuta("RutaConfirmar");
		assertEquals(ICR.listarDatosRuta("RutaConfirmar").getEstado(),logica.EstadoRuta.Confirmada);
		assertEquals(ICR.listarDatosRuta("RutaRechazar").getEstado(),logica.EstadoRuta.Rechazada);
		assertEquals(ICR.listarDatosRuta("RutaFinalizar").getEstado(),logica.EstadoRuta.Finalizada);
		
		assertTrue(ICR.listarRutasConfirmadas().contains("RutaConfirmar"));
	}
}
