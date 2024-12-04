package logica;

/*
 * Vuelo
 * 
 * Implementacion de la clase vuelo
 *
 * 02/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

//import logica.enumerado.*;


public class Vuelo{

	private RutaDeVuelo ruta;
	private Map<String, ClienteVuelo> reservas;
	private String nombre;
	private LocalDate fecha;
	private LocalTime duracion;
	private int asientosTurista;
	private int vendidosTurista;
	private int asientosEjecutivo;
	private int vendidosEjecutivo;
	private LocalDate fechaAlta;
	private String imagen;
	private int asientosEjecConCheckIn;
	private int asientosTurConCheckIn;
	
	public Vuelo(RutaDeVuelo ruta, String nombre, LocalDate fecha, LocalTime duracion, 
			int asientosTurista, int asientosEjecutivo, LocalDate fechaAlta, 
			String imagen) {
		this.ruta = ruta;
		this.reservas = new HashMap<String, ClienteVuelo>();
		this.nombre = nombre;
		this.fecha = fecha;
		this.duracion = duracion;
		this.asientosTurista = asientosTurista;
		this.vendidosTurista = 0;
		this.asientosEjecutivo = asientosEjecutivo;
		this.vendidosEjecutivo = 0;
		this.fechaAlta = fechaAlta;
		this.imagen = imagen;
		this.asientosEjecConCheckIn = 0;
		this.asientosTurConCheckIn = 0;
	}

	public RutaDeVuelo getRuta() {
		return ruta;
	}
	
	public Map<String, ClienteVuelo> getReservas() {
		return reservas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public LocalTime getDuracion() {
		return duracion;
	}
	
	public int getAsientosTurista() {
		return asientosTurista;
	}
	
	public int getVendidosTurista() {
		return vendidosTurista;
	}
	
	public int getAsientosEjecutivo() {
		return asientosEjecutivo;
	}
	
	public int getVendidosEjecutivo() {
		return vendidosEjecutivo;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public int getAsientosTurConCheckIn() {
	    return asientosTurConCheckIn;
	}
	
	public int getAsientosEjecConCheckIn() {
	    return asientosEjecConCheckIn;
	}
	
	public void setRuta(RutaDeVuelo ruta) {
		this.ruta = ruta;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	} 
	
	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}
	
	public void setAsientosTurista(int acientosTurista) {
		this.asientosTurista = acientosTurista;
	}
	
	public void setAsientosEjecutivo(int acientosEjecutivo) {
		this.asientosEjecutivo = acientosEjecutivo;
	}
	
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public void setAsientosTurConCheckIn(int asientosTurConCheckIn) {
	    this.asientosTurConCheckIn = asientosTurConCheckIn;
	}
	
	public void setAsientosEjecConCheckIn(int asientosEjecConCheckIn) {
	    this.asientosEjecConCheckIn = asientosEjecConCheckIn;
	}
	
	public Boolean asientosDisponibles(Asiento tipoAsiento, int cantPasajes) {
		if (tipoAsiento == Asiento.turista) {
			return vendidosTurista + cantPasajes <= asientosTurista;
		} else {
			return vendidosEjecutivo + cantPasajes <= asientosEjecutivo;
		}
	}
	
	public void reservarAsientos(Asiento tipoAsiento, int cantPasajes) {
		if (tipoAsiento == Asiento.turista) {
			vendidosTurista += cantPasajes;
		} else {
			vendidosEjecutivo += cantPasajes;
		}
	}
	
	public float calcularCosto(Asiento tipoAsiento, int cantPasajes, int cantEquipajeExtra) {
		if (tipoAsiento == Asiento.turista) {
			return (cantPasajes * ruta.getTuristaBase()) + 
					(cantEquipajeExtra * ruta.getUnidadEquipajeExtra());
		} else {
			return cantPasajes * ruta.getEjecutivoBase() + 
					cantEquipajeExtra * ruta.getUnidadEquipajeExtra();
		}
	}
	
	public void addReserva(ClienteVuelo clienteVuelo) {
		String key = clienteVuelo.getCliente().getNickname();
		reservas.put(key, clienteVuelo);
	}
}
	