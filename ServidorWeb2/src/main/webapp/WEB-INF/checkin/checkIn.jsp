<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="publicar.*" %>
<%@ page import="java.util.Set, java.util.List" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CheckIn</title>
        <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/checkIn.css">
    

</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />

<%
DataCheckIn checkIn = (DataCheckIn) request.getSession().getAttribute("CheckIn");
String Vuelo = (String) request.getAttribute("Vuelo");

%>

<div class="container rounded">
	<div class="card">
		<div class="header">
			<div class="titulo">
				<h1>Check-In</h1>
			<a href="${pageContext.request.contextPath}/usuarios/crearPdf">	
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-arrow-down-fill" viewBox="0 0 16 16">
	  				<path d="M9.293 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.707A1 1 0 0 0 13.707 4L10 .293A1 1 0 0 0 9.293 0M9.5 3.5v-2l3 3h-2a1 1 0 0 1-1-1m-1 4v3.793l1.146-1.147a.5.5 0 0 1 .708.708l-2 2a.5.5 0 0 1-.708 0l-2-2a.5.5 0 0 1 .708-.708L7.5 11.293V7.5a.5.5 0 0 1 1 0"/>
				</svg>
				Tarjeta de Embarque
			</a>
			</div>
			
				
		</div>
		<div class="contenedor-datos">
			<div class="fecha-hora">
				<p class="datos"><strong>Vuelo: </strong> <%=Vuelo %></p>
				<p class="datos"><strong>Fecha de Check-In: </strong> <%=checkIn.getFechaCheckIn() %></p>
			</div>
			<div class="fecha-hora">
				<p class="datos"><strong>Fecha de embarque:		</strong><%=checkIn.getFechaEmbarque() %></p>
				<p class="datos hora"><strong>Hora de embarque: 	</strong> <%=checkIn.getHoraEmbarque()  %>h </p>
			</div>
			<hr>
			<!-- Contenedor de pasajes y asientos en una grilla -->
			<div class="pasajes-asientos-grid">
				<div class="datos"><strong>Pasajes</strong></div>
				<div class="datos"><strong>Asientos</strong></div>
				<%for (DataPasaje pasaje : checkIn.getPasajesConCheckIn()){
					%>
				
				<div class="item"><%= pasaje.getNombre() %> <%= pasaje.getApellido() %> </div>
				
				<div class="item"><%= pasaje.getAsiento().charAt(0) %>-<%= pasaje.getAsiento().substring(1) %></div>
				
				<%} %>
				
			</div>
		</div>
	</div>
</div>









<div id="footer">
<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
      <div class="container-fluid">
        <ol class="breadcrumb navbar-text mb-auto">
          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/vuelos">Reservas con CheckIn</a></li>
          <li class="breadcrumb-item active" aria-current="page" id="nombre-breadcrumb"><%=Vuelo %></li>
        </ol>
      </div>
    </nav>
<jsp:include page="/WEB-INF/template/footer.jsp" />
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>