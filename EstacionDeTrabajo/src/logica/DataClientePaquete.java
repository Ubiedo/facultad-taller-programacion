package logica;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataClientePaquete {
	private String fecha;
	private String vencimiento;
	private float costo;
	private String cliente;
	private String paquete;
	private String imgPaquete;
	
	public DataClientePaquete(LocalDate fechaCompra, LocalDate vencimiento2, float costo, String cliente, String paquete, String imgPaquete) {
		this.fecha= fechaCompra.toString();
		this.vencimiento = vencimiento2.toString();
		this.costo = costo;
		this.cliente = cliente;
		this.paquete = paquete;
		this.imgPaquete = imgPaquete;
	}
	
	public String getFecha(){
		return fecha;
	}

	public String getVencimiento(){
		return vencimiento;
	}


	public float getCosto(){
		return costo;
	}
	
	public String getCliente(){
		return cliente;
	}
	
	public String getPaquete(){
		return paquete;
	}
	
	public String getImgPaquete(){
        return imgPaquete;
    }
}
