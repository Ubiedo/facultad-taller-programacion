package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPaquete {

	private String nombre, descripcion, imagen;
	private String fechaAlta;
	private Integer cantRutas, diasValidez;
	private Float descuento, costo, costoDesc;
	
	public DataPaquete(String nombre, String descirpcion, LocalDate fechaAlta, Integer cantRutas, Integer validez, Float descu, Float costo, Float costoDesc, String imagen) {
		this.nombre = nombre;
		this.descripcion = descirpcion;
		this.fechaAlta = fechaAlta.toString();
		this.cantRutas = cantRutas;
		this.diasValidez = validez;
		this.descuento = descu;
		this.costo = costo;
		this.costoDesc = costoDesc;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getFechaAlta() {
		return fechaAlta;
	}
	
	public Integer getCantRutas() {
		return cantRutas;
	}
	
	public Integer getDiasValidez() {
		return diasValidez;
	}
	public Float getDescuento() {
		return descuento;
	}
	
	public Float getCosto() {
		return costo;
	}
	
	public Float getCostoDesc() {
		return costoDesc;
	}
	
	public String getImagen() {
		return imagen;
	}
}
