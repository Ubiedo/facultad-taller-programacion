package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataCheckIn {
	
	private String fechaEmbarque;
	private String horaEmbarque;
	private Set<DataPasaje> pasajesConCheckIn;
	private String fechaCheckIn;

	
	public DataCheckIn(LocalDate fechaE, LocalTime horaE, Set<DataPasaje> pasajesCheckIneados, LocalDate fechaCheckIn) {
		this.fechaEmbarque = fechaE.toString();
		this.horaEmbarque = horaE.toString();
		this.pasajesConCheckIn = pasajesCheckIneados;
		this.fechaCheckIn = fechaCheckIn.toString();
	}
	
	
	public String getfechaEmbarque() {
		return this.fechaEmbarque;
	}
	
	public String gethoraEmbarque() {
		return this.horaEmbarque;
	}
	
	public Set<DataPasaje> getPasajesCheckIn(){
		return pasajesConCheckIn;
	}
	
	public String getFechaCheckIn() {
		return fechaCheckIn;
	}

}
