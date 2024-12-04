package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataImagenNombreType {

	private String imagen, nombre, type, fechaAlta;
	
	public DataImagenNombreType(String imagen, String nombre, String type, String fechaAlta) {
		this.imagen = imagen;
		this.nombre = nombre;
		this.type = type;
		this.fechaAlta = fechaAlta;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getType() {
		return type;
	}
	
	public String getFechaAlta() {
		return fechaAlta;
	}
}
