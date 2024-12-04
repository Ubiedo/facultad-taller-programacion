package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class CheckIn {
	private LocalDate fechaEmbarque;
	private LocalTime horaEmbarque;
	private Set<DataPasaje> pasajes;
	private LocalDate fechaCheckIn;
	
	public CheckIn(LocalDate fecha, LocalTime hora, LocalDate fechaCheckIn) {
		fechaEmbarque = fecha;
		horaEmbarque = hora;
		pasajes = new HashSet<>();
		this.fechaCheckIn = fechaCheckIn;
	}
	
	public LocalTime getHoraEmbarque() {
		return horaEmbarque;
	}
	
	public LocalDate getFehaEmbarque() {
		return fechaEmbarque;
	}
	
	public Set<DataPasaje> getPasajes() {
		return pasajes;
	}
	
	public LocalDate getFechaCheckIn() {
		return fechaCheckIn;
	}
	
	public void setHoraEmbarque(LocalTime hora) {
		horaEmbarque = hora;
	}
	
	public void setFechaEmbarque(LocalDate fecha) {
		fechaEmbarque = fecha;
	}
	
	public void setPasajes(Set<DataPasaje> pasajes) {
		this.pasajes = pasajes;
	}
	
	public void addPasaje(DataPasaje pasaje) {
		this.pasajes.add(pasaje);
	}
	
	public void setFechaCheckIn(LocalDate fecha) {
		this.fechaCheckIn = fecha;
	}
}
