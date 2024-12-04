package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataColeccion {
	private Set<String> ConjString;
	private Set<DataClienteVuelo> ConjDataClienteVuelo;
	private Set<DataClientePaquete> ConjDataClientePaquete;
	private Set<DataRuta> ConjDataRuta;
	private List<DataRutaAsiento> ConjDataRutaAsiento;
	private Set<DataReserva> ConjDataReserva;
	private Set<DataPaqueteRuta> ConjDataPaqueteRuta;
	private Set<DataPaquete> ConjDataPaquete;
	private Set<DataPasaje> ConjDataPasaje;
	private Map<String,ArrayList<String>> MapStringListString;
	private List<DataImagenNombreType> ConjDataImagenNombreType;
	
	public DataColeccion(){
		
	}
	
	public Set<String> getConjString() {
	    return ConjString;
	}

	public void setConjString(Set<String> conjString) {
	    ConjString = conjString;
	}

	public Set<DataClienteVuelo> getConjDataClienteVuelo() {
	    return ConjDataClienteVuelo;
	}

	public void setConjDataClienteVuelo(Set<DataClienteVuelo> conjDataClienteVuelo) {
	    ConjDataClienteVuelo = conjDataClienteVuelo;
	}

	public Set<DataClientePaquete> getConjDataClientePaquete() {
	    return ConjDataClientePaquete;
	}

	public void setConjDataClientePaquete(Set<DataClientePaquete> conjDataClientePaquete) {
	    ConjDataClientePaquete = conjDataClientePaquete;
	}

	public Set<DataRuta> getConjDataRuta() {
	    return ConjDataRuta;
	}

	public void setConjDataRuta(Set<DataRuta> conjDataRuta) {
	    ConjDataRuta = conjDataRuta;
	}

	public List<DataRutaAsiento> getConjDataRutaAsiento() {
	    return ConjDataRutaAsiento;
	}

	public void setConjDataRutaAsiento(List<DataRutaAsiento> conjDataRutaAsiento) {
	    ConjDataRutaAsiento = conjDataRutaAsiento;
	}
	
	public Set<DataReserva> getConjDataReserva() {
	    return ConjDataReserva;
	}

	public void setConjDataReserva(Set<DataReserva> conjDataReserva) {
	    ConjDataReserva = conjDataReserva;
	}

	public Set<DataPaqueteRuta> getConjDataPaqueteRuta() {
	    return ConjDataPaqueteRuta;
	}

	public void setConjDataPaqueteRuta(Set<DataPaqueteRuta> conjDataPaqueteRuta) {
	    ConjDataPaqueteRuta = conjDataPaqueteRuta;
	}

	public Set<DataPaquete> getConjDataPaquete() {
	    return ConjDataPaquete;
	}

	public void setConjDataPaquete(Set<DataPaquete> conjDataPaquete) {
	    ConjDataPaquete = conjDataPaquete;
	}

	public Set<DataPasaje> getConjDataPasaje() {
	    return ConjDataPasaje;
	}

	public void setConjDataPasaje(Set<DataPasaje> conjDataPasaje) {
	    ConjDataPasaje = conjDataPasaje;
	}

	public Map<String, ArrayList<String>> getMapStringListString() {
	    return MapStringListString;
	}

	public void setMapStringListString(Map<String, ArrayList<String>> mapStringListString) {
	    MapStringListString = mapStringListString;
	}
	
	public void setConjDataImagenNombreType(List<DataImagenNombreType> conjDataImagenNombreType) {
		ConjDataImagenNombreType = conjDataImagenNombreType;
	}
	
	public List<DataImagenNombreType> getConjDataImagenNombreType() {
		return ConjDataImagenNombreType;
	}
}
