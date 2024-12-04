package logica;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataVuelo {

	private String nombre;
	private String fecha;
	private String duracion;
	private String fechaAlta;
	private Integer asientosTurista, asientosEjecutivo;
	private String imagen;
	
	public DataVuelo(String nom, LocalDate fechaVuelo, LocalTime durVuelo, 
			LocalDate fechaA, Integer turista, Integer ejecutivo, String imagenN) {
		nombre = nom;
		fecha = fechaVuelo.toString();
		duracion = durVuelo.toString();
		fechaAlta = fechaA.toString();
		asientosTurista = turista;
		asientosEjecutivo = ejecutivo;
		imagen = imagenN;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getDuracion() {
		return duracion;
	}
	
	public String getFechaAlta() {
		return fechaAlta;
	}
	
	public Integer getAsientosTurista() {
		return asientosTurista;
	}
	
	public Integer getAsientosEjecutivo() {
		return asientosEjecutivo;
	}
	
	public String getImagen() {
		return imagen;
	}
}
