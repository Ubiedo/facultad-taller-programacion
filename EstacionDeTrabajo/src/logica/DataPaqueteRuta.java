package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPaqueteRuta {
	private Integer cantidad;
	private Asiento tipoAsiento;
	private String ruta;
	
	public DataPaqueteRuta(Integer quantity, Asiento asiento, String rutaa) {
		cantidad = quantity;
		tipoAsiento = asiento;
		ruta = rutaa;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public Asiento getAsiento() {
		return tipoAsiento;
	}
	
	public String getRuta() {
		return ruta;
	}
	
}
