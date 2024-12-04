package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/*import logica.enumerado.*;
import logica.datatype.*;
import logica.controlador.*;*/
import logica.*;

public class AgregarRutaAPaqueteTest {
	    
	
	
	@Test
	public void testListarPaquetesNoCompradosVacio() {
		ControladorVuelos CtrlVuelo = new ControladorVuelos();
		Set<String> paquetesNoComprados = CtrlVuelo.listarPaquetesNoComprados();
		assertTrue(paquetesNoComprados.isEmpty());
	}
	
	@Test
	public void testListarPaquetesNoVacio() {
		ControladorVuelos CtrlVuelo = new ControladorVuelos();
		LocalDate fecha1 = LocalDate.of(2024, 4, 6);
		CtrlVuelo.crearPaquete("Paq", "es un paquete", 2, 30f, fecha1, "");
		
		LocalDate fecha2 = LocalDate.of(2025, 4, 6);
		CtrlVuelo.crearPaquete("PaqPrueba", "es otro paquete", 5, 80.9f, fecha2, "");

		Set<String> paquetesNoComprados = CtrlVuelo.listarPaquetesNoComprados();
		
		assertTrue(paquetesNoComprados.contains("Paq"));
		assertTrue(paquetesNoComprados.contains("PaqPrueba"));
		assertEquals(paquetesNoComprados.size(), 2);
		
		ControladorUsuarios cu = new ControladorUsuarios();
		cu.crearCliente("cli1", "nomCli1", "cli1@gmail.com", "Ci1", LocalDate.parse("2001-09-11"), "Estados Unidos", Documento.CI, "12345678", "", "");
		
		Boolean ret = CtrlVuelo.comprarPaquete("Paq", "cli1", fecha1, 0, fecha2);
		assertTrue(ret);
		
		Set<String> paquetesNoComprados2 = CtrlVuelo.listarPaquetesNoComprados();
		assertEquals(1,paquetesNoComprados2.size());
		
		cu.borrarClientes();
		CtrlVuelo.borrarPaquetes();
	}
	
	@Test
	public void testListarAerolineasVacio() {
		ControladorUsuarios CtrlUsuarios = new ControladorUsuarios();
		Set<String> aerolineas = CtrlUsuarios.listarAerolineas();
		assertTrue(aerolineas.isEmpty());
	}
	
	@Test
	public void testListarAerolineasNoVacio() {
		ControladorUsuarios CtrlUsuarios = new ControladorUsuarios();
		CtrlUsuarios.crearAerolinea("Aero1", "nombreAero1", "aero1@gmail.com", "www.aero1.com", "es una aerolinea", "", "");
		CtrlUsuarios.crearAerolinea("Aero2", "nombreAero2", "aero2@gmail.com", "www.aero2.com", "es otra aerolinea", "", "");
		Set<String> aerolineas = CtrlUsuarios.listarAerolineas();
		Set<String> res = Set.of("Aero1","Aero2");
		assertEquals(aerolineas, res);
		CtrlUsuarios.borrarAerolineas();

	}
	
	@Test
	public void testInfoRutasDeAerolineaSinRutas() {
		ControladorUsuarios CtrlUsuarios = new ControladorUsuarios();
		CtrlUsuarios.crearAerolinea("Aero1", "nombreAero1", "aero1@gmail.com", "www.aero1.com", "es una aerolinea", "", "");
		Set<DataRuta> ret = CtrlUsuarios.infoRutasDeAerolinea("Aero1");
		assertTrue(ret.isEmpty());
		CtrlUsuarios.borrarAerolineas();
	}
	
