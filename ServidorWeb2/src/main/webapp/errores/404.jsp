<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
    <head>
	  <jsp:include page="/WEB-INF/template/head.jsp" />
      <title>Error 404</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
    </head>
    <body>
      <!-- Header -->
      <jsp:include page="/WEB-INF/template/header.jsp" />
      <!-- Error Message -->
      
  <div class="my-4">
    <div class="px-0justify-content-center">
      	<svg style="transform: rotate(90deg); transform-origin: center;" class="adelante my-5" xmlns="http://www.w3.org/2000/svg" width="160" height="160" fill="currentColor" class="bi bi-airplane-fill" viewBox="0 0 16 16">
  			<path d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849"/>
		</svg>
		<div class="container text-center">
    		<p class="custom-text mb-0 text-body-tertiary" style="display: none;">404</p>
    		<p class="error_url text-danger-emphasis ps-3" style="display: none;">Solicitud No Encontrada: <%= request.getRequestURI() %></p>
		</div>
    </div>
  </div>
		<style>
  .adelante {
    position: relative;
    animation: moveForward 1s linear forwards;
  }

  @keyframes moveForward {
    0% {
      left: 19vw;
      opacity: 0;
    }
    10% {
      left: 20vw;
      opacity: 1;
    }
    90% {
      left: 79vw; /* Moverá el avión hacia adelante fuera de la pantalla */
      opacity: 1;
    }
    100% {
      left: 80vw;
      opacity: 0;
      display: none;
    }
  }

  .custom-text {
    opacity: 0; /* Comienza invisible */
    transition: opacity 1s ease-in; /* Transición de opacidad */
    font-size: 10em;
    font-weight: bolder;
  }

  .error_url {
    opacity: 0; /* Comienza invisible */
    transition: opacity 1s ease-in; /* Transición de opacidad */
  }

  .show-text {
    opacity: 1; /* Hacerlo completamente visible */
  }
</style>

<script>
  // Asegúrate de que el DOM esté completamente cargado antes de agregar el listener
  document.addEventListener("DOMContentLoaded", function() {
    const airplane = document.querySelector('.adelante');
    const textElement = document.querySelector('.custom-text');
    const urlElement = document.querySelector('.error_url');

    // Al finalizar la animación del avión
    airplane.addEventListener('animationend', function() {
      textElement.classList.add('show-text'); // Agregar clase para mostrar el texto
      urlElement.classList.add('show-text'); // Agregar clase para mostrar el texto
      textElement.style.display = 'block'; // Asegurarte de que el texto se muestre
      urlElement.style.display = 'block'; // Asegurarte de que el texto se muestre
    });
  });
</script>
      <!-- Footer -->
      <div id="footer">
      	<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
        	<div class="container-fluid">
          	<ol class="breadcrumb navbar-text mb-auto">
            	<li class="breadcrumb-item active" aria-current="page">Home</li>
          	</ol>
        	</div>
      	</nav>	
      	<jsp:include page="/WEB-INF/template/footer.jsp" />
      </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>