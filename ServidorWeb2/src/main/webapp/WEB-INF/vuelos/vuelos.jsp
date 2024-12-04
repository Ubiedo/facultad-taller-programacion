<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import ="publicar.DataVuelo" %>
<%@ page import ="publicar.TipoSesion" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Vuelos</title>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vuelos.css">
	

</head>

<body>
    <!-- Header -->
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <%
    TipoSesion tipo_sesion = (TipoSesion) request.getSession().getAttribute("tipo_sesion");
    String botonVueloHTML = "";
    if (tipo_sesion == TipoSesion.AEROLINEA) {
        botonVueloHTML = "<a href='vuelos/crear' class='btn btn-primary' style='margin-right: 72px;'>Crear Nuevo Vuelo</a>";
    }

	%>


    <div class="text-end my-3" id="boton-vuelo"> <%= botonVueloHTML %></div>
    
    

    <div class="container my-4">
        <div class="row justify-content-center">

            <!-- Filtros laterales -->
            <aside class="col-md-3 sticky-top" style="height: fit-content;">
                <h4 class="py-3" style="text-align:center;">Aerolíneas y Rutas</h4>
                <div class="accordion pb-3" id="accordionFiltros">
                    <!-- Un formulario para todos los filtros -->
                    <form id="filtro-form">

                        <% 
                        // Obtener aerolíneas y rutas desde el request
                        Map<String, List<String>> aeroRutas = (Map<String, List<String>>) request.getAttribute("aeroRutas");
                        Map<String, List<DataVuelo>> rutaVuelos = (Map<String, List<DataVuelo>>) request.getAttribute("rutaVuelos");
                        Map<String,String> aeroNombres = (Map<String,String>) request.getAttribute("aeroNombres");
                        
                        
                        int iAerolinea = 1;

                        // Recorrer aerolíneas
                        for (String aerolinea : aeroRutas.keySet()) {
                        	
                            String headingAero = "heading" + iAerolinea;
                            String collapseAero = "collapse" + iAerolinea;
                            List<String> rutas = aeroRutas.get(aerolinea);
                            if (rutas != null){
                            	int iRuta = 1;
                            	
                            
                        %>

                        <!-- Filtro  -->
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="<%= headingAero %>">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#<%= collapseAero %>" aria-expanded="true" aria-controls="<%= collapseAero %>">
                                    <%= aeroNombres.get(aerolinea) %>
                                </button>
                            </h2>
                            <div id="<%= collapseAero %>" class="accordion-collapse collapse" aria-labelledby="<%= headingAero %>">
                                <div class="accordion-body">
                                    <% 
                                    // Recorrer rutas de la aerolínea actual
                                    for (String ruta : rutas) {
                                    %>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="<%= iAerolinea + "." + iRuta %>" id="<%= iAerolinea + "." + iRuta %>">
                                        <label class="form-check-label" for="<%= iAerolinea + "." + iRuta %>">
                                            <%= ruta %>
                                        </label>
                                    </div>
                                    <% 
                                    iRuta++;
                                    	}  
                                    }
                                    %>
                                </div>
                            </div>
                        </div>

                        <% 
                            iAerolinea++;
                        }
                        %>
                    </form>
                </div>
            </aside>

            <!-- Vuelos -->
            <div class="col-md-9">
                <div class="row" id="flight-cards">
                    <% 
                    iAerolinea = 1;
                    
                    
                    for (String aerolinea : aeroRutas.keySet()) {
                        List<String> rutas = aeroRutas.get(aerolinea);
                        if (rutas != null){
                        	int iRuta = 1;
                        
                        
                        for (String ruta : rutas) {
                            List<DataVuelo> vuelos = rutaVuelos.get(ruta);
                            if (vuelos != null){
                            	
                            
                                for (DataVuelo vuelo : vuelos) {
                    %>
                    <!-- Tarjeta de vuelo -->
                    <div class="col-md-6 mb-4 flight-option" data-filter="<%= iAerolinea %>" data-subfilter="<%= iAerolinea + "." + iRuta %>" onclick="location.href='vuelos/<%= vuelo.getNombre() %>';">
                        <div class="card">
                        <%
                        if ((!vuelo.getImagen().equals("(Sin imagen)") ) && (!vuelo.getImagen().equals(""))){
                        	%>
                            <img src="${pageContext.request.contextPath}/Imagenes?id=<%= vuelo.getImagen() %>" class="card-img-top" alt="(Sin Imagen)" height="300">
                            <%} %>
                            <div class="card-body">
                                <h5 class="card-title"><%= vuelo.getNombre() %></h5>
                                <hr>
                                <p class="flight-info">Ruta: <span><%= ruta %></span></p>
                                <p class="flight-info">Aerolínea: <span><%= aeroNombres.get(aerolinea) %></span></p>
                                <p class="flight-info">Fecha: <span><%= vuelo.getFecha() %></span></p>
                                <p class="flight-info">Duración: <span><%= vuelo.getDuracion() %> h</span></p>
                            </div>
                        </div>
                    </div>
                    <% 
                               }  
                             } 
                        iRuta++;
                        } 
                        }
                    iAerolinea++;
                    }
                    %>
                </div>
            </div>
        </div>
    </div>

    

      <div id="footer">
    <nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
        <div class="container-fluid">
            <ol class="breadcrumb navbar-text mb-auto">
                <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Vuelos</li>
            </ol>
        </div>
    </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>
    <script src="<%= request.getContextPath() %>/static/js/vuelos.js"></script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
