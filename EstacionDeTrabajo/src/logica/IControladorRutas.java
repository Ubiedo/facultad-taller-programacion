package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import logica.enumerado.*;
//import logica.datatype.*;

public interface IControladorRutas {

	public abstract Boolean altaCiudad(String nombre, String pais, String aeropuerto, String descripcion, String sweb, LocalDate fechaAlta);
	public abstract DataRuta listarDatosRuta(String ruta);
	public abstract Boolean ingresarNombre(String nombre);
	public abstract int altaRutaVuelo(String aerolinea, String nombre, String descripcion, LocalTime hora, 
			float costoTurista, float costoEjecutivo, float costoEquipaje, String ciudadOrigen,
			String ciudadDestino, LocalDate fechaAlta, Set<String> categorias, String imagen, String descCorta, EstadoRuta estado, String video);
	public abstract DataCiudad listarDatosCiudad(String nombre);
	public abstract Set<String> vuelosDeRuta(String nombre);
	public abstract Boolean agregarRutaPaquete(String nombreRuta, String nombrePaquete, Integer cantidad, Asiento tipoAsiento);
	public abstract DataCiudad getOrigenRuta(String ruta);
	public abstract DataCiudad getDestinoRuta(String ruta);
	public abstract void confirmarRuta(String nombreRuta);
	public abstract void rechazarRuta(String nombreRuta);
	public abstract void finalizarRuta(String nombreRuta);
	//lectura de datos
	public abstract void leerDatosPaquetesRutas();
	public abstract void leerDatosRutasVuelo();
	public abstract void leerDatosCiudades();
	public abstract void leerDatosCategorias();
	public abstract Set<String> listarCategorias();
	public abstract Set<String> listarCiudades();
	public abstract Set<String> listarRutas();
	public abstract Set<String> listarRutasConfirmadas();
	public abstract Boolean existeRuta(String nombreRuta);
	public abstract String nicknameAerolineaDeRuta(String nombreRuta);
	public abstract String nombreAerolineaDeRuta(String nombreRuta);
	public abstract String nombreCiudadOrigenRuta(String nombreRuta);
	public abstract String nombreCiudadDestinoRuta(String nombreRuta);
	public abstract Map<String, ArrayList<String>> categoriasConRutas();
	public abstract void borrarCategorias();
	public abstract void borrarCiudades();
	// busqueda
	public abstract List<DataImagenNombreType> getRutasConMatch(String buscado);
	// visita ruta
	public abstract void visitarRuta(String ruta);
	public abstract List<DataRutaVisitas> top5();

}
