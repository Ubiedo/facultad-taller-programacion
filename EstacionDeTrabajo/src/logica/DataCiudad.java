package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataCiudad {

	private String nombre, pais;
	
	public DataCiudad(String namee, String country) {
		nombre = namee;
		pais = country;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public String getPais() {
		return pais;
	}
}
