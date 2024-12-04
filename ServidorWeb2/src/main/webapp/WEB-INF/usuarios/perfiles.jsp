<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <%@page import="publicar.DataUsuario"%>
   <%@page import="java.util.Set"%>
   <%@page import="java.util.List"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es" data-bs-theme="light">
  <head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Perfiles</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  </head>
  <body>
      <!-- Header -->
      <jsp:include page="/WEB-INF/template/header.jsp" />
      <!-- Contenido -->
    <div class="container my-4">
        <div class="row">
            <!-- Panel de filtros a la izquierda -->
            <div class="col-md-3 mb-4 sticky-top" style="height: fit-content;">
                <div class="card">
                    <div class="card-header">
                        <h3>Perfiles</h3>
                    </div>
                    <div class="card-body">
                        <h6>Filtrar por tipo de usuario</h6>
                        <form id="filterForm">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="cliente" id="checkCliente" checked>
                                <label class="form-check-label" for="checkCliente">
                                    Cliente
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="aerolinea" id="checkAerolinea" checked>
                                <label class="form-check-label" for="checkAerolinea">
                                    Aerolinea
                                </label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    
            <!-- Lista de usuarios a la derecha -->
            <div class="col-md-9">
                <ul class="list-group list-group-striped">
                    <!-- Cliente -->
                    		<%! List<DataUsuario> usuarios; %>
                    		<%! int indice=0; %>
                    		<%! String color; %>
                    		<%! String tipoUsuario; %>
                    		<%
                            	usuarios = (List<publicar.DataUsuario>) request.getAttribute("usuarios");
							%>
							<c:forEach items="${usuarios}" var="usuario">
										<%tipoUsuario = "aerolinea"; %>
									<c:if test="${usuario.isEsCliente() == true}">
        								<% 
        								tipoUsuario = "cliente";
        								%>
    								</c:if>
										<%
        								indice++;
        								int pos = indice%2;
        								if (pos == 0){
        									color = "bg-body";
        								}
        								else {
        									color = "bg-body-tertiary";
        								}
        								
        								%>
        							<li class="list-group-item <%= color %> <%= tipoUsuario %>">
                        				<div class="row   align-items-center">                 
   											<!-- Imagen de perfil -->
                            				<div class="col-auto">
                                				<div class="rounded-circle" style="width: 75px; height: 75px; overflow: hidden;">
                                				<c:choose>
                                					<c:when test="${usuario.isEsCliente() == true}">
											            <img src="${pageContext.request.contextPath}/Imagenes?id=${usuario.getImagenC()}" id="imgC" class="img-thumbnail rounded-circle" style="width: 75px; height: 75px; object-fit: cover;">
											        </c:when>
											        <c:otherwise>
											          	<img src="${pageContext.request.contextPath}/Imagenes?id=${usuario.getImagenA()}" id="imgA" class="img-thumbnail rounded-circle" style="width: 75px; height: 75px; object-fit: cover;">
											        </c:otherwise>
                                   				</c:choose>
                                				</div>
                            				</div>
                            				<!-- Nickname y correo -->
                            			<div class="col" style="min-width: 0;">
                            				<c:choose>
                                					<c:when test="${usuario.isEsCliente() == true}">
											            <a id="spiderman-link" style="text-decoration:none" href="${pageContext.request.contextPath}/usuarios/${usuario.getNicknameC()}"><h5 class="mb-0 text-dark-emphasis text-truncate" id="name-link">${usuario.getNombreC()}</h5></a>
                                						<small class="text-secondary-emphasis text-truncate">${usuario.getEmailC()}</small> 
                                					</c:when>
											        <c:otherwise>
											          	<a id="spiderman-link" style="text-decoration:none" href="${pageContext.request.contextPath}/usuarios/${usuario.getNicknameA()}"><h5 class="mb-0 text-dark-emphasis text-truncate" id="name-link">${usuario.getNombreA()}</h5></a>
                                						<small class="text-secondary-emphasis text-truncate">${usuario.getEmailA()}</small> 
                                					</c:otherwise>
                                   				</c:choose>
                                			
                            			</div>
                        				</div>
                   		 			</li>
    						</c:forEach>
                </ul>
            </div>
        </div>
    </div>
    
    <script>
        // Función para aplicar los filtros
        function applyFilters() {
            // Obtener los valores de los checkboxes
            var showClientes = document.getElementById("checkCliente").checked;
            var showAerolineas = document.getElementById("checkAerolinea").checked;

            // Mostrar u ocultar los elementos según el filtro
            var clientes = document.querySelectorAll('.cliente');
            var aerolineas = document.querySelectorAll('.aerolinea');

            clientes.forEach(function(cliente) {
                cliente.style.display = showClientes ? 'block' : 'none';
            });

            aerolineas.forEach(function(aerolinea) {
                aerolinea.style.display = showAerolineas ? 'block' : 'none';
            });
        }

        // Asignar evento change a los checkboxes para actualizar la lista en tiempo real
        document.getElementById('checkCliente').addEventListener('change', applyFilters);
        document.getElementById('checkAerolinea').addEventListener('change', applyFilters);

        // Aplicar los filtros al cargar la página
        applyFilters();
    </script>
    <div id="footer">
	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
	      <div class="container-fluid">
	        <ol class="breadcrumb navbar-text mb-auto">
	          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
	          <li class="breadcrumb-item active" aria-current="page">Usuarios</li>
	        </ol>
	      </div>
	    </nav>
      <jsp:include page="/WEB-INF/template/footer.jsp" />
      </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
