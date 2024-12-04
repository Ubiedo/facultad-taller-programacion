<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="publicar.DataUsuario"%>
<%@page import="publicar.DataCliente"%>
<%@page import="publicar.DataClienteVuelo"%>
<!DOCTYPE html>
<html lang="es" data-bs-theme="light">
<head>
	<style><%@include file="/static/css/perfil.css"%></style>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	<jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Consultar Reservas</title>
</head>
<body>
<!-- Header -->
<jsp:include page="/WEB-INF/template/header.jsp" />
<%
        		//datos usuario
            	DataCliente usuario = (DataCliente) request.getAttribute("perfil");
%>
<script type="text/javascript">
        function cargarDatos(tipo, aerolinea, ruta, vuelo){
        	// Hacer la solicitud AJAX al servlet
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                if (xhttp.readyState === XMLHttpRequest.DONE && xhttp.status === 200) {
                    // Actualizar el contenido de la reserva con la respuesta del servlet
                    if (tipo == 0){
                    	document.getElementById("aerolineas").innerHTML = this.responseText;
                    	document.getElementById("vuelos").innerHTML = "";
                    	document.getElementById("reserva").innerHTML = "";
                    	document.getElementById("rutas").innerHTML = "";
                    }
                    else if (tipo == 1){
                    	document.getElementById("rutas").innerHTML = this.responseText;
                    	document.getElementById("vuelos").innerHTML = "";
                    	document.getElementById("reserva").innerHTML = "";
                    }
                    else if (tipo == 2){
                    	document.getElementById("vuelos").innerHTML = this.responseText;
                    	document.getElementById("reserva").innerHTML = "";
                    }
                    else{
                    	document.getElementById("reserva").innerHTML = this.responseText;
                    }
                    
                }
            };
            xhttp.open("GET", "${pageContext.request.contextPath}/usuarios/consultarReservas?tipo=" + encodeURIComponent(tipo)
            		 + "&aerolinea=" + encodeURIComponent(aerolinea)
            		  + "&ruta=" + encodeURIComponent(ruta)
            		   + "&vuelo=" + encodeURIComponent(vuelo), true);
            xhttp.send();
        }
        cargarDatos(0,"","","");
	</script>
<div class="form-floating mb-3 mt-3 mx-3">
	<select class="form-select" id="aerolineas" name="aerolineas" onchange="cargarDatos(1,this.value,'','')" required></select>
  <label for="aerolineas" class="form-label">Seleccionar una Aerolinea:</label>
</div>
<div class="form-floating mb-3 mt-3 mx-3">
	<select class="form-select" id="rutas" name="rutas" onchange="cargarDatos(2,'',this.value,'')" required></select>
  <label for="rutas" class="form-label">Seleccionar una Ruta:</label>
</div>
<div class="form-floating mb-3 mt-3 mx-3">
	<select class="form-select" id="vuelos" name="vuelos" onchange="cargarDatos(3,'','',this.value)" required></select>
  <label for="vuelos" class="form-label">Seleccionar un Vuelo:</label>
</div>
              <!--DENTRO DE ACA SE GENERA EL CONTENIDO PARA LAS RESERVAS -->
 <div class="w3-container mx-3" id="reserva"></div>	

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="myModalLabel">Check-in exitoso</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        Check-in realizado con exito!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="confirmCheckIn">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<script>
function openModalAndRedirect(event) {
    event.preventDefault(); 
    const href = event.currentTarget.getAttribute('href'); 


    const modal = new bootstrap.Modal(document.getElementById('myModal'));
    modal.show();


    document.getElementById('confirmCheckIn').onclick = function() {
    	window.location.href = href;
    };
}
</script>
<div id="footer">
<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
      <div class="container-fluid">
        <ol class="breadcrumb navbar-text mb-auto">
          <li class="breadcrumb-item active" aria-current="page" id="nombre-breadcrumb">Consultar Reservas</li>
        </ol>
      </div>
    </nav>
    <jsp:include page="/WEB-INF/template/footer.jsp" />
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>