package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataReserva {//algunos datos de la reserva
	private String fechaReserva;
	private float costo;
	private String vuelo;
	private String aerolinea;
	
	public DataReserva(float costo, LocalDate fechaReserva, String vuelo, String aerolinea) {
		this.costo = costo;
		this.fechaReserva = fechaReserva.toString();
		this.vuelo = vuelo;
		this.aerolinea = aerolinea;
	}

	public float getCosto(){
		return costo;
	}

	public String getFechaReserva(){
		return fechaReserva;
	}
	
	public String getVuelo(){
		return vuelo;
	}
	
	
	public String getAerolinea(){
        return aerolinea;
    }	
}
