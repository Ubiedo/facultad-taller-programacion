package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import logica.*;
public class AltaUsuarioTest {
	private static IControladorUsuarios ICU;
    
	@BeforeAll
	static void load() {
		Fabrica f = Fabrica.getInstance();
		ICU = f.getIUsuarios();
	}

	@Test
	@Order(1)
	void testAltaUsuarios() {
		//crear usuarios
		int c1 = ICU.crearCliente("juampaotero", "Juan Pablo", "juampaotero@gmail.com", "Otero", LocalDate.of(2003, 1, 13), "Uruguay", Documento.CI, "52801723","","");
		assertEquals(c1, 0);
		int c2 = ICU.crearCliente("sazu4", "Sazú", "sazusito@gmail.com", "Otero", LocalDate.of(2008, 12, 9), "Uruguay", Documento.PASAPORTE, "abc1234","","");
		assertEquals(c2, 0);
		int a1 = ICU.crearAerolinea("pluna_uy", "Pluna", "pluna@gmail.com", "www.pluna.com.uy", "Nos fundimos :(","","");
		assertEquals(a1, 0);
		//mismo nickname
		int c3 = ICU.crearCliente("juampaotero", "Juan Pablo II", "jp.otero@gmail.com", "Paolillo", LocalDate.of(2004, 2, 14), "Argentina", Documento.PASAPORTE, "xyz321","","");
		assertEquals(c3, 1);
		int a2 = ICU.crearAerolinea("pluna_uy", "Pluna 2", "pluna2@outlook.com", "www.pluna2.com.uy", "Nos volvimos a fundir :(","","");
		assertEquals(a2, 1);
		//mismo mail
		int c4 = ICU.crearCliente("jp.ote", "Juan Pablo III", "juampaotero@gmail.com", "Ote", LocalDate.of(2005, 3, 15), "Brasil", Documento.CI, "52801734","","");
		assertEquals(c4, 1);
		int a3 = ICU.crearAerolinea("pluna_ar", "Pluna 3", "pluna@gmail.com", "www.pluna3.com.ar", "hola","","");
		assertEquals(a3, 1);
	}
	
	@Test
    @Order(2)
    void testLoginUsuarios() {
        //crear usuarios
        int c1 = ICU.crearCliente("juampaotero", "Juan Pablo", "juampaotero@gmail.com", "Otero", LocalDate.of(2003, 1, 13), "Uruguay", Documento.CI, "52801723","contra","img1");
        DataSesion sesion1 = ICU.loginUsuario("juampaotero",ICU.listarDatosUsuarioNickname("juampaotero").getContrasenha(), false);
        int c2 = ICU.crearCliente("sazu4", "Sazú", "sazusito@gmail.com", "Otero", LocalDate.of(2008, 12, 9), "Uruguay", Documento.PASAPORTE, "abc1234","contra2","img2");
        DataSesion sesion2 = ICU.loginUsuario("sazusito@gmail.com", ICU.listarDatosUsuarioNickname("sazu4").getContrasenha(), true);
        DataSesion DTS1 = new DataSesion(TipoSesion.CLIENTE,"juampaotero", "img1");
        DataSesion DTS2 = new DataSesion(TipoSesion.CLIENTE, "sazu4", "img2");
        int a1 = ICU.crearAerolinea("pluna_uy", "Pluna", "pluna@gmail.com", "www.pluna.com.uy", "Nos fundimos :(","contra3","img3");
        DataSesion sesion3 = ICU.loginUsuario("pluna_uy", ICU.listarDatosUsuarioNickname("pluna_uy").getContrasenha(), false);
        int a2 = ICU.crearAerolinea("pluna_uy2", "Pluna 2", "pluna2@outlook.com", "www.pluna2.com.uy", "Nos volvimos a fundir :(","contra4","img4");
        DataSesion sesion4 = ICU.loginUsuario("pluna2@outlook.com", ICU.listarDatosUsuarioNickname("pluna_uy2").getContrasenha(), true);
        DataSesion DTS3 = new DataSesion(TipoSesion.AEROLINEA,"pluna_uy", "img1");
        DataSesion DTS4 = new DataSesion(TipoSesion.AEROLINEA, "pluna_uy2", "img2");
        System.out.println(sesion1);
        System.out.println(DTS1);
        assertEquals(sesion1.getTipo(), DTS1.getTipo());
        assertEquals(sesion2.getTipo(), DTS2.getTipo());
        assertEquals(sesion3.getTipo(), DTS3.getTipo());
        assertEquals(sesion4.getTipo(), DTS4.getTipo());
	}
}