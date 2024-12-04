package logica;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Cliente
 * 
 * Implementacion de la clase cliente, child de usuario
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import logica.enumerado.*;

public class Cliente extends Usuario {

	private String apellido;
	private LocalDate fechaNac;
	private String nacionalidad;
	private Documento tipoDocumento;
	private String nroDocumento;
	private Map<String, ClienteVuelo> reservas;
	private Set<ClientePaquete> paquetes;
	private String nickname;
	private String contrasenha;
	private String imagen;
	private String nombre;
	private String email;
	private Map<String, Usuario> seguidos;
	private Map<String, Usuario> seguidores;
	
	public Cliente(String nickname, String nombre, String email, String apellido, LocalDate fechaNac, 
			String nacionalidad, Documento tipoDocumento, String nroDocumento, String contrasenha, String imagen) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.contrasenha = contrasenha;
		this.imagen = imagen;
		this.email = email;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.nacionalidad = nacionalidad;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.reservas = new HashMap<String, ClienteVuelo>();
		this.paquetes = new HashSet<ClientePaquete>();
		this.seguidos = new HashMap<String, Usuario>();
		this.seguidores = new HashMap<String, Usuario>();
	}
	public String getApellido() {
		return apellido;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public Documento getTipoDocumento() {
		return tipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public Map<String, ClienteVuelo> getReservas() {
		return reservas;
	}
	public Set<ClientePaquete> getPaquetes() {
		return paquetes;
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
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public void setTipoDocumento(Documento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
	public Boolean existeReserva(String vuelo) {
		return reservas.containsKey(vuelo);
	}
	public void addReserva(ClienteVuelo clienteVuelo) {
		String key = clienteVuelo.getVuelo().getNombre();
		reservas.put(key, clienteVuelo);
	}
	public boolean existeCompraClientePaquete(String nickname, String nombre) {
		boolean condition = false;
		for (ClientePaquete cp : paquetes) {
			condition = condition || cp.isPaquete(nombre);
		}
		return condition;
	}
	public void addClientePaquete(ClientePaquete clientePaquete) {
		paquetes.add(clientePaquete);
	}

    public String getNickname() {
        return this.nickname;
    }
    
    public List<DataRutaAsiento> paquetesCanjeables(String nombreRuta, LocalDate fechaActual) { 
    	//paquetes que tienen vuelos canjeables de la ruta dada
    	List<DataRutaAsiento> resultado = new ArrayList<DataRutaAsiento>();
    	Iterator<ClientePaquete> iter = paquetes.iterator();
		while (iter.hasNext()) {
			ClientePaquete paquete = (ClientePaquete) iter.next();
			if (!fechaActual.isAfter(paquete.getVencimiento())) { //paquete no vencido
				DataRutaAsiento disponibles = paquete.canjesDisponiblesRuta(nombreRuta);
				if (disponibles.getCantidad() > 0) {
					DataRutaAsiento res = new DataRutaAsiento(paquete.getPaquete().getNombre(), disponibles.getCantidad(), disponibles.getAsiento());
					resultado.add(res);
				}
			}
		}
    	return resultado;
    }
    
    public void canjearPaquete(String nombrePaquete, String nombreRuta, int cantPasajes) {
    	Iterator<ClientePaquete> iter = paquetes.iterator();
    	boolean continuar = true;
		while ((iter.hasNext()) && continuar){
			ClientePaquete paquete = (ClientePaquete) iter.next();
			if (paquete.getPaquete().getNombre().equals(nombrePaquete)) {
				paquete.canjearRuta(nombreRuta, cantPasajes);
				continuar = false;
			}
		}
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
    
    public Map<String, ClienteVuelo> getReservasSinCheckIn() {
    	Map<String, ClienteVuelo> sinCheckIn = new HashMap<>();
        for (Map.Entry<String, ClienteVuelo> reserva : reservas.entrySet()) {
            ClienteVuelo reservaVuelo = reserva.getValue();
            if (reserva.getValue().getCheckIn() == null) {
            	String nombreVuelo = reserva.getKey();
                sinCheckIn.put(nombreVuelo, reservaVuelo);
            }
        }
        return sinCheckIn;
	}
    
    public Map<String, ClienteVuelo> getReservasConCheckIn() {
    	Map<String, ClienteVuelo> conCheckIn = new HashMap<>();
        for (Map.Entry<String, ClienteVuelo> reserva : reservas.entrySet()) {
            ClienteVuelo reservaVuelo = reserva.getValue();
            if (reserva.getValue().getCheckIn() != null) {
            	String nombreVuelo = reserva.getKey();
                conCheckIn.put(nombreVuelo, reservaVuelo);
            }
        }
        return conCheckIn;
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
    	DataCliente resultado = new DataCliente(nickname, nombre, apellido, email, fechaNac, nroDocumento, nacionalidad, tipoDocumento , contrasenha, imagen, true);
    	return resultado;
    }
}
	