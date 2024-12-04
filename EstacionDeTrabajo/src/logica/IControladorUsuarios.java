package logica;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import logica.enumerado.*;
//import logica.datatype.*;

/*
 * IControladorUsuario
 * 
 * Implementacion de una interfaz de controlador
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
public interface IControladorUsuarios {
	
	public abstract int crearCliente(String nickname, String nombre, String email, String apellido, LocalDate fechaNac, String nacionalidad, Documento tipoDocumento, String nroDocumento, String contrasenha, String imagen);
	public abstract int crearAerolinea(String nickname, String nombre, String email, String web, String descripcion, String contrasenha, String imagen);
	public abstract boolean existeUsuario(String nickname);
	public abstract Set<String> listarUsuarios();
	public abstract Set<String> listarClientes();
	public abstract Set<String> listarAerolineas();
	public abstract DataUsuario listarDatosUsuarioNickname(String nickname);
	public abstract Set<DataClienteVuelo> listarReservasUsuario(String nickname);
	public abstract Set<String> nombresReservasUsuario(String nickname);
	public abstract Set<DataClientePaquete> listarPaquetesUsuario(String nickname);
	public abstract void setDatosCliente(String nickname, String nombre, String apellido, LocalDate fechaNac, String nacionalidad, String tipoDocumento, String nroDocumento);
	public abstract void setDatosAerolinea(String nickname, String nombre, String web, String descripcion);
	public abstract Set<String> listarAerolineasConRutas();
	public abstract Set<String> listarRutasDeAerolinea(String nickname);
	public abstract Set<DataRuta> infoRutasDeAerolinea(String nombreAero);
	public abstract boolean esCliente(String nickname);
	public abstract DataSesion loginUsuario(String identificador, String password, boolean mail);
	public abstract Set<String> listarVuelosAerolinea(String aerolinea);
	public abstract void setImagenAndPasswordUsuario(String nickname, String password, String image);
	public abstract List<DataRutaAsiento> paquetesCanjeables(String cliente, String vuelo, LocalDate fecha);
	public abstract Set<DataReserva> reservasSinCheckIn(String usuario);
	public abstract DataClienteVuelo verReserva(String usuario, String nombreVuelo);
	public abstract Boolean realizarCheckIn(String nicknameCliente, String nombreVuelo, LocalDate fechaCheckIn);
	public abstract Set<DataReserva> reservasConCheckIn(String usuario);
	public abstract DataCheckIn consultaCheckIn(String usuario, String vuelo);
	public abstract int seguirUsuario(String seguidor, String seguido);
	public abstract int dejarDeSeguirUsuario(String seguidor, String seguido);
	public abstract Boolean esSeguidor(String seguidor, String seguido);
	public abstract Set<String> seguidosDeUsuario(String usuario);
	public abstract Set<String> seguidoresDeUsuario(String usuario);
	//lectura de datos
	public abstract void leerDatosUsuarios();
	public abstract void leerDatosCheckIn();
	public abstract void leerDatosSeguidos();
}
