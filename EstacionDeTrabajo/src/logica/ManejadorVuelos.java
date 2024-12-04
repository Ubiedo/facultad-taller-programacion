package logica;
/*
 * ManejadorVuelos
 * 
 * Implementacion de un manejador
 *
 * 02/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ManejadorVuelos {
	private Map<String, Vuelo> vuelos;
	private Map<String, PaqueteDeRutas> paquetes;
	private static ManejadorVuelos instancia = null;
	
	private ManejadorVuelos() {
		vuelos = new HashMap<String, Vuelo>(); // key: nombre
		paquetes = new HashMap<String, PaqueteDeRutas>(); // key: nombre
	}
	public static ManejadorVuelos getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorVuelos();
		}
		return instancia;
	}
	public boolean crearVuelo(String ruta, String nombre, LocalDate fecha, LocalTime duracion, 
			int asientosTurista, int asientosEjecutivo, LocalDate fechaAlta, 
			String imagen) {
		if (vuelos.containsKey(nombre)) {
			return false;
		}
		ManejadorRutas manejadorRutas = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = manejadorRutas.getRuta(ruta);
		Vuelo vuelo = new Vuelo(rutaVuelo, nombre, fecha, duracion, asientosTurista, asientosEjecutivo, fechaAlta, imagen);
		manejadorRutas.agregarVueloARuta(vuelo, ruta);
		vuelos.put(nombre, vuelo);
		return true;
	}
	    
	public Vuelo findVuelo(String vuelo) {
		return vuelos.get(vuelo);
	}
	
	public DataVuelo getDataVuelo(String vuelo) {
		Vuelo newVuelo = vuelos.get(vuelo);
		DataVuelo dataVuelo = new DataVuelo(newVuelo.getNombre(), newVuelo.getFecha(), newVuelo.getDuracion(),
				newVuelo.getFechaAlta(), newVuelo.getAsientosTurista() - newVuelo.getVendidosTurista() ,
				newVuelo.getAsientosEjecutivo() - newVuelo.getVendidosEjecutivo(), newVuelo.getImagen());
		return dataVuelo;
	}
	
	public DataPaquete findDataPaquete(String paquete) {
		PaqueteDeRutas paqueteRutas = paquetes.get(paquete);
		DataPaquete dataPaquete = new DataPaquete(paqueteRutas.getNombre(), paqueteRutas.getDescripcion(), paqueteRutas.getFechaAlta(),
				paqueteRutas.getCantRutas(), paqueteRutas.getDiasValidez(), paqueteRutas.getDescuento(), paqueteRutas.getCosto(), 
				paqueteRutas.getCostoDesc(), paqueteRutas.getImagen());
		return dataPaquete;
	}
	
	public PaqueteDeRutas findPaquete(String nomPaquete) {
		return paquetes.get(nomPaquete);
	}
	
	public Set<String> getReservas(String vuelo) {
		Map<String, ClienteVuelo> reservas = vuelos.get(vuelo).getReservas(); 
		Set<String> setReservas = new HashSet<String>();
		for (ClienteVuelo r : reservas.values()) {
			setReservas.add(r.getCliente().getNickname());
		}
		return setReservas;
	}

	public DatosPR getDatosPaqueteRutas(String paquete) {
		Set<PaqueteRuta> rutas = paquetes.get(paquete).getRutas();
		Map<String, String> nomImgRutas = new HashMap<String, String>();
		int cantT = 0;
		int cantE = 0;
		for (PaqueteRuta pr : rutas) {
			nomImgRutas.put(pr.getRuta().getNombre(), pr.getRuta().getImagen());
			if (pr.getAsiento().name() == "ejecutivo") {
				cantE += pr.getCantidad();
			} else {
				cantT += pr.getCantidad();
			}
		}
		
		return new DatosPR(findDataPaquete(paquete), nomImgRutas, cantE , cantT);
	}

	
	
	public Boolean existsPaquete(String key) {
		return paquetes.containsKey(key);
	}
	
	public void addPaquete(PaqueteDeRutas paqueteRutas) {
		paquetes.put(paqueteRutas.getNombre(), paqueteRutas);

	}
	
	public Set<String> getPaquetesNoComprados() {
		Set<String> nombres = new HashSet<String>();
		for (PaqueteDeRutas pq : paquetes.values()) {
			Set<ClientePaquete> aux = pq.getClientes();
			if (aux.isEmpty()) {
				nombres.add(pq.getNombre());
			}
	
		}
		return nombres;
	}
	public Map<String, PaqueteDeRutas> getPaquetes() {
		return paquetes;
	}
	
	public void borrarPaquetes() {
		Iterator<Map.Entry<String, PaqueteDeRutas>> iterator = paquetes.entrySet().iterator();
        while (iterator.hasNext()) {
        	iterator.next();
            iterator.remove();
        }
	}

}
