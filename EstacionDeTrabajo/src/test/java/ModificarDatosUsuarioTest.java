package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import logica.*;
@TestMethodOrder(OrderAnnotation.class)
class ModificarDatosUsuarioTest {
	private static IControladorVuelos ICV;
	private static IControladorUsuarios ICU;
	private static IControladorRutas ICR;
    
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICV = f.getIVuelos();
		ICU = f.getIUsuarios();
		ICR = f.getIRutas();
		ControladorUsuarios CU = new ControladorUsuarios();
		CU.borrarAerolineas();
		CU.borrarClientes();
		ICU.crearCliente("FefoManya", "Federico", "fefo@cap.uy", "Gonzalez", LocalDate.of(2003, 4, 16), "Uruguayo", Documento.CI, "5312045", "", "");
		ICU.crearAerolinea("MiAerolinea", "My Airlines", "air@fly.com", "www.airfly.com", "breve intro...", "", "");
	}

	@Test
	@Order(1)
	void testModificarDatosClienteConMismosDatos() {
		Set<String> usuario = ICU.listarUsuarios();
		for(String nickname : usuario) {
			if(ICU.esCliente(nickname)) {
				DataCliente dc = (DataCliente) ICU.listarDatosUsuarioNickname(nickname);
				ICU.setDatosCliente(nickname, dc.getNombre(), dc.getApellido(), LocalDate.parse(dc.getFechaNac()), dc.getNacionalidad(), "CI", dc.getNroDocumento());
				DataCliente newdc = (DataCliente) ICU.listarDatosUsuarioNickname(nickname);
				assertEquals(dc.getNombre(), newdc.getNombre());
				assertEquals(dc.getApellido(), newdc.getApellido());
				assertEquals(dc.getFechaNac(), newdc.getFechaNac());
				assertEquals(dc.getNacionalidad(), newdc.getNacionalidad());
				assertEquals(dc.getTipoDocumento(), newdc.getTipoDocumento());
				assertEquals(dc.getNroDocumento(), newdc.getNroDocumento());
			}
		}
	}
	
	@Test
	@Order(2)
	void testModificarDatosCliente() {
		Set<String> usuario = ICU.listarUsuarios();
		for(String nickname : usuario) {
			if(ICU.esCliente(nickname)) {
				DataCliente dc = (DataCliente) ICU.listarDatosUsuarioNickname(nickname);
				ICU.setDatosCliente(nickname, dc.getNombre(), "Lopez", LocalDate.parse(dc.getFechaNac()), "Peruano", "Pasaporte", dc.getNroDocumento());
				DataCliente newdc = (DataCliente) ICU.listarDatosUsuarioNickname(nickname);
				assertEquals(dc.getNombre(), newdc.getNombre());
				assertNotEquals(dc.getApellido(), newdc.getApellido());
				assertEquals(dc.getFechaNac(), newdc.getFechaNac());
				assertNotEquals(dc.getNacionalidad(), newdc.getNacionalidad());
				assertNotEquals(dc.getTipoDocumento(), newdc.getTipoDocumento());
				assertEquals(dc.getNroDocumento(), newdc.getNroDocumento());
			}
		}
	}
	
	@Test
	@Order(3)
	void testModificarDatosAerolinea() {
		Set<String> usuario = ICU.listarUsuarios();
		for(String nickname : usuario) {
			if(!ICU.esCliente(nickname)) {
				DataAerolinea da = (DataAerolinea) ICU.listarDatosUsuarioNickname(nickname);
				ICU.setDatosAerolinea(nickname, "Alah Airlines", "www.new.com", da.getDescripcion());
				DataAerolinea newda = (DataAerolinea) ICU.listarDatosUsuarioNickname(nickname);
				assertNotEquals(da.getNombre(), newda.getNombre());
				assertNotEquals(da.getWeb(), newda.getWeb());
				assertEquals(da.getDescripcion(), newda.getDescripcion());
			}
		}
	}
}
