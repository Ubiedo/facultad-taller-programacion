<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="publicar.DataClienteVuelo"%>
<%@page import="publicar.DataPasaje"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reserva</title>

	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <style><%@include file="../../static/css/reserva.css"%></style>

</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />

<main class = "container my-5">
	<%
       DataClienteVuelo reserva = (DataClienteVuelo) request.getAttribute("reserva");	
	%>
	<h1>Consultar reserva</h1>
	<div class="package-container">
		<p class="package-info"><strong>Vuelo reservado:</strong> <span>${reserva.getVuelo()}</span></p>
		<p class="package-info"><strong>Ruta:</strong> <span>${reserva.getRuta()}</span></p>
		<p class="package-info"><strong>Aerolínea:</strong> <span>${reserva.getAerolinea()}</span></p>
		<p class="package-info"><strong>Fecha de reserva:</strong> <span>${reserva.getFechaReserva()}</span></p>
		<p class="package-info"><strong>Tipo de asiento:</strong> <span>${reserva.getTipoAsiento()}</span></p>
		<p class="package-info"><strong>Costo: </strong><span>${reserva.getCosto()}</span></p>
		<p class="package-info"><strong>Cantidad de equipaje extra:</strong> <span>${reserva.getCantEquipajeExtra()}</span></p>
		<p class="package-info"><strong>Pasajes:</strong> <span>${reserva.getCantPasajes()}</span></p>
			<% 
	           List<DataPasaje> pasajes = (List<DataPasaje>) reserva.getPasajes(); 
	        %>
	        <%
            	if (pasajes != null) {
               		for (DataPasaje pasaje : pasajes) {
            %>
				<p class="package-info"><span><%= pasaje.getNombre() %> <%= pasaje.getApellido() %></span></p>
			<%
					}
				}

			%> 
			
		<a href='${pageContext.request.contextPath}/usuarios/checkIn' class='btn btn-primary w-100'>Consultar Check-In</a>
		

		
		
		
	</div>
</main>

<div id="footer">
	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
	      <div class="container-fluid">
	        <ol class="breadcrumb navbar-text mb-auto">
	          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	          <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/reservas">Consulta Reservas</a></li>
	          <li class="breadcrumb-item active" aria-current="page">Reserva de ${reserva.vuelo}</li>
	        </ol>
	      </div>
	 </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>