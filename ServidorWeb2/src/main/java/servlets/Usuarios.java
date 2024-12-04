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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.ArrayList;
/**
 * Servlet implementation class usuarios
 */
@WebServlet("/usuarios/*")
@MultipartConfig
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<DataClienteVuelo> reservas;
	private ArrayList<DataClientePaquete> paquetes;    
	private ArrayList<DataRuta> rutas;
	private publicar.WebServicesService service = new publicar.WebServicesService();
	private publicar.WebServices port = service.getWebServicesPort();
	//private boolean rutasListadas = false;
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
                	// se abre con error en los link
                	listarUsuarios(request, response);
                    break;
                case "/acceso":
                	request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
                	break;
                case "/logout":
                	cerrarSesion(request, response);
                	break;
                case "/reserva":
                    cargarReserva(request, response);
                    break;
                case "/paquete":
                    cargarPaquete(request, response);
                    break;
                case "/ruta":
                    cargarRutas(request, response);
                    break;
                case "/finalizar":
                	finalizarRuta(request, response);
                	break;
                case "/follow":
        			seguirUsuario(request, response);
        			break;
                case "/unfollow":
        			dejarSeguirUsuario(request, response);
        			break;
                case "/reservas":
                	cargarReservasConCheckIn(request, response);
                	break;
                case "/VerReserva":
                	verReserva(request, response);
                	break;
                case "/checkIn":
	          		verCheckIn(request, response);
	          		break;
                case "/crearPdf":
                	crearPdf(request, response);
	          		break;
                default:
                	path = path.substring(1);
                	 if (port.existeUsuario(path)) {
                    	DataUsuario DTUsuario = port.listarDatosUsuarioNickname(path);       	
                    	request.setAttribute("perfil", DTUsuario);
                    	
                    	Boolean esSeguidor = false;
                    	Boolean esMismoUsuario = false;
                		HttpSession session = request.getSession();
                        if (session.getAttribute("tipo_sesion") != publicar.TipoSesion.INVITADO) {
                        	String nicknameUsuario = (String) request.getSession().getAttribute("sesion_id");
                        	esSeguidor = port.esSeguidor(nicknameUsuario, path);
                        	esMismoUsuario = nicknameUsuario.compareTo(path) == 0;
                    	}
                        request.setAttribute("esSeguidor", esSeguidor);
                        request.setAttribute("esMismoUsuario", esMismoUsuario);
                    	
                    	List<String> seguidos = port.seguidosDeUsuario(path).getConjString();
                    	List<publicar.DataUsuario> DTSeguidos = new ArrayList<>();
                    	for (String seguido : seguidos) {
                    		DTSeguidos.add(port.listarDatosUsuarioNickname(seguido));
                    	}
                    	request.setAttribute("seguidos", DTSeguidos);
                    	List<String> seguidores = port.seguidoresDeUsuario(path).getConjString();
                    	List<publicar.DataUsuario> DTSeguidores = new ArrayList<>();
                    	for (String seguidor : seguidores) {
                    		DTSeguidores.add(port.listarDatosUsuarioNickname(seguidor));
                    	}
                    	request.setAttribute("seguidores", DTSeguidores);
                    	if (port.esCliente(path)) {
                    	    List<publicar.DataClienteVuelo> DTReservas = port.listarReservasUsuario(path).getConjDataClienteVuelo();
                            List<publicar.DataClientePaquete> DTPaquetes = port.listarPaquetesUsuario(path).getConjDataClientePaquete();
                    	    reservas = new ArrayList<DataClienteVuelo>();
                    	    reservas.addAll(DTReservas);
                    	    paquetes = new ArrayList<DataClientePaquete>();
                    	    paquetes.addAll(DTPaquetes);
                    	    request.setAttribute("cantReservas", DTReservas.size());
                            request.setAttribute("cantPaquetes", DTPaquetes.size());
                    		request.getRequestDispatcher("/WEB-INF/usuarios/perfilCliente.jsp").forward(request, response);
                    	} else {
                    		List<DataRuta> DTRutas = port.infoRutasDeAerolinea(path).getConjDataRuta();
                    	    rutas = new ArrayList<DataRuta>();
                    	    rutas.addAll(DTRutas);
                    	    request.getRequestDispatcher("/WEB-INF/usuarios/perfilAerolinea.jsp").forward(request, response);
                    	}
            		} else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                    break;
            }
        } else {
        	// se abre bien, alguno sabe porque la diferencia? --> joaco="nidea bro :v"
        	listarUsuarios(request, response);
        }
	}
    
    private void seguirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String usuarioASeguir = request.getParameter("usuarioASeguir");
    	String nickname = (String) request.getSession().getAttribute("sesion_id");
    	port.seguirUsuario(nickname, usuarioASeguir);
    	response.sendRedirect(request.getContextPath() + "/usuarios/"+usuarioASeguir);
    }
    
    private void dejarSeguirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String usuarioSeguido = request.getParameter("usuarioSeguido");
    	String nickname = (String) request.getSession().getAttribute("sesion_id");
    	port.dejarDeSeguirUsuario(nickname, usuarioSeguido);
    	response.sendRedirect(request.getContextPath() + "/usuarios/"+usuarioSeguido);
    }
	
    // cargar los usuarios y ir a perfiles.jsp
    private void verReserva(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String cliente = (String) request.getSession().getAttribute("sesion_id");
    	String nombreVuelo = request.getParameter("nombreVuelo");
    	DataClienteVuelo reserva = port.verReserva(cliente, nombreVuelo);
    	request.getSession().setAttribute("Vuelo", reserva.getVuelo());
    	request.setAttribute("reserva", reserva);
        request.getRequestDispatcher("/WEB-INF/checkin/reserva.jsp").forward(request, response);
	}

	private void cargarReservasConCheckIn(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String cliente = (String) request.getSession().getAttribute("sesion_id");
		List<DataReserva> reservas = port.reservasConCheckIn(cliente).getConjDataReserva();
		request.setAttribute("reservas", reservas);
        request.getRequestDispatcher("/WEB-INF/checkin/reservasConCheckIn.jsp").forward(request, response);
		
	}
	
	private void verCheckIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cliente = (String) request.getSession().getAttribute("sesion_id");
		String nombreVuelo = (String) request.getSession().getAttribute("Vuelo");
	    DataCheckIn CheckIn = port.consultaCheckIn(cliente, nombreVuelo);
	    
	    request.getSession().setAttribute("CheckIn", CheckIn);
	    request.setAttribute("Vuelo", nombreVuelo);
	    request.getRequestDispatcher("/WEB-INF/checkin/checkIn.jsp").forward(request, response);
	}
	
	private void crearPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreVuelo = (String) request.getSession().getAttribute("Vuelo");
		DataCheckIn checkIn = (DataCheckIn) request.getSession().getAttribute("CheckIn");
		
		response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"Tarjeta_de_Embarque.pdf\"");


        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        
        DeviceRgb customColor = new DeviceRgb(29, 114, 184);


        Paragraph titulo = new Paragraph("volando.uy")
                .setFontSize(24)
                .setBold()
                .setFontColor(customColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(titulo);


        Table infoTable = new Table(UnitValue.createPercentArray(new float[]{1, 2}))
                .setWidth(UnitValue.createPercentValue(100))
                .setMarginBottom(20);

        infoTable.addCell(new Cell().add(new Paragraph("Vuelo:").setBold())
                .setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph(nombreVuelo))
                .setBorder(Border.NO_BORDER));
        
        infoTable.addCell(new Cell().add(new Paragraph("Fecha de Check-In:").setBold())
                .setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph(checkIn.getFechaCheckIn()))
                .setBorder(Border.NO_BORDER));

        infoTable.addCell(new Cell().add(new Paragraph("Fecha de Embarque:").setBold())
                .setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph(checkIn.getFechaEmbarque()))
                .setBorder(Border.NO_BORDER));

        infoTable.addCell(new Cell().add(new Paragraph("Hora de Embarque:").setBold())
                .setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph(checkIn.getHoraEmbarque() + " h"  )) 
                .setBorder(Border.NO_BORDER));

        document.add(infoTable);
        
        SolidLine solidLine = new SolidLine(1f); 
        solidLine.setColor(ColorConstants.BLACK);
        
        LineSeparator separator = new LineSeparator(solidLine);
        separator.setWidth(UnitValue.createPercentValue(100));
        separator.setStrokeColor(ColorConstants.BLACK); 
        document.add(separator);

        Paragraph pasajesTitulo = new Paragraph("Pasajes Asociados")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);
        document.add(pasajesTitulo);


        Table pasajesTable = new Table(UnitValue.createPercentArray(new float[]{2, 1}))
                .setWidth(UnitValue.createPercentValue(100))
                .setBorder(new SolidBorder(ColorConstants.GRAY, 1));
        

        pasajesTable.addHeaderCell(new Cell().add(new Paragraph("Pasajero").setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER));
        pasajesTable.addHeaderCell(new Cell().add(new Paragraph("Asiento").setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER));

        
        List<DataPasaje> pasajes = checkIn.getPasajesConCheckIn();
        
        for (DataPasaje pasaje : pasajes) {
            pasajesTable.addCell(new Cell().add(new Paragraph( pasaje.getNombre() + " " + pasaje.getApellido()))
                    .setTextAlignment(TextAlignment.CENTER));
            pasajesTable.addCell(new Cell().add(new Paragraph(pasaje.getAsiento().charAt(0) + "-" + pasaje.getAsiento().substring(1)))
                    .setTextAlignment(TextAlignment.CENTER));
        }
        



        document.add(pasajesTable);


        Paragraph despedida = new Paragraph("¡Gracias por elegir volando.uy!")
                .setFontSize(12)
                .setItalic()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(20);
        document.add(despedida);


        document.close();
	}


	// cargar los usuarios y ir a perfiles.jsp
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	List<String> usuarios = port.listarUsuarios().getConjString();
    	List<publicar.DataUsuario> DTUsuarios = new ArrayList<>();
    	for (String usuario : usuarios) {
    		DTUsuarios.add(port.listarDatosUsuarioNickname(usuario));
    	}
    	
    	//usuarios.add("prueba");
    	request.setAttribute("usuarios", DTUsuarios);
    	request.getRequestDispatcher("/WEB-INF/usuarios/perfiles.jsp").forward(request, response);
    }
    
    // CERRAR SESION
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    request.getSession().setAttribute("tipo_sesion", TipoSesion.INVITADO);
	    request.getSession().setAttribute("sesion_id", "");
	    // redirigir a home
	    response.sendRedirect(request.getContextPath() + "/home");
    }
    
    // INICIAR SESION
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String identificador = request.getParameter("identificador");
    	Boolean mail = identificador.contains("@");
	    DataSesion resultado = port.loginUsuario(identificador, request.getParameter("password"), mail);
	    if (resultado.getTipo() == TipoSesion.INVITADO) {
	    	// fallo el login
	    	request.setAttribute("show-form", "log");
	    	request.setAttribute("error-form-usuarios", "Identificador o contraseña incorrectos, porfavor reescriba los datos y vuelva a intentar.");
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
	        response.sendRedirect(request.getContextPath() + "/home");
	    }
    }
    
    // CREAR CLIENTE
    private void crearCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IOException_Exception {
    	String nickname = request.getParameter("nickname");
    	if (port.existeUsuario(nickname)) {
	    	request.setAttribute("show-form", "cliente");
	    	request.setAttribute("error-form-usuarios", "Nickname ya en uso, porfavor intenta otro.");
	    	// datos madnar para rellenar el form
			request.setAttribute("nickname", nickname);
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			request.setAttribute("apellido", request.getParameter("apellido"));
			request.setAttribute("mail", request.getParameter("mail"));
			request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));
			request.setAttribute("n-documento", request.getParameter("numero_documento"));
	    	request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
			return;
    	}
    	Documento documento = Documento.CI;
    	if ("pasaporte".equals(request.getParameter("tipo_documento"))) {
    		documento = Documento.PASAPORTE;
    	}
        String imagen = "";
        //obtener la imagen
        Part filePart = request.getPart("imagen"); 
        if (filePart != null && filePart.getSize() > 0) {
            imagen = filePart.getSubmittedFileName(); // Obtiene el nombre del archivo
        }
    	int resultado = port.crearCliente(nickname, request.getParameter("nombre"), request.getParameter("mail"), request.getParameter("apellido"), request.getParameter("nacimiento"), request.getParameter("nacionalidad"), documento, request.getParameter("numero_documento"), request.getParameter("password"), imagen);
    	if (resultado == 1) {
	    	request.setAttribute("show-form", "cliente");
	    	request.setAttribute("error-form-usuarios", "Email ya en uso, porfavor intenta otro.");
	    	// datos madnar para rellenar el form
			request.setAttribute("nickname", nickname);
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			request.setAttribute("apellido", request.getParameter("apellido"));
			request.setAttribute("mail", request.getParameter("mail"));
			request.setAttribute("nacionalidad", request.getParameter("nacionalidad"));
			request.setAttribute("n-documento", request.getParameter("numero_documento"));
	        request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
			return;
    	}
    	if (!imagen.isEmpty()) {
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
    	// iniciarle sesion
    	HttpSession session = request.getSession();
		session.setAttribute("tipo_sesion", TipoSesion.CLIENTE);
		session.setAttribute("sesion_id", nickname);
		session.setAttribute("sesion_image", imagen);
	    // redirigir a anterior o si no hay anterior al home
		response.sendRedirect(request.getContextPath() + "/home");
    }
    
    // CREAR AEROLINEA
    private void crearAerolinea(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IOException_Exception {
    	String nickname = request.getParameter("nickname");
    	if (port.existeUsuario(nickname)) {
	    	request.setAttribute("show-form", "aerolinea");
	    	request.setAttribute("error-form-usuarios", "Nickname ya en uso, porfavor intenta otro.");
	    	// datos madnar para rellenar el form
			request.setAttribute("nickname", nickname);
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			request.setAttribute("mail", request.getParameter("mail"));
			request.setAttribute("descripcion", request.getParameter("descripcion"));
			request.setAttribute("web", request.getParameter("web"));
	        request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
			return;
    	}
        String imagen = "";
        //obtener la imagen
        Part filePart = request.getPart("imagen"); 
        if (filePart != null && filePart.getSize() > 0) {
            imagen = filePart.getSubmittedFileName(); // Obtiene el nombre del archivo
        }
    	int resultado = port.crearAerolinea(nickname, request.getParameter("nombre"), request.getParameter("mail"), request.getParameter("web"), request.getParameter("descripcion"), request.getParameter("password"), imagen);
    	if (resultado == 1) {
	    	request.setAttribute("show-form", "aerolinea");
	    	request.setAttribute("error-form-usuarios", "Email ya en uso, porfavor intenta otro.");
	    	// datos madnar para rellenar el form
			request.setAttribute("nickname", nickname);
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			request.setAttribute("mail", request.getParameter("mail"));
			request.setAttribute("descripcion", request.getParameter("descripcion"));
			request.setAttribute("web", request.getParameter("web"));
	        request.getRequestDispatcher("/WEB-INF/usuarios/acceso.jsp").forward(request, response);
			return;
    	}
    	if (!imagen.isEmpty()) {
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

    	// iniciarle sesion
    	HttpSession session = request.getSession();
		session.setAttribute("tipo_sesion", TipoSesion.AEROLINEA);
		session.setAttribute("sesion_id", nickname);
		session.setAttribute("sesion_image", imagen);
	    // redirigir a anterior o si no hay anterior al home
		response.sendRedirect(request.getContextPath() + "/home");
    }
    // ACTUALIZAR RESERVAS
    private void cargarReserva(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int indice = Integer.parseInt(request.getParameter("indice"));
        if (indice >= 0 && indice < reservas.size()) {
            DataClienteVuelo reserva = reservas.get(indice);
            List<DataPasaje> pasajes = reserva.getPasajes();
            // Construir el HTML de la respuesta
            StringBuilder responseText = new StringBuilder(); 
            responseText.append("<ul class='list-group list-group-hover list-group-striped'>");
            responseText.append("<li class='list-group-item'><strong>Aerolinea:</strong> <a target='_blank' href='" + request.getContextPath() + "/usuarios/").append(reserva.getNickAerolinea()).append("'>").append(reserva.getAerolinea()).append("</a></li>");
            responseText.append("<li class='list-group-item'><strong>Ruta:</strong> <a target='_blank' href='" + request.getContextPath() + "/rutas/").append(reserva.getRuta()).append("'>").append(reserva.getRuta()).append("</a></li>");
            responseText.append("<li class='list-group-item'><strong>Vuelo:</strong> <a target='_blank' href='" + request.getContextPath() + "/vuelos/").append(reserva.getVuelo()).append("'>").append(reserva.getVuelo()).append("</a></li>");
            responseText.append("<li class='list-group-item'><strong>Fecha De Reserva:</strong> ").append(reserva.getFechaReserva()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Tipo De Asiento:</strong> ").append(reserva.getTipoAsiento()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Cantidad de Pasajes:</strong> ").append(reserva.getCantPasajes()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Cantidad de Equipaje Extra:</strong> ").append(reserva.getCantEquipajeExtra()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Costo:</strong> ").append(reserva.getCosto()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Pasajes:</strong></li> ");
            responseText.append("<ul id='lista-pasajeros' class='list-group list-group-flush'>");
            for (DataPasaje pasaje : pasajes) {
                responseText.append("<li class='list-group-item'> ").append(pasaje.getNombre()).append(" ").append(pasaje.getApellido()).append("</li>");
            }
            responseText.append("</ul>");
            responseText.append("</ul>");
            //PAGINACION
            responseText.append("<ul class='pagination justify-content-center' style='margin-top: 1rem;'>");
            responseText.append("<li class='page-item'>");
            responseText.append("<a class='page-link' href='#' onclick='cargarReserva(-1)'>Previous</a>");
            responseText.append("</li>");
            for (int i=1; i<=reservas.size(); i++) {
                responseText.append("<li class='page-item'>");
                responseText.append("<a class='page-link' href='#' onclick='cargarReserva(").append(i).append(")'>").append(i).append("</a>");
                responseText.append("</li>");
            }
            responseText.append("<li class='page-item'>");
            responseText.append("<a class='page-link' href='#' onclick='cargarReserva(-2)'>Next</a>");
            responseText.append("</li>");
            response.setContentType("text/html");
            response.getWriter().write(responseText.toString());
        } 
    }
    
 // ACTUALIZAR RESERVAS
    private void cargarPaquete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int indice = Integer.parseInt(request.getParameter("indice"));
            if (indice >= 0 && indice < paquetes.size()) {
            DataClientePaquete paquete = paquetes.get(indice);
            StringBuilder responseText = new StringBuilder();
            responseText.append("<div class='col-sm-9'>");
            responseText.append("<ul class='list-group list-group-hover list-group-striped'>");
            responseText.append("<li class='list-group-item'><strong>Paquete:</strong> <a id='paquete-link' target='_blank' href='" + request.getContextPath() + "/paquetes/").append(paquete.getPaquete()).append("'>").append(paquete.getPaquete()).append("</a></li>");
            responseText.append("<li class='list-group-item'><strong>Fecha De Compra: </strong>").append(paquete.getFecha()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Vencimiento: </strong>").append(paquete.getVencimiento()).append("</li>");
            responseText.append("<li class='list-group-item'><strong>Costo: </strong>").append(paquete.getCosto()).append("</li>");
            responseText.append("</ul>");
            responseText.append("</div>");
            responseText.append("<div class ='col-sm-3'>");
            responseText.append("<img id='imgPaquete' src='" + request.getContextPath() + "/Imagenes?id=").append(paquete.getImgPaquete()).append("' class='object-fit cover border border-imagen border-rounded float-start img-fluid' alt='foto Paquete'>");
            responseText.append("</div>");
            //PAGINACION
            responseText.append("<ul class='pagination justify-content-center' style='margin-top: 1rem;'>");
            responseText.append("<li class='page-item'>");
            responseText.append("<a class='page-link' href='#' onclick='cargarPaquete(-1)'>Previous</a>");
            responseText.append("</li>");
            for (int i=1; i<=paquetes.size(); i++) {
                responseText.append("<li class='page-item'>");
                responseText.append("<a class='page-link' href='#' onclick='cargarPaquete(").append(i).append(")'>").append(i).append("</a>");
                responseText.append("</li>");
            }
            responseText.append("<li class='page-item'>");
            responseText.append("<a class='page-link' href='#' onclick='cargarPaquete(-2)'>Next</a>");
            responseText.append("</li>");
            response.setContentType("text/html");
            response.getWriter().write(responseText.toString());
            }
        } 
    
 // ACTUALIZAR RUTAS    
    private void cargarRutas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            if (!rutas.isEmpty()) {
            	String nickVista = request.getParameter("nickVista");
                String nickSesion = (String) request.getSession().getAttribute("sesion_id");
                String nombreRuta = request.getParameter("nombreRuta");
                boolean encontrado = "".equals(nombreRuta);
                int cantConfirmada = 0;
        	    DataRuta ruta = rutas.get(0);
                int i = 0;
                while (!encontrado && i<rutas.size()) {
                    if (rutas.get(i).getNombre().equals(nombreRuta)) {
                        ruta = rutas.get(i);
                        encontrado = true;
                    }
                    i++;
                }
                StringBuilder responseText = new StringBuilder();
                responseText.append("<div class='row g-0'>");
                responseText.append("<div class='col-sm-3'>");
                responseText.append("<div class='list-group' id='listaRutas'>");
                String color;
                    for (int j=0; j<=rutas.size()-1; j++) {
                        if (rutas.get(j).getEstado().equals(EstadoRuta.CONFIRMADA)) {
                            color = "bg-success-subtle"; 
                            cantConfirmada++;
                            if ("".equals(nombreRuta)) {
                                ruta = rutas.get(j);
                            }
                    	} else if (rutas.get(j).getEstado().equals(EstadoRuta.INGRESADA)) {
                            color = "bg-warning-subtle";
                            
                    	} else if (rutas.get(j).getEstado().equals(EstadoRuta.FINALIZADA)) {
                            color = "bg-secondary-subtle"; 
                    	}
                    	else {color = "bg-danger-subtle"; }
                        if (nickSesion.equals(nickVista)||rutas.get(j).getEstado().equals(EstadoRuta.CONFIRMADA)) {
                            responseText.append("<a href='#' class='list-group-item list-group-item-action list-item-border ").append(color).append("' onclick=\"mostrarDetalles('")
                            .append(rutas.get(j).getNombre())
                            .append("')\" >")
                            .append(rutas.get(j).getNombre())
                            .append("</a>");
                        }
                        
                    }
                if (!"".equals(nombreRuta) || cantConfirmada!=0 || nickSesion.equals(nickVista)) {
                    responseText.append("</div>");
                    if (nickSesion.equals(nickVista)) {responseText.append("<a class='btn btn-primary' id='crear-ruta' href='" + request.getContextPath() + "/rutas/crear' role='button'>Crear Ruta de Vuelo</a>"); }
                    responseText.append("</div>");
                    responseText.append("<div class='col-sm-7'>");
                    responseText.append("<ul class='list-group list-group-hover list-group-striped'>");
                    responseText.append("<li class='list-group-item'><strong>Nombre:</strong> <a id='nombre-ruta' target='_blank' href='" + request.getContextPath() + "/rutas/").append(ruta.getNombre()).append("'>").append(ruta.getNombre()).append("</a></li>");
                    responseText.append("<li class='list-group-item'><strong>Hora: </strong>").append(ruta.getHora()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Fecha de Alta: </strong>").append(ruta.getFechaAlta()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Precio Turista: </strong>").append(ruta.getTuristaBase()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Precio Ejecutivo: </strong>").append(ruta.getEjecutivoBase()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Precio Unidad de Equipaje Extra: </strong>").append(ruta.getUnidadEquipajeExtra()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Origen: </strong>").append(ruta.getOrigen()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Destino: </strong>").append(ruta.getDestino()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Descripcion: </strong>").append(ruta.getDescripcion()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Estado: </strong>").append(ruta.getEstado()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Descripcion Corta: </strong>").append(ruta.getDescCorta()).append("</li>");
                    responseText.append("<li class='list-group-item'><strong>Categorias: </strong>");
                    List<String> categorias = ruta.getCategorias();
                    int cantCat = categorias.size();
                    for (String categoria : categorias) {
                    	responseText.append(categoria);
                    	if (cantCat!=1) {
                    		responseText.append(", ");
                    	}
                    	cantCat--;
                    }
                    responseText.append("</li>");
                    responseText.append("</ul>");
                    responseText.append("</div>");
                    responseText.append("<div class='col-sm-2'>");
                    responseText.append("<img id='imgRuta' src='" + request.getContextPath() + "/Imagenes?id=").append(ruta.getImagen()).append("' class='object-fit cover border border-imagen border-rounded float-start img-fluid' alt='foto ruta'>");
                    responseText.append("<div class='dropdown mb-2'>");
                    responseText.append("<button type='button' class='btn btn-primary dropdown-toggle' data-bs-toggle='dropdown'>Vuelos Asociados</button>");
                    responseText.append("<ul class='dropdown-menu'>");
                    List<String> vuelos = port.vuelosDeRuta(ruta.getNombre()).getConjString();
                    boolean vuelosPendientes = false;
                    boolean pertenecePaquete = false;
                    LocalTime horaRuta = LocalTime.parse(ruta.getHora());
                    LocalDateTime fechaHoraActuales = LocalDateTime.now();
                    for (String vuelo : vuelos) {
                        responseText.append("<li><a class='dropdown-item' href='" + request.getContextPath() + "/vuelos/").append(vuelo).append("'>").append(vuelo).append("</a></li>");
                        LocalDate fechaVuelo = LocalDate.parse(port.listarDatosVuelo(vuelo).getFecha());
                        LocalDateTime fechaHoraVuelo = LocalDateTime.of(fechaVuelo.getYear(), fechaVuelo.getMonthValue(), fechaVuelo.getDayOfMonth(), horaRuta.getHour(), horaRuta.getMinute());
                        //fechaHoraVuelo = fechaHoraVuelo.minusHours(4);
                        vuelosPendientes = fechaHoraVuelo.isAfter(fechaHoraActuales);
                    }
                    responseText.append("</ul>");
                    responseText.append("</div>");
                    if (nickSesion.equals(nickVista)) {responseText.append("<a class='btn btn-primary ' id='crear-vuelo' href='" + request.getContextPath() + "/vuelos/crear' role='button'>Crear Vuelo Asociado</a>"); }
                    
                    List<String> paquetes = port.listarPaquetesConRutas().getConjString();
                    int p = 0;
                    while (!pertenecePaquete && p<paquetes.size()) {
                    	pertenecePaquete = port.paqueteContieneARuta(paquetes.get(p), ruta.getNombre());
                    	p++;
                    }
                    if (nickSesion.equals(nickVista) && ruta.getEstado().equals(EstadoRuta.CONFIRMADA) && !vuelosPendientes && !pertenecePaquete) {
                    	responseText.append("<a class='btn bg-danger-subtle  mt-2' id='finalizar-ruta' href='#' role='button' onclick=\"finalizarRuta('").append(ruta.getNombre())
                        .append("')\" >")
                        .append("Finalizar Ruta</a>");
                    }
                    
                }
                responseText.append("</div>");
                responseText.append("</div>");
                responseText.append("</div>");
                response.setContentType("text/html");
                response.getWriter().write(responseText.toString());
        }
     }
    
    // MODIFICAR DATOS CLIENTE
    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IOException_Exception {
        String imagen = "";
        String nickname = (String) request.getSession().getAttribute("sesion_id");
        //obtener la imagen
        Part filePart = request.getPart("nueva-imagen"); 
        if (filePart != null && filePart.getSize() > 0) {
            imagen = filePart.getSubmittedFileName(); // Obtiene el nombre del archivo
        }

    	if (!imagen.isEmpty()) {
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
		           request.getSession().setAttribute("sesion_image", imagen);
		       } catch (IOException e) {
		           e.printStackTrace();
		       }
		} else {
        	imagen = (String) request.getSession().getAttribute("sesion_image");
    	}
    	String documento = "CI";
    	if ("pasaporte".equals(request.getParameter("nuevo-tipo-documento"))) {
    		documento = "Pasaporte";
    	}
        String fecha = (String) request.getParameter("nueva-fecha");
    	LocalDate nacimiento = LocalDate.parse(((DataCliente) port.listarDatosUsuarioNickname(nickname)).getFechaNac());
        if(fecha != null && !fecha.isEmpty()) {
        	nacimiento = LocalDate.parse(fecha);
        }
        if (request.getParameter("nuevo-nacimiento") != null) {
        	nacimiento = LocalDate.parse(request.getParameter("nuevo-nacimiento"));
        }
        port.setImagenAndPasswordUsuario(nickname, request.getParameter("nueva-password"), imagen);
        port.setDatosCliente(nickname, (String) request.getParameter("nuevo-nombre"), (String) request.getParameter("nuevo-apellido"), nacimiento.toString(), (String) request.getParameter("nueva-nacionalidad"), documento, (String) request.getParameter("nuevo-documento"));
        response.sendRedirect( request.getContextPath() + "/usuarios/"+nickname);
    }
    
    // MODIFICAR DATOS AEROLINEA
    private void modificarAerolinea(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IOException_Exception {
        String imagen = "";
        String nickname = (String) request.getSession().getAttribute("sesion_id");
        //obtener la imagen
        Part filePart = request.getPart("imagen"); 
        if (filePart != null && filePart.getSize() > 0) {
            imagen = filePart.getSubmittedFileName(); // Obtiene el nombre del archivo
        }
    	if (!imagen.isEmpty()) {
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
		           request.getSession().setAttribute("sesion_image", imagen);
		       } catch (IOException e) {
		           e.printStackTrace();
		       }
		} else {
        	imagen = (String) request.getSession().getAttribute("sesion_image");
    	}
        port.setDatosAerolinea(nickname, request.getParameter("nuevo-nombre"),  request.getParameter("nueva-web"), request.getParameter("nueva-descripcion"));
        port.setImagenAndPasswordUsuario(nickname, request.getParameter("nueva-password"), imagen);
        response.sendRedirect( request.getContextPath() + "/usuarios/"+nickname);
    }
    // FINALIZAR RUTA
    private void finalizarRuta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String nombreRuta = request.getParameter("nombreRuta");
    	port.finalizarRuta(nombreRuta);
    	String nickVista = request.getParameter("nickVista");
    	List<DataRuta> DTRutas = port.infoRutasDeAerolinea(nickVista).getConjDataRuta();
	    rutas = new ArrayList<DataRuta>();
	    rutas.addAll(DTRutas);

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
		String formId = request.getParameter("formId");
		if (formId != null) {
		    switch(formId) {
		    	case "log":
		    		iniciarSesion(request, response);
		    		break;
		    	case "logout":
		    		cerrarSesion(request, response);
		    		break;
		    	case "cliente":
		    		try {
						crearCliente(request, response);
					} catch (ServletException | IOException | IOException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		break;
		    	case "aerolinea":
		    		try {
						crearAerolinea(request, response);
					} catch (ServletException | IOException | IOException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		break;
	            case "modificarCliente":
	            	try {
						modificarCliente(request, response);
					} catch (ServletException | IOException | IOException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	break;
	          	case "modificarAerolinea":
	             	try {
						modificarAerolinea(request, response);
					} catch (ServletException | IOException | IOException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	break;
	          	case "seguir-usuario":
	             	try {
						seguirUsuario(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	break;
	          	case "dejar-seguir-usuario":
	             	try {
						dejarSeguirUsuario(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
