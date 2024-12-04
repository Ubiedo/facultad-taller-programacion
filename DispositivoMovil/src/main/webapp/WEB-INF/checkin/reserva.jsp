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
    <style><%@include file="/static/css/reserva.css"%></style>

</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />

<main class = "container my-5">
	<%
       DataClienteVuelo reserva = (DataClienteVuelo) request.getAttribute("reserva");	
	%>
	<div class="package-container">
	<p class="package-title">Reserva:</p>
		<p class="package-info">Vuelo reservado: <span>${reserva.getVuelo()}</span></p>
		<p class="package-info">Ruta: <span>${reserva.getRuta()}</span></p>
		<p class="package-info">Aerolínea: <span>${reserva.getAerolinea()}</span></p>
		<p class="package-info">Fecha de reserva: <span>${reserva.getFechaReserva()}</span></p>
		<p class="package-info">Tipo de asiento: <span>${reserva.getTipoAsiento()}</span></p>
		<p class="package-info">Costo: <span>$ ${reserva.getCosto()}</span></p>
		<p class="package-info">Cantidad de equipaje extra: <span>${reserva.getCantEquipajeExtra()}</span></p>
		<p class="package-info">Pasajes: <span>${reserva.getCantPasajes()}</span></p>
			<% 
	           List<DataPasaje> pasajes = (List<DataPasaje>) reserva.getPasajes(); 
	        %>
	        <%
            	if (pasajes != null) {
               		for (DataPasaje pasaje : pasajes) {
            %>
				<p class="package-info"><span>• <%= pasaje.getNombre() %> <%= pasaje.getApellido() %></span></p>
			<%
					}
				}
			%> 
		<form action="${pageContext.request.contextPath}/usuarios" method="POST">
		    <input type="hidden" name="formId" value="checkIn">
		    <input type="hidden" name="nombreVuelo" value="${reserva.getVuelo()}">
		    <%
				String exito = (String) request.getAttribute("checkInExitoso");
				if (exito == "null" || exito=="false") {
			%>
		    	<button class="btn btn-primary" type="submit" id="checkIn">Realizar check in</button>
		    <%
				} else if(exito=="true"){
						%>
				<button class="btn btn-primary" type="submit" id="checkIn" disabled>Realizar check in</button>
			<%
				}
			%>
		</form>
		<div class="modal" tabindex="-1" id="mensajeDeExito">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title">Check-in exitoso </h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                <p>El check-in de la reserva fue realizado con éxito!</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-success" data-bs-dismiss="modal">Cerrar</button>
	            </div>
		        </div>
		    </div>
		</div>


		
		<div class="modal" tabindex="-1" id="mensajeError">
		    <div class="modal-dialog modal-dialog-centered">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">Error </h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <div class="modal-body">
		                <p>No se pudo realizar el check-in de la reserva.</p>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
		            </div>
		        </div>
		    </div>
		</div>
		
		<script>
		    document.addEventListener('DOMContentLoaded', function () {
		    	var exito = "<%= exito %>";
		        if (exito=="true") {
		            var mensajeDeExito = new bootstrap.Modal(document.getElementById('mensajeDeExito'));
		            mensajeDeExito.show();
		        }else if(exito=="false"){
		        	var mensajeError = new bootstrap.Modal(document.getElementById('mensajeError'));
		        	mensajeError.show();
		        }
		    });
		</script>
		
	</div>
</main>

<div id="footer">
	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
	      <div class="container-fluid">
	        <ol class="breadcrumb navbar-text mb-auto">
	          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	          <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/usuarios/reservas">Reservas sin check in</a></li>
	          <li class="breadcrumb-item active" aria-current="page">Reserva de ${reserva.vuelo}</li>
	        </ol>
	      </div>
	 </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>