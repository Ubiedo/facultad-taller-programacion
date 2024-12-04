package logica;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataAerolinea extends DataUsuario{
	private String web;
	private String descripcion;
	private String nicknameA;
	private String nombreA;
	private String emailA;
	private String contrasenhaA;
	private String imagenA;
	private boolean esCliente;
	public DataAerolinea(String nickname, String nombre, String email, String web, String descripcion, String contrasenha, String imagen, boolean esCliente) {
		this.nicknameA = nickname;
		this.nombreA = nombre;
		this.web = web;
		this.contrasenhaA = contrasenha;
		this.imagenA = imagen;
		this.emailA = email;
		this.descripcion = descripcion;
		this.esCliente = esCliente;
	}
	
	public String getNickname() {
        return nicknameA;
    }
    public String getNombre() {
        return nombreA;
    }
    public String getEmail() {
        return emailA;
    }
    public String getContrasenha() {
        return contrasenhaA;
    }
    public String getImagen() {
        return imagenA;
    }
	
	public String getWeb() {
		return web;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public boolean escliente() {
        return esCliente;
    }
    public boolean getEsCliente() {
        return false;
    }
}