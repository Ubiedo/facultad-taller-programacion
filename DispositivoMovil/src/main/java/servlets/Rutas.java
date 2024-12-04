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

import java.io.IOException;
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
    	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
	        return;
    	}
    	String path = request.getPathInfo();
        if (path != null) {
            switch (path) {
                case "/":
                	listarRutas(request, response);
                    break;
                default:
                	WebServicesService service1 = new WebServicesService();
    		        WebServices port1 = service1.getWebServicesPort();
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
