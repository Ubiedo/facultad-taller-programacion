package logica;

import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DatosPR {
	private DataPaquete packageData;
	private Map<String, String> nombresImgRutas;
	private int cantE;
	private int cantT;
	
	public DatosPR(DataPaquete packageData, Map<String, String> rutas, int cantE, int cantT) {
		this.packageData = packageData;
		this.nombresImgRutas = rutas;
		this.cantE = cantE;
		this.cantT = cantT;
	}
	
	public DataPaquete getDataPaquete() {
		return packageData;
	}
	public Map<String, String> getNomImgRutas() {
		return nombresImgRutas;
	}
	public int getCantE() {
		return cantE;
	}
	public int getCantT() {
		return cantT;
	}
	public int getCantRutas() {
		return nombresImgRutas.size();
	}
}
