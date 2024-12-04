package logica;
/*
 * Fabrica
 * 
 * Implementacion de una fabrica usando patron singleton
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
public class Fabrica {
	
    private static Fabrica instancia = null;
    private Fabrica() {
    }
    ;

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }
    public IControladorUsuarios getIUsuarios() {
        return new ControladorUsuarios();
    }
    public IControladorRutas getIRutas() {
		 return new ControladorRutas();
	 }
    public IControladorVuelos getIVuelos() {
		 return new ControladorVuelos();
	 }
}