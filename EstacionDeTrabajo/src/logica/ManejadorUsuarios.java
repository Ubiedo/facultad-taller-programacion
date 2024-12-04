package logica;
/*
 * ManejadorUsuarios
 * 
 * Implementacion de un manejador
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
//import logica.datatype.*;
//import logica.entidad.*;

public class ManejadorUsuarios {
	
	//un map con dos keys? map de usuarios general? 
    private Map<String, Cliente> clientesN; //key: nickname
    private Map<String, Cliente> clientesE; //key: email
    private Map<String, Aerolinea> aerolineasN; //key: nickname
    private Map<String, Aerolinea> aerolineasE; //key: email
    
    private static ManejadorUsuarios instancia = null;
    private ManejadorUsuarios() {
        clientesN = new HashMap<String, Cliente>();
        clientesE = new HashMap<String, Cliente>();
        aerolineasN = new HashMap<String, Aerolinea>();
        aerolineasE = new HashMap<String, Aerolinea>();
    }
    public static ManejadorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuarios();
        }   
        return instancia;
    }
    
    public void addCliente(Cliente client) {
    	String keyN = client.getNickname();
    	clientesN.put(keyN, client);
    	String keyE = client.getEmail();
    	clientesE.put(keyE, client);
    }
    public void addAerolinea(Aerolinea airline) {
    	String keyN = airline.getNickname();
    	aerolineasN.put(keyN, airline);
    	String keyE = airline.getEmail();
    	aerolineasE.put(keyE, airline);
    }
    public Cliente findClienteNickname(String nickname) {
        return (Cliente) clientesN.get(nickname);
    }
    public Cliente findClienteEmail(String email) {
        return (Cliente) clientesE.get(email);
    }
    public Aerolinea findAerolineaNickname(String nickname) {
        return (Aerolinea) aerolineasN.get(nickname);
    }
    public Aerolinea findAerolineaEmail(String email) {
        return (Aerolinea) aerolineasE.get(email);
    }
    public Boolean existsNickname(String nickname) {
    	return clientesN.containsKey(nickname) || aerolineasN.containsKey(nickname);
    }
    public Boolean existsEmail(String email) {
    	return clientesE.containsKey(email) || aerolineasE.containsKey(email);
    }
    public Set<String> getNicknameClientes() {
    	return clientesN.keySet();
    }
    public Set<String> getNicknameAerolineas() {
    	return aerolineasN.keySet();
    }
    public Usuario findUsuarioNickname(String nickname) {
    	if (clientesN.containsKey(nickname)) {
    		return clientesN.get(nickname);
    	} else {
    		return aerolineasN.get(nickname);
    	}
    }
    public void setDatosAerolinea(String nickname, String nombre, String website, String descripcion) {
		Aerolinea airline = findAerolineaNickname(nickname);
		airline.setNombre(nombre);
		airline.setWeb(website);
		airline.setDescripcion(descripcion);
	}
    public Set<String> getNicknameAerolineasConRutas(){
    	Set<String> airline = new HashSet<String>();
    	for (Map.Entry<String, Aerolinea> airlineIterator : aerolineasN.entrySet()) {
    		if (airlineIterator.getValue().hasRutas()) {
    			airline.add(airlineIterator.getKey());
    		}
    	}
    	return airline;
    }
    public Set<String> getNicknameRutasDeAerolinea(String nickname){
    	return aerolineasN.get(nickname).getNicknameRutas();
    }
    public Set<DataRuta> infoRutasDeAerolinea(String nombreAero){
    	Aerolinea airline = aerolineasN.get(nombreAero);
    	return airline.getDTRutas();
    }
    public Cliente getCliente(String nickname) {
    	return clientesN.get(nickname);
    }
    
    public void borrarAerolineas() {
        Iterator<Map.Entry<String, Aerolinea>> iterator = aerolineasN.entrySet().iterator();
        while (iterator.hasNext()){
        	iterator.next();
            iterator.remove();
        }
        Iterator<Map.Entry<String, Aerolinea>> iteratorE = aerolineasE.entrySet().iterator();
        while (iteratorE.hasNext()) {
        	iteratorE.next();
        	iteratorE.remove();
        }
    }
    
    public void borrarClientes() {
    	Iterator<Map.Entry<String, Cliente>> iterator = clientesN.entrySet().iterator();
        while (iterator.hasNext()){
        	iterator.next();
            iterator.remove();
        }
        Iterator<Map.Entry<String, Cliente>> iteratorE = clientesE.entrySet().iterator();
        while (iteratorE.hasNext()) {
        	iteratorE.next();
        	iteratorE.remove();
        }
    }
    
    public Set<String> listarVuelosAerolinea(String aerolinea){
    	Aerolinea airline = aerolineasN.get(aerolinea);
    	return airline.getVuelos();
    }
}
