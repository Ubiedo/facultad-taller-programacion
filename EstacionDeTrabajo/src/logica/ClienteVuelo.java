package logica;

/*
 * Cliente Vuelo
 * 
 * Implementacion de la clase Cliente Vuelo
 *
 * 31/08/2024
 * 
 * Grupo 44 | Taller de Programacion | Facultad de Ingenieria | Universidad de la Republica Uruguay
 */
import java.time.LocalDate;
import java.util.Set;
/*import logica.enumerado.*;
import logica.datatype.*;*/


public class ClienteVuelo {	
	private Cliente cliente;
	private Vuelo vuelo;
	private Set<DataPasaje> pasajes;
	private LocalDate fechaReserva;
	private Asiento tipoAsiento; 
	private int cantPasajes;
	private int cantEquipajeExtra;
	private float costo;
	private CheckIn checkIn;


public ClienteVuelo(Cliente client, Vuelo flyyyy, Set<DataPasaje> pasajes, LocalDate fechaReserva, Asiento tipoAsiento, 
	int cantPasajes, int cantEquipajeExtra, float costo) {
	this.cliente = client;
	this.vuelo = flyyyy;
	this.pasajes = pasajes;
	this.tipoAsiento=tipoAsiento;
	this.cantPasajes = cantPasajes;
	this.cantEquipajeExtra = cantEquipajeExtra;
	this.costo = costo;
	this.fechaReserva = fechaReserva;
}

public Cliente getCliente() {
	return cliente;
}

public Vuelo getVuelo() {
	return vuelo;
}

public Set<DataPasaje> getPasajes() {
	return pasajes;
}

public Asiento getTipoAsiento(){
	return tipoAsiento;
}

public int getCantPasajes(){
	return cantPasajes;
}

public int getCantEquipajeExtra(){
	return cantEquipajeExtra;
}

public float getCosto(){
	return costo;
}

public LocalDate getFechaReserva(){
	return fechaReserva;
}

CheckIn getCheckIn() {
	return checkIn;
}

public void setTipoAsiento(Asiento tipoAsiento) {
	this.tipoAsiento = tipoAsiento;
}

public void setCantPasajes(int cantPasajes) {
	this.cantPasajes = cantPasajes;
}

public void setCantEquipajeExtra(int cantEquipajeExtra) {
	this.cantEquipajeExtra = cantEquipajeExtra;
}

public void setCosto(float costo) {
	this.costo = costo;
}

public void setFechaReserva(LocalDate fechaReserva) {
	this.fechaReserva = fechaReserva;
}

void setCheckIn(CheckIn checkIn) {
	this.checkIn = checkIn;
}

}