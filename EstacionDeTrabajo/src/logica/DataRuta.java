package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

//import logica.enumerado.EstadoRuta;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataRuta {

	private String nombre, descripcion, descCorta, imagen, origen, destino, video;
	private String hora;
	private String fechaAlta;
	private Float turistaBase, ejecutivoBase, unidadEquipajeExtra;
	private EstadoRuta estado;
	private Set<String> categorias;
	
	public DataRuta(String namee, String description, LocalTime hour, LocalDate datee, Float touristBase, Float ejecutivBase, Float unitEquipExtra, EstadoRuta stateRuta, String imagen, String descCorta, String origen, String destino, Set<String> categorias, String video) {
	    this.nombre = namee;
		this.descripcion = description;
		this.hora = hour.toString();
		this.fechaAlta = datee.toString();
		this.turistaBase = touristBase;
		this.ejecutivoBase = ejecutivBase;
		this.unidadEquipajeExtra = unitEquipExtra;
		this.estado = stateRuta;
		this.imagen = imagen;
		this.descCorta = descCorta;
		this.origen = origen;
		this.destino = destino;
		this.categorias = categorias;
		this.video = video;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getHora() {
		return hora;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public Float getTuristaBase() {
		return turistaBase;
	}
	public Float getEjecutivoBase() {
		return ejecutivoBase;
	}
	public Float getUnidadEquipajeExtra() {
		return unidadEquipajeExtra;
	}
	public EstadoRuta getEstado() {
        return estado;
    }
	public String getImagen() {
        return imagen;
    }
	public String getDescCorta() {
        return descCorta;
    }
	public String getOrigen() {
        return origen;
    }
	public String getDestino() {
        return destino;
    }
	
	public String getVideo() {
        return video;
    }
	
	public Set<String> getCategorias() {
    	return categorias;
    }
	 @Override
	    public boolean equals(Object objeto) {
	        if (this == objeto) {
	        	return true;
	        }
	        if (objeto == null || getClass() != objeto.getClass()) {
	        	return false;
	        }

	        DataRuta dataRuta = (DataRuta) objeto;
	        return nombre.equals(dataRuta.nombre) &&
	               descripcion.equals(dataRuta.descripcion) &&
	               fechaAlta.equals(dataRuta.fechaAlta) &&
	               turistaBase.equals(dataRuta.turistaBase) &&
	               ejecutivoBase.equals(dataRuta.ejecutivoBase) &&
	               unidadEquipajeExtra.equals(dataRuta.unidadEquipajeExtra);
	    }


	    @Override
	    public int hashCode() {
	        return Objects.hash(nombre, descripcion, fechaAlta, turistaBase, ejecutivoBase, unidadEquipajeExtra);
	    }
	    
}
