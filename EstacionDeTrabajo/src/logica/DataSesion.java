package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

//import logica.enumerado.TipoSesion;
@XmlAccessorType(XmlAccessType.FIELD)
public class DataSesion {
	private TipoSesion tipo;
	private String nickname, imagen;
	
	public DataSesion(TipoSesion sessionType, String apodo, String image) {
		tipo = sessionType;
		nickname = apodo;
		imagen = image;
	}
	
	public TipoSesion getTipo() {
		return tipo;
	}
	public String getNickname() {
		return nickname;
	}
	public String getImagen() {
		return imagen;
	}
}
