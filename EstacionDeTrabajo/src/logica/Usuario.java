package logica;

import java.util.Map;

/*
 * Usuario
 * 
 * Implementacion de la clase abstracta Usuario
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
public abstract class Usuario {

	private String nickname;
	private String contrasenha;
	private String imagen;
	private String nombre;
	private String email;
	private Map<String, Usuario> seguidos;
	private Map<String, Usuario> seguidores;
	
	public abstract String getNickname();
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
    
    public abstract DataUsuario getDataUsuario();
    
    public abstract Boolean sigueA(String usuario) ;
   
    public abstract void seguirA(Usuario usuario) ;
  
    public abstract void agregarSeguidor(Usuario usuario) ;
   
    public abstract void dejarDeSeguirA(String usuario) ;
  
    public abstract void removerSeguidor(String usuario);
  
}
