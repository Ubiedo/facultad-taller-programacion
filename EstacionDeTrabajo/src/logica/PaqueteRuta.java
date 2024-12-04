package logica;


public class PaqueteRuta {
	
	private Integer cantidad;
	private Asiento tipoAsiento;
	private RutaDeVuelo ruta;
	
	public PaqueteRuta(Integer cant, Asiento asiento, RutaDeVuelo rutaDeVuelo) {
		this.cantidad = cant;
		this.tipoAsiento = asiento;
		this.ruta = rutaDeVuelo;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setTipoAsiento(Asiento asiento) {
	    this.tipoAsiento = asiento;
	}
	
	public void setRutas(RutaDeVuelo ruta) {
	    this.ruta = ruta;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public Asiento getAsiento() {
		return tipoAsiento;
	}
	
	public RutaDeVuelo getRuta() {
		return ruta;
	}

}
