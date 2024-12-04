package logica;

import java.time.LocalDate;
//import logica.enumerado.*;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataClienteVuelo {


	private String fechaReserva;
	private Set<DataPasaje> pasajes;
	private Asiento tipoAsiento; 
	private int cantPasajes;
	private int cantEquipajeExtra;
	private float costo;
	private String vuelo;
	private String ruta;
	private String aerolinea;
	private String nickAerolinea;

	public DataClienteVuelo(Asiento tipoAsiento, int cantPasajes, int cantEquipajeExtra, float costo, LocalDate fechaReserva,
			Set<DataPasaje> pasajes, String vuelo, String ruta, String aerolinea, String nickAerolinea) {
		this.tipoAsiento= tipoAsiento;
		this.cantPasajes = cantPasajes;
		this.cantEquipajeExtra = cantEquipajeExtra;
		this.costo = costo;
		this.fechaReserva = fechaReserva.toString();
		this.pasajes = pasajes;
		this.vuelo = vuelo;
		this.ruta = ruta;
		this.aerolinea = aerolinea;
		this.nickAerolinea = nickAerolinea;
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

	public String getFechaReserva(){
		return fechaReserva;
	}
	
	public Set<DataPasaje> getPasajes(){
		return pasajes;
	}
	
	public String getVuelo(){
		return vuelo;
	}
	
	public String getRuta(){
        return ruta;
    }
	
	public String getAerolinea(){
        return aerolinea;
    }
	
	public String getNickAerolinea(){
        return nickAerolinea;
    }
	
	

}

