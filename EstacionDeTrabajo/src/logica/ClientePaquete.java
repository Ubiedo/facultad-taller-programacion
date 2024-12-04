package logica;
import java.time.LocalDate;
import java.util.Map;

public class ClientePaquete {	
	private LocalDate fecha;
	private LocalDate vencimiento;
	private float costo;
	private Cliente cliente;
	private PaqueteDeRutas paquete;
	private Map<String, DataRutaAsiento> rutasDisponibles; //cuantos vuelos canjeables tiene de cada ruta. dismunuye con cada canje

public ClientePaquete(LocalDate fechaCompra, LocalDate vencimiento2, float costo, Cliente cliente, PaqueteDeRutas paquete) {
	this.fecha=fechaCompra;
	this.vencimiento = vencimiento2;
	this.costo = costo;
	this.cliente = cliente;
	this.paquete = paquete;
	this.rutasDisponibles = paquete.getRutasCantidad();
}

public LocalDate getFecha(){
	return fecha;
}

public LocalDate getVencimiento(){
	return vencimiento;
}


public float getCosto(){
	return costo;
}

public Cliente getCliente(){
	return cliente;
}

public PaqueteDeRutas getPaquete(){
	return paquete;
}
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}

public void setVencimiento(LocalDate vencimiento) {
	this.vencimiento = vencimiento;
}

public void setCosto(float costo) {
	this.costo = costo;
}
public boolean isPaquete(String nombre) {
	if (paquete.getNombre().equals(nombre)) {
		return true;
	}
	return false;
}
public DataRutaAsiento canjesDisponiblesRuta(String nombreRuta) {
	DataRutaAsiento resultado = new DataRutaAsiento("", 0, Asiento.turista);
	if (rutasDisponibles.containsKey(nombreRuta)) {
		resultado = rutasDisponibles.get(nombreRuta);
	}
	return resultado;
}
public void canjearRuta(String nombreRuta, int cantPasajes) {
	if (rutasDisponibles.containsKey(nombreRuta)) {
		DataRutaAsiento value = rutasDisponibles.get(nombreRuta);
		DataRutaAsiento nueva = new DataRutaAsiento(value.getNombre(), value.getCantidad()-cantPasajes, value.getAsiento());
		rutasDisponibles.put(nombreRuta, nueva);
	}
}

}