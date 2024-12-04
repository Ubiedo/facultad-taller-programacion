package presentacion;
/*
 * AltaCategoriaConConsola
 * 
 * Implementacion en consola de caso de operacion
 *
 * 04/09/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.util.Scanner;
//import logica.controlador.*;
import logica.*;
public class AltaCategoriaConConsola {
	private Scanner scanner;
	private IControladorRutas CRutas;
	
	public AltaCategoriaConConsola(IControladorRutas c){
		CRutas = c;
	}
	public void setScanner(Scanner s) {
		scanner = s;
	}
	
	public void realizarAlta() {

	    boolean seguir = true;
	    while (seguir) {
	        System.out.println("Ingresar el nombre de la categoría (o 'volver' si quiere finalizar el Alta y regresar a la GUI): ");
	        String nombreCat = scanner.nextLine();

	        if (nombreCat.equalsIgnoreCase("volver")) {
	        	System.out.println("\n\n\n");
	            break;
	        }
	        Boolean noExiste = CRutas.ingresarNombre(nombreCat);
	        if (noExiste == null || !noExiste) {
	        	if(noExiste==null) {
	        		System.out.println("No se puede ingresar un nombre vacío.");
		        }else {
		        	System.out.println("Ya existe una categoría con el nombre ingresado.");
		        }
	            while(true) {
		            System.out.println("¿Desea reingresar el dato? (si o no): ");
		            
		            String s = scanner.nextLine();
	
		            if (s.equalsIgnoreCase("no")) {
		                seguir = false;
		                System.out.println("\n\n\n");
		                break;
		            }else if(s.equalsIgnoreCase("si")) {
		            	break;
		            }else {
		            	System.out.println("La respuesta no coincide.");
		            }
	            }
	 
	          } else {
	            System.out.println("Categoría agregada.\n\n\n\n");
	            seguir = false;
	        }
	    }
	}
}
