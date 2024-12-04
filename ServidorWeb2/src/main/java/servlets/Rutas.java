package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import publicar.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Servlet implementation class rutas
 */
@WebServlet("/rutas/*")
@MultipartConfig
public class Rutas extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rutas() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * inicializa la sesión si no estaba creada 
	 * @param request 
	 */
	public static void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
        if (session.getAttribute("tipo_sesion") == null) {
    		session.setAttribute("tipo_sesion", publicar.TipoSesion.INVITADO);
    		request.getSession().setAttribute("sesion_id", "");
    		request.getSession().setAttribute("az", "false");
    	}
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initSession(request);
    	String path = request.getPathInfo();
        if (path != null) {
            switch (path) {
                case "/":
                	listarRutas(request, response);
                    break;
        		case "/crear":
        			publicar.WebServicesService service = new publicar.WebServicesService();
    		        publicar.WebServices port = service.getWebServicesPort();
    		        try {
	        			if (request.getSession().getAttribute("tipo_sesion") == TipoSesion.AEROLINEA) {
	            			List<String> ciudades = port.listarCiudades().getConjString();
	            			request.setAttribute("ciudades", ciudades);
	            			List<String> categorias = port.listarCategorias().getConjString();
	            			request.setAttribute("categorias", categorias);
	            			request.getRequestDispatcher("/WEB-INF/rutas/crearRuta.jsp").forward(request, response);
	        			} else {
	        			    response.sendRedirect(request.getContextPath() + "/rutas");
	        			}
    		        }catch(Exception ex) {
    		        	ex.printStackTrace(); // Para ver el error en consola
    		        }
        			break;
                case "/creada":
                	crearRuta(request, response);
                    break;
                default:
                	publicar.WebServicesService service1 = new publicar.WebServicesService();
    		        publicar.WebServices port1 = service1.getWebServicesPort();
    		        try {
	                	path = path.substring(1);
	                    if (port1.existeRuta(path)) {
	                    	String nicknameAerolineaDeRuta = port1.nicknameAerolineaDeRuta(path);
	                    	request.setAttribute("nicknameAerolinea", nicknameAerolineaDeRuta);
	                    	String nombreAerolineaDeRuta = port1.nombreAerolineaDeRuta(path);
	                    	request.setAttribute("nombreAerolinea", nombreAerolineaDeRuta);
	                    	
	                    	String ciudadOrigen = port1.nombreCiudadOrigenRuta(path);
	                    	request.setAttribute("ciudadOrigen", ciudadOrigen);
	                    	String ciudadDestino = port1.nombreCiudadDestinoRuta(path);
	                    	request.setAttribute("ciudadDestino", ciudadDestino);
	                    	
	                    	DataRuta DTRuta = port1.listarDatosRuta(path);
	                    	request.setAttribute("ruta", DTRuta);
	                    	
	                    	String categoriasString = "";
	                       	for (String categoria: DTRuta.getCategorias()) {
	                       		if (categoriasString == "")
	                       			categoriasString = categoria;
	                       		else
	                       			categoriasString = categoriasString + ", " + categoria;
	                       	}
	                       	if (categoriasString == "")
	                       		categoriasString = "Sin categorías";
	                       	request.setAttribute("categoriasString", categoriasString);
	                    	
	                       	
	            			if (DTRuta.getEstado() != EstadoRuta.CONFIRMADA) {
	            				if (request.getSession().getAttribute("tipo_sesion") == TipoSesion.AEROLINEA) {
	            					String aerolinea = (String) request.getSession().getAttribute("sesion_id");
	                				String aeroRuta = port1.nicknameAerolineaDeRuta(path);
	                				if (aerolinea.compareTo(aeroRuta) != 0)
	                					response.sendError(HttpServletResponse.SC_FORBIDDEN);
	                				else {
	                					port1.visitarRuta(path);
	                					request.getRequestDispatcher("/WEB-INF/rutas/ruta.jsp").forward(request, response);
	                				}
	            				} else
	            					response.sendError(HttpServletResponse.SC_FORBIDDEN);
	            			} else {
	            				port1.visitarRuta(path);
	            				request.getRequestDispatcher("/WEB-INF/rutas/ruta.jsp").forward(request, response);
	            			}
	                    } else {
	                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	                    }
                    break;
    		       }catch(Exception ex) {
    		    	   ex.printStackTrace(); // Para ver el error en consola
    		       }
            }
        } else {
        	listarRutas(request, response);
        }
	}

    private void listarRutas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	publicar.WebServicesService service = new publicar.WebServicesService();
        publicar.WebServices port = service.getWebServicesPort();
        try {
	    	List<String> rutas = port.listarRutasConfirmadas().getConjString();
	    	Set<DataRuta> DTRutas = new HashSet<>();
	    	for (String ruta : rutas) {
	    		DTRutas.add(port.listarDatosRuta(ruta));
	    	}
	    	
	    	request.setAttribute("rutas", DTRutas);
	    	request.getRequestDispatcher("/WEB-INF/rutas/rutas.jsp").forward(request, response);
        }catch(Exception ex) {
        	ex.printStackTrace(); // Para ver el error en consola
        }
    }
    
    private void crearRuta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	publicar.WebServicesService service = new publicar.WebServicesService();
        publicar.WebServices port = service.getWebServicesPort();
        try {
	        String nombreRuta = request.getParameter("identificador");
	        String descripcion = request.getParameter("descripcion");
	        String descCorta = request.getParameter("descCorta");
	        String ciudadOrigen = request.getParameter("ciudadOrigen");
	        String ciudadDestino = request.getParameter("ciudadDestino");
	        String horaS = request.getParameter("hora");
	        String precioTuristaS = request.getParameter("precioTurista");
	        String precioEjecutivoS = request.getParameter("precioEjecutivo");
	        String precioEquipajeExtraS = request.getParameter("precioEquipaje");
	        String[] categoriasA = request.getParameterValues("categorias");
	        String imagen = "";
	        String video = request.getParameter("video");
	       
	        Float precioTurista = Float.parseFloat(precioTuristaS);
	        Float precioEjecutivo = Float.parseFloat(precioEjecutivoS);
	        Float precioEquipajeExtra = Float.parseFloat(precioEquipajeExtraS);
	        LocalTime hora = LocalTime.parse(horaS);
	        LocalDate fActual = LocalDate.now();
	        Set<String> categoriasS = new HashSet<String>();
	        if (categoriasA != null) {
	        	categoriasS.addAll(Arrays.asList(categoriasA)); 
	        }
	         
	        
	        String aerolinea = (String) request.getSession().getAttribute("sesion_id");
	        
	        //obtener la imagen
	        
	        Part filePart = request.getPart("imagen-c"); 
	        if (filePart != null && filePart.getSize() > 0) {
	            imagen = filePart.getSubmittedFileName(); // Obtiene el nombre del archivo
	        }
	        
	        DataColeccion categorias = new DataColeccion();
	        List<String> categoriasL = new ArrayList<>(categoriasS);
	        categorias.setconjString(categoriasL);
	    	Integer resultado = port.altaRutaVuelo(aerolinea, nombreRuta, descripcion, hora.toString(), precioTurista, precioEjecutivo, 
	    			precioEquipajeExtra, ciudadOrigen, ciudadDestino, fActual.toString(), categorias, imagen, descCorta, EstadoRuta.INGRESADA, video);
	    	
	    	if (resultado == 0 && !imagen.isEmpty()) {
			    try (InputStream inputStream = filePart.getInputStream();
		            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
		           
		           byte[] buffer = new byte[1024];
		           int bytesRead;
		           
		           // Leer todo el contenido del archivo en el ByteArrayOutputStream
		           while ((bytesRead = inputStream.read(buffer)) != -1) {
		               byteArrayOutputStream.write(buffer, 0, bytesRead);
		           }
		           
		           // Convertir a byte[] y enviar al método postFile solo una vez
		           byte[] fileData = byteArrayOutputStream.toByteArray();
		           port.postFile(imagen, fileData);
		           
		       } catch (IOException e) {
		           e.printStackTrace();
		       }
	    	}
	    	if (resultado != 0) {
	    		DataRuta rutaV = new DataRuta();
	    		rutaV.setDescCorta(descCorta);
	    		rutaV.setDescripcion(descripcion);
	    		rutaV.setDestino(ciudadDestino);
	    		rutaV.setEjecutivoBase(precioEjecutivo);
	    		rutaV.setEstado(EstadoRuta.INGRESADA);
	    		rutaV.setFechaAlta(fActual.toString());
	    		rutaV.setHora(hora.toString());
	    		rutaV.setImagen(imagen);
	    		rutaV.setNombre(nombreRuta);
	    		rutaV.setOrigen(ciudadOrigen);
	    		rutaV.setTuristaBase(precioTurista);
	    		rutaV.setUnidadEquipajeExtra(precioEquipajeExtra);
	    		rutaV.setCategorias(categoriasL);
	    		rutaV.setVideo(video);
	    		request.setAttribute("rutaV", rutaV);
	    		List<String> ciudades = port.listarCiudades().getConjString();
				request.setAttribute("ciudades", ciudades);
				List<String> categoriasTotales = port.listarCategorias().getConjString();
				request.setAttribute("categorias", categoriasTotales);
	    	}
	        
	    	request.setAttribute("resultado", resultado);
	        request.getRequestDispatcher("/WEB-INF/rutas/crearRuta.jsp").forward(request, response);
        }catch(Exception ex) {
        	ex.printStackTrace(); // Para ver el error en consola
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
