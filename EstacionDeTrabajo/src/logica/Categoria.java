package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categoria {
	private String nombre;
	private Map<String, RutaDeVuelo> rutas;
	
	public Categoria(String name) {
		nombre = name;
		rutas = new HashMap<String, RutaDeVuelo>();
	}

	public String getNombre() {
		return nombre;
	}
    public Map<String, RutaDeVuelo> getRutas(){
    	return rutas;
    }
	public void addRuta(RutaDeVuelo rutaVuelo) {
		rutas.put(rutaVuelo.getNombre(), rutaVuelo);
	}
	public List<String> getNombreRutas() {
		List<String> res = new ArrayList<String>();
		for (RutaDeVuelo ruta : rutas.values()) {
		    res.add(ruta.getNombre());
		}
		return res;
	}
}
