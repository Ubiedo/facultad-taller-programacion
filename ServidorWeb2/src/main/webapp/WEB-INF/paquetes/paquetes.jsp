<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="publicar.DataPaquete"%>
   <%@page import="java.util.List"%>
   <%@ taglib prefix="c" uri="jakarta.tags.core" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
    <title>Paquetes de Rutas | Volando.uy</title>

    <jsp:include page="/WEB-INF/template/head.jsp" />
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/paquetes.css">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
      <script src="${pageContext.request.contextPath}/static/js/paquetes.js" type="text/javascript" defer></script>
</head>
<body>

<jsp:include page="/WEB-INF/template/header.jsp" />
<main class="container my-5">
    <div class="row justify-content-center">
        
            <%! List<DataPaquete> paquetes; %>
                    		<%
                            	paquetes = (List<DataPaquete>) request.getAttribute("paquetes");
							%>
            <c:forEach var="paquete" items="${paquetes}">
                <div class="col-lg-4 col-md-6 col-sm-12 d-flex align-items-stretch mb-4">
	                <a href="${pageContext.request.contextPath}/paquetes/${paquete.getNombre()}" class="card bg-body-tertiary">
	                    <img src="${pageContext.request.contextPath}/Imagenes?id=${paquete.getImagen()}" class="imagen" alt="foto paquete de rutas">
	                    <div class="card-body">
	                        <h5 class="card-title">${paquete.getNombre()}</h5>
	                        <p class="card-text">${paquete.getDescripcion()}</p>
	                        <div class="triangle-descuento"><fmt:formatNumber value="${paquete.getDescuento()}" maxFractionDigits="0" />%</div>
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
            <li class="breadcrumb-item active" aria-current="page">Paquetes</li>
        </ol>
    </div>
</nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
