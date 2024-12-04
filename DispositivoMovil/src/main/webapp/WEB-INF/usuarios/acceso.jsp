<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
	<jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Acceso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/acceso.css">
      <script src="${pageContext.request.contextPath}/static/js/acceso.js" type="text/javascript" defer></script>
</head>
<body>
	<% if(((String) request.getAttribute("error-form-usuarios")) != null) { %>
	<!-- Alerta de error -->
	<div class="modal fade" id="error" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="exampleModalLabel">Alerta de error!</h1>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <%= (String) request.getAttribute("error-form-usuarios") %>
	      </div>
	    </div>
	  </div>
	</div>
	<script>
	  // Usamos el evento 'DOMContentLoaded' para asegurarnos que todo el DOM esta cargado antes de mostrar el modal
	  document.addEventListener('DOMContentLoaded', function() {
	    var myModal = new bootstrap.Modal(document.getElementById('error'));
	    myModal.show(); // Esto mostrara el modal automaticamente
	  });
	</script>
	<% } %>
    <!-- Contenido -->
    <div class="card my-3 bg-success-subtle" style="height: fit-content; max-width: 90%; margin-left: 5vw;">
      <div class="row g-0">
        <div class="col-md-5 mt-3">
            <div class="card-body sticky-top">
                <div class="text-center mt-3 mb-3">
                    <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                    </svg>
                </div>
                <p class="fs-1 center" style="font-weight: bolder; text-align: center;">Iniciar Sesion</p>
            </div>
        </div>
        <div class="col-md-7" id="formularios">
            <div class="card-body bg-body-tertiary">
                <!-- Log In -->
                <form class="needs-validation" method="post" id="l-usuario" novalidate>
                  <input type="text" value="log" name="formId" hidden="true">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="identificador" id="id-user" placeholder="..." required>
                    <label for="id-user">Usuario</label>
                    <div class="invalid-feedback">
                      Porfavor escriba su nickname o mail.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control" name="password" id="password-user" placeholder="..." required>
                    <label for="password-user">Contraseña</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su contraseña.
                    </div>
                  </div>
                    <button type="submit" class="btn btn-success largo-100 my-3" id="log-in">Iniciar Sesion</button>
                </form>
            </div>
        </div>
      </div>
    </div>
    <!-- Footer -->
    <div id="footer">
    <nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
        <div class="container-fluid">
          <ol class="breadcrumb navbar-text mb-auto">
            <li class="breadcrumb-item active" aria-current="page">Acceso</li>
          </ol>
        </div>
      </nav>
      <jsp:include page="/WEB-INF/template/footer.jsp" />
      </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<% if(((String) request.getAttribute("error-form-usuarios")) != null) { %>
		<!-- Mostrar el intento reciente de form fallado -->
		<script>
			document.getElementById("id-user").value = "<%= (String) request.getAttribute("id") %>";
			document.getElementById("password-user").value = "<%= (String) request.getAttribute("password") %>";
		</script>
	<% } %>
</body>
</html>