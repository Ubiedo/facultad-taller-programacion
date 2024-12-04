package logica;

import java.time.LocalDate;

public class Ciudad {

	private String nombre, pais, aeropuerto, descripcion, sitioWeb;
	private LocalDate fechaAlta;
	
	public Ciudad(String nameee, String country, String airport, String description, String webbbb, LocalDate dateee) {
		nombre = nameee;
		pais = country;
		aeropuerto = airport;
		descripcion = description;
		sitioWeb = webbbb;
		fechaAlta = dateee;
	}
	
	public void setNombre(String nameee) {
		nombre = nameee;
	}
	public void setPais(String country) {
		pais = country;
	}
	public void setAeropuerto(String airport) {
		aeropuerto = airport;
	}
	public void setDescripcion(String description) {
		descripcion = description;
	}
	public void setSweb(String webbbb) {
		sitioWeb = webbbb;
	}
	public void setFechaAlta(LocalDate dateee) {
		fechaAlta = dateee;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getPais() {
		return pais;
	}
	public String getSweb() {
		return sitioWeb;
	}
	public String getAeropuerto() {
		return aeropuerto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
}
