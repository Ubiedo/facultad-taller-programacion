package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({logica.DataCliente.class, logica.DataAerolinea.class})
public abstract class DataUsuario {
	private String nickname;
	//protected String contraseña;
	private String nombre;
	private String email;
	private String contrasenha;
	private String imagen;
	//private boolean esCliente;
	
	
	
    public String getNickname() {
        return nickname;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getContrasenha() {
        return contrasenha;
    }
    public String getImagen() {
        return imagen;
    }
    public abstract boolean getEsCliente();
}
