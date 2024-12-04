package logica;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataRutaAsiento {

	private String nombre;
	private Integer cantidad;
	private Asiento tipoAsiento;
	
	public DataRutaAsiento() {
		
	}
	
	public DataRutaAsiento(String nombre, Integer cantidad, Asiento tipoAsiento) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.tipoAsiento = tipoAsiento;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public Asiento getAsiento() {
		return tipoAsiento;
	}
	
}