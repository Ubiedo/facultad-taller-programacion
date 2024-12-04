package logica;
import java.util.ArrayList;
/*
 * ManejadorVuelos
 * 
 * Implementacion de un manejador
 *
 * 02/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import logica.datatype.*;
//import logica.entidad.*;

public class ManejadorRutas {

	private Map<String, Ciudad> ciudades;
	private Map<String, RutaDeVuelo> rutas;
	private Map<String, Categoria> categorias;
	private static ManejadorRutas instancia = null;
	
	private ManejadorRutas() {
		ciudades = new HashMap<String, Ciudad>(); // key: nombre + pais
		rutas = new HashMap<String, RutaDeVuelo>(); // key: nombre + ruta
		categorias = new HashMap<String, Categoria>(); //key: nombre
	}
	public static ManejadorRutas getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorRutas();
		}
		return instancia;
	}
	
	public void addCiudad(Ciudad cityy) {
		String keyCity = cityy.getNombre() + cityy.getPais();
		ciudades.put(keyCity, cityy);
	}
	public Ciudad findCiudad(String keyword) {
		return ciudades.get(keyword);
	}
	public DataRuta findRuta(String keyword) {
		RutaDeVuelo rutaVuelo = rutas.get(keyword);
		DataRuta dataRuta = new DataRuta(rutaVuelo.getNombre(), rutaVuelo.getDescripcion(), rutaVuelo.getHora(), rutaVuelo.getFechaAlta(), rutaVuelo.getTuristaBase(), rutaVuelo.getEjecutivoBase(), rutaVuelo.getUnidadEquipajeExtra(), rutaVuelo.getEstadoRuta(), rutaVuelo.getImagen(), rutaVuelo.getDescCorta(), rutaVuelo.getCiudadOrigen().getNombre()+" "+rutaVuelo.getCiudadOrigen().getPais() , rutaVuelo.getCiudadDestino().getNombre()+" "+rutaVuelo.getCiudadDestino().getPais(), rutaVuelo.getCategorias(), rutaVuelo.getVideo());
		return dataRuta;
	}
	public RutaDeVuelo getRuta(String keyword) {
		RutaDeVuelo rutaVuelo = rutas.get(keyword);
		return rutaVuelo;
	}
	public Boolean exists(String keyword) {
		return ciudades.containsKey(keyword);
	}

	public void agregarVueloARuta(Vuelo vueloo, String rutaa) {
		RutaDeVuelo rutaVuelo = rutas.get(rutaa);
		rutaVuelo.agregarVuelo(vueloo);
		return;
	}
	public boolean existeCategoria(String nombreCat) {
		return categorias.containsKey(nombreCat);
	}
	public void agregarCategoria(Categoria category) {
		categorias.put(category.getNombre(), category);
	}
	public Categoria findCategoria(String keyword) {
		return categorias.get(keyword);
	}
	
	public Boolean existsRuta(String keyword) {
		return rutas.containsKey(keyword);
	}
	
	public void addRuta(RutaDeVuelo rutaVuelo) {
		String key = rutaVuelo.getNombre();
		rutas.put(key, rutaVuelo);
	}
	public Set<String> getNombreVuelosDeRuta(String nombre){
		return rutas.get(nombre).getVuelos().keySet();
	}
	public Ciudad getOrigenRuta(String rutaa){
		return rutas.get(rutaa).getCiudadOrigen();
	}
	public Ciudad getDestinoRuta(String rutaa){
		return rutas.get(rutaa).getCiudadDestino();
	}
	public Set<String> getCategorias() {
		return categorias.keySet();
	}
	public Set<String> getCiudades() {
		return ciudades.keySet();
	}
	
	public void borrarRutas() {
		Iterator<Map.Entry<String, RutaDeVuelo>> iterator = rutas.entrySet().iterator();
        while (iterator.hasNext()){
        	iterator.next();
            iterator.remove();
        }
	}
	
    public Set<String> getNombreRutas() {
    	return rutas.keySet();
    }
    
    public Set<String> getNombreRutasConfirmadas() {
    	Set<String> resultado = new HashSet<String>();
    	for (var entry : rutas.entrySet()) {
    		if (entry.getValue().getEstadoRuta() == EstadoRuta.Confirmada)
    			resultado.add(entry.getKey());
    	}
    	return resultado;
    }
    
    public void borrarCategorias() {
    	categorias.clear();
    }
    public void borrarCiudades() {
    	ciudades.clear();
    }
    public List<DataRutaVisitas> getTop5(){
    	List<DataRutaVisitas> resultado = new ArrayList<DataRutaVisitas>();
		List<RutaDeVuelo> rutasList = new ArrayList<>(rutas.values());
		rutasList.sort((o1, o2) -> o2.getVisitas() - o1.getVisitas());
		// agregarle las top 5
		for(int index = 0; index < 5; index++) {
			DataCiudad cOrigen = new DataCiudad(rutasList.get(index).getCiudadOrigen().getNombre(), rutasList.get(index).getCiudadOrigen().getPais());
			DataCiudad cDestino = new DataCiudad(rutasList.get(index).getCiudadDestino().getNombre(), rutasList.get(index).getCiudadDestino().getPais());
			resultado.add(new DataRutaVisitas(rutasList.get(index).getNombre(), rutasList.get(index).getAerolinea().getNickname(), rutasList.get(index).getVisitas(), cOrigen, cDestino));
		}
		return resultado;
    }
}
