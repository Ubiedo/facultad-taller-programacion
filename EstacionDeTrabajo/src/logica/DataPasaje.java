package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPasaje {

	private String nombre, apellido, asiento;
	
	public DataPasaje(){
		
	}
	public DataPasaje(String namee, String surname) {
		nombre = namee;
		apellido = surname;
		asiento = null;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	
	public String getAsiento() {
		return asiento;
	}
	
	public void setAsiento(String asientoAsig) {
		asiento = asientoAsig;
	}
}
