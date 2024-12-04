<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="publicar.DataRuta"%>
<%@page import="java.util.Set"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import ="publicar.TipoSesion" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<title>Rutas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/rutas.css">
</head>

<body>
      <!-- Header -->
      <jsp:include page="/WEB-INF/template/header.jsp" />
      <%
		TipoSesion tipo_sesion = (TipoSesion) request.getSession().getAttribute("tipo_sesion");
		String botonRuta = "";
		if (tipo_sesion == TipoSesion.AEROLINEA) {
		    botonRuta = "<a href='"+request.getContextPath()+"/rutas/crear' class='btn btn-primary' style='margin-right: 72px;'>Crear Nueva Ruta</a>";
		}
	   %>
	   
    <div class="text-end my-3" id="boton-ruta"> <%= botonRuta %></div>
    
      <!-- Contenido -->
      <div class="container my-4">
        <div class="row justify-content-center">
        
        	<!-- Filtros laterales -->
            <aside class="col-md-3 sticky-top" style="height: fit-content;">
                <h4 class="py-3" style="text-align:center;">Filtrar</h4>
                <div class="accordion pb-3" id="accordionFiltros" >
                    <!-- Un formulario para todos los filtros -->

                        <form id="filterForm">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Nacionales" id="checkNacionales" checked>
                                <label class="form-check-label" for="checkNacionales">
                                    Nacionales
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Internacionales" id="checkInternacionales" checked>
                                <label class="form-check-label" for="checkInternacionales">
                                    Internacionales
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Europa" id="checkEuropa" checked>
                                <label class="form-check-label" for="checkEuropa">
                                    Europa
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="America" id="checkAmerica" checked>
                                <label class="form-check-label" for="checkAmerica">
                                    America
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Exclusivos" id="checkExclusivos" checked>
                                <label class="form-check-label" for="checkExclusivos">
                                    Exclusivos
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Temporada" id="checkTemporada" checked>
                                <label class="form-check-label" for="checkTemporada">
                                    Temporada
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Cortos" id="checkCortos" checked>
                                <label class="form-check-label" for="checkCortos">
                                    Cortos
                                </label>
                            </div>
                        </form>

                </div>
            </aside>
            
        
            <!-- Rutas -->
            <div class="col-md-9">
                <div class="row" id="flight-cards">
                    <!-- Ruta Card -->
                    <%! Set<DataRuta> rutas; %>
                    <%
                    	rutas = (Set<DataRuta>) request.getAttribute("rutas");
					%>
					<c:forEach items="${rutas}" var="ruta">
	                    <div class="col-md-6 mb-4 flight-option <c:forEach items="${ruta.getCategorias()}" var="categoria"> ${categoria}</c:forEach>">
	                      <a href="${pageContext.request.contextPath}/rutas/${ruta.getNombre()}"">
	                        <div class="card">
	                        <c:if test="${not ruta.getImagen().equals('(Sin Imagen)')}" >
	                            <img src="${pageContext.request.contextPath}/Imagenes?id=${ruta.getImagen()}" class="card-img-top" alt="Imagen destino" height="300">
	                        </c:if>
	                            <div class="card-body">
	                                <h5 class="card-title">${ruta.getNombre()}</h5>
	                                <hr>
	                                <p class="flight-info">${ruta.getDescCorta()}<span></span></p>
	                            </div>
	                        </div>
	                      </a>
	                    </div>
   					</c:forEach>
                    
                </div>
            </div>
        </div>
    </div>

    <div id="footer">
	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
	      <div class="container-fluid">
	        <ol class="breadcrumb navbar-text mb-auto">
	          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	          <li class="breadcrumb-item active" aria-current="page">Rutas</li>
	        </ol>
	      </div>
	    </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>
    <script>
    function applyFilters() {
        var selectedCategories = [];
        if (document.getElementById("checkNacionales").checked) selectedCategories.push("Nacionales");
        if (document.getElementById("checkInternacionales").checked) selectedCategories.push("Internacionales");
        if (document.getElementById("checkEuropa").checked) selectedCategories.push("Europa");
        if (document.getElementById("checkAmerica").checked) selectedCategories.push("America");
        if (document.getElementById("checkExclusivos").checked) selectedCategories.push("Exclusivos");
        if (document.getElementById("checkTemporada").checked) selectedCategories.push("Temporada");
        if (document.getElementById("checkCortos").checked) selectedCategories.push("Cortos");
        var rutas = document.querySelectorAll('.flight-option');
        rutas.forEach(function(ruta) {
            var categoriasRuta = ruta.classList;
            var visible = false;
            selectedCategories.forEach(function(categoria) {
                if (categoriasRuta.contains(categoria)) {
                    visible = true;
                }
            });
            ruta.style.display = visible ? 'block' : 'none';
        });
    }
    document.getElementById('checkNacionales').addEventListener('change', applyFilters);
    document.getElementById('checkInternacionales').addEventListener('change', applyFilters);
    document.getElementById('checkEuropa').addEventListener('change', applyFilters);
    document.getElementById('checkAmerica').addEventListener('change', applyFilters);
    document.getElementById('checkExclusivos').addEventListener('change', applyFilters);
    document.getElementById('checkTemporada').addEventListener('change', applyFilters);
    document.getElementById('checkCortos').addEventListener('change', applyFilters);
    applyFilters();
    </script>  
   	<!-- Bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            
</body>
</html>