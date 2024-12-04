package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicar.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class HomeSearch
 */
@WebServlet ("/home")
public class HomeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeSearch() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * Devuelve el tipo de sesión
	 * @param request
	 * @return 
	 */
	public static TipoSesion getTipoSesion(HttpServletRequest request)
	{
		return (TipoSesion) request.getSession().getAttribute("tipo_sesion");
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
		request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// este funciona solo para el buscador
		initSession(request);
		if(request.getParameter("az") != null) {
			request.getSession().setAttribute("az", request.getParameter("az"));
		}
		String buscado = (String) request.getParameter("buscado");
		if(buscado == null) {
			buscado = "";
		}
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();
		List<DataImagenNombreType> resultado = new ArrayList<>();
		List<DataImagenNombreType> rutas = port.getResultadoBusquedaRutas(buscado).getConjDataImagenNombreType();
		List<DataImagenNombreType> paquetes = port.getResultadoBusquedaPaquetes(buscado).getConjDataImagenNombreType();
		if(rutas != null) {
			resultado.addAll(rutas);
		}
		if(paquetes != null) {
			resultado.addAll(paquetes);
		}
		if(resultado.size() > 0) {
			if(request.getSession().getAttribute("az").equals("false")) {
			    resultado.sort((o1, o2) -> o2.getFechaAlta().compareTo(o1.getFechaAlta()));
			} else {
			    resultado.sort((o1, o2) -> o1.getNombre().compareTo(o2.getNombre()));
			}
		}
		int cantidad = resultado.size();
		request.setAttribute("obtenidos", resultado);
		request.setAttribute("cantidad", cantidad);
		request.setAttribute("buscado", buscado);
		request.getRequestDispatcher("/WEB-INF/buscado.jsp").forward(request, response);
		return;
	}

}
