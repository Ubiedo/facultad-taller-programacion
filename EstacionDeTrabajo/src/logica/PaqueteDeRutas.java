package logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PaqueteDeRutas {
	
	private String nombre, descripcion, imagen;
	private LocalDate fechaAlta;
	private Integer cantRutas, diasValidez;
	private Float descuento, costo, costoDesc;
	private Set<ClientePaquete> clientes;
	private Set<PaqueteRuta> rutas;
	
	public PaqueteDeRutas(String nombre, String descripc, Integer validez, Float descuento, LocalDate fecha, String img) {
		this.nombre = nombre;
		this.descripcion = descripc;
		this.fechaAlta = fecha;
		this.cantRutas = 0;
		this.diasValidez = validez;
		this.descuento = descuento;
		this.costo = 0f;
		this.costoDesc= 0f;
		this.clientes = new HashSet<ClientePaquete>();
		this.rutas = new HashSet<PaqueteRuta>();
		this.imagen = img;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String desc) {
		this.descripcion = desc;
	}
	
	public void setFechaAlta(LocalDate fecha) {
		this.fechaAlta = fecha;
	}
	
	public void setCantRutas(Integer cantRutas) {
		this.cantRutas = cantRutas;
	}
	
	public void setDescuento(Float desc) {
		this.descuento = desc;
	}
	
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	
	public void setImagen(String img) {
	    imagen = img;
    }
	
	public String getImagen() {
        return imagen;
    }
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public LocalDate getFechaAlta() {
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
	
	public Set<PaqueteRuta> getRutas() {
		return rutas;
	}
	
	public Set<ClientePaquete> getClientes() {
		return clientes;
	}
	
	public Boolean agregarRutaPaquete(RutaDeVuelo ruta, Integer cantidad, Asiento tipoAsiento) {
		Boolean yaexiste = false;
		for (PaqueteRuta r: rutas) {
			String nombreRutaExistente = r.getRuta().getNombre();
			if (nombreRutaExistente == ruta.getNombre()) {
				yaexiste = true;
			}
		}
		if (!yaexiste) {
			PaqueteRuta paqueteRuta = new PaqueteRuta(cantidad, tipoAsiento, ruta);
			rutas.add(paqueteRuta);
			cantRutas ++;
			if (tipoAsiento == Asiento.ejecutivo) {
				costo = costo + (ruta.getEjecutivoBase()*cantidad);
				costoDesc = costoDesc + (ruta.getEjecutivoBase()*cantidad*(1 - descuento/100));
			}else {
				costo = costo +(ruta.getTuristaBase()*cantidad);
				costoDesc = costoDesc + (ruta.getTuristaBase()*cantidad*(1 - descuento/100));
			}
		}
		return !yaexiste;
	}
	
	public void addClientePaquete(ClientePaquete clientePaquete) {
		clientes.add(clientePaquete);
	}
	public DataPaquete getDataPaquete() {
		return new DataPaquete(nombre, descripcion, fechaAlta, cantRutas, diasValidez, descuento, costo, costoDesc, imagen);
	}
	public Map<String, DataRutaAsiento> getRutasCantidad() {
		Map<String, DataRutaAsiento> resultado = new HashMap<String, DataRutaAsiento>();
		Iterator iter = rutas.iterator();
		while (iter.hasNext()) {
			PaqueteRuta paqueteR = (PaqueteRuta) iter.next();
			DataRutaAsiento value = new DataRutaAsiento(paqueteR.getRuta().getNombre(), paqueteR.getCantidad(), paqueteR.getAsiento());
		    resultado.put(paqueteR.getRuta().getNombre(), value);
		}
		return resultado;
	}
}
