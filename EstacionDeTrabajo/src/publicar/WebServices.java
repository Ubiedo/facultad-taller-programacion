
package publicar;



import logica.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {
	
	Fabrica fabrica = Fabrica.getInstance();
	IControladorRutas ICRutas = fabrica.getIRutas();
	IControladorUsuarios ICUsuarios = fabrica.getIUsuarios();
	IControladorVuelos ICVuelos = fabrica.getIVuelos();

    private Endpoint endpoint = null;
    //Constructor
    public WebServices(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar() throws FileNotFoundException, IOException{
    	 String userHome = System.getProperty("user.home");
    	 String filePath = userHome+"/.volandoUy/.properties";
         Properties properties = new Properties();
         try (FileInputStream fis = new FileInputStream(filePath)) {
             properties.load(fis);
             //String urlWebService = properties.getProperty("URLWebService");
         } catch (IOException e) {
             e.printStackTrace();
         }
         endpoint = Endpoint.publish((String)properties.get("URLWebService"), this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String name)
                    throws  IOException {
        byte[] byteArray = null;
        try {
                File f = new File("img/" + name);
                FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
        } catch (IOException e) {
                throw e;
        }
        return byteArray;
    }

    @WebMethod
    public boolean postFile(@WebParam(name = "fileName") String name, @WebParam(name = "fileData") byte[] data)
                    throws  IOException {
        try {
            String filePath = "img/" + name;
            Files.write(Paths.get(filePath), data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    /*			Controlador Rutas			*/
    
    @WebMethod
    public void visitarRuta(String nombreRuta) {
    	ICRutas.visitarRuta(nombreRuta);
    };    
    
    @WebMethod
    public Boolean altaCiudad(String nombre, String pais, String aeropuerto, String descripcion, String sweb, String fechaAlta) {
    	return ICRutas.altaCiudad(nombre, pais, aeropuerto, descripcion, sweb, LocalDate.parse(fechaAlta));
    };
    
    @WebMethod
	public  DataRuta listarDatosRuta(String ruta) {
    	return ICRutas.listarDatosRuta(ruta);
    };
    
    @WebMethod
	public  Boolean ingresarNombre(String nombre) {
    	return ICRutas.ingresarNombre(nombre);
    };
    
    @WebMethod
    public int altaRutaVuelo(String aerolinea, String nombre, String descripcion, String hora, 
			float costoTurista, float costoEjecutivo, float costoEquipaje, String ciudadOrigen,
			String ciudadDestino, String fechaAlta, DataColeccion categorias, String imagen, String descCorta, EstadoRuta estado, String video) {
    	return ICRutas.altaRutaVuelo(aerolinea, nombre, descripcion, LocalTime.parse(hora), costoTurista, costoEjecutivo, costoEquipaje, ciudadOrigen, ciudadDestino, LocalDate.parse(fechaAlta), categorias.getConjString(), imagen, descCorta, estado, video);
    };
    
    @WebMethod
	public DataCiudad listarDatosCiudad(String nombre) {
    	return ICRutas.listarDatosCiudad(nombre);
    };
    
    @WebMethod
	public DataColeccion vuelosDeRuta(String nombre){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICRutas.vuelosDeRuta(nombre));
    	return colecc;
	};
	
    @WebMethod
	public Boolean agregarRutaPaquete(String nombreRuta, String nombrePaquete, Integer cantidad, Asiento tipoAsiento) {
    	return ICRutas.agregarRutaPaquete(nombreRuta, nombrePaquete, cantidad, tipoAsiento);
    };
    
    @WebMethod
	public DataCiudad getOrigenRuta(String ruta) {
    	return ICRutas.getOrigenRuta(ruta);
    };
    
    @WebMethod
	public DataCiudad getDestinoRuta(String ruta) {
    	return ICRutas.getDestinoRuta(ruta);
    };
    
     
    @WebMethod
	public void confirmarRuta(String nombreRuta) {
    	ICRutas.confirmarRuta(nombreRuta);
    };
    
    @WebMethod
	public void rechazarRuta(String nombreRuta) {
    	ICRutas.rechazarRuta(nombreRuta);
    };
    
    @WebMethod
	public void finalizarRuta(String nombreRuta) {
    	ICRutas.finalizarRuta(nombreRuta);
    };
    
    @WebMethod
	public Boolean existeRuta(String nombreRuta) {
    	return ICRutas.existeRuta(nombreRuta);
    };
    
    @WebMethod
	public String nicknameAerolineaDeRuta(String nombreRuta) {
    	return ICRutas.nicknameAerolineaDeRuta(nombreRuta);
    };
    
    @WebMethod
	public String nombreAerolineaDeRuta(String nombreRuta) {
    	return ICRutas.nombreAerolineaDeRuta(nombreRuta);
    };
    
    @WebMethod
	public String nombreCiudadOrigenRuta(String nombreRuta) {
    	return ICRutas.nombreCiudadOrigenRuta(nombreRuta);
    };
    
    @WebMethod
	public String nombreCiudadDestinoRuta(String nombreRuta) {
    	return ICRutas.nombreCiudadDestinoRuta(nombreRuta);
    };
    
    
    @WebMethod
	public void leerDatosPaquetesRutas(){
		ICRutas.leerDatosPaquetesRutas();
	}
    
    @WebMethod
	public void leerDatosRutasVuelo() {
    	ICRutas.leerDatosRutasVuelo();
    };
    
    @WebMethod
	public void leerDatosCiudades() {
    	ICRutas.leerDatosCiudades();
    };
    
    @WebMethod
	public void leerDatosCategorias() {
    	ICRutas.leerDatosCategorias();
    };
    
    @WebMethod
	public DataColeccion listarCategorias(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICRutas.listarCategorias());
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarCiudades(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICRutas.listarCiudades());
    	return colecc;
    };
    
    @WebMethod
    public DataColeccion listarRutas(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICRutas.listarRutas());
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarRutasConfirmadas(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICRutas.listarRutasConfirmadas());
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion categoriasConRutas(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setMapStringListString(ICRutas.categoriasConRutas());
    	return colecc;
    };
    
    
    
    /*			Controlador Usuarios			*/
    
    
    
    @WebMethod
	public int crearCliente(String nickname, String nombre, String email, String apellido, String fechaNac, String nacionalidad, Documento tipoDocumento, String nroDocumento, String contrasenha, String imagen) {
    	return ICUsuarios.crearCliente(nickname, nombre, email, apellido, LocalDate.parse(fechaNac), nacionalidad, tipoDocumento, nroDocumento, contrasenha, imagen);
    };

	
    @WebMethod
	public int crearAerolinea(String nickname, String nombre, String email, String web, String descripcion, String contrasenha, String imagen) {
    	return ICUsuarios.crearAerolinea(nickname, nombre, email, web, descripcion, contrasenha, imagen);
    };
    
    @WebMethod
	public boolean existeUsuario(String nickname) {
    	return ICUsuarios.existeUsuario(nickname);
    };
    
    @WebMethod
    public DataColeccion listarUsuarios(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.listarUsuarios());
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarClientes(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.listarClientes());
    	return colecc;
    };
    
    @WebMethod
    public DataColeccion seguidosDeUsuario(String usuario){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.seguidosDeUsuario(usuario));
    	return colecc;
    };
    
    @WebMethod
    public DataColeccion seguidoresDeUsuario(String usuario){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.seguidoresDeUsuario(usuario));
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarAerolineas(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.listarAerolineas());
    	return colecc;
    };
	
	
    @WebMethod
	public DataUsuario listarDatosUsuarioNickname(String nickname) {
    	return ICUsuarios.listarDatosUsuarioNickname(nickname);
    };
    
    
    @WebMethod
    public DataColeccion listarReservasUsuario(String nickname) {
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataClienteVuelo(ICUsuarios.listarReservasUsuario(nickname));
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion nombresReservasUsuario(String nickname) {
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.nombresReservasUsuario(nickname));
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarPaquetesUsuario(String nickname) {
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataClientePaquete(ICUsuarios.listarPaquetesUsuario(nickname));
    	return colecc;
    };
	
	
    @WebMethod
	public void setDatosAerolinea(String nickname, String nombre, String web, String descripcion) {
    	ICUsuarios.setDatosAerolinea(nickname, nombre, web, descripcion);
    };
    
    @WebMethod
    public DataColeccion listarAerolineasConRutas(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.listarAerolineasConRutas());
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarRutasDeAerolinea(String nickname){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.listarRutasDeAerolinea(nickname));
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion infoRutasDeAerolinea(String nombreAero){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataRuta(ICUsuarios.infoRutasDeAerolinea(nombreAero));
    	return colecc;
    };
	
	
    @WebMethod
    public void setDatosCliente(String nickname, String nombre, String apellido, String fechaNac, String nacionalidad, String tipoDocumento, String nroDocumento) {
    	ICUsuarios.setDatosCliente(nickname, nombre, apellido, LocalDate.parse(fechaNac), nacionalidad, tipoDocumento, nroDocumento);
    };
    
    @WebMethod
	public boolean esCliente(String nickname) {
    	return ICUsuarios.esCliente(nickname);
    };
    
    @WebMethod
	public DataSesion loginUsuario(String identificador, String password, boolean mail) {
    	return ICUsuarios.loginUsuario(identificador, password, mail);
    };
    
    @WebMethod
	public DataColeccion listarVuelosAerolinea(String aerolinea){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICUsuarios.listarVuelosAerolinea(aerolinea));
    	return colecc;
    };

    @WebMethod
	public void setImagenAndPasswordUsuario(String nickname, String password, String image) {
    	ICUsuarios.setImagenAndPasswordUsuario(nickname, password, image);
    };
    
    @WebMethod
    public  DataColeccion paquetesCanjeables(String cliente, String vuelo, String fecha){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataRutaAsiento(ICUsuarios.paquetesCanjeables(cliente, vuelo, LocalDate.parse(fecha)));
    	return colecc;
    };
    
    @WebMethod
	public  DataColeccion reservasSinCheckIn(String usuario){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataReserva(ICUsuarios.reservasSinCheckIn(usuario));
    	return colecc;
    };
	

    @WebMethod
	public DataClienteVuelo verReserva(String usuario, String nombreVuelo) {
    	return ICUsuarios.verReserva(usuario, nombreVuelo);
    };
    
    @WebMethod
	public Boolean realizarCheckIn(String nicknameCliente, String nombreVuelo, String fechaCheckIn) {
    	return ICUsuarios.realizarCheckIn(nicknameCliente, nombreVuelo, LocalDate.parse(fechaCheckIn));
    };
    
    @WebMethod
    public DataColeccion reservasConCheckIn(String usuario){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataReserva(ICUsuarios.reservasConCheckIn(usuario));
    	return colecc;
    };
    
    
    @WebMethod
	public DataCheckIn consultaCheckIn(String usuario, String vuelo) {
    	return ICUsuarios.consultaCheckIn(usuario, vuelo);
    };
    
    @WebMethod
	public int seguirUsuario(String seguidor, String seguido) {
    	return ICUsuarios.seguirUsuario(seguidor, seguido);
    };
    
    @WebMethod
	public int dejarDeSeguirUsuario(String seguidor, String seguido) {
    	return ICUsuarios.dejarDeSeguirUsuario(seguidor, seguido);
    };
    
    @WebMethod
	public Boolean esSeguidor(String seguidor, String seguido) {
    	return ICUsuarios.esSeguidor(seguidor, seguido);
    };
    
    @WebMethod
    public void leerDatosUsuarios() {
    	ICUsuarios.leerDatosUsuarios();
    };
    

    
    /*			Controlador Vuelos			*/
    
    
    @WebMethod
    public boolean altaVuelo(String ruta, String nombre, String fecha, String duracion, 
			int asientosTurista, int asientosEjecutivo, String fechaAlta, String imagen) {
    	return ICVuelos.altaVuelo(ruta, nombre, LocalDate.parse(fecha), LocalTime.parse(duracion), asientosTurista, asientosEjecutivo, LocalDate.parse(fechaAlta), imagen);
    };
    
    @WebMethod
	public DataVuelo listarDatosVuelo(String vuelo) {
    	return ICVuelos.listarDatosVuelo(vuelo);
    };
    
    @WebMethod
    public int reservaDeVuelo(String vuelo, String cliente, String fechaReserva, Asiento tipoAsiento,
			int cantPasajes, int cantEquipajeExtra, DataColeccion pasajes) {
    	Set<DataPasaje> p = pasajes.getConjDataPasaje();
    	return ICVuelos.reservaDeVuelo(vuelo, cliente, LocalDate.parse(fechaReserva), tipoAsiento, cantPasajes, cantEquipajeExtra, p);
    };
    
    @WebMethod
	public DataColeccion listarReservasAsociadasVuelo(String vuelo){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICVuelos.listarReservasAsociadasVuelo(vuelo));
    	return colecc;
    };

	
    @WebMethod
	public ParPaqueteRutas mostrarInfoPaquete(String paquete) {
    	return ICVuelos.mostrarInfoPaquete(paquete);
    };
    
    @WebMethod
    public boolean crearPaquete(String nombre, String descripcion, Integer validez, Float descuento, String fechaAlta, String imagen) {
    	return ICVuelos.crearPaquete(nombre, descripcion, validez, descuento, LocalDate.parse(fechaAlta), imagen);
    };
    
    @WebMethod
    public DataColeccion listarPaquetesNoComprados(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICVuelos.listarPaquetesNoComprados());
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarPaquetesConRutas(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICVuelos.listarPaquetesConRutas());
    	return colecc;
    };
    
    @WebMethod
	public boolean comprarPaquete(String nombre, String nickname, String fechaCompra, float costo, String vencimiento) {
    	return ICVuelos.comprarPaquete(nombre, nickname, LocalDate.parse(fechaCompra), costo, LocalDate.parse(vencimiento));
    };

    @WebMethod
	public float getCostoPaquete(String nombre) {
    	return ICVuelos.getCostoPaquete(nombre);
    };
    
    
    @WebMethod
	public String calcularVencimientoCompra(String comprado, String nombre) {
    	return ICVuelos.calcularVencimientoCompra(LocalDate.parse(comprado), nombre).toString();
    };

    @WebMethod
	public DataPaquete getDataPaquete(String nombre) {
    	return ICVuelos.getDataPaquete(nombre);
    };
    
    @WebMethod
    public DataColeccion getDataPaqueteRuta(String paquete){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataPaqueteRuta(ICVuelos.getDataPaqueteRuta(paquete));
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listasPaquetesRegistrados(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjString(ICVuelos.listasPaquetesRegistrados());
    	return colecc;
    };
    
    @WebMethod
    public DataColeccion getResultadoBusquedaRutas(String buscado){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataImagenNombreType(ICRutas.getRutasConMatch(buscado));
    	return colecc;
    };
    
    @WebMethod
    public DataColeccion getResultadoBusquedaPaquetes(String buscado){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataImagenNombreType(ICVuelos.getPaquetesConMatch(buscado));
    	return colecc;
    };
    
    @WebMethod
	public DataColeccion listarDataPaquetes(){
    	DataColeccion colecc = new DataColeccion();
    	colecc.setConjDataPaquete(ICVuelos.listarDataPaquetes());
    	return colecc;
    };
	
    @WebMethod
	public boolean yaComproPaquete(String cliente, String nombrepaquete) {
    	return ICVuelos.yaComproPaquete(cliente, nombrepaquete);
    };
    
    @WebMethod
	public boolean paqueteContieneRutas(String paquete) {
    	return ICVuelos.paqueteContieneRutas(paquete);
    };
    
    @WebMethod
   	public boolean paqueteContieneARuta(String paquete, String ruta) {
       	return ICVuelos.paqueteContieneARuta(paquete, ruta);
       };
    
    @WebMethod
	public int reservaDeVueloPaquete(String vuelo, String cliente, String fechaReserva, Asiento tipoAsiento,
			int cantPasajes, int cantEquipajeExtra, DataColeccion pasajes, String paquete) {
    	return ICVuelos.reservaDeVueloPaquete(vuelo, cliente, LocalDate.parse(fechaReserva), tipoAsiento, cantPasajes, cantEquipajeExtra, pasajes.getConjDataPasaje(), paquete);
    };
	
	
    @WebMethod
    public void leerDatosReservas() {
    	ICVuelos.leerDatosReservas();
    };
    
    @WebMethod
	public void leerDatosPaquetes() {
    	ICVuelos.leerDatosPaquetes();
    };
    
    @WebMethod
	public void leerDatosVuelo() {
    	ICVuelos.leerDatosVuelo();
    };
    
    @WebMethod
	public void leerDatosCompraPaquetes() {
    	ICVuelos.leerDatosCompraPaquetes();
    };

}

