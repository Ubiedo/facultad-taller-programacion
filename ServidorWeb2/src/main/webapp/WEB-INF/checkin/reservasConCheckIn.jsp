<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@page import="publicar.DataReserva"%>
   <%@page import="java.util.List"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<title>Reservas con check in</title>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reservasConCheckIn.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />
<main class="container my-5">

    <div class="row justify-content-center">
        <h1 style="text-align: center;">Reservas con Check-in</h1>
            <%! List<DataReserva> reservas; %>
                    		<%
                            	reservas = (List<DataReserva>) request.getAttribute("reservas");
							%>
            <c:forEach var="reserva" items="${reservas}">
                <div class="col-md-9">
	                <a href="${pageContext.request.contextPath}/usuarios/VerReserva?nombreVuelo=${reserva.vuelo}" class="card bg-body-tertiary">
	                    <div class="card-body">
	                    	<h5 class="card-title"><strong>Vuelo :</strong> <span>${reserva.getVuelo()}</span></h5>
	                    	<h5 class="card-text">Fecha: <span>${reserva.getFechaReserva()}</span></h5>
	                        <h5 class="card-text">Aerolínea: <span>${reserva.getAerolinea()}</span></h5>
	                        <h5 class="card-text">Costo: <span>$ ${reserva.getCosto()}</span></h5>
	                    </div>
	                </a>
	             </div>
            </c:forEach>
        
    </div>
</main>

<div id="footer">
<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
    <div class="container-fluid">
        <ol class="breadcrumb navbar-text mb-auto">
            <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">Reservas sin check in</li>
        </ol>
    </div>
</nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>