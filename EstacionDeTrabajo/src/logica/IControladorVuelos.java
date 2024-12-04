package logica;
/*
 * IControladorVuelos
 * 
 * Implementacion de una interfaz
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
//import logica.enumerado.*;
//import logica.datatype.*;
//import logica.entidad.*;
public interface IControladorVuelos {
	
	public abstract boolean altaVuelo(String ruta, String nombre, LocalDate fecha, LocalTime duracion, 
			int asientosTurista, int asientosEjecutivo, LocalDate fechaAlta, String imagen);
	public abstract DataVuelo listarDatosVuelo(String vuelo);
	public abstract int reservaDeVuelo(String vuelo, String cliente, LocalDate fechaReserva, Asiento tipoAsiento,
			int cantPasajes, int cantEquipajeExtra, Set<DataPasaje> pasajes);
	public abstract Set<String> listarReservasAsociadasVuelo(String vuelo);
	public abstract ParPaqueteRutas mostrarInfoPaquete(String paquete);
	public abstract boolean crearPaquete(String nombre, String descripcion, Integer validez, Float descuento, LocalDate fechaAlta, String imagen);
	public abstract Set<String> listarPaquetesNoComprados();
	public abstract Set<String> listarPaquetesConRutas();
	public abstract boolean comprarPaquete(String nombre, String nickname, LocalDate fechaCompra, float costo, LocalDate vencimiento);
	public abstract float getCostoPaquete(String nombre);
	public abstract LocalDate calcularVencimientoCompra(LocalDate comprado, String nombre);
	public abstract DataPaquete getDataPaquete(String nombre);
	public abstract Set<DataPaqueteRuta> getDataPaqueteRuta(String paquete);
	public abstract Set<String> listasPaquetesRegistrados();
	public abstract Set<DataPaquete> listarDataPaquetes();
	public abstract boolean yaComproPaquete(String cliente, String nombrepaquete);
	public abstract boolean paqueteContieneRutas(String paquete);
	public abstract boolean paqueteContieneARuta(String paquete, String ruta);
	public abstract int reservaDeVueloPaquete(String vuelo, String cliente, LocalDate fechaReserva, Asiento tipoAsiento,
			int cantPasajes, int cantEquipajeExtra, Set<DataPasaje> pasajes, String paquete);

	//lectura de datos
	public abstract void leerDatosReservas();
	public abstract void leerDatosPaquetes();
	public abstract void leerDatosVuelo();
	public abstract void leerDatosCompraPaquetes();
	// busqueda
	public abstract List<DataImagenNombreType> getPaquetesConMatch(String buscado);
}
