<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
    <head>
	  <jsp:include page="/WEB-INF/template/head.jsp" />
      <title>Home</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
      <script src="${pageContext.request.contextPath}/static/js/carousel.js" type="text/javascript" defer></script>
    </head>
    <body>
      <!-- Header -->
      <jsp:include page="/WEB-INF/template/header.jsp" />
      <!-- Slider -->
        <div id="carouselExampleIndicators" class="carousel slide carousel-fade" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
          </div>
          <button id="close-carousel" type="button" class="btn-close position-absolute top-0 start-0 m-3 z-3" aria-label="Close" onclick="closeCarousel()" style="box-shadow: none;"></button>
          <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="5000">
              <div class="card mb-3 bg-primary-subtle" style="height: fit-content;">
                <div class="row g-0 d-flex align-items-center">
                  <div class="col-md-5 d-flex align-items-center">
                    <div class="card-body" style="padding: 4em;">
                      <h3 class="card-title pb-2" style="font-weight: bold;">Entre tú y tu destino hay un vuelo al mejor precio</h3>
                      <h6 class="card-subtitle mb-2 text-body-secondary">Descubre tu lugar especial</h6>
                      <p class="card-text">Vuela hacia tu próxima aventura! Reserva fácil, tarifas increíbles. ¡Comienza hoy!</p>
                      <a href="${pageContext.request.contextPath}/vuelos" class="card-link">Reserva tu vuelo</a>
                    </div>
                  </div>
                  <div class="col-md-7 text-center">
                    <img src="${pageContext.request.contextPath}/Imagenes?id=luggage-airport-terminal-with-airplane_953425-4648.jpg" class="imagen" alt="...">
                  </div>
                </div>
              </div>
            </div>
            <div class="carousel-item" data-bs-interval="5000">
              <div class="card mb-3 bg-success-subtle" style="height: fit-content;">
                <div class="row g-0 d-flex align-items-center">
                  <div class="col-md-5 d-flex align-items-center">
                    <div class="card-body" style="padding: 4em;">
                      <h3 class="card-title pb-2" style="font-weight: bold;">Viajar es mejor juntos, registrate y descubre nuevas experiencias</h3>
                      <h6 class="card-subtitle mb-2 text-body-secondary">Ofertas que despegan</h6>
                      <p class="card-text">Viaja sin límites, reserva sin preocupaciones, la confianza de elegir bien</p>
                      <a href="${pageContext.request.contextPath}/usuarios/acceso" class="card-link link-success">Iniciar Sesion</a>
                      <a href="${pageContext.request.contextPath}/usuarios/acceso" class="card-link link-success">Registrarse</a>
                    </div>
                  </div>
                  <div class="col-md-7 text-center">
                    <img src="${pageContext.request.contextPath}/Imagenes?id=M9.png" class="imagen" alt="...">
                  </div>
                </div>
              </div>
            </div>
            <div class="carousel-item" data-bs-interval="5000">
              <div class="card mb-3 bg-warning-subtle" style="height: fit-content;">
                <div class="row g-0 d-flex align-items-center">
                  <div class="col-md-5 d-flex align-items-center">
                    <div class="card-body" style="padding: 4em;">
                      <h3 class="card-title pb-2" style="font-weight: bold;">Paquetes ideales para tus viajes</h3>
                      <h6 class="card-subtitle mb-2 text-body-secondary">Ahorra al viajar</h6>
                      <p class="card-text">Viaja más y gasta menos, ¡descubre nuestros increíbles paquetes promocionales!</p>
                      <a href="${pageContext.request.contextPath}/paquetes/" class="card-link link-warning">Paquetes</a>
                    </div>
                  </div>
                  <div class="col-md-7 text-center">
                    <img src="${pageContext.request.contextPath}/Imagenes?id=OIG4.png" class="imagen" alt="...">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="suplir" class="titulo" style="display: none;">
          <p class="fs-1 center text-body-tertiary" style="font-weight: bolder; text-align: center; padding: 2.75em 0px .75em 0px;">Volando.uy</p>
        </div>
      <!-- Contenido -->
      <div class="row row-cols-1 row-cols-md-4 g-10" style="margin-left: 4.5rem; margin-right: 4.5rem; margin-top: 5rem; margin-bottom: 5rem;">
          <div class="col">
              <a href="${pageContext.request.contextPath}/usuarios">
              	<div class="card content">
                	<div class="card-img-top bg-success-subtle elemento">
                    	<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-people-fill" viewBox="0 0 16 16">
                        	<path d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6m-5.784 6A2.24 2.24 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.3 6.3 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5"/>
                    	</svg>
                	</div>
                	<div class="card-body bg-body-tertiary">
                    	<h5 class="card-title" style="text-align: center;">Usuarios</h5>
                	</div>
              	</div>
              </a>
          </div>
          <div class="col">
          	<a href="${pageContext.request.contextPath}/rutas">
            	<div class="card content">
               		<div class="card-img-top bg-danger-subtle elemento">
                  		<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-signpost-2-fill" viewBox="0 0 16 16">
                      		<path d="M7.293.707A1 1 0 0 0 7 1.414V2H2a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1h5v1H2.5a1 1 0 0 0-.8.4L.725 8.7a.5.5 0 0 0 0 .6l.975 1.3a1 1 0 0 0 .8.4H7v5h2v-5h5a1 1 0 0 0 1-1V8a1 1 0 0 0-1-1H9V6h4.5a1 1 0 0 0 .8-.4l.975-1.3a.5.5 0 0 0 0-.6L14.3 2.4a1 1 0 0 0-.8-.4H9v-.586A1 1 0 0 0 7.293.707"/>
                    	</svg>
                	</div>
                	<div class="card-body bg-body-tertiary">
                  		<h5 class="card-title" style="text-align: center;">Rutas</h5>
                	</div>
          		</div>
          	</a>
      	</div>
      	<div class="col">
        	<a href="${pageContext.request.contextPath}/vuelos">
            	<div class="card content">
                	<div class="card-img-top bg-primary-subtle elemento">
                    	<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-airplane-fill" viewBox="0 0 16 16">
                        	<path d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849"/>
                       	</svg>
                   	</div>
                   	<div class="card-body bg-body-tertiary">
                    	<h5 class="card-title" style="text-align: center;">Vuelos</h5>
                   	</div>
               	</div>
          	</a>
       	</div>
       	<div class="col">
        	<a href="${pageContext.request.contextPath}/paquetes/">
        		<div class="card content">
                  	<div class="card-img-top bg-warning-subtle elemento">
                     	<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-box2-fill" viewBox="0 0 16 16">
                         	<path d="M3.75 0a1 1 0 0 0-.8.4L.1 4.2a.5.5 0 0 0-.1.3V15a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1V4.5a.5.5 0 0 0-.1-.3L13.05.4a1 1 0 0 0-.8-.4zM15 4.667V5H1v-.333L1.5 4h6V1h1v3h6z"/>
                       	</svg>
                   	</div>
                 	<div class="card-body bg-body-tertiary">
                     	<h5 class="card-title" style="text-align: center;">Paquetes</h5>
                   	</div>
            	</div>
          	</a>
      	</div>
        </div>
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