<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="publicar.*" %>
<%@ page import="java.util.Set, java.util.List" %>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vuelo</title>
        <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/vuelo.css">
    

</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp" />

<% 
	TipoSesion tSesion = (TipoSesion) request.getSession().getAttribute("tipo_sesion");
	DataVuelo vuelo = (DataVuelo) request.getAttribute("vuelo"); 
	String nickAero = (String) request.getAttribute("nickAero");
	String nombreAero = (String) request.getAttribute("nombreAero");
	DataRuta ruta = (DataRuta) request.getAttribute("ruta");
	Set<DataClienteVuelo> reservas = (Set<DataClienteVuelo>) request.getAttribute("reservas");
	
	
	
	%>
<div class="container rounded mb-3">
    <div class="card">
        <!-- Encabezado con imagen del vuelo -->
        <div class="flight-header">
        <%if (!vuelo.getImagen().equals("")){
        	%>
            <div id="imagen">
            	<img src="${pageContext.request.contextPath}/Imagenes?id=<%= vuelo.getImagen() %>" style="max-width:300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);"  alt="foto vuelo">
            </div>
            <%} %>
            <div class="flight-title-container" id="vuelo-titulo">
            	<h1 class="flight-title">Vuelo <%=vuelo.getNombre() %></h1>
					<a href="${pageContext.request.contextPath}/usuarios/<%=nickAero%>">
						<button type="button" class="btn flight-airline"><%=nombreAero %></button>
					</a>
            </div>
        </div>
        
        <!-- Detalles del vuelo -->
        <div class="flight-details" id="vuelo-info"><!-- Datos del vuelo -->
	         <p class="flight-info"><strong>Ruta de Vuelo: </strong><a href="${pageContext.request.contextPath}/rutas/<%= ruta.getNombre()%>"><%= ruta.getNombre()%></a></p>
	        <p class="flight-info"><strong>Duración:</strong> <%=vuelo.getDuracion() %> h</p>
	        <p class="flight-info"><strong>Asientos turista:</strong> <%=vuelo.getAsientosTurista() %></p>
	        <p class="flight-info"><strong>Asientos ejecutivo:</strong> <%=vuelo.getAsientosEjecutivo() %></p>
	        <p class="flight-info"><strong>Aerolínea:</strong> <%=nombreAero %></p>
	        <p class="flight-info"><strong>Fecha:</strong> <%=vuelo.getFecha() %></p>
	        <p class="flight-info"><strong>Fecha de alta:</strong> <%=vuelo.getFechaAlta() %></p>
	        
	        <%
	        	if ((tSesion == TipoSesion.CLIENTE) ){
	        		if ((reservas.isEmpty())){
	        			request.getSession().setAttribute("nombreVuelo", vuelo.getNombre());
	        		
	        		
	        	

	        %>
	        
	        <button class="btn btn-primary w-100" type="button" onclick="window.location.href='${pageContext.request.contextPath}/vuelos/reservarVuelo'">Reservar</button>
	        <%
	        		}else{
	        			for (DataClienteVuelo reserva: reservas){
	        
	        	%>
	        
	        	<div class="accordion" id="accordionFiltros" >
		                        <div class="accordion-item">
		                            <h2 class="accordion-header" id="headingOne">
		                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
		                                    Ver Reserva
		                                </button>
		                            </h2>
		                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" >
		                                <div class="accordion-body">
										<ul class="list-group list-group-hover list-group-striped">
					                        <li class="list-group-item"><strong>Aerolinea:</strong> <a href="${pageContext.request.contextPath}/usuarios/<%=nickAero%>"><%=nombreAero %></a></li>
					                        <li class="list-group-item"><strong>Ruta:</strong> <a href="${pageContext.request.contextPath}/rutas/<%= ruta.getNombre()%>" ><%= ruta.getNombre()%></a></li>
					                        <li class="list-group-item"><strong>Vuelo:</strong> <a><%=vuelo.getNombre() %></a></li>
					                        <li class="list-group-item"><strong>Fecha De Reserva:</strong> <span id="reserva-fecha"><%= reserva.getFechaReserva() %></span></li>
					                        <li class="list-group-item"><strong>Tipo De Asiento:</strong> <span id="reserva-tipoAsiento"><%=reserva.getTipoAsiento() %></span></li>
					                        <li class="list-group-item"><strong>Cantidad de Pasajes:</strong> <span id="reserva-cantPasajes"><%= reserva.getCantPasajes() %></span></li>
					                        <li class="list-group-item"><strong>Cantidad de Equipaje Extra:</strong> <span id="reserva-cantEquipaje"><%= reserva.getCantEquipajeExtra() %></span></li>
					                        <li class="list-group-item"><strong>Costo:</strong> <span id="reserva-costo"><%=reserva.getCosto() %></span></li>
											<li class="list-group-item">
											    <strong>Pasajes:</strong>
											    
											    <%
											    List<DataPasaje> pasajes = reserva.getPasajes();
											    for (DataPasaje pasaje: pasajes){
											    	
											    
											    
											    %>
											    <ul id="lista-pasajeros" class="list-group list-group-flush">
											        <li class="list-group-item" style="border: none;"><%=pasaje.getNombre() %> <%=pasaje.getApellido() %></li>
											    </ul>
											    <%
	        									}
											    
											    %>

					                      </ul>
		                                </div>
		                            </div>
		                        </div>
		                </div>
	        
	        <%
	        			}
	        		}
	        	}else if ((tSesion == TipoSesion.AEROLINEA)&&(!reservas.isEmpty())){
	        		
	        	
	        %>
	        	<div class="accordion" id="accordionFiltros" >
				                        <div class="accordion-item">
				                            <h2 class="accordion-header" id="headingOne">
				                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
				                                    Ver Reservas
				                                </button>
				                            </h2>
				                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" >
				                                <div class="accordion-body" id="items">
				                                <%
				                                Integer numRes = 1;
				                                for (DataClienteVuelo reserva: reservas){
				                                
				                                
				                                %>
				                                
				                                <div class="accordion-item">
										            <h2 class="accordion-header" id="headingTwo">
										                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
										                    <%=numRes %>
										                </button>
										            </h2>
										            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" >
										                <div class="accordion-body">
														<ul class="list-group list-group-hover list-group-striped">
										                   <li class="list-group-item"><strong>Aerolinea:</strong> <a href="${pageContext.request.contextPath}/usuarios/<%=nickAero%>"><%=nombreAero %></a></li>
									                        <li class="list-group-item"><strong>Ruta:</strong> <a href="${pageContext.request.contextPath}/rutas/<%= ruta.getNombre()%>" ><%= ruta.getNombre()%></a></li>
									                        <li class="list-group-item"><strong>Vuelo:</strong> <a><%=vuelo.getNombre() %></a></li>
									                        <li class="list-group-item"><strong>Fecha De Reserva:</strong> <span id="reserva-fecha"><%= reserva.getFechaReserva() %></span></li>
									                        <li class="list-group-item"><strong>Tipo De Asiento:</strong> <span id="reserva-tipoAsiento"><%=reserva.getTipoAsiento() %></span></li>
									                        <li class="list-group-item"><strong>Cantidad de Pasajes:</strong> <span id="reserva-cantPasajes"><%= reserva.getCantPasajes() %></span></li>
									                        <li class="list-group-item"><strong>Cantidad de Equipaje Extra:</strong> <span id="reserva-cantEquipaje"><%= reserva.getCantEquipajeExtra() %></span></li>
									                        <li class="list-group-item"><strong>Costo:</strong> <span id="reserva-costo"><%=reserva.getCosto() %></span></li>
															<li class="list-group-item">
															    <strong>Pasajes:</strong>
															    
															    <%
															    for (DataPasaje pasaje : reserva.getPasajes()){
															    	
															    
															    %>
															    <ul id="lista-pasajeros" class="list-group list-group-flush">
											        				<li class="list-group-item" style="border: none;"><%=pasaje.getNombre() %> <%=pasaje.getApellido() %></li>
											    				</ul>
											    				<%} %>
										
										                  </ul>
												  		</div>
													</div>
													</div>
													                                
				                                <%
	        										numRes++;
				                                } 
				                                numRes = 1;
				                                %>
												
				                                </div>
				                            </div>
				                        </div>
				                </div>
	        	
	        	<%} %>
        </div>
    </div>
</div>

<div id="footer">
<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
      <div class="container-fluid">
        <ol class="breadcrumb navbar-text mb-auto">
          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/vuelos">Vuelos</a></li>
          <li class="breadcrumb-item active" aria-current="page" id="nombre-breadcrumb"><%=vuelo.getNombre() %></li>
        </ol>
      </div>
    </nav>
<jsp:include page="/WEB-INF/template/footer.jsp" />
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
