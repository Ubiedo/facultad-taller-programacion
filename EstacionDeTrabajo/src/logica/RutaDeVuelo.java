package logica;

/*
 * RutaDeVuelo
 * 
 * Implementacion de la clase ruta de vuelo
 *
 * 2/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//import logica.enumerado.EstadoRuta;

public class RutaDeVuelo {
	private Map<String, Vuelo> vuelos;
	private Aerolinea aerolinea;
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private String nombre;
	private String descripcion;
	private LocalTime hora;
	private LocalDate fechaAlta;
	private float turistaBase;
	private float ejecutivoBase;
	private float unidadEquipajeExtra;
	private String imagen; //path
	private String video; //url
	private String descCorta;
	private EstadoRuta estado; //inicializada en Ingresada
	private Set<String> categorias;
	private int visitas;
	
	public RutaDeVuelo(Aerolinea airline, String nombre, String descripcion, LocalTime hora, 
			float costoTurista, float costoEjecutivo, float costoEquipaje, 
			Ciudad origen, Ciudad destino, LocalDate fechaAlta, String imagen, 
			String descCorta, EstadoRuta stateRuta, Set<String> categorias, String video) {
		this.vuelos = new HashMap<String, Vuelo>(); // key: nombre
		this.aerolinea = airline;
		this.ciudadOrigen = origen;
		this.ciudadDestino = destino;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hora = hora;
		this.turistaBase = costoTurista;
		this.ejecutivoBase = costoEjecutivo;
		this.unidadEquipajeExtra = costoEquipaje;
		this.fechaAlta = fechaAlta;
		this.imagen = imagen;
		this.descCorta = descCorta;
		this.estado = stateRuta;
		this.categorias = categorias;
		this.video = video;
		this.visitas = 0;
	}
	
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	
	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}
	
	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}
	
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public LocalTime getHora() {
        return hora;
    }
    
    public float getTuristaBase() {
        return turistaBase;
    }
    
    public float getEjecutivoBase() {
        return ejecutivoBase;
    }
    
    public float getUnidadEquipajeExtra() {
        return unidadEquipajeExtra;
    }
    
    public LocalDate getFechaAlta() {
    	return fechaAlta;
    }
    
    public String getImagen() {
    	return imagen;
    }
    
    public String getVideo() {
    	return video;
    }
    
    
    public String getDescCorta() {
    	return descCorta;
    }
    
    public EstadoRuta getEstadoRuta() {
    	return estado;
    }
    
    public Set<String> getCategorias() {
    	return categorias;
    }
    
    public int getVisitas() {
    	return visitas;
    }
    
    public void setCategorias(Set<String> categorias) {
    	this.categorias = categorias;
    }
    
    public void setEstadoRuta(EstadoRuta stateRuta) {
        this.estado = stateRuta;
    }
    
    public void setAerolinea(Aerolinea aereo) {
    	this.aerolinea = aereo;
    }
    
    public void setCiudadOrigen(Ciudad origen) {
    	this.ciudadOrigen = origen;
    }
    
    public void setCiudadDestino(Ciudad destino) {
    	this.ciudadDestino = destino;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setHora(LocalTime hora) {
    	this.hora = hora;
    }
    
    public void setTuristaBase(float turistaBase) {
    	this.turistaBase = turistaBase;
    }
    
    public void setEjecutivoBase(float ejecutivoBase) {
    	this.ejecutivoBase = ejecutivoBase;
    }
    
    public void setUnidadEquipajeExtra(float unidadEquipajeExtra) {
    	this.unidadEquipajeExtra = unidadEquipajeExtra;
    }
    
    public void setFechaAlta(LocalDate fechaAlta) {
    	this.fechaAlta = fechaAlta;
    }
    
    public void setImagen(String imagen) {
    	this.imagen = imagen;
    }
    
    public void setVideo(String video) {
    	this.video = video;
    }
    
    public void setDescCorta(String descCorta) {
    	this.descCorta = descCorta;
    }
    
    public void agregarVuelo(Vuelo vueloo) {
    	vuelos.put(vueloo.getNombre(), vueloo);
    }
    
    public Map<String, Vuelo> getVuelos(){
    	return vuelos;
    }
    
    public void confirmarRuta() {
    	if (estado == EstadoRuta.Ingresada) { //para que no se pueda alterar una ruta ya confirmada/rechazada
    		estado = EstadoRuta.Confirmada;
    	}
    }
    
    public void rechazarRuta() {
    	if (estado == EstadoRuta.Ingresada) {
    		estado = EstadoRuta.Rechazada;
    	}
    }
    
    public void finalizarRuta() {
    		estado = EstadoRuta.Finalizada;
    }
    
    public String getNicknameAerolinea() {
    	return aerolinea.getNickname();
    }
    
    public String getNombreAerolinea() {
    	return aerolinea.getNombre();
    }
    
    public String getNombreCiudadOrigen() {
    	return ciudadOrigen.getNombre() + ", " + ciudadOrigen.getPais();
    }
    
    public String getNombreCiudadDestino() {
    	return ciudadDestino.getNombre() + ", " + ciudadDestino.getPais();
    }
    
    public void visitar() {
    	visitas = visitas + 1;
    	System.out.println(visitas);
    }
}
