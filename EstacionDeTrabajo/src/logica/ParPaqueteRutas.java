package logica;

import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class ParPaqueteRutas {

	private DataPaquete  paquete;
	private Map<String, String> nombreImgRutas;
	private Integer cantRutas, cantEjecutivo, cantTurista;
	
	public ParPaqueteRutas(DataPaquete dtPaquete, Map<String, String> rutas, Integer rutasCant, Integer ejecutivo, Integer turista) {
		paquete = dtPaquete;
		nombreImgRutas = rutas;
		cantRutas = rutasCant;
		cantEjecutivo = ejecutivo;
		cantTurista = turista;
	}
	
	public DataPaquete getPaquete() {
		return paquete;
	}
	public Map<String, String> getNombImgRutas(){
		return nombreImgRutas;
	}
	public Integer getCantRutas() {
		return cantRutas;
	}
	public Integer getCantEjecutivo() {
		return cantEjecutivo;
	}
	public Integer getCantTurista() {
		return cantTurista;
	}
	
}
