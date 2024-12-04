package logica;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
/*
 * ControladorUsuarios
 * 
 * Implementacion de un controlador
 *
 * 02/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*import logica.enumerado.*;
import logica.datatype.*;
import logica.entidad.*;
import logica.manejador.*;*/
//import logica.*;

/*
 * ControladorUsuario
 * 
 * Implementacion de un controlador
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
public class ControladorUsuarios implements IControladorUsuarios {

	public ControladorUsuarios() {
	}
	
	public int crearCliente(String nickname, String nombre, 
	        String email, String apellido, LocalDate fechaNac, 
			String nacionalidad, Documento tipoDocumento, 
			String nroDocumento, String contrasenha, String imagen) {
		nickname = nickname.trim();
		nombre = nombre.trim();
		contrasenha = contrasenha.trim();
		imagen = imagen.trim();
		email = email.trim();
		apellido = apellido.trim();
		nacionalidad = nacionalidad.trim();
		nroDocumento = nroDocumento.trim();
		ManejadorUsuarios manejadorUsuario = ManejadorUsuarios.getInstancia();
		if (!manejadorUsuario.existsNickname(nickname) && !manejadorUsuario.existsEmail(email)) {
			Cliente cliente = new Cliente(nickname, nombre, 
			        email, apellido, fechaNac, nacionalidad, 
					tipoDocumento, nroDocumento, contrasenha, imagen);
			  manejadorUsuario.addCliente(cliente);
			return 0;
		} else {
            return 1;
        }
	}
	
	public int crearAerolinea(String nickname, String nombre, 
	        String email, String web, String descripcion, String contrasenha, String imagen) {
		nickname = nickname.trim();
		nombre = nombre.trim();
		email = email.trim();
		web = web.trim();
		descripcion = descripcion.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		if (!manejadorUsuarios.existsNickname(nickname) && !manejadorUsuarios.existsEmail(email)) {
			Aerolinea aerolinea = new Aerolinea(nickname, nombre, email, web, 
			        descripcion, contrasenha, imagen);
			manejadorUsuarios.addAerolinea(aerolinea);
			return 0;
		} else {
            return 1;
        }
	}
	
	public Set<String> listarUsuarios() {
		
		Set<String> setC = listarClientes();
		Set<String> setA = listarAerolineas();
		//setC.addAll(setA);
		//return setC;
		//addAll es random, si se precisan en orden se puede esto:
		Set<String> combined = Stream.concat(setA.stream(), 
		        setC.stream()).collect(Collectors.toSet());
		return combined;
	}
	
	public Set<String> listarClientes() {
		ManejadorUsuarios manejdadorUsuarios = ManejadorUsuarios.getInstancia();
		Set<String> set = manejdadorUsuarios.getNicknameClientes();
		return set;
	}
	
	public Set<String> listarAerolineas() {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Set<String> set = manejadorUsuarios.getNicknameAerolineas();
		return set;
	}

	public DataUsuario listarDatosUsuarioNickname(String nickname) {
		nickname = nickname.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Usuario encontrado = manejadorUsuarios.findUsuarioNickname(nickname);
    	if (encontrado instanceof Cliente) {
    		Cliente cliente = (Cliente) encontrado;
    		return new DataCliente(cliente.getNickname(), cliente.getNombre(), cliente.getApellido(), 
    		        cliente.getEmail(), cliente.getFechaNac(), cliente.getNroDocumento(), 
    		        cliente.getNacionalidad(), cliente.getTipoDocumento(), cliente.getContrasenha(), 
    		        cliente.getImagen(), true);
    	} else {
    		Aerolinea aerolinea = (Aerolinea) encontrado;
    		return new DataAerolinea(aerolinea.getNickname(), aerolinea.getNombre(), aerolinea.getEmail(), 
    		        aerolinea.getWeb(), aerolinea.getDescripcion(), aerolinea.getContrasenha(), aerolinea.getImagen(), false);
    	}
	}

	public Set<DataClienteVuelo> listarReservasUsuario(String nickname) {
		nickname = nickname.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(nickname);
		Set<ClienteVuelo> reservas = new HashSet<>(cliente.getReservas().values());
		Set<DataClienteVuelo> drResultado = new HashSet<>();
		for (ClienteVuelo clienteVuelo : reservas) {
			DataClienteVuelo dtClienteVuelo = new DataClienteVuelo(clienteVuelo.getTipoAsiento(), 
			        clienteVuelo.getCantPasajes(), clienteVuelo.getCantEquipajeExtra(), clienteVuelo.getCosto(), 
			        clienteVuelo.getFechaReserva(), clienteVuelo.getPasajes(), clienteVuelo.getVuelo().getNombre(),
			        clienteVuelo.getVuelo().getRuta().getNombre(), clienteVuelo.getVuelo().getRuta().getAerolinea().getNombre(), clienteVuelo.getVuelo().getRuta().getAerolinea().getNickname());
			drResultado.add(dtClienteVuelo);
		}
		return drResultado;
	}
	
	public Set<String> nombresReservasUsuario(String nickname) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(nickname);
		Set<String> resultado = new HashSet<>(cliente.getReservas().keySet());
		return resultado;
	}
	
	public Set<DataClientePaquete> listarPaquetesUsuario(String nickname) {
		nickname = nickname.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(nickname);
		Set<ClientePaquete> paquetes = cliente.getPaquetes();
		Set<DataClientePaquete> dtClientePaquete = new HashSet<>();
		for (ClientePaquete clientePaquete : paquetes) {
			DataClientePaquete dtClientePaq = new DataClientePaquete(clientePaquete.getFecha(),
			        clientePaquete.getVencimiento(), clientePaquete.getCosto(), clientePaquete.getCliente().getNickname(),
			        clientePaquete.getPaquete().getNombre(), clientePaquete.getPaquete().getImagen());
			dtClientePaquete.add(dtClientePaq);
		}
		return dtClientePaquete;
	}
	
	public void setDatosCliente(String nickname, String nombre, String apellido, 
	        LocalDate fechaNac, String nacionalidad, String tipoDocumento,  String nroDocumento) {
		nickname = nickname.trim();
		nombre = nombre.trim();
		apellido = apellido.trim();
		nacionalidad = nacionalidad.trim();
		nroDocumento = nroDocumento.trim();
		tipoDocumento = tipoDocumento.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(nickname);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setNacionalidad(nacionalidad);
		if (tipoDocumento == "CI") {
			cliente.setTipoDocumento(Documento.CI);
		} else {
			cliente.setTipoDocumento(Documento.PASAPORTE);
		}
		cliente.setNroDocumento(nroDocumento);
		cliente.setFechaNac(fechaNac);
	}
	
	public void setDatosAerolinea(String nickname, String nombre, String web, String descripcion) {
		nickname = nickname.trim();
		nombre = nombre.trim();
		web = web.trim();
		descripcion = descripcion.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		manejadorUsuarios.setDatosAerolinea(nickname, nombre, web, descripcion);
	}
	
	public Set<String> listarAerolineasConRutas() {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Set<String> set = manejadorUsuarios.getNicknameAerolineasConRutas();
		return set;
	}
	public Set<String> listarRutasDeAerolinea(String nickname) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		return manejadorUsuarios.getNicknameRutasDeAerolinea(nickname);
	}
	
	public Set<DataRuta> infoRutasDeAerolinea(String nombreAero) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		return manejadorUsuarios.infoRutasDeAerolinea(nombreAero);
	}
	public  boolean esCliente(String nickname) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Set<String> clientes = manejadorUsuarios.getNicknameClientes();
		if (clientes.contains(nickname)) { 
			return true;
		}
		return false;
	}
	public void leerDatosUsuarios() {
		CargarDatos datos = CargarDatos.getInstancia();
	    datos.leerDatosUsuarios();  
	    Map<String, DataUsuario> usuarios = datos.getUsuarios();
	    
	    for (Map.Entry<String, DataUsuario> usuarioSeleccionado : usuarios.entrySet()) {
	        DataUsuario usuario = usuarioSeleccionado.getValue();

	        if (usuario instanceof DataCliente) {
	            DataCliente cliente = (DataCliente) usuario; 
	            crearCliente(cliente.getNickname(), cliente.getNombre(), cliente.getEmail(), cliente.getApellido(), LocalDate.parse(cliente.getFechaNac()), cliente.getNacionalidad(), cliente.getTipoDocumento(), cliente.getNroDocumento(), cliente.getContrasenha(), cliente.getImagen());
	        } else if (usuario instanceof DataAerolinea) {
	            DataAerolinea aerolinea = (DataAerolinea) usuario;
	            crearAerolinea(aerolinea.getNickname(), aerolinea.getNombre(), aerolinea.getEmail(), aerolinea.getWeb(), aerolinea.getDescripcion(), aerolinea.getContrasenha(), aerolinea.getImagen()); // Llamar al método correspondiente
	        }
	    }
	}
	
	public void borrarAerolineas() {
		ManejadorUsuarios manejadorUsuario = ManejadorUsuarios.getInstancia();
		manejadorUsuario.borrarAerolineas();
	}
	
	public void borrarClientes() {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		manejadorUsuarios.borrarClientes();
	}

	public boolean existeUsuario(String nickname) {
			ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
			return manejadorUsuarios.existsNickname(nickname);
	}
	
	public DataSesion loginUsuario(String identificador, String password, boolean mail) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		DataSesion resultado = null;
		if (mail) { // el identificador es el mail
			if (manejadorUsuarios.existsEmail(identificador)) {
				Usuario user = manejadorUsuarios.findClienteEmail(identificador);
				if (user != null) {
					if (user.getContrasenha().equals(password)) {
						resultado = new DataSesion(TipoSesion.CLIENTE, user.getNickname(), user.getImagen());
					}
				} else {
					user = manejadorUsuarios.findAerolineaEmail(identificador);
					if (user.getContrasenha().equals(password)) {
						resultado = new DataSesion(TipoSesion.AEROLINEA, user.getNickname(), user.getImagen());
					}
				}
			}
		} else { // el identificador es nickname
			if (manejadorUsuarios.existsNickname(identificador)) {
				Usuario user = manejadorUsuarios.findClienteNickname(identificador);
				if (user != null) {
					if (user.getContrasenha().equals(password)) {
						resultado = new DataSesion(TipoSesion.CLIENTE, user.getNickname(), user.getImagen());
					}
				} else {
					user = manejadorUsuarios.findAerolineaNickname(identificador);
					if (user.getContrasenha().equals(password)) {
						resultado = new DataSesion(TipoSesion.AEROLINEA, user.getNickname(), user.getImagen());
					}
				}
			}
		}
		if(resultado == null) {
			resultado = new DataSesion(TipoSesion.INVITADO, "", "");
		}
		return resultado;
	}
	
	public Set<String> listarVuelosAerolinea(String aerolinea) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		return manejadorUsuarios.listarVuelosAerolinea(aerolinea);
	}
	
	public void setImagenAndPasswordUsuario(String nickname, String password, String image) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Usuario user = manejadorUsuarios.findUsuarioNickname(nickname);
		user.setImagen(image);
		user.setContrasenha(password);
	}
	
	public List<DataRutaAsiento> paquetesCanjeables(String nicknameCliente, String nombreVuelo, LocalDate fecha) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(nicknameCliente);
		ManejadorVuelos manejadorVuelos = ManejadorVuelos.getInstancia();
		Vuelo vuelo = manejadorVuelos.findVuelo(nombreVuelo);
		String nombreRuta = vuelo.getRuta().getNombre();
		List<DataRutaAsiento> res = cliente.paquetesCanjeables(nombreRuta, fecha);
		System.out.println(res.size());
		return res;
	}
	
	public Set<DataReserva> reservasSinCheckIn(String usuario) {
		usuario = usuario.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(usuario);
		Set<ClienteVuelo> reservas = new HashSet<>(cliente.getReservasSinCheckIn().values());
		Set<DataReserva> dataReservas = new HashSet<>();
		for (ClienteVuelo reserva : reservas) {
			DataReserva dataReserva = new DataReserva(reserva.getCosto(), 
			        reserva.getFechaReserva(), reserva.getVuelo().getNombre(),
			        reserva.getVuelo().getRuta().getAerolinea().getNombre());
			dataReservas.add(dataReserva);
		}
		return dataReservas;
	}
	
	public DataClienteVuelo verReserva(String usuario, String nombreVuelo) {
		usuario = usuario.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(usuario);
		ClienteVuelo reserva  =  cliente.getReservas().get(nombreVuelo);
		DataClienteVuelo dataReserva = new DataClienteVuelo(reserva.getTipoAsiento(), 
		        reserva.getCantPasajes(), reserva.getCantEquipajeExtra(), reserva.getCosto(), 
		        reserva.getFechaReserva(), reserva.getPasajes(), reserva.getVuelo().getNombre(),
		        reserva.getVuelo().getRuta().getNombre(), reserva.getVuelo().getRuta().getAerolinea().getNombre(), 
		        reserva.getVuelo().getRuta().getAerolinea().getNickname());
		return dataReserva;
	}
	
	public Boolean realizarCheckIn(String nicknameCliente, String nombreVuelo, LocalDate fechaCheckIn) {
		nicknameCliente = nicknameCliente.trim();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(nicknameCliente);
		ClienteVuelo reserva = cliente.getReservas().get(nombreVuelo);
		Vuelo vuelo = reserva.getVuelo();
		Boolean res = reserva.getCheckIn() == null;
		if(res) {
			LocalDate fecha = vuelo.getFecha();
			LocalTime hora = vuelo.getRuta().getHora().minusMinutes(30);
			if(hora.isAfter(vuelo.getRuta().getHora())) {
				//el embarque es el día anterior
				fecha = fecha.minusDays(1);
			}
			CheckIn checkIn = new CheckIn(fecha, hora, fechaCheckIn);
			reserva.setCheckIn(checkIn);
			Set<DataPasaje> pasajes = reserva.getPasajes();
			Asiento tipoAsiento = reserva.getTipoAsiento();
		    if (tipoAsiento == Asiento.ejecutivo) {
		    	int numeroAsiento = vuelo.getAsientosEjecConCheckIn(); 
		        for (DataPasaje pasaje : pasajes) {
		            numeroAsiento++; 
		            String asiento = "E" + numeroAsiento;
		            pasaje.setAsiento(asiento); 
		            checkIn.addPasaje(pasaje);
		        }
		        vuelo.setAsientosEjecConCheckIn(numeroAsiento); 	
		    } else if (tipoAsiento == Asiento.turista) {
		    	int numeroAsiento = vuelo.getAsientosTurConCheckIn(); 
		        for (DataPasaje pasaje : pasajes) {
		            numeroAsiento++; 
		            String asiento = "T" + numeroAsiento;
		            pasaje.setAsiento(asiento);
		            checkIn.addPasaje(pasaje);
		        }
		        vuelo.setAsientosTurConCheckIn(numeroAsiento); 
		    }
		}
		return res;
	}
	
	public Set<DataReserva> reservasConCheckIn(String usuario){
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(usuario);
		Map<String, ClienteVuelo> reservas = cliente.getReservasConCheckIn();
		Set<DataReserva> dataReservas = new HashSet<>();
		for (ClienteVuelo reserva : reservas.values()) {
			DataReserva dataReserva = new DataReserva(reserva.getCosto(), 
			        reserva.getFechaReserva(), reserva.getVuelo().getNombre(),
			        reserva.getVuelo().getRuta().getAerolinea().getNombre());
			dataReservas.add(dataReserva);
		}
		return dataReservas;
	}
	
	public DataCheckIn consultaCheckIn(String usuario, String vuelo) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		Cliente cliente = manejadorUsuarios.findClienteNickname(usuario);
		Map<String, ClienteVuelo> reservas = cliente.getReservasConCheckIn();
		ClienteVuelo reserva = reservas.get(vuelo);
		CheckIn checkIn = reserva.getCheckIn();
		Set<DataPasaje> pasajesConCheckIn = checkIn.getPasajes();
		DataCheckIn dCheckIn = new DataCheckIn(checkIn.getFehaEmbarque(), checkIn.getHoraEmbarque(), pasajesConCheckIn, checkIn.getFechaCheckIn());
		return dCheckIn;
	}
	
	public int seguirUsuario(String seguidor, String seguido) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		if (!manejadorUsuarios.existsNickname(seguidor))
			return 1;
		if (!manejadorUsuarios.existsNickname(seguido))
			return 2;
		if (seguidor.compareTo(seguido) == 0)
			return 3;
		Usuario usuarioSeguidor = manejadorUsuarios.findUsuarioNickname(seguidor);
		Usuario usuarioSeguido = manejadorUsuarios.findUsuarioNickname(seguido);
		if (usuarioSeguidor.sigueA(seguido))
			return 4;
		usuarioSeguidor.seguirA(usuarioSeguido);
		usuarioSeguido.agregarSeguidor(usuarioSeguidor);
		return 0;
	}
	
	public int dejarDeSeguirUsuario(String seguidor, String seguido) {
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		if (!manejadorUsuarios.existsNickname(seguidor))
			return 1;
		if (!manejadorUsuarios.existsNickname(seguido))
			return 2;
		if (seguidor.compareTo(seguido) == 0)
			return 3;
		Usuario usuarioSeguidor = manejadorUsuarios.findUsuarioNickname(seguidor);
		Usuario usuarioSeguido = manejadorUsuarios.findUsuarioNickname(seguido);
		if (!usuarioSeguidor.sigueA(seguido))
			return 4;
		usuarioSeguidor.dejarDeSeguirA(seguido);
		usuarioSeguido.removerSeguidor(seguidor);
		return 0;
	}
	
	public Boolean esSeguidor(String seguidor, String seguido) {
		Boolean resultado = false;
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		if ((manejadorUsuarios.existsNickname(seguidor)) && (manejadorUsuarios.existsNickname(seguido))) {
			Usuario usuarioSeguidor = manejadorUsuarios.findUsuarioNickname(seguidor);
			if (usuarioSeguidor.sigueA(seguido))
				resultado = true;
		}
		return resultado;
	}
	
	public Set<String> seguidosDeUsuario(String usuario) {
		Set<String> resultado =  new HashSet<String>();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		if (manejadorUsuarios.existsNickname(usuario)) {
			Usuario user = manejadorUsuarios.findUsuarioNickname(usuario);
			Map<String, Usuario> seguidos = user.getSeguidos();
			for (var entry : seguidos.entrySet()) {
			    resultado.add(entry.getKey());
			}
		}
		return resultado;
	}
	
	public Set<String> seguidoresDeUsuario(String usuario) {
		Set<String> resultado =  new HashSet<String>();
		ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
		if (manejadorUsuarios.existsNickname(usuario)) {
			Usuario user = manejadorUsuarios.findUsuarioNickname(usuario);
			Map<String, Usuario> seguidores = user.getSeguidores();
			for (var entry : seguidores.entrySet()) {
			    resultado.add(entry.getKey());
			}
		}
		return resultado;
	}
	
	public void leerDatosCheckIn() {
		CargarDatos datos = CargarDatos.getInstancia();
	    List<String[]> datosCheckIn = datos.leerCheckIn();
	    Map<String, String[]> reservas = datos.getReservas();
	    Map<String, DataUsuario> usuarios = datos.getUsuarios();
	    Map<String, String> vuelos = datos.getVuelos();
	    for (String[] dato : datosCheckIn) {
	    	if (dato.length > 1 && !dato[0].equals("Ref")) {
	    		String nombreVuelo = vuelos.get(reservas.get(dato[1])[3]);
		    	String nickCliente = usuarios.get(reservas.get(dato[1])[4]).getNickname();
		    	LocalDate fecha = LocalDate.parse(dato[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
		    	realizarCheckIn(nickCliente, nombreVuelo, fecha);
	    	}
	    	
	    }
	}
	
	public void leerDatosSeguidos() {
		CargarDatos datos = CargarDatos.getInstancia();
	    List<String[]> datosSeguidos = datos.leerSeguidos();
	    for (String[] dato : datosSeguidos) {
	    	if (dato.length > 1 && !dato[0].equals("Ref")) {
	    	seguirUsuario(dato[2], dato[4]);
	    }}
	}
}
