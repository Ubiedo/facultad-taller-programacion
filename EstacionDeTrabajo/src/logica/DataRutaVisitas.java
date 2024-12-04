package logica;

public class DataRutaVisitas {
	private String ruta, aerolinea;
	private int visitas;
	private DataCiudad origen, destino;

	public DataRutaVisitas(String ruta, String aerolinea, int visitas, DataCiudad origen, DataCiudad destino) {
		this.ruta = ruta;
		this.aerolinea = aerolinea;
		this.visitas = visitas;
		this.origen = origen;
		this.destino = destino;
	}

	public String getRuta() {
		return ruta;
	}
	
	public String getAerolinea() {
		return aerolinea;
	}
	public int getVisitas() {
		return visitas;
	}
	public DataCiudad getOrigen() {
		return origen;
	}
	public DataCiudad getDestino() {
		return destino;
	}
}
