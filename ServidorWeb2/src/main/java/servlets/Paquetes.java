package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import publicar.*;

/**
 * Servlet implementation class Paquetes
 */
@WebServlet("/paquetes/*")
public class Paquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paquetes() {
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
            if (path.equals("/")) {
                listarPaquetes(request, response);
            }else {
                mostrarPaquete(request, response, path);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } 
	}
    
    private void listarPaquetes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	publicar.WebServicesService service = new publicar.WebServicesService();
        publicar.WebServices port = service.getWebServicesPort();
        try {
			List<DataPaquete> paquetes = port.listarDataPaquetes().getConjDataPaquete();
			//Set<DataPaquete> paquetes = paquetesL.stream().collect(Collectors.toSet());//new HashSet<>(Collections.addAll(paquetes, paquetesL.toArray(new DataPaquete[0])));
			request.setAttribute("paquetes", paquetes);
	    	request.getRequestDispatcher("/WEB-INF/paquetes/paquetes.jsp").forward(request, response);
        }catch(Exception ex){
        	ex.printStackTrace(); // Para ver el error en consola
        }
		
    }
    private void mostrarPaquete(HttpServletRequest request, HttpServletResponse response, String nombrePaquete)
			throws ServletException, IOException {
    	publicar.WebServicesService service = new publicar.WebServicesService();
        publicar.WebServices port = service.getWebServicesPort();
        try {
	        ParPaqueteRutas paquete = port.mostrarInfoPaquete(URLDecoder.decode(nombrePaquete.substring(1), StandardCharsets.UTF_8));
	        
	        //para controlar la visibilidad del boton comprar
	        TipoSesion sesion = (TipoSesion) request.getSession().getAttribute("tipo_sesion");
	        
	        boolean noComprado = false;
	        boolean tieneRutas = false;
	        boolean noEsCliente = false;
	
	        if (sesion == TipoSesion.CLIENTE) {
	        	String nickname = (String) request.getSession().getAttribute("sesion_id");
	            noComprado = !port.yaComproPaquete(nickname, URLDecoder.decode(nombrePaquete.substring(1), StandardCharsets.UTF_8));
	            tieneRutas = port.paqueteContieneRutas(URLDecoder.decode(nombrePaquete.substring(1), StandardCharsets.UTF_8));
	        }else {
	        	noEsCliente = true;
	        }
	
	
	        //compra
	        String pedido = request.getParameter("pedidoCompra");
	        if ("comprar".equals(pedido)) {
	            Float costo = Float.parseFloat(request.getParameter("costo"));
	            int diasValidos = Integer.parseInt(request.getParameter("validez"));
	            
	            
	            LocalDate fechaActual = LocalDate.now();
	            LocalDate fechaVencimiento = fechaActual.plusDays(diasValidos);
	            String nickname = (String) request.getSession().getAttribute("sesion_id");
	            
	            boolean compraExitosa = port.comprarPaquete(URLDecoder.decode(nombrePaquete.substring(1), StandardCharsets.UTF_8), nickname, fechaActual.toString(), costo, fechaVencimiento.toString());
	            request.setAttribute("compraExitosa", compraExitosa);
	            if (compraExitosa) {
	            	noComprado = false;
	            }
	        }
	        
	        request.setAttribute("noEsCliente", noEsCliente);
	        request.setAttribute("noComprado", noComprado);
	        request.setAttribute("tieneRutas", tieneRutas);
	        request.setAttribute("paquete", paquete);
	        request.getRequestDispatcher("/WEB-INF/paquetes/paquete.jsp").forward(request, response);
        }catch(Exception ex){
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
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
