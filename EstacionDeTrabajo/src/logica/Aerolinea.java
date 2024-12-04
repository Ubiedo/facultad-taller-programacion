package logica;
/*
 * Aerolinea
 * 
 * Implementacion de la clase aerolinea, child de usuario
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Aerolinea extends Usuario {

	private Map<String, RutaDeVuelo> rutas;
	private String web;
	private String descripcion;
	private String nickname;
	private String contrasenha;
	private String imagen;
	private String nombre;
	private String email;
	private Map<String, Usuario> seguidos;
	private Map<String, Usuario> seguidores;
	
	public Aerolinea(String nickname, String nombre, String email, String web, String descripcion, String contrasenha, String imagen) {
		rutas = new HashMap<String, RutaDeVuelo>();
		this.nickname = nickname;
		this.nombre = nombre;
		this.contrasenha = contrasenha;
		this.imagen = imagen;
		this.email = email;
		this.web = web;
		this.descripcion = descripcion;
		this.seguidos = new HashMap<String, Usuario>();
		this.seguidores = new HashMap<String, Usuario>();
	}
	public String getWeb() {
		return web;
	}
	public String getDescripcion() {
		return descripcion;
	}
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
	public void setWeb(String web) {
		this.web = web;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean hasRutas() {
		if (rutas.isEmpty()) {
			return false;
		}
		return true;
	}
	public Set<String> getNicknameRutas(){
		return rutas.keySet();
	}
	
	public void addRuta(RutaDeVuelo rutaVuelo) {
		String key = rutaVuelo.getNombre();
		rutas.put(key, rutaVuelo);
	}
	public Set<DataRuta> getDTRutas(){
		Set<DataRuta> ret = new HashSet<DataRuta>();
		for (RutaDeVuelo ruta : rutas.values()) {
			DataRuta rut = new DataRuta(ruta.getNombre(), ruta.getDescripcion(), ruta.getHora(), ruta.getFechaAlta(), ruta.getTuristaBase(), ruta.getEjecutivoBase(), ruta.getUnidadEquipajeExtra(), ruta.getEstadoRuta(), ruta.getImagen(), ruta.getDescCorta(), ruta.getCiudadOrigen().getNombre()+" "+ruta.getCiudadOrigen().getPais(), ruta.getCiudadDestino().getNombre()+" "+ruta.getCiudadDestino().getPais(), ruta.getCategorias(), ruta.getVideo());
			ret.add(rut);
	   }
	  return ret;
	}
	public Set<String> getVuelos(){
		Set<String> vuelos = new HashSet<>();
		for (RutaDeVuelo ruta: rutas.values()) {
			vuelos.addAll(ruta.getVuelos().keySet());
		}
		return vuelos;
	}
  
    public String getNickname() {
        return this.nickname;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getContrasenha() {
        return contrasenha;
    }
    public String getImagen() {
        return imagen;
    }
    public Map<String, Usuario> getSeguidos() {
    	return seguidos;
    }
    public Map<String, Usuario> getSeguidores() {
    	return seguidores;
    }
    public Boolean sigueA(String usuario) {
    	return seguidos.containsKey(usuario);
    }
    public void seguirA(Usuario usuario) {
    	seguidos.put(usuario.getNickname(), usuario);
    }
    public void agregarSeguidor(Usuario usuario) {
    	seguidores.put(usuario.getNickname(), usuario);
    }
    public void dejarDeSeguirA(String usuario) {
    	seguidos.remove(usuario);
    }
    public void removerSeguidor(String usuario) {
    	seguidores.remove(usuario);
    }
    public DataUsuario getDataUsuario() {
    	DataAerolinea resultado = new DataAerolinea(nickname, nombre, email, web, descripcion, contrasenha, imagen, false);
    	return resultado;
    }
}
