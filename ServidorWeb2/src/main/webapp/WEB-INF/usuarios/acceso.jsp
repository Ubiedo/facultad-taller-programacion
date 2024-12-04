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
    <div class="card my-5 bg-success-subtle" style="height: fit-content; max-width: 70%; margin-left: 15vw;">
      <div class="row g-0">
        <div class="col-md-5 mt-3">
            <div class="card-body sticky-top">
                <p class="fs-1 center" style="font-weight: bolder; text-align: center;">Volando.uy</p>
                <div style="width: 100%; text-align: center;">
                    <p>Para ir al inicio haz click <a class="link-success" href="${pageContext.request.contextPath}/home">aqui</a></p>
                </div>
            </div>
        </div>
        <div class="col-md-7" id="formularios">
            <div class="card-body bg-body-tertiary">
                <div class="text-center mt-3 mb-3">
                    <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                    </svg>
                </div>
                <div class="option mb-3">
                    <button type="button" class="btn bg-transparent options" id="show-log">Iniciar Sesion</button>
                    <!-- <button type="button" class="btn bg-transparent options" id="show-sign">Resgistrarse</button> -->
                    <div class="dropdown options-drop">
                        <button class="btn bg-transparent dropdown-toggle largo-100" type="button" data-bs-toggle="dropdown" aria-expanded="false" id="show-sign">
                          Registrarse
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end">
                          <li><a class="dropdown-item" id="show-r-cliente">Cliente</a></li>
                          <li><a class="dropdown-item" id="show-r-aerolinea">Aerolinea</a></li>
                        </ul>
                      </div>
                </div>
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
                <!-- Sing In Cliente -->
                <form class="needs-validation" method="post" enctype="multipart/form-data" id="r-cliente" style="display: none;" novalidate>
                  <input type="text" value="cliente" name="formId" hidden="true">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nickname-c" name="nickname" placeholder="..." required>
                    <label for="nickname-c">Nickname</label>
                    <div class="invalid-feedback">
                      Porfavor escribir un nickname.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password-c" name="password" placeholder="..." required>
                    <label for="password">Contraseña</label>
                    <div class="invalid-feedback">
                      Porfavor escribir una contraseña.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password-confirm-c" name="confirmation" placeholder="..." required>
                    <label for="password-confirm">Confirmar Contraseña</label>
                    <div class="invalid-feedback">
                      Porfavor escribir la misma contraseña.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="date" class="form-control" id="nacimiento-c" name="nacimiento" placeholder="..." required>
                    <label for="nacimiento-c">Fecha de Nacimiento</label>
                    <div class="invalid-feedback">
                      Porfavor escribir una fecha.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nombre-c" name="nombre" placeholder="..." required>
                    <label for="nombre-c">Nombre</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su nombre.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="apellido-c" name="apellido" placeholder="..." required>
                    <label for="apellido-c">Apellido</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su apellido.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="mail-c" name="mail" placeholder="..." required>
                    <label for="mail-c">Mail</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su mail.
                    </div>
                  </div>
                  <div class="input-group mb-3">
                    <label for="imagen-c" class="input-group-text">Imagen</label>
                    <input type="file" class="form-control" id="imagen-c" name="imagen" accept=".jpg,.gif,.png">
                  </div>
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nacionalidad-c" name="nacionalidad" placeholder="..." required>
                    <label for="nacionalidad-c">Nacionalidad</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su nacionalidad.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <select class="form-select" id="t-documento-c" name="tipo_documento" aria-label="Tipo de Documento" required>
                      <option disabled value="" selected>Seleccionar Documento</option>
                      <option value="pasaporte">Pasaporte</option>
                      <option value="CI">Cedula</option>
                    </select>
                    <label for="t-documento-c">Tipo de Documento</label>
                    <div class="invalid-feedback">
                      Porfavor eliga un tipo de documento.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="n-documento-c" name="numero_documento" placeholder="..." required>
                    <label for="n-documento-c">Numero de Documento</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su numero de documento.
                    </div>
                  </div>
                  <button type="submit" class="btn btn-success largo-100 my-3">Registrar Cliente</button>
                </form>
                <!-- Sign In Aerolinea -->
                <form class="needs-validation" method="post" enctype="multipart/form-data" id="r-aerolinea" style="display: none;" novalidate>
                  <input type="text" value="aerolinea" name="formId" hidden="true">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nickname-a" name="nickname" placeholder="..." required>
                    <label for="nickname-a">Nickname</label>
                    <div class="invalid-feedback">
                      Porfavor escribir un nickname.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password-a" name="password" placeholder="..." required>
                    <label for="password">Contraseña</label>
                    <div class="invalid-feedback">
                      Porfavor escribir una contraseña.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password-confirm-a" name="confirmation" placeholder="..." required>
                    <label for="password-confirm">Confirmar Contraseña</label>
                    <div class="invalid-feedback">
                      Porfavor escribir la misma contraseña.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nombre-a" name="nombre" placeholder="..." required>
                    <label for="nombre-a">Nombre</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su nombre.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="mail-a" name="mail" placeholder="..." required>
                    <label for="mail-a">Mail</label>
                    <div class="invalid-feedback">
                      Porfavor escribir su mail.
                    </div>
                  </div>
                  <div class="input-group mb-3">
                    <label for="imagen-a" class="input-group-text">Imagen</label>
                    <input type="file" class="form-control" id="imagen-a" name="imagen" accept=".jpg,.gif,.png">
                  </div>
                  <div class="form-floating mb-3">
                    <textarea class="form-control" placeholder="Leave a comment here" id="descripcion-a" name="descripcion" required></textarea>
                    <label for="descripcion-a">Descripcion General</label>
                    <div class="invalid-feedback">
                      Porfavor escribir una descripcion general.
                    </div>
                  </div>
                  <div class="form-floating mb-3">
                    <input type="url" class="form-control" id="web-a" name="web" placeholder="...">
                    <label for="web-a">Sitio Web</label>
                  </div>
                  <button type="submit" class="btn btn-success largo-100 my-3">Registrar Aerolinea</button>
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
            <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/usuarios">Usuarios</a></li>
            <li class="breadcrumb-item active" aria-current="page">Acceso</li>
          </ol>
        </div>
      </nav>
      <jsp:include page="/WEB-INF/template/footer.jsp" />
      </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<% if(((String) request.getAttribute("show-form")) != null) { %>
	<!-- Mostrar el intento reciente de form fallado -->
		<% String form = (String) request.getAttribute("show-form"); %>
		<% if (form.equals("log")) { %>
			<script>
				document.getElementById("id-user").value = "<%= (String) request.getAttribute("id") %>";
				document.getElementById("password-user").value = "<%= (String) request.getAttribute("password") %>";
			</script>
		<% } else if (form.equals("cliente")) {%>
			<script>
				// mostrar el form adecuado
		    	document.getElementById('show-log').classList.remove('active');
		    	document.getElementById('l-usuario').style.display = 'none';
		    	document.getElementById('show-sign').classList.add('active');
		    	document.getElementById('r-aerolinea').style.display = 'none';
		    	document.getElementById('r-cliente').style.display = 'block';
				// mostrar los valores ingresados
				document.getElementById("nickname-c").value = "<%= (String) request.getAttribute("nickname") %>";
				document.getElementById("password-c").value = "<%= (String) request.getAttribute("password") %>";
				document.getElementById("password-confirm-c").value = "<%= (String) request.getAttribute("password") %>";
				document.getElementById("nombre-c").value = "<%= (String) request.getAttribute("nombre") %>";
				document.getElementById("apellido-c").value = "<%= (String) request.getAttribute("apellido") %>";
				document.getElementById("mail-c").value = "<%= (String) request.getAttribute("mail") %>";
				document.getElementById("nacionalidad-c").value = "<%= (String) request.getAttribute("nacionalidad") %>";
				document.getElementById("n-documento-c").value = "<%= (String) request.getAttribute("n-documento") %>";
			</script>
		<% } else { %>
			<script>
				// mostrar el form adecuado
				document.getElementById('show-log').classList.remove('active');
	    		document.getElementById('l-usuario').style.display = 'none';
		    	document.getElementById('show-sign').classList.add('active');
		    	document.getElementById('r-cliente').style.display = 'none';
		    	document.getElementById('r-aerolinea').style.display = 'block';
				// mostrar los valores ingresados
				document.getElementById("nickname-a").value = "<%= (String) request.getAttribute("nickname") %>";
				document.getElementById("password-a").value = "<%= (String) request.getAttribute("password") %>";
				document.getElementById("password-confirm-a").value = "<%= (String) request.getAttribute("password") %>";
				document.getElementById("nombre-a").value = "<%= (String) request.getAttribute("nombre") %>";
				document.getElementById("mail-a").value = "<%= (String) request.getAttribute("mail") %>";
				document.getElementById("descripcion-a").value = "<%= (String) request.getAttribute("descripcion") %>";
				document.getElementById("web-a").value = "<%= (String) request.getAttribute("web") %>";
			</script>
		<% } %>
	<% } %>
</body>
</html>