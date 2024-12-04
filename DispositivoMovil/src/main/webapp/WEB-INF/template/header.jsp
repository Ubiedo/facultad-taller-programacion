<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.Usuarios"%>
   <%@page import="publicar.TipoSesion"%>
          
      <style>
        input[type="search"]::-webkit-search-cancel-button {
          -webkit-appearance: none;
          appearance: none;
        }
        .btn-close {
          width: .5em;
          height: .5em;
        }
        .nav-img {
          height: 30px;
          width: 30px;
          object-fit: cover;
          border-radius: 100%;
        }
      </style>
        <nav class="navbar navbar-expand-lg bg-body-tertiary px-3" id="buscador" style="height: fit-content;">
          <div class="container-fluid">
      		<% String nickname = (String) request.getSession().getAttribute("sesion_id"); %>
      		<% String imagen = (String) request.getSession().getAttribute("sesion_image"); %>
		    <button style="border: 0px;" class="navbar-toggler p-0" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
        	<div class="d-flex align-items-center">
            	<a class="navbar-brand"><%= nickname %></a>
            	<img src="${pageContext.request.contextPath}/Imagenes?id=<%= imagen%>" class="nav-img my-auto me-2" id="a-img">
        	</div>
		    <div class="offcanvas offcanvas-start px-3 w-100 h-100" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
		      <div class="offcanvas-header">
		        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Volando.uy</h5>
		        <button type="button" class="btn-close pe-1" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		      </div>
		      <div class="offcanvas-body pt-0">
		          <hr class="my-2">
		        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
		          <li class="nav-item">
		            <a class="nav-link" href="${pageContext.request.contextPath}/rutas">Rutas</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="${pageContext.request.contextPath}/vuelos/">Vuelos</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="${pageContext.request.contextPath}/usuarios/reservas">Reservas sin check-in</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link" href="${pageContext.request.contextPath}/usuarios/cargarReservas">Consultar Reservas</a>
		          </li>
		         </ul>
		          <hr class="my-2">
		        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
		          <li class="nav-item">
	                <a class="nav-link" aria-disabled="true" id="changeTheme">
	                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-brightness-high-fill" viewBox="0 0 16 16">
	                      <path d="M12 8a4 4 0 1 1-8 0 4 4 0 0 1 8 0M8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0m0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13m8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5M3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8m10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0m-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0m9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707M4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708"/>
	                  </svg>
	                  <span>Cambiar Modo</span>
	                </a>
		          </li>
		        </ul>
		          <hr class="">
		        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
		          <li class="nav-item">
		            <a class="nav-link text-danger-emphasis" href="${pageContext.request.contextPath}/usuarios/logout">Cerrar Sesion</a>
		          </li>
		        </ul>
		        <form class="d-flex mt-3 fixed-bottom mb-3 mx-3" role="search" action="${pageContext.request.contextPath}/buscar" method="POST">
		          <input class="form-control me-2" type="search" name="buscado" placeholder="Buscar" aria-label="Buscar">
		          <button class="btn btn-outline-success" type="submit">
					<svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                  		<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                	</svg>
				  </button>
		        </form>
		      </div>
		    </div>
          </div>
        </nav>
        
        <script src="${pageContext.request.contextPath}/static/js/header.js" type="text/javascript" defer></script>