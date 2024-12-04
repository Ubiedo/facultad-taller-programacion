<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="publicar.DataImagenNombreType"%>
   <%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
    <head>
	  <jsp:include page="/WEB-INF/template/head.jsp" />
	  <% String buscado = (String) request.getAttribute("buscado"); %>
      <title><%= buscado %></title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
      <script src="${pageContext.request.contextPath}/static/js/carousel.js" type="text/javascript" defer></script>
    </head>
    <body>
      <!-- Header -->
      <jsp:include page="/WEB-INF/template/header.jsp" />
      <!-- Buscado -->
      <div class="content-fluid m-4">
	      <div class="row d-flex flex-wrap justify-content-center">
	      	<h3 class="text-body-secondary">Resultados obtenidos para<span class="ps-2 text-body-emphasis"><%= buscado %></span></h3>
	      </div>
	      <div class="row d-flex flex-wrap justify-content-center">
            <form class="d-flex flex-grow-1 ms-2 mx-3 mb-3 mt-auto" role="search" action="${pageContext.request.contextPath}/home" method="POST">
                <button class="btn bg-body rounded-start border-bottom boreder-1 ps-3" type="submit" style="border-radius: 0px; border: 0px;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                    </svg>
                </button>
                <input class="form-control rounded-end border-bottom boreder-1" value="<%= buscado %>" id="searchInput" name="buscado" type="search" placeholder="Buscar" aria-label="Buscar" style="border-radius: 0px; box-shadow: none; border: 0px;">
            </form>
	      </div>
      <!-- Resultados y filtros -->
      	<div class="row">
      		<div class="col d-flex flex-wrap">
      			<span class="btn bg-body-secondary rounded px-2 py-1 me-1">Encontrados<span class="text-success ps-1"><%= (int) request.getAttribute("cantidad") %></span></span>
      			<% if (((String)request.getSession().getAttribute("az")).equals("false")) { %>
      			<button class="btn bg-body-secondary rounded px-2 py-1 me-1"  onclick="showHideFilter()">Filtros <span class="text-warning ps-1" id="actives">0</span></button>
				<form method="POST" action="${pageContext.request.contextPath}/home">
				<input type="hidden" name="buscado" value="<%= buscado %>">
      			<input type="hidden" name="az" id="az">
				<button class="btn bg-body-secondary rounded px-2 py-1 me-1" type="submit" id="filtros" style="display: none;" onclick="eventFilter()">
					A-z
					<span class="ms-1">
						<svg id="add-filter" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
						</svg>
						<svg id="added-filter" style="display: none;" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2" viewBox="0 0 16 16">
						  <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0"/>
						</svg>
					</span>
				</button>
				<% } else { %>
				<button class="btn bg-body-secondary rounded px-2 py-1 me-1"  onclick="showHideFilter()">Filtros <span class="text-warning ps-1" id="actives">1</span></button>
				<form method="POST" action="${pageContext.request.contextPath}/home">
				<input type="hidden" name="buscado" value="<%= buscado %>">
      			<input type="hidden" name="az" id="az">
				<button class="btn bg-body-secondary rounded px-2 py-1 me-1" type="submit" id="filtros" style="display: none;" onclick="eventFilter()">
					A-z
					<span class="ms-1">
						<svg id="add-filter" style="display: none;" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
						</svg>
						<svg id="added-filter" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2" viewBox="0 0 16 16">
						  <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0"/>
						</svg>
					</span>
				</button>
				<% } %>
				</form>
				<script>
				    function showHideFilter() {
				        const filtros = document.getElementById("filtros");
				        
				        if (filtros.style.display === 'none' || filtros.style.display === '') {
				            filtros.style.display = 'inline'; // Mostrar filtros
				        } else {
				            filtros.style.display = 'none'; // Ocultar filtros
				        }
				    }
				    function eventFilter() {
				        const add = document.getElementById("add-filter");
				        const added = document.getElementById("added-filter");
				        const actives = document.getElementById("actives");
				        const az = document.getElementById("az");
				
				        if (add.style.display === 'none') {
				            add.style.display = 'inline';
				            added.style.display = 'none';
				            actives.textContent = '0'; // Cambia el texto del span de 1 a 0
				            az.value="false";
				        } else {
				            add.style.display = 'none';
				            added.style.display = 'inline';
				            actives.textContent = '1'; // Cambia el texto del span de 0 a 1
				            az.value="true";
				        }
				    }
				</script>
      		</div>
      	</div>
      </div>
      <!-- Objetos -->
      <div class="content-fluid mx-4 mt-4 d-flex flex-wrap justify-content-center">
        <% List<DataImagenNombreType> objetos = (List<DataImagenNombreType>) request.getAttribute("obtenidos"); %>
      	<% if (objetos.size() > 0) {
      	    for (DataImagenNombreType objeto : objetos) { %>
      	<!-- Objeto -->
      	<% if (objeto.getType().equals("Ruta")){ %>
      	<a href="${pageContext.request.contextPath}/rutas/<%= objeto.getNombre() %>" class="me-4 mb-4">
      	<% } else { %>
      	<a href="${pageContext.request.contextPath}/paquetes/<%= objeto.getNombre() %>" class="me-4 mb-4">
      	<% } %>
	      	<div class="card bg-body border border-secondary-subtle rounded" style="width: 18rem; height: fit-content;">
			  <% if(objeto.getImagen() == null || objeto.getImagen().isEmpty() || objeto.getImagen().equals("(Sin Imagen)")){ %>
                <div class="card-img-top bg-body-secondary elemento rounded-top" style="height: 8rem;">
                <!-- paquete -->
                <% if (objeto.getType().equals("Ruta")) { %>
                  <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-signpost-2-fill" viewBox="0 0 16 16">
                    <path d="M7.293.707A1 1 0 0 0 7 1.414V2H2a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1h5v1H2.5a1 1 0 0 0-.8.4L.725 8.7a.5.5 0 0 0 0 .6l.975 1.3a1 1 0 0 0 .8.4H7v5h2v-5h5a1 1 0 0 0 1-1V8a1 1 0 0 0-1-1H9V6h4.5a1 1 0 0 0 .8-.4l.975-1.3a.5.5 0 0 0 0-.6L14.3 2.4a1 1 0 0 0-.8-.4H9v-.586A1 1 0 0 0 7.293.707"/>
                  </svg>
                  <% } else { %>
                  <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-box2-fill" viewBox="0 0 16 16">
                    <path d="M3.75 0a1 1 0 0 0-.8.4L.1 4.2a.5.5 0 0 0-.1.3V15a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1V4.5a.5.5 0 0 0-.1-.3L13.05.4a1 1 0 0 0-.8-.4zM15 4.667V5H1v-.333L1.5 4h6V1h1v3h6z"/>
                  </svg>
                  <% } %>
                </div>
               <% } else { %>
			  	<img src="${pageContext.request.contextPath}/Imagenes?id=<%= objeto.getImagen() %>" class="card-img-top" style="height: 8rem;object-fit: cover;" alt="...">
               <% } %>
			  <div class="card-body">
			  	<div style="height: 3em;">
			    	<h5 class="card-title"><%= objeto.getNombre() %></h5>
			  	</div>
			    <hr>
	    		<h6 class="card-subtitle text-body-secondary text-end"><%= objeto.getType() %></h6>
			  </div>
			</div>
		</a>
		<% }
      	} else { %>
      	   <p>No Hay Matches</p> 
      	<% } %>
      </div>
      <!-- Footer -->
      <div id="footer">
      	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
        	<div class="container-fluid">
          	<ol class="breadcrumb navbar-text mb-auto">
            	<li class="breadcrumb-item active" aria-current="page">Home</li>
            <li class="breadcrumb-item active" aria-current="page"><%= buscado %></li>
          	</ol>
        	</div>
      	</nav>	
      	<jsp:include page="/WEB-INF/template/footer.jsp" />
      </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>