	@Test
	public void testInfoRutasDeAerolineaNoExistente() {
		
		ControladorUsuarios CtrlUsuarios = new ControladorUsuarios();
		CtrlUsuarios.crearAerolinea("Aero1", "nombreAero1", "aero1@gmail.com", "www.aero1.com", "es una aerolinea", "", "");
		
		ControladorRutas CtrlRutas = new ControladorRutas();
		Set<String> cat = new HashSet<String>();
		LocalDate fecha = LocalDate.of(2024, 4, 6);
		LocalTime hora = LocalTime.of(13, 13);
		CtrlRutas.altaCiudad("CO1", "PO1", "aeropuerto", "desc", "web", fecha);
		CtrlRutas.altaCiudad("CO2", "PO2", "aeropuerto2", "desc2", "web2", fecha);
		CtrlRutas.altaRutaVuelo("Aero1", "Ruta1", "es la ruta1", hora, 56f,80f, 20f, "CO1PO1", "CO2PO2", fecha, cat, "", "",EstadoRuta.Ingresada,"");
		CtrlRutas.altaRutaVuelo("Aero1", "Ruta2", "es la ruta2", hora, 56f,80f, 20f, "CO1PO1", "CO2PO2", fecha, cat, "", "",EstadoRuta.Ingresada,"");
		CtrlRutas.altaRutaVuelo("Aero1", "Ruta3", "es la ruta3", hora, 56f,80f, 20f, "CO1PO1", "CO2PO2", fecha, cat, "", "",EstadoRuta.Ingresada,"");
		
		DataRuta dt1 =new DataRuta("Ruta1","es la ruta1",hora,fecha,56f,80f,20f,EstadoRuta.Ingresada,"","","CO1PO1","CO2PO2", cat,"");
		DataRuta dt2 =new DataRuta("Ruta2","es la ruta2",hora,fecha,56f,80f,20f,EstadoRuta.Ingresada,"","","CO1PO1","CO2PO2", cat,"");
		DataRuta dt3 =new DataRuta("Ruta3","es la ruta3",hora,fecha,56f,80f,20f,EstadoRuta.Ingresada,"","","CO1PO1","CO2PO2", cat,"");
		
		Set<DataRuta> sdt = new HashSet<DataRuta>();
		sdt.add(dt1);
		sdt.add(dt2);
		sdt.add(dt3);
		
		Set<DataRuta> rutasDeAero1 = CtrlUsuarios.infoRutasDeAerolinea("Aero1");
		
		assertEquals(sdt,rutasDeAero1);
		CtrlUsuarios.borrarAerolineas();
		CtrlRutas.borrarRutas();
		
		
	}
	
	@Test
	public void testAgregarRutaRepetidaAPaquete() {
		ControladorUsuarios CtrlUsuarios = new ControladorUsuarios();
		CtrlUsuarios.crearAerolinea("Aero1", "nombreAero1", "aero1@gmail.com", "www.aero1.com", "es una aerolinea","","");
		
		ControladorVuelos CtrlVuelo = new ControladorVuelos();
		LocalDate fecha = LocalDate.of(2024, 4, 6);
		CtrlVuelo.crearPaquete("Paquete1", "es un paquete", 2, 30f, fecha, "");
		
		ControladorRutas CtrlRutas = new ControladorRutas();
		Set<String> cat = new HashSet<String>();
		CtrlRutas.altaRutaVuelo("Aero1", "Ruta1", "es la ruta1", null, 56,80, 20, "CO1", "CD1", fecha, cat, "", "", EstadoRuta.Ingresada,"");
		
		Boolean ret1 = CtrlRutas.agregarRutaPaquete("Ruta1", "Paquete1", 5, Asiento.turista);
		Boolean ret2 = CtrlRutas.agregarRutaPaquete("Ruta1", "Paquete1", 5, Asiento.ejecutivo);
		
		assertTrue(ret1);
		assertFalse(ret2);
		CtrlUsuarios.borrarAerolineas();
		CtrlVuelo.borrarPaquetes();
		CtrlRutas.borrarRutas();
		
		
	}
	
	@Test
	public void testAgregarRutaAPaquete() {
		ControladorUsuarios CtrlUsuarios = new ControladorUsuarios();
		CtrlUsuarios.crearAerolinea("Aero1", "nombreAero1", "aero1@gmail.com", "www.aero1.com", "es una aerolinea", "", "");
		
		ControladorVuelos CtrlVuelo = new ControladorVuelos();
		LocalDate fecha = LocalDate.of(2024, 4, 6);
		CtrlVuelo.crearPaquete("Paquete1", "es un paquete", 2, 30f, fecha, "");
		
		ControladorRutas CtrlRutas = new ControladorRutas();
		Set<String> cat = new HashSet<String>();
		CtrlRutas.altaRutaVuelo("Aero1", "Ruta1", "es la ruta1", null, 56,80, 20, "CO1", "CD1", fecha, cat, "", "", EstadoRuta.Ingresada,"");
		
		CtrlRutas.altaRutaVuelo("Aero1", "Ruta2", "es la ruta2", null, 56,80, 20, "CO1", "CD1", fecha, cat, "", "", EstadoRuta.Ingresada,"");
		
		Boolean ret1 = CtrlRutas.agregarRutaPaquete("Ruta1", "Paquete1", 5, Asiento.ejecutivo);
		Boolean ret2 = CtrlRutas.agregarRutaPaquete("Ruta2", "Paquete1", 5, Asiento.ejecutivo);
		
		assertTrue(ret1);
		assertTrue(ret2);
		CtrlUsuarios.borrarAerolineas();
		CtrlVuelo.borrarPaquetes();
		CtrlRutas.borrarRutas();
		
	}
	
	
	

}
