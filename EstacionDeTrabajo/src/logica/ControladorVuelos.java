package logica;

/*
 * ControladorVuelos
 * 
 * Implementacion de un controlador
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ControladorVuelos implements IControladorVuelos {

	public ControladorVuelos() {};
	
	public boolean altaVuelo(String ruta, String nombre, LocalDate fecha, LocalTime duracion, 
			int asientosTurista, int asientosEjecutivo, LocalDate fechaAlta, String imagen) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		return vuelosHandler.crearVuelo(ruta, nombre, fecha, duracion, asientosTurista, asientosEjecutivo, fechaAlta, imagen);
	}

	
	public DataVuelo listarDatosVuelo(String vuelo) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		return vuelosHandler.getDataVuelo(vuelo);
	}


	public Set<String> listarReservasAsociadasVuelo(String vuelo) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		return vuelosHandler.getReservas(vuelo);
	}

	public ParPaqueteRutas mostrarInfoPaquete(String paquete) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		DatosPR datosPaqRutas = vuelosHandler.getDatosPaqueteRutas(paquete);
		return new ParPaqueteRutas(datosPaqRutas.getDataPaquete(), datosPaqRutas.getNomImgRutas(), datosPaqRutas.getCantRutas(), datosPaqRutas.getCantE(), datosPaqRutas.getCantT());
	}
	
	public  boolean crearPaquete(String nombre, String descripcion, Integer validez, Float descuento, LocalDate fechaAlta, String imagen) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		boolean existe = vuelosHandler.existsPaquete(nombre);
		if (!existe) {
			PaqueteDeRutas paqueteRutas = new PaqueteDeRutas(nombre, descripcion, validez, descuento, fechaAlta, imagen);
			vuelosHandler.addPaquete(paqueteRutas);
		}
		return !existe;
	}
	public Set<String> listarPaquetesNoComprados(){
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		return vuelosHandler.getPaquetesNoComprados();
	}
	
	public DataPaquete getDataPaquete(String nombre) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		return vuelosHandler.findDataPaquete(nombre);
	}
	
	public int reservaDeVuelo(String vuelo, String cliente, LocalDate fechaReserva, Asiento tipoAsiento,
			int cantPasajes, int cantEquipajeExtra, Set<DataPasaje> pasajes) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		Vuelo vueloo = vuelosHandler.findVuelo(vuelo);
		if (vueloo.asientosDisponibles(tipoAsiento, cantPasajes)) {
			ManejadorUsuarios userHandler = ManejadorUsuarios.getInstancia();
			Cliente client = userHandler.findClienteNickname(cliente);
			if (!client.existeReserva(vuelo)) {
				//DataPasaje dp = new DataPasaje(cl.getNombre(), cl.getApellido());
				//pasajes.add(dp);
				float costo = vueloo.calcularCosto(tipoAsiento, cantPasajes, cantEquipajeExtra);
				ClienteVuelo clientVuelo = new ClienteVuelo(client, vueloo, pasajes, fechaReserva, tipoAsiento, cantPasajes,
						cantEquipajeExtra, costo);
				vueloo.reservarAsientos(tipoAsiento, cantPasajes);
				client.addReserva(clientVuelo);
				vueloo.addReserva(clientVuelo);
				return 0;
			} else {
				return 1;
			}
		} else {
			return 2;
		}
	}
	public Set<String> listarPaquetesConRutas(){
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		Map<String, PaqueteDeRutas> paquetes = vuelosHandler.getPaquetes();
		Set<String> nombres = new HashSet<String>();
		Set<PaqueteRuta> paqueteRuta;
		for (PaqueteDeRutas iteradorPaqueteRutas : paquetes.values()) {
			paqueteRuta =  iteradorPaqueteRutas.getRutas();
			if (!paqueteRuta.isEmpty()) {
				nombres.add(iteradorPaqueteRutas.getNombre());
			}
		}
		return nombres;
	}
	public boolean comprarPaquete(String nombre, String nickname, LocalDate fechaCompra, float costo, LocalDate vencimiento) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		ManejadorUsuarios userHandler = ManejadorUsuarios.getInstancia();
		Cliente client = userHandler.getCliente(nickname);
		Boolean condition = !client.existeCompraClientePaquete(nickname, nombre);
		
		if (condition) {
			PaqueteDeRutas paqueteRuta = vuelosHandler.findPaquete(nombre);
			ClientePaquete clientPaquete = new ClientePaquete(fechaCompra, vencimiento, costo, client, paqueteRuta);
			paqueteRuta.addClientePaquete(clientPaquete);
			client.addClientePaquete(clientPaquete);
		}
		return condition;
	}
	public float getCostoPaquete(String nombre) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		PaqueteDeRutas paqueteRuta = vuelosHandler.findPaquete(nombre);
		return paqueteRuta.getCostoDesc();
	}
	public LocalDate calcularVencimientoCompra(LocalDate comprado, String nombre) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		PaqueteDeRutas paqueteRuta = vuelosHandler.findPaquete(nombre);
		int validez = paqueteRuta.getDiasValidez();
		return comprado.plusDays(validez);
	}
	public void leerDatosVuelo() {
		CargarDatos datos = CargarDatos.getInstancia();
		List<String[]> vuelos = datos.leerVuelos(); 
		Map<String, String> rutas = datos.getRutas();
	    for (int itarador = 1; itarador < vuelos.size(); itarador++) {
	    	String[] vuelo = vuelos.get(itarador);
	    	if (vuelo.length>1) {
	    		String rutaa = rutas.get(vuelo[2]);
		    	LocalDate fecha = LocalDate.parse(vuelo[4], DateTimeFormatter.ofPattern("d/M/yyyy"));
		    	LocalTime duracion = LocalTime.parse(vuelo[5], DateTimeFormatter.ofPattern("H:mm"));
		    	LocalDate fechaAlta = LocalDate.parse(vuelo[8], DateTimeFormatter.ofPattern("d/M/yyyy"));
		    	int turista = Integer.parseInt(vuelo[6]);
		    	int ejecutivo = Integer.parseInt(vuelo[7]);
		    	String imagen = "";
		    	if (!vuelo[9].equals("(Sin Imagen)") ) {
		    		imagen = vuelo[9];
		    	}
		    	altaVuelo(rutaa, vuelo[3], fecha, duracion, turista, ejecutivo, fechaAlta, imagen);
	    	}
	    }
	}
	
	public void leerDatosPaquetes() {
		CargarDatos datos = CargarDatos.getInstancia();
		List<String[]> paquetes = datos.leerPaquetes(); 
	    for (int iterador = 1; iterador < paquetes.size(); iterador++) {
	    	String[] paquete = paquetes.get(iterador);
	    	if (paquete.length>1) {
	    	LocalDate fechaAlta = LocalDate.parse(paquete[5], DateTimeFormatter.ofPattern("d/M/yyyy"));
	    	int validez = Integer.parseInt(paquete[3]);
	    	float descuento = Integer.parseInt(paquete[4]);
	    	crearPaquete(paquete[1], paquete[2], validez, descuento, fechaAlta, paquete[7]);
	    }}
	}
	
	public void leerDatosReservas() {
		CargarDatos datos = CargarDatos.getInstancia();
		List<String[]> reservas = datos.leerReservas();
		Map<String, String> vuelos = datos.getVuelos();
		Map<String, DataUsuario> usuarios =  datos.getUsuarios();
		Map<String, Set<DataPasaje>> pasajesRef = datos.leerPasajes();
		for (int iterador = 1; iterador < reservas.size(); iterador++) {
			String[] reserva = reservas.get(iterador);
			if (reserva.length > 1) {
			String vuelo = vuelos.get(reserva[3]);
			String cliente = usuarios.get(reserva[4]).getNickname();
			Asiento tipoAsiento = Asiento.valueOf(reserva[5].toLowerCase());
			LocalDate fechaReserva = LocalDate.parse(reserva[8], DateTimeFormatter.ofPattern("d/M/yyyy"));
			int cantPasajes = Integer.parseInt(reserva[6]);
			int cantEquipajeExtra = Integer.parseInt(reserva[7]);
			Set<DataPasaje> pasajes = pasajesRef.get(reserva[0]);
			reservaDeVuelo(vuelo, cliente, fechaReserva, tipoAsiento, cantPasajes, cantEquipajeExtra, pasajes);
		}}
	}
	
	public void leerDatosCompraPaquetes() {
		CargarDatos datos = CargarDatos.getInstancia();
		List<String[]> compras = datos.leerComprasPaquetes();
		Map<String, DataUsuario> usuarios =  datos.getUsuarios();
		Map<String, String> paquetes =  datos.getPaquetes();
		for (int iterador = 1; iterador < compras.size(); iterador++) {
			String[] compra = compras.get(iterador);
			LocalDate fechaCompra = LocalDate.parse(compra[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
			LocalDate validez = LocalDate.parse(compra[4], DateTimeFormatter.ofPattern("d/M/yyyy"));
			float costo = Float.parseFloat(compra[5]);
			String nombrePaquete = paquetes.get(compra[1]);
			String nickCliente = usuarios.get(compra[2]).getNickname();
			comprarPaquete(nombrePaquete, nickCliente, fechaCompra, costo, validez);
		}
	}
	
	public void borrarPaquetes() {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		vuelosHandler.borrarPaquetes();
	}


	public Set<DataPaqueteRuta> getDataPaqueteRuta(String paquete) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		Map<String, PaqueteDeRutas> paquetes = vuelosHandler.getPaquetes();
		PaqueteDeRutas paqueteRutas = paquetes.get(paquete);
		Set<PaqueteRuta> paquetesRutas = paqueteRutas.getRutas();
		Set<DataPaqueteRuta> dataPaqRutas = new HashSet<>();
		for (PaqueteRuta paqueteRuta : paquetesRutas) {
			DataPaqueteRuta paqRuta = new DataPaqueteRuta(paqueteRuta.getCantidad(), paqueteRuta.getAsiento(), paqueteRuta.getRuta().getNombre());
			dataPaqRutas.add(paqRuta);
		}
		return dataPaqRutas;
	}
	public Set<String> listasPaquetesRegistrados(){
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		Set<String> nombres = vuelosHandler.getPaquetes().keySet();
		return nombres;
	}

	
	public Set<DataPaquete> listarDataPaquetes(){
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		Map<String, PaqueteDeRutas> paquetes = vuelosHandler.getPaquetes();
		Set<DataPaquete> result = new HashSet<>();
		for (PaqueteDeRutas paqueteRuta : paquetes.values()) {
			result.add(paqueteRuta.getDataPaquete());
		}
		return result;
	}

	public boolean yaComproPaquete(String cliente, String nombrepaquete) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		PaqueteDeRutas paquete = vuelosHandler.findPaquete(nombrepaquete);
		boolean resultado = false;
		if (paquete == null) {
	        return resultado;
	    } else {
			Set<ClientePaquete> clientes = paquete.getClientes();
			for (ClientePaquete clientPaquete : clientes) {
				String nombre = clientPaquete.getCliente().getNickname();
				if (cliente.equals(nombre)) {
					return true;
				}
			}
			return resultado;
	    }
	}
	
	public  boolean paqueteContieneRutas(String nombrepaquete) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		PaqueteDeRutas paquete = vuelosHandler.findPaquete(nombrepaquete);
		return !paquete.getRutas().isEmpty();
	}
	
	public  boolean paqueteContieneARuta(String nombrePaquete, String nombreRuta) {
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		PaqueteDeRutas paquete = vuelosHandler.findPaquete(nombrePaquete);
		Set<PaqueteRuta> rutas = paquete.getRutas();
		for (PaqueteRuta ruta : rutas) {
			if(ruta.getRuta().getNombre().equals(nombreRuta)) {return true;}
		}
		return false;
	}
	
	public int reservaDeVueloPaquete(String nombreVuelo, String cliente, LocalDate fechaReserva, Asiento tipoAsiento,
			int cantPasajes, int cantEquipajeExtra, Set<DataPasaje> pasajes, String paquete) {
		//chequea si el paquete como forma de pago es valido. si lo es, llama a reservaDeVuelo
		ManejadorUsuarios userHandler = ManejadorUsuarios.getInstancia();
		Cliente client = userHandler.findClienteNickname(cliente);
		ManejadorVuelos manejadorVuelos = ManejadorVuelos.getInstancia();
		Vuelo vuelo = manejadorVuelos.findVuelo(nombreVuelo);
		String nombreRuta = vuelo.getRuta().getNombre();
		
		List<DataRutaAsiento> paquetesCliente = client.paquetesCanjeables(nombreRuta, fechaReserva);
		//paquetesCliente tiene los paquetes con vuelos canjeables para la ruta
		
		int resultado = 3; //0, 1 y 2 son codigos de reservaDeVuelo. "3" significa que el paquete no fue valido
		Boolean flagExistePaquete = false;
		DataRutaAsiento paqueteValido = new DataRutaAsiento("", 0, Asiento.turista);
		for (DataRutaAsiento valor : paquetesCliente) {
			if (valor.getNombre().compareTo(paquete) == 0) {
				flagExistePaquete = true;
				paqueteValido = valor;
			}
		}
		if (flagExistePaquete) {
			if (paqueteValido.getCantidad() >= cantPasajes && paqueteValido.getAsiento() == tipoAsiento){
				resultado = reservaDeVuelo(nombreVuelo, cliente, fechaReserva, tipoAsiento, cantPasajes, cantEquipajeExtra, pasajes);
			}
			if (resultado == 0) {
				client.canjearPaquete(paquete, nombreRuta, cantPasajes);
			}
		}
		return resultado;
	}
	// busqueda
	
	// hayar los que hagan match por descripcion
	// ordenar por fecha de alta
	// hayar los que hagan match por nombre
	public List<DataImagenNombreType> getPaquetesConMatch(String buscado) {
		buscado = buscado.toLowerCase();
		List<DataImagenNombreType> resultado = new ArrayList<>();
		ManejadorVuelos manejadorVuelos = ManejadorVuelos.getInstancia();
		Map<String, PaqueteDeRutas> paquetes = manejadorVuelos.getPaquetes();
		for(PaqueteDeRutas paquete : paquetes.values()) {
			if(buscado == null || buscado.isEmpty() || paquete.getNombre().toLowerCase().contains(buscado) || paquete.getDescripcion().toLowerCase().contains(buscado)) {
				resultado.add(new DataImagenNombreType(paquete.getImagen(), paquete.getNombre(), "Paquete", paquete.getFechaAlta().toString()));
			}
		}
		if(resultado.size() > 0) {
			resultado.sort((o1, o2) -> o2.getFechaAlta().compareTo(o1.getFechaAlta()));
		}
		return resultado;
	}
}
