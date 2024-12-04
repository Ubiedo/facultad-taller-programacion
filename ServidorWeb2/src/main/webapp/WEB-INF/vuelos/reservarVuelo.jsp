<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="publicar.DataRutaAsiento" %>
<%@ page import="publicar.DataPasaje" %>
<%@ page import="publicar.Asiento" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reservarVuelo.css">
	<title>Reservar Vuelo</title>

</head>

<body>

    <!-- Header -->
    <jsp:include page="/WEB-INF/template/header.jsp" />

<div class="container md-5 mt-3" >
    <%! String cliente; %>
    <%
   		cliente = (String) request.getAttribute("cliente");
	%>
    <%! String vuelo; %>
    <%
   		vuelo = (String) request.getAttribute("vuelo");
	%>
	    <%! String nombreCliente; %>
    <%
   		nombreCliente = (String) request.getAttribute("nombreCliente");
	%>
	    <%! String apellidoCliente; %>
    <%
   		apellidoCliente = (String) request.getAttribute("apellidoCliente");
	%>
	<script>
			const pasajes = [];
	</script>
    <h2 class="text-start">Reservar Vuelo: <%=vuelo%></h2>
     <form class="needs-validation" action="${pageContext.request.contextPath}/vuelos/reservada" method="post" onsubmit="return handleFormSubmit.call(this, event);" novalidate id="reservar-vuelo">
        <input type="hidden" name="formId" value="reservar-vuelo">
        <input type="hidden" id="arrayPasajes" name="pasajes" value="">
        <input type="text" name="cliente" value="<%=cliente%>" style="display:none;">
        <input type="text" name="vuelo" value="<%=vuelo%>" style="display:none;">
           
         <table id="table" class="table table-striped caption-top">
	         <caption>Pasajes Incluídos</caption>
	          	<thead>
			  <tr>
			    <th scope="col">#</th>
			    <th scope="col">Nombre</th>
			    <th scope="col">Apellido</th>
			  </tr>
			</thead>
			<tbody>
			  <tr>
			  	<td>1</td>
			    <td><%=nombreCliente%></td>
			    <td><%=apellidoCliente%></td>
			</tbody>
         </table>
         
		<div class="row align-items-center">
		  <div class="col-md-4">
		    <div class="form-floating mb-2 mt-3">
              <input type="text" class="form-control" id="nombre" placeholder="Nombre Pasaje" name="nombre">
              <label for="nombre">Nombre</label>
            </div>
		  </div>
		  <div class="col-md-4">
		    <div class="form-floating mb-2 mt-3">
              <input type="text" class="form-control" id="apellido" placeholder="Apellido Pasaje" name="apellido">
              <label for="apellido">Apellido</label>
            </div>
		  </div>
		  <div class="col-md-4">
    		<button type="button" onclick="agregarPasaje();" class="btn btn-primary" id="agregar-pasaje">Agregar Pasaje</button>
    		<button type="button" onclick="borrarPasajes();" class="btn btn-danger" id="limpiar">Limpiar</button>
    	  </div>
		</div><br/>
        
        <script>
            
            var table = document.getElementById("table");
            
            function agregarPasaje() {
	            var nombre = document.getElementById("nombre").value,
	                apellido = document.getElementById("apellido").value;
	            
	            if ((nombre == "") || (apellido == ""))
	            	return;
	            pasajes.push(nombre);
	            pasajes.push(apellido);
	            document.getElementById('arrayPasajes').value = pasajes.join(',')
	            var newRow = table.insertRow(table.length),
	                cell1 = newRow.insertCell(0),
	                cell2 = newRow.insertCell(1),
	                cell3 = newRow.insertCell(2),
	                rowNumber = table.rows.length - 1;
	        
	            cell1.innerHTML = rowNumber;
	            cell2.innerHTML = nombre;
	            cell3.innerHTML = apellido;
	            
	            document.getElementById("nombre").value = "";
	            document.getElementById("apellido").value = "";
            }
            
            function borrarPasajes() {
            	while (table.rows.length > 2) {
					table.deleteRow(table.rows.length -1);
				}
            }
            
            /*function getTableData() {
				var rowCount = table.row.length;
				var tableData = [];
				
				for (var i = 2; i < rowCount; i++) {
					var row = table.rows[i]
				}
            }*/
            
        </script>
        
        <div class="row">
		  <div class="col-md-6">
	        <div class="form-floating mb-3">
	            <select class="form-select" id="tipoAsiento" name = "tipoAsiento" aria-label="Tipo de Asiento" required>
	                <option disabled value="" selected>Asiento</option>
	                <option value="1">Turista</option>
	                <option value="2">Ejecutivo</option>
	            </select>
	            <label for="tipoAsiento">Tipo de Asiento</label>
	            <div class="invalid-feedback">
	            	Por favor seleccione un tipo de asiento
	            </div>
	        </div>
	      </div>
	      <div class="col-md-6">
	        <div class="form-floating mb-3">
	        	<input type="number" min="0" class="form-control" id="equipajeExtra" name="equipajeExtra" placeholder="..."required>
	        	<label for="equipajeExtra">Cantidad de Equipaje Extra</label>
	            <div class="invalid-feedback">Por favor, ingresa una cantidad válida.</div>
	        </div>
	       </div>
	      </div>
        
        <%
        	request.setAttribute("cliente", cliente);
        	request.setAttribute("vuelo", vuelo);
		%>
		
		<div class="form-floating mb-6">
            <select class="form-select" id="formaPago" name = "formaPago" aria-label="Forma de Pago" required>
                <option disabled value="" selected>Seleccione una forma de pago...</option>
                <option value="0">Sin Paquete de Rutas</option>
                
                <% 
                List<DataRutaAsiento> rutasPaquetesCliente = (List<DataRutaAsiento>) request.getAttribute("paquetes");

                Integer cantidad = 0;
                String asiento = "";
                if (rutasPaquetesCliente != null){
                	Integer codigoPaquete = 1;
                	for (DataRutaAsiento paquete: rutasPaquetesCliente){
                		cantidad = paquete.getCantidad();
                		if (paquete.getTipoAsiento() == Asiento.TURISTA)
                			asiento = "Turista";
                		else
                			asiento = "Ejecutivo";
                %>
                <option value="<%= codigoPaquete %>">Paquete <%= paquete.getNombre() %>: <%= cantidad %> canjeable(s) de tipo <%= asiento %></option>
                <%
                		codigoPaquete++;
                	}
                }
                %>
            </select>
            <label for="formaPago">Forma de Pago</label>
            <div class="invalid-feedback">
            	Por favor seleccione una forma de pago
            </div>
            <small class="form-text text-muted">Descubre las mejores oportunidades <a href="${pageContext.request.contextPath}/paquetes/">comprando un paquete de rutas</a></small>
        </div><br/>
        
        <div class="row align-items-center">
	        <div class="col-3">
	        	<button type="submit" class="btn btn-success px-4" id="reservar-vuelo">Reservar Vuelo</button>
	        </div>
	        <div class="col-9">
			     <% 
			        Integer resultado = (Integer) request.getAttribute("resultado");
			        if (resultado != null) {
			            if (resultado == 0) {
							out.println("<div style='color: green;'>¡Vuelo reservado con éxito!</div>");
			              } else {
			            	if (resultado == 1) {
			                	out.println("<div style='color: red;'>El vuelo ya fue reservado por el cliente previamente.</div>");
			            	} else {
			            		if (resultado == 2) {
			            			out.println("<div style='color: red;'>No hay suficientes asientos disponibles para la cantidad de pasajes socilitados.</div>");
			            		} else {
			            			if (resultado == 3) {
			            				out.println("<div style='color: red;'>Forma de pago inválida.</div>");
			            			} else {
			            				out.println("<div style='color: red;'>Error inesperado al reservar el vuelo.</div>");
			            			}
			            		}
			            	}
			            }
			        }
			    %>
		    </div>
	    </div>

            <script type="text/javascript">
             function handleFormSubmit(event) {
                 'use strict';
                 const formaPago = document.getElementById("formaPago").value;
                 console.log(formaPago);
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
                <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/vuelos">Vuelos</a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="${pageContext.request.contextPath}/vuelos/<%=vuelo %>"><%=vuelo %></a></li>
                <li class="breadcrumb-item active" aria-current="page">Reservar</li>
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