<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="servlets.HomeSearch"%>
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
      
      <nav class="navbar navbar-expand-lg bg-body-tertiary" id="header" style="padding-left: 1em; padding-right: 1em;">
        <div class="container-fluid">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Volando.uy</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/usuarios">
                  Usuarios
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/rutas">
                  Rutas
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/vuelos">
                  Vuelos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/paquetes/">
                  Paquetes
                </a>
              </li>
            </ul>
            <div class="">
              <button class="btn bg-transparent" type="button" id="abrirBuscador" style="border-radius: 0px; border: 0px;">
                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                </svg>
              </button>
            </div>
              <div class="vr m-2"></div>
            <ul class="navbar-nav me-0 mb-2 mb-lg-0">
				<!-- contenido dinamico -->
              	<% TipoSesion log = (TipoSesion) request.getSession().getAttribute("tipo_sesion"); %>
      			<% if (log == TipoSesion.INVITADO) { %>
  					<li class="nav-item" id="not-in">
    					<a class="nav-link" href="${pageContext.request.contextPath}/usuarios/acceso">Acceso</a>
					</li>
      			<% } else { %>
      			<% String nickname = (String) request.getSession().getAttribute("sesion_id"); %>
      			<% String imagen = (String) request.getSession().getAttribute("sesion_image"); %>
  					<div class="btn-group" id="log-in">
    					<button type="button" class="btn d-flex align-items-center justify-content-center dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="outline: none; border: 0px;">
      						<p class="m-0" id="a-nick"><%= nickname %></p> <!-- Usamos EL para obtener el valor -->
      						<img src="${pageContext.request.contextPath}/Imagenes?id=<%= imagen%>" class="nav-img my-auto ms-2" id="a-img">
    					</button>
    					<ul class="dropdown-menu dropdown-menu-end">
      						<li><a class="dropdown-item" type="button" href="${pageContext.request.contextPath}/usuarios/<%= nickname%>">Perfil</a></li>
			      			<% if (log == TipoSesion.CLIENTE) { %>
			              	<li><a class="dropdown-item" type="button" href="${pageContext.request.contextPath}/usuarios/reservas">Check-In</a></li>
			              	<%} %>
      						<li><hr class="dropdown-divider"></li>
      						<li>
      							<form action="${pageContext.request.contextPath}/usuarios" method="post">
      								<input type="text" value="logout" name="formId" hidden="true">
    								<button class="dropdown-item text-danger" type="submit" id="log-out">Cerrar Sesión</button>
								</form>
							</li>
    					</ul>
  					</div>
      			<% } %>
			  <!-- Fin dinamico  -->
              <div class="vr m-2"></div>
              <li class="nav-item">
                <a class="nav-link" aria-disabled="true" id="changeTheme">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-brightness-high-fill" viewBox="0 0 16 16">
                      <path d="M12 8a4 4 0 1 1-8 0 4 4 0 0 1 8 0M8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0m0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13m8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5M3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8m10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0m-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0m9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707M4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708"/>
                  </svg>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
        <nav class="navbar navbar-expand-lg bg-body-tertiary" id="buscador" style="height: fit-content; padding-left: 1em; padding-right: 1em; display: none;">
          <div class="container-fluid">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Volando.uy</a>
            <form class="d-flex flex-grow-1 ms-2 mx-3 mb-auto mt-auto" role="search" action="${pageContext.request.contextPath}/home" method="POST">
                <input class="form-control" id="searchInput" name="buscado" type="search" placeholder="Buscar" aria-label="Buscar" style="border-radius: 0px; box-shadow: none; border: 0px;">
                <button class="btn bg-body" type="submit" style="border-radius: 0px; border: 0px;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                    </svg>
                </button>
            </form>
            <button type="button" class="btn-close mt-1 me-2" id="close-buscador" style="box-shadow: none; width: 0.5em;"></button>
          </div>
        </nav>
        
        <script src="${pageContext.request.contextPath}/static/js/header.js" type="text/javascript" defer></script>