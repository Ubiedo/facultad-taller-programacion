package logica;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CargarDatos {
	private Map<String, String> categorias;
	private Map<String, DataCiudad> ciudades;
	private Map<String, DataUsuario> usuarios;
	private Map<String, String> rutas;
	private Map<String, String> vuelos;
	private Map<String, String> paquetes;
	private Map<String, String[]> reservas;
	private static CargarDatos instancia;
	
	private CargarDatos() {
		categorias = new HashMap<>();
        ciudades = new HashMap<>();
        usuarios = new HashMap<>();
        rutas = new HashMap<>();
        vuelos = new HashMap<>();
        paquetes = new HashMap<>();
    }

    public static CargarDatos getInstancia() {
        if (instancia == null) {
            instancia = new CargarDatos();
        }
        return instancia;
    }
	
    public Map<String, DataUsuario> getUsuarios(){
		return usuarios;
	}
    public Map<String, String> getCategorias(){
		return categorias;
	}
    public Map<String, DataCiudad> getCiudades(){
		return ciudades;
	}
    public Map<String, String> getRutas(){
		return rutas;
	}
    public Map<String, String> getPaquetes(){
		return paquetes;
	}
    public Map<String, String> getVuelos(){
		return vuelos;
	}
    
    public Map<String, String[]> getReservas(){
		return reservas;
	}
	
	public void leerDatosCategorias(){
		Map<String, String> categorias = new HashMap<>();
		try (CSVReader csvreader = new CSVReaderBuilder(
		        new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Categorias.csv")))
		        .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
		        .build()) {
		    List<String[]> todaInfo = csvreader.readAll();
		    for (int i = 1; i < todaInfo.size(); i++) {
		        String[] lineaI = todaInfo.get(i);
		        if (lineaI.length > 1) {
		        categorias.put(lineaI[0], lineaI[1]);
		    }}
		} catch (IOException | CsvException e) {
		    e.printStackTrace();
		}

        this.categorias = categorias;
	}
	
	public List<String[]> leerDatosCiudades() {
	    Map<String, DataCiudad> ciudades = new HashMap<>();
	    List<String[]> todaInfo = new ArrayList<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Ciudades.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        todaInfo = csvreader.readAll();
	        // En i = 0 están los encabezados de las columnas
	        for (int i = 1; i < todaInfo.size(); i++) { 
	            String[] lineaI = todaInfo.get(i);
	            if (lineaI.length > 1) {
	            DataCiudad ciudad = new DataCiudad(lineaI[1], lineaI[2]);
	            ciudades.put(lineaI[0], ciudad);
	        }}
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    this.ciudades = ciudades;
	    return todaInfo;
	}

	
	public void leerDatosUsuarios() {
	    Map<String, String[]> aereos = new HashMap<>();
	    try (CSVReader leerAerolineas = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Usuarios-Aerolineas.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        List<String[]> todaInfoAero = leerAerolineas.readAll();
	        for (int i = 1; i < todaInfoAero.size(); i++) {
	            String[] lineaIA = todaInfoAero.get(i);
	            if (lineaIA.length > 1) {
	            aereos.put(lineaIA[0], lineaIA);
	        }}
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }

	    Map<String, String[]> clientes = new HashMap<>();
	    try (CSVReader leerClientes = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Usuarios-Clientes.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        List<String[]> todaInfoCli = leerClientes.readAll();
	        for (int c = 1; c < todaInfoCli.size(); c++) {
	            String[] lineaIC = todaInfoCli.get(c);
	            if (lineaIC.length > 1) {
	            clientes.put(lineaIC[0], lineaIC);
	        }}
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }

	    Map<String, DataUsuario> usuarios = new HashMap<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Usuarios.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        List<String[]> todaInfo = csvreader.readAll();
	        // En i = 0 están los encabezados de las columnas
	        for (int j = 1; j < todaInfo.size(); j++) {
	            String[] lineaI = todaInfo.get(j);
	            if (lineaI.length > 1) {
	                if ("A".equals(lineaI[1])) {
	                    String[] datosAereo = aereos.get(lineaI[0]);
	                    DataAerolinea usuario = new DataAerolinea(
	                            lineaI[2], lineaI[3], lineaI[4], datosAereo[2], datosAereo[1],
	                            lineaI[5], lineaI[6], false);
	                    usuarios.put(lineaI[0], usuario);
	                } else {
	                    String[] datosCliente = clientes.get(lineaI[0]);
	                    LocalDate fechaNac = LocalDate.parse(datosCliente[2], DateTimeFormatter.ofPattern("d/M/yyyy"));
	                    Documento tipoDocumento = Documento.valueOf(datosCliente[4].toUpperCase());
	                    DataCliente usuario = new DataCliente(
	                            lineaI[2], lineaI[3], datosCliente[1], lineaI[4], fechaNac,
	                            datosCliente[5], datosCliente[3], tipoDocumento,
	                            lineaI[5], lineaI[6], true);
	                    usuarios.put(lineaI[0], usuario);
	                }
	            }
	        }
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    this.usuarios = usuarios;
	}

	
	
	public List<String[]> leerRutasVuelo() {
	    Map<String, String> rutas = new HashMap<>();
	    List<String[]> todaInfo = new ArrayList<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024RutasVuelosv1-2.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        todaInfo = csvreader.readAll();
	        // En i = 0 están los encabezados de las columnas
	        for (int i = 1; i < todaInfo.size(); i++) { 
	            String[] lineaI = todaInfo.get(i);
	            if (lineaI.length > 1) {
	                rutas.put(lineaI[0], lineaI[2]);
	            }
	        }
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    this.rutas = rutas;
	    return todaInfo;
	}

	
	
	public List<String[]> leerVuelos() {
	    Map<String, String> vuelos = new HashMap<>();
	    List<String[]> todaInfo = new ArrayList<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Vuelosv1-2.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        todaInfo = csvreader.readAll();
	        for (int i = 1; i < todaInfo.size(); i++) { 
	            String[] lineaI = todaInfo.get(i);
	            if (lineaI.length > 1) {
	                vuelos.put(lineaI[0], lineaI[3]);
	            }
	        }
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    this.vuelos = vuelos;
	    return todaInfo;
	}

	
	public List<String[]> leerPaquetes() {
	    Map<String, String> paquetes = new HashMap<>();
	    List<String[]> todaInfo = new ArrayList<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Paquetes.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        todaInfo = csvreader.readAll();
	        for (int i = 1; i < todaInfo.size(); i++) { 
	            String[] lineaI = todaInfo.get(i);
	            if (lineaI.length > 1) {
	            paquetes.put(lineaI[0], lineaI[1]);
	        }}
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    this.paquetes = paquetes;
	    return todaInfo;
	}

	
	public List<String[]> leerPaquetesRutas() {
	    List<String[]> todaInfo = new ArrayList<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024PaquetesRutas.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        todaInfo = csvreader.readAll();
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    return todaInfo;
	}

	
	public List<String[]> leerReservas() {
	    List<String[]> todaInfo = new ArrayList<>();
	    Map<String, String[]> reservas = new HashMap<>();
	    try (CSVReader csvreader = new CSVReaderBuilder(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Reservas.csv")))
	            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
	            .build()) {
	        todaInfo = csvreader.readAll();
	        for (int i = 1; i < todaInfo.size(); i++) { 
	            String[] lineaI = todaInfo.get(i);
	            String[] lineaIA = todaInfo.get(i);
	            if (lineaI.length > 1) {
	            reservas.put(lineaI[0], lineaIA);
	        }}
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    this.reservas = reservas;
	    return todaInfo;
	}

	
	
	public Map<String, Set<DataPasaje>> leerPasajes(){
		List<String[]> todaInfo = new ArrayList<>();
		Map<String, Set<DataPasaje>> pasajesReserva = new HashMap<>(); //key: referencia a reserva
		try (CSVReader csvreader = new CSVReaderBuilder(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Pasajes.csv")))
				.withCSVParser(new CSVParserBuilder().withSeparator(';').build())
		        .build()) {
            todaInfo = csvreader.readAll();
            //en i 0 estan los encabezados de las columnas
            for (int i = 1; i < todaInfo.size(); i++) { 
            	String[] lineaI = todaInfo.get(i);
            	if (lineaI.length > 1) {
            	DataPasaje pasaje = new DataPasaje(lineaI[2], lineaI[3]);
            	if (pasajesReserva.containsKey(lineaI[1])) {
            		pasajesReserva.get(lineaI[1]).add(pasaje);
            	}else {
            		Set<DataPasaje> setPasajes = new HashSet<>();
            		setPasajes.add(pasaje);
            		pasajesReserva.put(lineaI[1], setPasajes);
            	}
            }}
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } 
		return pasajesReserva;
	}
	
	public List<String[]> leerComprasPaquetes(){
		List<String[]> todaInfo = new ArrayList<>();
		try (CSVReader csvreader = new CSVReaderBuilder(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024ComprasPaquetes.csv")))
				.withCSVParser(new CSVParserBuilder().withSeparator(';').build())
		        .build()) {
            todaInfo = csvreader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } 
		return todaInfo;
	}
	
	public List<String[]> leerCheckIn(){
		List<String[]> todaInfo = new ArrayList<>();
		try (CSVReader csvreader = new CSVReaderBuilder(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Checkin.csv")))
				.withCSVParser(new CSVParserBuilder().withSeparator(';').build())
		        .build()) {
            todaInfo = csvreader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } 
		return todaInfo;
	}
	
	public List<String[]> leerSeguidos(){
		List<String[]> todaInfo = new ArrayList<>();
		try (CSVReader csvreader = new CSVReaderBuilder(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("datos/2024Seguidos.csv")))
				.withCSVParser(new CSVParserBuilder().withSeparator(';').build())
		        .build()) {
            todaInfo = csvreader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } 
		return todaInfo;
	}
}
