package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import logica.*;

@TestMethodOrder(OrderAnnotation.class)
public class DatosControladorRutaTest {
    private static IControladorUsuarios ICU;
    private static IControladorRutas ICR;
    @BeforeAll
    static void load() {
        Fabrica f = Fabrica.getInstance();
        ICU = f.getIUsuarios();
        ICR = f.getIRutas();
        ICU.crearAerolinea("aero_test", "Aerolinea de Testeo", "aerotest@gmail.com", "www.aerotest.com.uy", "hola", "","");
        ICR.borrarCiudades();
        ICR.altaCiudad("San Diego","Estados Unidos", "aeropuerto", "ciudad", "www.la.com", LocalDate.of(2024,9,8));
        ICR.altaCiudad("Nueva York","Estados Unidos", "aeropuerto2", "ciudad2", "www.ny.com", LocalDate.of(2024,9,8));
        ICR.borrarCategorias();
        ICR.ingresarNombre("Categoria 1");
        ICR.ingresarNombre("Categoria 2");
    }

    @Test
    @Order(1)
    void testIngresarNoRepetido() {
        Set<String> categorias = new HashSet<>();
        categorias.add("Categoria 1");
        ControladorRutas CRutas = new ControladorRutas();
        ICR.altaRutaVuelo("aero_test", "Ruta de AeroTest590", "hola!", LocalTime.of(12, 30), 
                120, 340, 20, "San DiegoEstados Unidos", "Nueva YorkEstados Unidos", LocalDate.of(2024, 9, 8), 
                categorias, "img", "descCorta", EstadoRuta.Ingresada,"");
        Map<String, ArrayList<String>> categorias_Rutas = ICR.categoriasConRutas();
        assertEquals(categorias_Rutas.get("Categoria 1").get(0),"Ruta de AeroTest590");
    }

    @Test
    @Order(2)
    void listarDatos() {
        Set<String> categorias = ICR.listarCategorias();
        Set<String> categorias2 = new HashSet<String>();
        categorias2.add("Categoria 1");
        categorias2.add("Categoria 2");
        System.out.println(categorias);
        System.out.println(categorias2);
        assertEquals(categorias,categorias2);
        Set<String> ciudades = ICR.listarCiudades();
        Set<String> ciudades2 = new HashSet<String>();
        ciudades2.add("San DiegoEstados Unidos");
        ciudades2.add("Nueva YorkEstados Unidos");
        assertEquals(ciudades,ciudades2);
    }
    
}/*public Set<String> listarCategorias() {
    ManejadorRutas mr = ManejadorRutas.getInstancia();
    return mr.getCategorias();
}

public Set<String> listarCiudades() {
    ManejadorRutas mr = ManejadorRutas.getInstancia();
    return mr.getCiudades();
}*/
