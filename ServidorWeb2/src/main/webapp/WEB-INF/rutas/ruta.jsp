<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="publicar.DataRuta"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Ruta</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/ruta.css">
</head>
<body>
      <!-- Header -->
      <jsp:include page="/WEB-INF/template/header.jsp" />
   	<%! DataRuta rutaVuelo; %>
   	<%
       	rutaVuelo = (DataRuta) request.getAttribute("ruta");
	%>
	<%! String nicknameAerolinea; %>
	<%
       	nicknameAerolinea = (String) request.getAttribute("nicknameAerolinea");
	%>
	<%! String nombreAerolinea; %>
	<%
       	nombreAerolinea = (String) request.getAttribute("nombreAerolinea");
	%>
	<%! String ciudadOrigen; %>
	<%
       	ciudadOrigen = (String) request.getAttribute("ciudadOrigen");
	%>
		<%! String ciudadDestino; %>
	<%
       	ciudadDestino = (String) request.getAttribute("ciudadDestino");
	%>
		<%! String categoriasString; %>
	<%
		categoriasString = (String) request.getAttribute("categoriasString");
	%>
<div class="container rounded mb-3">
    <div class="card">
        <!-- Encabezado con imagen de ruta -->
        <div class="flight-header">
            <div id="imagen">
            	<!-- Imagen de ruta -->
            	<%if (!rutaVuelo.getImagen().equals("(Sin Imagen)")){
            		%>
            	
            	<img src="${pageContext.request.contextPath}/Imagenes?id=<%= rutaVuelo.getImagen()%>" style="max-width:300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);"  alt="foto ruta">
            	<%} %>
            </div>
            <div class="flight-title-container" id="ruta-titulo"><!-- Nombre de ruta -->
            	<h1 class="flight-title">Ruta <%= rutaVuelo.getNombre()%></h1>
				<a href="${pageContext.request.contextPath}/usuarios/<%= nicknameAerolinea %>">
					<button type="button" class="btn flight-airline"><%= nombreAerolinea %></button>
				</a>
            </div>
        </div>
        
        <!-- Detalles de ruta -->
        <div class="flight-details" id="ruta-info">
        	<!-- Datos de ruta -->
        	<p class="flight-info"><strong>Aerolinea:</strong> <%= nombreAerolinea %></p>
        	<p class="flight-info"><strong>Descripción:</strong> <%= rutaVuelo.getDescripcion()%></p>
        	<p class="flight-info"><strong>Hora:</strong> <%= rutaVuelo.getHora()%></p>
        	<p class="flight-info"><strong>Costo Turista:</strong> <%= rutaVuelo.getTuristaBase()%></p>
        	<p class="flight-info"><strong>Costo Ejecutivo:</strong> <%= rutaVuelo.getEjecutivoBase()%></p>
        	<p class="flight-info"><strong>Costo Equipaje Extra:</strong> <%= rutaVuelo.getUnidadEquipajeExtra()%></p>
        	<p class="flight-info"><strong>Ciudad Orígen:</strong> <%= ciudadOrigen %></p>
        	<p class="flight-info"><strong>Ciudad Destino:</strong> <%= ciudadDestino %></p>
        	<p class="flight-info"><strong>Fecha de alta:</strong> <%= rutaVuelo.getFechaAlta()%></p>
        	<p class="flight-info"><strong>Categorías:</strong> <%= categoriasString%></p>
        </div>
        <%
		    String youtubeLink = rutaVuelo.getVideo();
		    String embedLink = "";
		    if (!youtubeLink.isEmpty() && youtubeLink.contains("youtu.be/")) {
		        String videoId = youtubeLink.substring(youtubeLink.indexOf("youtu.be/") + 9);
		        if (videoId.contains("?")) {
		                videoId = videoId.substring(0, videoId.indexOf("?"));
		            }
		        embedLink = "https://www.youtube.com/embed/" + videoId;
		    }
		    else if (!youtubeLink.isEmpty() && youtubeLink.contains("youtube.com/watch?v=")) {
		        String videoId = youtubeLink.substring(youtubeLink.indexOf("v=") + 2);
		        if (videoId.contains("&")) {
		            videoId = videoId.substring(0, videoId.indexOf("&"));
		        }
		        embedLink = "https://www.youtube.com/embed/" + videoId;
		    }
    	%>
		<div class="flight-video my-2">
		    <h2 class="text-center">Video de la Ruta</h2>
		    <div class="video-container d-flex justify-content-center">
		        <% if (!embedLink.isEmpty()) { %>
		            <iframe width="560" height="315" 
		                    src="<%= embedLink %>" 
		                    title="YouTube video player" 
		                    frameborder="0" 
		                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
		                    allowfullscreen>
		            </iframe>
		        <% } else { %>
		            <p>Esta Ruta no Contiene Video.</p>
		        <% } %>
		    </div>
		</div>
</div>

    <div id="footer">
<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
      <div class="container-fluid">
        <ol class="breadcrumb navbar-text mb-auto">
          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/rutas">Rutas</a></li>
          <li class="breadcrumb-item active" aria-current="page" id="nombre-breadcrumb"><%= rutaVuelo.getNombre() %></li>
        </ol>
      </div>
    </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>