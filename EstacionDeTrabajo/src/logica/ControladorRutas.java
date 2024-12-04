package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*import logica.enumerado.*;
import logica.datatype.*;
import logica.entidad.*;
import logica.manejador.*;*/
//import logica.*;

public class ControladorRutas implements IControladorRutas {

	public ControladorRutas() {};
	
	public Boolean altaCiudad(String nombre, String pais, String aeropuerto, String descripcion, String sweb, LocalDate fechaAlta) {
		nombre = nombre.trim();
		pais = pais.trim();
		aeropuerto = aeropuerto.trim();
		descripcion = descripcion.trim();
		sweb = sweb.trim();
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Boolean existe = rutasHandler.exists(nombre + pais);
		if (!existe) {
			Ciudad nuevaC = new Ciudad(nombre, pais, aeropuerto, descripcion, sweb, fechaAlta);
			rutasHandler.addCiudad(nuevaC);
		}
		return !existe;
	}

	public DataRuta listarDatosRuta(String ruta) {
		ruta = ruta.trim();
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		return rutasHandler.findRuta(ruta);
	}
	
	public Boolean ingresarNombre(String nombre) {
		nombre = nombre.trim(); //para eliminar espacios del principio y final
		if (nombre.isEmpty()) {
		    return null; 
		}
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		boolean noExiste = !rutasHandler.existeCategoria(nombre);
		if (noExiste) {
			Categoria catttt = new Categoria(nombre);
			rutasHandler.agregarCategoria(catttt);
		}
		return noExiste;
	}
	
