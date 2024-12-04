package logica;

import java.time.LocalDate;
//import logica.enumerado.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataCliente extends DataUsuario{
	private String apellido;
	private String fechaNac;
	private Documento tipoDocumento;
	private String nroDocumento;
	private String nacionalidad;
	private String nicknameC;
	private String nombreC;
	private String emailC;
	private String contrasenhaC;
	private String imagenC;
	private boolean esCliente;
	public DataCliente(String nickname, String nombre, String apellido, String email, LocalDate fecha, String nroDocumento, String nacionalidad, Documento tipoDocumento , String contrasenha, String imagen, boolean esCliente) {
		this.nicknameC = nickname;
		this.nombreC = nombre;
		this.apellido = apellido;
		this.contrasenhaC = contrasenha;
		this.imagenC = imagen;
		this.emailC = email;
		this.fechaNac = fecha.toString();
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nacionalidad = nacionalidad;
		this.tipoDocumento = tipoDocumento;
		this.esCliente = esCliente;
	}
	public String getNickname() {
        return nicknameC;
    }
    public String getNombre() {
        return nombreC;
    }
    public String getEmail() {
        return emailC;
    }
    public String getContrasenha() {
        return contrasenhaC;
    }
    public String getImagen() {
        return imagenC;
    }
    
	public String getApellido() {
		return apellido;
	}
	
	public String getFechaNac() {
		return fechaNac;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public Documento getTipoDocumento() {
		return tipoDocumento;
	}
	
	public boolean escliente() {
		return esCliente;
	}
    public boolean getEsCliente() {
        return true;
    }
}