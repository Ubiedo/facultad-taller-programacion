<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set, java.time.LocalDate, java.util.List, java.time.LocalTime" %>
<%@ page import="publicar.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/crearRuta.css">
	<title>Crear Ruta</title>

</head>

<body>

    <!-- Header -->
    <jsp:include page="/WEB-INF/template/header.jsp" />
    
    <%
    String nombreRuta = "";
    String descripcion = "";
    String hora = null;
    Float turistaBase = null;
    Float ejecutivoBase = null;
    Float equiExtra = null;
    String video="";
    String descCorta = "";
    String origen = "";
    String destino = "";
    List<String> categoriasSelec = null;
    
    
    DataRuta rutaV = (DataRuta) request.getAttribute("rutaV");
    
    if (rutaV != null){
    	
    	nombreRuta = rutaV.getNombre();
    	descripcion = rutaV.getDescripcion();
    	hora = rutaV.getHora();
    	turistaBase = rutaV.getTuristaBase();
    	ejecutivoBase = rutaV.getEjecutivoBase();
    	equiExtra = rutaV.getUnidadEquipajeExtra();
    	descCorta = rutaV.getDescCorta();
    	origen = rutaV.getOrigen();
    	destino = rutaV.getDestino();
    	categoriasSelec = rutaV.getCategorias();
    	video = rutaV.getVideo();
    }
    
    
    %>
