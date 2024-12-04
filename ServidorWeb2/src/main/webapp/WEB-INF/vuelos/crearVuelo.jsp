<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="publicar.*" %>
<%@ page import ="java.time.LocalDate, java.time.LocalTime" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/crearVuelo.css">
	<title>Crear Vuelo</title>

	
</head>

<body>

    <!-- Header -->
    <jsp:include page="/WEB-INF/template/header.jsp" />
<div class="container md-5 mt-3" >
    <h2 class="text-start">Crear Vuelo</h2>
    
    
    
    <%
    String rutaValue = "";
    String nombreVuelo = "";
	Integer dia = null; 
	Integer mes = null;
	Integer anio = null; 
	Integer hora = null;
	Integer minuto = null; 
	Integer turista = null; 
	Integer ejecutivo = null; 
	LocalDate fecha = null;
       
    
    DataVuelo vuelo = (DataVuelo) request.getAttribute("vuelo");
    
    
    if (vuelo != null){
        
        rutaValue = (String) request.getAttribute("ruta");
    	nombreVuelo = vuelo.getNombre();
   		fecha = LocalDate.parse(vuelo.getFecha());
    	hora = LocalTime.parse(vuelo.getDuracion()).getHour();
    	minuto = LocalTime.parse(vuelo.getDuracion()).getMinute();
    	turista = vuelo.getAsientosTurista();
    	ejecutivo = vuelo.getAsientosEjecutivo();
    }
        
    %>

     <% 
    Integer resultado = (Integer) request.getAttribute("resultadoCrear");
    if (resultado != null) {
    	if (resultado == 1 || resultado == 2){
    		

%>
    <!-- Mensaje de error más pequeño y centrado -->
    <div class="alert alert-danger" role="alert" style="max-width: 600px; margin: auto; margin-bottom: 1rem;">
        <strong>Se han encontrado errores:</strong>
        <ul>
            <% if (resultado == 1) { %>
                <li>La fecha ingresada no puede ser anterior a la actual</li>
            <% } %>
            <% if (resultado == 2) { %>
                <li>Ya existe un vuelo con el nombre ingresado en el sistema.</li>
            <% } %>
        </ul>
    </div>
    
    			
    			
<% 	}}
    if (resultado != null) {
    
		if(resultado == 0){
	

%>
	
	<div class="alert alert-success" role="alert" style="max-width: 600px; margin: auto; margin-bottom: 1rem;">
        <strong>Vuelo creado con éxito</strong>

    </div>
<%
    	}
	}
%>
<form class="needs-validation" action="${pageContext.request.contextPath}/vuelos/crearvuelo" method="post" onsubmit="return handleFormSubmit.call(this, event);" enctype="multipart/form-data" id="crear-vuelo" novalidate>
    <input type="hidden" name="formId" value="crear-vuelo">
    
    <div class="form-floating mb-3">
        <select class="form-select" id="ruta" name="ruta" aria-label="Ruta de Vuelo" required>
            <option disabled value="" <%= (rutaValue == null || rutaValue.isEmpty()) ? "selected" : "" %>>Seleccionar Ruta</option>
            <% if (rutaValue != null && !rutaValue.isEmpty()) { %>
                <option value="<%=rutaValue %>" selected><%= rutaValue %></option>
            <% } %>
            <% Integer valor = 1; Set<String> rutas = (Set<String>) request.getAttribute("rutas");
            if (rutas != null){
                for (String ruta: rutas) { %>
                    <option value="<%= ruta %>"><%= ruta %></option>
            <% } } %>    
        </select>
        <label for="ruta">Ruta de Vuelo</label>
        <div class="invalid-feedback">Por favor seleccione una ruta de vuelo.</div>
    </div>
    
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="nombre-v" name="nombre-v" value="<%=nombreVuelo %>" placeholder="..." required>
        <small class="form-text text-muted">Este nombre debe ser único en la plataforma</small>
        <label for="nombre-v">Nombre de vuelo</label>
        <div class="invalid-feedback">El nombre ya está en uso.</div>
    </div>
    
    <div class="form-floating mb-3">
        <input type="date" class="form-control" id="nacimiento-c" name="nacimiento-c" placeholder="..." value="<%=fecha %>" required>
        <label for="nacimiento-c">Fecha de Vuelo</label>
        <div class="invalid-feedback">Por favor escribir una fecha.</div>
    </div>
    
    <div class="mb-3">
        <label for="duration" class="form-label">Duración</label>
        <div class="input-group">
            <input type="number" class="form-control" id="horas" name="horas" min="0" max ="23" placeholder="Horas" value="<%=hora %>" required>
            <span class="input-group-text">:</span>
            <input type="number" class="form-control" id="minutos" name="minutos" min="0" max="59" placeholder="Minutos" value="<%=minuto %>" required>
        </div>
        <div class="form-text text-danger"><!-- Por favor, ingresa una duración válida  --></div>
    </div>
    
    <div class="form-floating mb-3">
        <input type="number" class="form-control" id="asientosTurista" name="asientosTurista" min="0" value="<%=turista %>" placeholder="..." required>
        <label for="asientosTurista">Cantidad Máxima de Asientos Turista:</label>
        <div class="invalid-feedback">Por favor, ingresa una cantidad válida.</div>
    </div>
    
    <div class="form-floating mb-3">
        <input type="number" class="form-control" id="asientosEjecutivo" name="asientosEjecutivo" min="0" value="<%=ejecutivo %>" placeholder="..." required>
        <label for="asientosEjecutivo">Cantidad Máxima de Asientos Ejecutivo:</label>
        <div class="invalid-feedback">Por favor, ingresa una cantidad válida.</div>
    </div>
    
    <div class="input-group mb-3">
        <label for="imagen-c" class="input-group-text">Imagen <i><span class="text-muted fw-light">(opcional)</span></i></label>
        <input type="file" class="form-control" id="imagen-c" name="imagen-c" accept=".jpg,.gif,.png">
    </div>
    
    <button type="submit" class="btn btn-primary my-3 w-100">Ingresar Vuelo</button>
</form>

</div>
	
    <div id="footer">
    <nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
        <div class="container-fluid">
            <ol class="breadcrumb navbar-text mb-auto">
                <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/vuelos">Vuelos</a></li>
                <li class="breadcrumb-item active" aria-current="page">Crear</li>
            </ol>
        </div>
    </nav>
      <jsp:include page="/WEB-INF/template/footer.jsp" />
      </div>
    <script src="${pageContext.request.contextPath}/static/js/validar.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script type="text/javascript"><%@include file="/static/js/validar.js"%></script>
</body>
</html>