	public int altaRutaVuelo(String aerolinea, String nombre, String descripcion, LocalTime hora, 
			float costoTurista, float costoEjecutivo, float costoEquipaje, String ciudadOrigen,
			String ciudadDestino, LocalDate fechaAlta, Set<String> categorias, String imagen, String descCorta, EstadoRuta estado, String video) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		if (rutasHandler.existsRuta(nombre)) {
			return 1;
		} else {
			ManejadorUsuarios userHandler = ManejadorUsuarios.getInstancia();
			Aerolinea airline = userHandler.findAerolineaNickname(aerolinea);
			Ciudad cityOfOrigin = rutasHandler.findCiudad(ciudadOrigen);
			Ciudad cityOfDestination = rutasHandler.findCiudad(ciudadDestino);
			RutaDeVuelo rutaVuelo = new RutaDeVuelo(airline, nombre, descripcion, hora, costoTurista, costoEjecutivo, 
					costoEquipaje, cityOfOrigin, cityOfDestination, fechaAlta, imagen, descCorta, estado, categorias, video);
			airline.addRuta(rutaVuelo);
			if (categorias != null && !categorias.isEmpty()) {
				Iterator<String> itCat = categorias.iterator();
				while (itCat.hasNext()) {
					rutasHandler.findCategoria(itCat.next()).addRuta(rutaVuelo);
				}
			}
			rutasHandler.addRuta(rutaVuelo);
			return 0;
		}
	}


	public DataCiudad listarDatosCiudad(String key) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Ciudad cityyy = rutasHandler.findCiudad(key);
		return new DataCiudad(cityyy.getNombre(), cityyy.getPais());
	}


	public Set<String> vuelosDeRuta(String nombre) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		return rutasHandler.getNombreVuelosDeRuta(nombre);
	}
	
	public Boolean agregarRutaPaquete(String nombreRuta, String nombrePaquete, Integer cantidad, Asiento tipoAsiento) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaaa = rutasHandler.getRuta(nombreRuta);
		
		ManejadorVuelos vuelosHandler = ManejadorVuelos.getInstancia();
		PaqueteDeRutas paquete = vuelosHandler.findPaquete(nombrePaquete);
		
		Boolean exitoo = paquete.agregarRutaPaquete(rutaaa, cantidad, tipoAsiento);
		
		return exitoo;
	}


	public DataCiudad getOrigenRuta(String ruta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Ciudad cityyy = rutasHandler.getOrigenRuta(ruta);
		DataCiudad dataCity = new DataCiudad(cityyy.getNombre(), cityyy.getPais());
		return dataCity;
	}

	public DataCiudad getDestinoRuta(String ruta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Ciudad cityyy = rutasHandler.getDestinoRuta(ruta);
		DataCiudad dataCity = new DataCiudad(cityyy.getNombre(), cityyy.getPais());
		return dataCity;
	}
	
	public void leerDatosCategorias() {
		CargarDatos datos = CargarDatos.getInstancia();
	    datos.leerDatosCategorias();  
	    Map<String, String> mapUsers = datos.getCategorias();
	    for (Map.Entry<String, String> entry : mapUsers.entrySet()) {
	        String cattt = entry.getValue();
	        ingresarNombre(cattt);
	    }
	}
	public void leerDatosCiudades() {
		CargarDatos datos = CargarDatos.getInstancia();
	    List<String[]> ciudades = datos.leerDatosCiudades();  
	    for (int iterator = 1; iterator < ciudades.size(); iterator++) {
	    	String[] ciudad = ciudades.get(iterator);
	        LocalDate fecha = LocalDate.parse(ciudad[6], DateTimeFormatter.ofPattern("d/M/yyyy"));
	        altaCiudad(ciudad[1], ciudad[2], ciudad[3], ciudad[4], ciudad[5], fecha);
           
	    }
	}
	public void leerDatosRutasVuelo() { //imagen y descCorta vacias!!!!!!!!!!!!!!!!
		CargarDatos datos = CargarDatos.getInstancia();
		List<String[]> listaRutas = datos.leerRutasVuelo(); 
		Map<String, String> categorias = datos.getCategorias();
	    Map<String, DataCiudad> ciudades = datos.getCiudades();
	    Map<String, DataUsuario> usuarios = datos.getUsuarios();
	    for (int iterator = 1; iterator < listaRutas.size(); iterator++) {
	    	
	    	String[] ruta = listaRutas.get(iterator);
	    	if (ruta.length > 1) {
	    	String aerolinea = usuarios.get(ruta[1]).getNickname();
	    	LocalTime hora = LocalTime.parse(ruta[4], DateTimeFormatter.ofPattern("H:mm"));
            LocalDate fecha = LocalDate.parse(ruta[10], DateTimeFormatter.ofPattern("d/M/yyyy"));
            float turista = Float.parseFloat(ruta[5]);
            float ejecutivo = Float.parseFloat(ruta[6]);
            float extra = Float.parseFloat(ruta[7]);
            DataCiudad destino = ciudades.get(ruta[9]);
            DataCiudad origen = ciudades.get(ruta[8]);
            Set<String> cats = new HashSet<>();
            String[] refCats = ruta[11].split("\\s*,\\s*");
            for (int subIterator = 0; subIterator < refCats.length; subIterator++) {
                String nombreCat = categorias.get(refCats[subIterator]);
                cats.add(nombreCat);
            }
            EstadoRuta estado = EstadoRuta.valueOf(ruta[12]);
	    	altaRutaVuelo(aerolinea, ruta[2], ruta[3], hora, turista, ejecutivo, extra, origen.getNombre()+origen.getPais(), destino.getNombre()+destino.getPais(), fecha, cats, ruta[14], ruta[13], estado, ruta[15]);	
	    }}
	}
	
	public void leerDatosPaquetesRutas() {
		CargarDatos datos = CargarDatos.getInstancia();
		List<String[]> paqRutas = datos.leerPaquetesRutas();
		Map<String, String> paquetes = datos.getPaquetes();
	    Map<String, String> rutas = datos.getRutas();
	    for (int iterator = 1; iterator < paqRutas.size(); iterator++) {
	    	String[] paqueteRutas = paqRutas.get(iterator);
	    	String ruta = rutas.get(paqueteRutas[3]);
	    	String paquete = paquetes.get(paqueteRutas[1]);
	    	Integer cantidad = Integer.parseInt(paqueteRutas[4]);
	    	Asiento tipoAsiento = Asiento.valueOf(paqueteRutas[5].toLowerCase());
	    	agregarRutaPaquete(ruta, paquete, cantidad, tipoAsiento);
	    }
	}
	public Set<String> listarCategorias() {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		return rutasHandler.getCategorias();
	}
	
	public Set<String> listarCiudades() {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		return rutasHandler.getCiudades();
	}
	
	public void borrarRutas() {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		rutasHandler.borrarRutas();
	}
	
	public void confirmarRuta(String nombreRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreRuta);
		rutaVuelo.confirmarRuta();
	}
	
	public void rechazarRuta(String nombreRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreRuta);
		rutaVuelo.rechazarRuta();
	}
	
	public void finalizarRuta(String nombreRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreRuta);
		rutaVuelo.finalizarRuta();
	}
	
	public Set<String> listarRutas(){
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Set<String> settt = rutasHandler.getNombreRutas();
		return settt;
	}
	
	public Set<String> listarRutasConfirmadas(){
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Set<String> setRutas = rutasHandler.getNombreRutasConfirmadas();
		return setRutas;
	}
	
	public Boolean existeRuta(String nombreRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		return rutasHandler.existsRuta(nombreRuta);
	}
	
	public String nicknameAerolineaDeRuta(String nombreDeRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreDeRuta);
		return rutaVuelo.getNicknameAerolinea();
	}
	
	public String nombreAerolineaDeRuta(String nombreDeRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreDeRuta);
		return rutaVuelo.getNombreAerolinea();
	}
	
	public String nombreCiudadOrigenRuta(String nombreDeRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreDeRuta);
		return rutaVuelo.getNombreCiudadOrigen();
	}
	
	public String nombreCiudadDestinoRuta(String nombreDeRuta) {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		RutaDeVuelo rutaVuelo = rutasHandler.getRuta(nombreDeRuta);
		return rutaVuelo.getNombreCiudadDestino();
	}
	
	public Map<String, ArrayList<String>> categoriasConRutas() {
		ManejadorRutas rutasHandler = ManejadorRutas.getInstancia();
		Map<String, ArrayList<String>> resultado = new HashMap<String, ArrayList<String>>();
		Set<String> categorias = rutasHandler.getCategorias();
		for (String categoria : categorias) {
			ArrayList<String> rutas = new ArrayList<String>();
			Categoria category = rutasHandler.findCategoria(categoria);
			rutas = (ArrayList<String>) category.getNombreRutas();
			resultado.put(categoria, rutas);
		}
		return resultado;
	}
	public void borrarCategorias() {
		ManejadorRutas manejadorRutas = ManejadorRutas.getInstancia();
		manejadorRutas.borrarCategorias();
	}
	public void borrarCiudades() {
		ManejadorRutas manejadorRutas = ManejadorRutas.getInstancia();
		manejadorRutas.borrarCiudades();
	}
	
	// busqueda
	
	// hayar los que hagan match por descripcion
	// ordenar por fecha de alta
	// hayar los que hagan match por nombre
	public List<DataImagenNombreType> getRutasConMatch(String buscado) {
		buscado = buscado.toLowerCase();
		List<DataImagenNombreType> resultado = new ArrayList<>();
		ManejadorRutas manejadorRutas = ManejadorRutas.getInstancia();
		Set<String> nombres = manejadorRutas.getNombreRutas();
		for(String nombre : nombres) {
			RutaDeVuelo ruta = manejadorRutas.getRuta(nombre);
			if(ruta.getEstadoRuta().equals(EstadoRuta.Confirmada)) {
				if(buscado == null || buscado.isEmpty() || ruta.getNombre().toLowerCase().contains(buscado) || ruta.getDescripcion().toLowerCase().contains(buscado) || ruta.getDescCorta().toLowerCase().contains(buscado)) {
					resultado.add(new DataImagenNombreType(ruta.getImagen(), ruta.getNombre(), "Ruta", ruta.getFechaAlta().toString()));
				}
			}
		}
		if(resultado.size() > 0) {
			resultado.sort((o1, o2) -> o2.getFechaAlta().compareTo(o1.getFechaAlta()));
		}
		return resultado;
	}
	
	public void visitarRuta(String nombreRuta) {
		ManejadorRutas manejadorRutas = ManejadorRutas.getInstancia();
		RutaDeVuelo ruta = manejadorRutas.getRuta(nombreRuta);
		ruta.visitar();
	}
	
	public List<DataRutaVisitas> top5() {
		ManejadorRutas manejadorRutas = ManejadorRutas.getInstancia();
		return manejadorRutas.getTop5();
	}
}
