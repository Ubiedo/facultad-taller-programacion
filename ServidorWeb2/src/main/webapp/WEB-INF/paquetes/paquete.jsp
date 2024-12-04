<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Map"%>
    <%@page import="java.time.LocalDate"%>
    <%@page import="publicar.ParPaqueteRutas"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paquete</title>
	<jsp:include page="/WEB-INF/template/head.jsp" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <style><%@include file="/static/css/paquete.css"%></style>
    
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<main class="container my-5">

        <!-- Contenedor del paquete -->
        <div class="package-container">
         
                    		<%
                    		ParPaqueteRutas paquete = (ParPaqueteRutas) request.getAttribute("paquete");	
							%>
        	 <div class="row">
        	 	 <div class="col-md-6">
		            <h2 class="package-title">${paquete.getPaquete().getNombre()}</h2>
		            <img src="${pageContext.request.contextPath}/Imagenes?id=${paquete.getPaquete().getImagen()}" class="imagen" alt="foto paquete de rutas">
		             
		            <p class="package-info">Rutas incluídas en el paquete: <span>${paquete.getCantRutas()}</span></p>
		            		<% 
	                        Map<String, String> rutas = paquete.getNombreImgRutas(); 
	                        %>
	                        <%
                                	if (rutas != null) {
                                		for (String keyruta : rutas.keySet()) {
                                			String imagenRuta = rutas.get(keyruta);
                             %>
						<p class="package-info">
							<img src="${pageContext.request.contextPath}/Imagenes?id=<%= imagenRuta %>" class="imagen-ruta" alt="foto ruta RV03">
							<span><%= keyruta %></span>
						    <a href="${pageContext.request.contextPath}/rutas/<%= keyruta %>" class="link">Ver más.</a>
						</p>
					  	<%
					        }
					    }
						%>  	
					<p class="package-info">Rutas tipo ejecutivo: <span>${paquete.getCantEjecutivo()}</span></p>
		        	<p class="package-info">Rutas tipo turista: <span>${paquete.getCantTurista()}</span></p>
		            
	        	</div>
	        	
	        	
	        	<div class="col-md-6">
		            <p class="package-info">Descripción: </p>
		            <p class="package-info"> <span> ${paquete.getPaquete().getDescripcion()}</span></p>
		            <p class="package-info">Fecha de alta: <span>${paquete.getPaquete().getFechaAlta()}</span></p>
		            <p class="package-info">Días de validez: <span>${paquete.getPaquete().getDiasValidez()}</span></p>
		            <p class="package-info"><span class="costo-sin-desc">$ ${paquete.getPaquete().getCosto()}</span></p>
		            
		            <form action="${pageContext.request.contextPath}/paquetes/${paquete.getPaquete().getNombre()}" method="post">
					    <input type="hidden" name="pedidoCompra" value="comprar">
					    <input type="hidden" name="nombrePaquete" value="${paquete.getPaquete().getNombre()}">
					    <input type="hidden" name="validez" value="${paquete.getPaquete().getDiasValidez()}">
					    <input type="hidden" name="costo" value="${paquete.getPaquete().getCostoDesc()}">
					    
					    <%
						    boolean noEsCliente = (boolean) request.getAttribute("noEsCliente");
						    boolean tieneRutas = (boolean) request.getAttribute("tieneRutas");
						    boolean noComprado = (boolean) request.getAttribute("noComprado");
						    Boolean exito = (Boolean) request.getAttribute("compraExitosa");
						%>
					    
					    
					    <p class="package-info">
		            	<span class= "costo">$ ${paquete.getPaquete().getCostoDesc()}</span>
		            	<span class="discount"><fmt:formatNumber value="${paquete.getPaquete().getDescuento()}" maxFractionDigits="0" />% OFF</span>
		            	<%
						    if (!noEsCliente && tieneRutas && noComprado) {
						%>
						    <button type="submit" id="btnComprar" class="btn btn-primary ms-3" data-paquete="${paquete}" >Comprar</button>
							
						<%
						    } else if(!noEsCliente || (exito != null && exito)){
						%>
						    <button type="button" id="btnComprar" class="btn btn-primary ms-3" disabled >Comprar</button>
						<%
						    }
						%>

		            	</p>
					</form>

		           <%
						    if (!noEsCliente && !tieneRutas) {
						        out.println("<div style='color: red;'>El paquete no contiene rutas.</div>");
						    } else if (!noEsCliente && !noComprado && exito == null) {
						      	out.println("<div style='color: red;'>Ya compraste este paquete de rutas.</div>");
						    }
					        
					    %>
					
					<%				    
						    if (exito != null && exito) {
						        out.println("<div style='color: green;'>La compra se realizó con éxito.</div>");
						    } else if (exito != null && !exito) {
						        out.println("<div style='color: red;'>La compra no se pudo realizar.</div>");
						    }
					        
					    %>

        		</div>
        
        	</div>
        </div>

    </main>

    <div id="footer">
	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
	      <div class="container-fluid">
	        <ol class="breadcrumb navbar-text mb-auto">
	          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	          <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/paquetes/">Paquetes</a></li>
	          <li class="breadcrumb-item active" aria-current="page">${paquete.getPaquete().getNombre()}</li>
	        </ol>
	      </div>
	 </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	
</body>
</html>