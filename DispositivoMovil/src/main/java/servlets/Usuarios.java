package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicar.*;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;
/**
 * Servlet implementation class usuarios
 */
@WebServlet("/usuarios/*")
@MultipartConfig
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private publicar.WebServicesService service = new publicar.WebServicesService();
	private publicar.WebServices port = service.getWebServicesPort();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
        
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
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
            	        response.sendRedirect(request.getContextPath() + "/rutas");
                	}
                	break;
                case "/acceso":
                	request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
                	break;
                case "/logout":
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
            	        cerrarSesion(request, response);
                	}
                	break;
                case "/reservas":
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
                    	cargarReservasSinCheckIn(request, response);
                	}
                	break;
                case "/VerReserva":
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
                    	verReserva(request, response);
                	}
                	break;
                case "/checkInConsulta":
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
                		checkInConsulta(request, response);
                	}
                case "/cargarReservas":
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
                    	DataUsuario DTUsuario = port.listarDatosUsuarioNickname((String) request.getSession().getAttribute("sesion_id"));  
                    	request.setAttribute("perfil", DTUsuario);
                    	request.getRequestDispatcher("/WEB-INF/usuarios/reservas.jsp").forward(request, response);
                	}
                	break;
                case "/consultarReservas":
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
                    	consultarReservas(request, response);
                	}
                	break;
                default:
                	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
            	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
                	} else {
                    	response.sendError(HttpServletResponse.SC_NOT_FOUND);
                	}
                	break;
            }
        } else {
        	if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
    	        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
        	} else {
    	        response.sendRedirect(request.getContextPath() + "/rutas");
        	}
        }
	}
    
	//VER RESERVA
	private void verReserva(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String cliente = (String) request.getSession().getAttribute("sesion_id");
    	String nombreVuelo = request.getParameter("nombreVuelo");
    	DataClienteVuelo reserva = port.verReserva(cliente, nombreVuelo);
    	
    	//REALIZAR CHECK IN
    	Boolean checkInExitoso = null;
    	String pedido = request.getParameter("formId");
        if ("checkIn".equals(pedido)) {
        	 checkInExitoso = port.realizarCheckIn(cliente, nombreVuelo, LocalDate.now().toString());
        }
        String exito;
        if(checkInExitoso != null) {
        	exito = checkInExitoso.toString();
        }else {
        	exito = "null";
        }
        request.setAttribute("checkInExitoso", exito);
    	request.setAttribute("reserva", reserva);
        request.getRequestDispatcher("/WEB-INF/checkin/reserva.jsp").forward(request, response);
	}
	
	//LISTAR RESERVAS
	private void cargarReservasSinCheckIn(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String cliente = (String) request.getSession().getAttribute("sesion_id");
		List<DataReserva> reservas = port.reservasSinCheckIn(cliente).getConjDataReserva();
		request.setAttribute("reservas", reservas);
        request.getRequestDispatcher("/WEB-INF/checkin/reservasSinCheckIn.jsp").forward(request, response);
		
	}
	
    // CERRAR SESION
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    request.getSession().setAttribute("tipo_sesion", TipoSesion.INVITADO);
	    request.getSession().setAttribute("sesion_id", "");
	    // redirigir a acceso
        response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
    }
    
    // INICIAR SESION
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String identificador = request.getParameter("identificador");
    	Boolean mail = identificador.contains("@");
	    DataSesion resultado = port.loginUsuario(identificador, request.getParameter("password"), mail);
	    if (resultado.getTipo() == TipoSesion.INVITADO || !port.esCliente(resultado.getNickname())) {
	    	// fallo el login
	    	request.setAttribute("show-form", "log");
	    	request.setAttribute("error-form-usuarios", "Identificador o contraseña de cliente incorrectos, porfavor reescriba los datos y vuelva a intentar.");
	    	// set datos que mando
	    	request.setAttribute("id", identificador);
	    	request.setAttribute("password", request.getParameter("password"));
	    	request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
	    } else {
			HttpSession session = request.getSession();
			session.setAttribute("tipo_sesion", resultado.getTipo());
			session.setAttribute("sesion_id", resultado.getNickname());
			session.setAttribute("sesion_image", resultado.getImagen());
		    // redirigir a anterior o si no hay anterior al home
	        response.sendRedirect(request.getContextPath() + "/rutas");
	    }
    }
    
    //CONSULTAR RESERVAS
  	private void consultarReservas(HttpServletRequest request, HttpServletResponse response)
      		throws ServletException, IOException {
  	int tipo = Integer.parseInt(request.getParameter("tipo"));
  	if (tipo == 0) {
  		StringBuilder responseText = new StringBuilder(); 
  		List<String> aerolineas = port.listarAerolineas().getConjString();
  		responseText.append("<option value='' selected disabled>Seleccione una opción</option>");
  		for (String aerolinea : aerolineas) {
  			responseText.append("<option value='").append(aerolinea).append("'>")
  			.append(((publicar.DataAerolinea) port.listarDatosUsuarioNickname(aerolinea)).getNombreA())
  			.append("</option>");	
  		}
  	    response.setContentType("text/html");
        response.getWriter().write(responseText.toString());
  	}
  	else if (tipo == 1) {
  		StringBuilder responseText = new StringBuilder(); 
  		List<String> rutas = port.listarRutasDeAerolinea(request.getParameter("aerolinea")).getConjString();
  		responseText.append("<option value='' selected disabled>Seleccione una opción</option>");
  		for (String ruta : rutas) {
  			responseText.append("<option value='").append(ruta).append("'>")
  			.append(ruta)
  			.append("</option>");	
  		}
  	    response.setContentType("text/html");
        response.getWriter().write(responseText.toString());
  	}
  	else if (tipo == 2) {
  		StringBuilder responseText = new StringBuilder(); 
  		List<String> vuelos = port.vuelosDeRuta(request.getParameter("ruta")).getConjString();
  		responseText.append("<option value='' selected disabled>Seleccione una opción</option>");
  		for (String vuelo : vuelos) {
  			responseText.append("<option value='").append(vuelo).append("'>")
  			.append(vuelo)
  			.append("</option>");	
  		}
  	    response.setContentType("text/html");
        response.getWriter().write(responseText.toString());
  	}
  	else if (tipo == 3) {
  		List<DataClienteVuelo> reservasUsuario = port.listarReservasUsuario((String) request.getSession().getAttribute("sesion_id")).getConjDataClienteVuelo();
	    StringBuilder responseText = new StringBuilder();
  			boolean encontrado = false;
  	  		DataClienteVuelo reserva = new DataClienteVuelo();
  	  		int i = 0;
  	  		while (!encontrado && i < reservasUsuario.size()) {
  	  			if (reservasUsuario.get(i).getVuelo().equals(request.getParameter("vuelo"))) {
  	  				encontrado = true;
  	  				reserva = reservasUsuario.get(i);
  	  			}
  	  			i++;
  	  		}
  	  		if (encontrado) {
  	  		List<DataPasaje> pasajes = reserva.getPasajes();
  	        // Construir el HTML de la respuesta
  	        responseText.append("<ul class='list-group list-group-hover list-group-striped'>");
  	        responseText.append("<li class='list-group-item'><strong>Aerolinea: </strong>").append(reserva.getAerolinea()).append("</li>");
  	        responseText.append("<li class='list-group-item'><strong>Ruta:</strong> <a target='_blank' href='"+request.getContextPath()+"/rutas/").append(reserva.getRuta()).append("'>").append(reserva.getRuta()).append("</a></li>");
  	        responseText.append("<li class='list-group-item'><strong>Vuelo:</strong> <a target='_blank' href='"+request.getContextPath()+"/vuelos/").append(reserva.getVuelo()).append("'>").append(reserva.getVuelo()).append("</a></li>");
  	        responseText.append("<li class='list-group-item'><strong>Fecha De Reserva:</strong> ").append(reserva.getFechaReserva()).append("</li>");
  	        responseText.append("<li class='list-group-item'><strong>Tipo De Asiento:</strong> ").append(reserva.getTipoAsiento()).append("</li>");
  	        responseText.append("<li class='list-group-item'><strong>Cantidad de Pasajes:</strong> ").append(reserva.getCantPasajes()).append("</li>");
  	        responseText.append("<li class='list-group-item'><strong>Cantidad de Equipaje Extra:</strong> ").append(reserva.getCantEquipajeExtra()).append("</li>");
  	        responseText.append("<li class='list-group-item'><strong>Costo:</strong> ").append(reserva.getCosto()).append("</li>");
  	        responseText.append("<li class='list-group-item'><strong>Pasajes:</strong></li> ");
  	        responseText.append("<ul id='lista-pasajeros' class='list-group list-group-flush'>");
  	        for (DataPasaje pasaje : pasajes) {
  	            responseText.append("<li class='list-group-item'> -").append(pasaje.getNombre()).append(" ").append(pasaje.getApellido()).append("</li>");
  	        }
  	        responseText.append("</ul>");
  	        responseText.append("</ul>");
  	  		}
  	  		List<DataReserva> reservasSinCheckin = port.reservasSinCheckIn((String) request.getSession().getAttribute("sesion_id")).getConjDataReserva();
  	  		boolean checkeado = false;
	  		i = 0;
	  		while (!checkeado && i < reservasSinCheckin.size()) {
	  			if (reservasSinCheckin.get(i).getVuelo().equals(request.getParameter("vuelo"))) {
	  				checkeado = true;
	  				responseText.append("</div>");
	  				responseText.append("<a class='btn btn-success my-3' data-bs-toggle='modal' data-bs-target='#myModal' onclick='openModalAndRedirect(event)' href='"+request.getContextPath()+"/usuarios/checkInConsulta?vuelo=")
	  				.append(request.getParameter("vuelo"))
	  				.append("'>Realizar Check-In</a>");
	  				//<a class="btn btn-success" href="${pageContext.request.contextPath}/usuarios/checkIn?vuelo=vueloPrueba">Check In</a>
	  			}
	  			i++;
	  		}
	  	//	request.getParameter("vuelo")
  		
	        response.setContentType("text/html");
	        response.getWriter().write(responseText.toString());
  	}
  	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //CHECKIN
  	private void checkInConsulta(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
  		System.out.println("buenos dias");	
  		port.realizarCheckIn((String) request.getSession().getAttribute("sesion_id"), request.getParameter("vuelo"), LocalDate.now().toString());
  }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formId = request.getParameter("formId");
		if (formId != null) {
		    switch(formId) {
		    	case "log":
		    		iniciarSesion(request, response);
		    		break;
		    	case "logout":
		    		if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
		    			response.sendRedirect(request.getContextPath() + "/usuarios/acceso");
		    		} else {
			    		cerrarSesion(request, response);
		    		}
		    		break;
		    	case "checkIn":
		    		if(((String)request.getSession().getAttribute("sesion_id")).isEmpty()) {
		    			response.sendRedirect( request.getContextPath() + "/usuarios/acceso");
		    		} else {
		    			verReserva(request, response);
		    		}
	          		break;
	          	default:
	        		System.out.println("formId nulo");
	        		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formulario no reconocido.");
	        		break;
			}
		} else {
			System.out.println("formId nulo");
		 	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formulario no reconocido.");
		}
	}
}