<div class="container md-5 mt-3" >
    <h2 class="text-start">Crear Ruta</h2><br/>
    
    <%    
        Integer resultado = (Integer) request.getAttribute("resultado");
        if (resultado != null) {
            if (resultado == 0) {
             %>
             <div class="alert alert-success" role="alert" style="max-width: 600px; margin: auto; margin-bottom: 1rem;">
       			 <strong>La ruta de vuelo se ha creado exitosamente</strong>
    			</div>
             
              <% 
            } else {
          %>
		<div class="alert alert-danger" role="alert" style="max-width: 600px; margin: auto; margin-bottom: 1rem;">
        <strong>Error al crear la ruta de vuelo:</strong>
        <ul> 
       			 <li> Ya existe una con el mismo nombre en el sistema.</li>
       	</ul>
        </div>
        
         <%  
            }
        }
    %>
    
                <form class="needs-validation" action="${pageContext.request.contextPath}/rutas/creada" method="post" onsubmit="return handleFormSubmit.call(this, event);" id="crear-ruta" novalidate enctype="multipart/form-data">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="identificador" id="nombre-ruta" value = "<%= nombreRuta %>" placeholder="..." required>
                    <label for="id-user">Nombre de Ruta</label>
                    <div class="invalid-feedback">
                      Porfavor escriba un nombre de ruta.
                    </div>
                  </div>
                  <div class="row">
                  	<div class="col-md-5">
		                  <div class="form-floating mb-3">
		                    <select class="form-select" name="ciudadOrigen" id="ciudad-origen" aria-label="Ciudad de Origen" required>
				                <option disabled value="" selected>Seleccionar Ciudad de Orígen</option>
				                <%
				                if(rutaV != null){
				                	%>
				                	<option value="<%= rutaV.getOrigen() %>" selected><%= origen %></option>
				                	
				               <% 
				                }

				                Integer valor = 1;
				                List<String> ciudades = (List<String>) request.getAttribute("ciudades");
				                if (ciudades != null){
				                	for (String ciudad: ciudades){
				                %>
				                <option value="<%= ciudad %>"><%= ciudad %></option>
				                <%
				                	valor = valor + 1;
				                	}
				                }
				                %>	
				                 
				            </select>
				            <label for="ciudad-origen">Ciuadad de Orígen</label>
				            <div class="invalid-feedback">
				            	Por favor seleccione una ciudad de orígen.
				            </div>
		                  </div>
		              </div>
	                  <div class="col-md-5">
		                  <div class="form-floating mb-3">
		                    <select class="form-select" name="ciudadDestino" id="ciudad-destino" aria-label="Ciudad de Destino" required>
				                <option disabled value="" selected>Seleccionar Ciudad de Destino</option>
				                
				                <%
				                if(rutaV != null){
				                	%>
				                	<option value="<%= rutaV.getDestino() %>" selected><%= destino %></option>
				                	
				               <% 
				                }

				                valor = 1;
				                if (ciudades != null){
				                	for (String ciudad: ciudades){
				                %>
				                <option value="<%= ciudad %>"><%= ciudad %></option>
				                <%
				                	valor = valor + 1;
				                	}
				                }
				                %>	
				                 
				            </select>
				            <label for="ciudad-destino">Ciuadad de Destino</label>
				            <div class="invalid-feedback">
				            	Por favor seleccione una ciudad de destino.
				            </div>
				           </div>
			           </div>
			           <div class="col-md-2">
			           		<div class="form-floating mb-3">
					    		<input type="time" class="form-control" name="hora" value="<%= hora %>" id="hora-r" placeholder="..." required>
					    		<label for="hora-r">Hora</label>
				    		<div class="invalid-feedback">Por favor, ingresa una hora válida.</div>
					</div> 
			           </div>
		           </div><br/>
		           
		           <div class="row">
		           	<div class="col-md-7">
			            <div class="form-floating mb-3 text-wrap">
					        <textarea type="text" style="height: fit-content;" class="form-control" name="descripcion" id="descripcion-r"  placeholder="..." required><%=descripcion %></textarea>
					        <label for="descripcion-r">Descripción</label>
					        <div class="invalid-feedback">
		                      Porfavor escriba una descripción.
		                    </div>
				        </div>
			        </div>

					<div class="col-md-5">
				        <div class="form-floating mb-3">
					        <textarea type="text" style="height: fit-content;" class="form-control" name="descCorta"  id="descCorta-r" placeholder="..." required><%=descCorta %></textarea>
					        <label for="descCorta-r">Descripción Corta</label>
					        <div class="invalid-feedback">
		                      Porfavor escriba una descripción corta.
		                    </div>
				        </div>
			        </div>
			       </div>
							
					<div class="row">
                  		<div class="col-md-4">    
					        <div class="form-floating mb-3">
					        	<input type="number" step="0.01" min="0" class="form-control" name="precioTurista" value="<%= turistaBase %>" id="precio-turista" placeholder="..."required>
					        	<label for="precio-turista">Precio Turista</label>
					            <div class="invalid-feedback">Por favor, ingresa una cantidad válida.</div>
					        </div>
				        </div>
				        <div class="col-md-4"> 
							<div class="form-floating mb-3">
					        	<input type="number" step="0.01" min="0" class="form-control" name="precioEjecutivo" id="precio-ejecutivo" value="<%= ejecutivoBase %>" placeholder="..."required>
					        	<label for="precio-ejecutivo">Precio Ejecutivo</label>
					            <div class="invalid-feedback">Por favor, ingresa una cantidad válida.</div>
					        </div>
				        </div>
				        <div class="col-md-4"> 
					        <div class="form-floating mb-3">
					        	<input type="number" step="0.01" min="0" class="form-control" name="precioEquipaje" id="precio-equipaje" value="<%=equiExtra %>" placeholder="..."required>
					        	<label for="precio-equipaje">Precio Equipaje</label>
					            <div class="invalid-feedback">Por favor, ingresa una cantidad válida.</div>
					        </div>
				        </div>
						<small class="form-text text-muted">Los precios se muestran en pesos uruguayos (UYU)</small>
			        </div><br/>
			        <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="video" id="video" value = "<%= video %>" placeholder="...">
                    <label for="id-user">Video de Ruta</label>
                    <div class="invalid-feedback">
                      Porfavor escriba una direccion para el video.
                    </div>
                  </div>
			        <div class="mb-3">
					    <div id="categorias-r">
					    <div>
					     <label for="categorias-r">Categorías (opcional)</label>
					    </div>
					        <% 
					        List<String> categorias = (List<String>) request.getAttribute("categorias");
					        if (categorias != null){
					            for (String categoria: categorias){
					        %>
					        <div class="form-check">
					            <input class="form-check-input" type="checkbox" name="categorias" value="<%= categoria %>" id="categoria_<%= categoria %>">
					            <label class="form-check-label" for="categoria_<%= categoria %>"><%= categoria %></label>
					        </div>
					        <%
					            }
					        }
					        %>
					    </div>
					</div>
			        <div class="input-group mb-3">
			                    <label for="imagen-c" class="input-group-text">Imagen <i><span class="text-muted fw-light">(opcional)</span></i></label>
			                    <input type="file" class="form-control" id="imagen-c" name="imagen-c" accept=".jpg,.gif,.png">
			        </div>
                    <button type="submit" class="btn btn-success largo-100 my-3" id="crear-ruta">Crear Ruta de Vuelo</button>
                <script type="text/javascript">
	                function handleFormSubmit(event) {
	                    'use strict';
	                    if (!this.checkValidity()) {
	                        event.preventDefault();
	                        event.stopPropagation();
	                    } else {
	                        return true; // Permitir el envío del formulario si es válido
	                    }
	                    this.classList.add('was-validated');
	                    return false; // Evitar el envío si hay errores
	                }
                </script>
                </form>
</div><br/>
	
    <div id="footer">
<nav class="navbar mb-0 bg-body-secondary" aria-label="breadcrumb" style="padding-left: 1em; padding-right: .5em;">
      <div class="container-fluid">
        <ol class="breadcrumb navbar-text mb-auto">
          <li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li class="breadcrumb-item nav-link" aria-current="page"><a href="${pageContext.request.contextPath}/rutas">Rutas</a></li>
          <li class="breadcrumb-item active" aria-current="page" id="nombre-breadcrumb">Crear</li>
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