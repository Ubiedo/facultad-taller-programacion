const rutas = {
    "AR1380": {
        nombre: "AR1380",
        aerolinea: "Aerolíneas Argentinas",
        descripcion: "Tiempo de vuelo 1 hora, driecto y sin escalas",
        hora: "07:55",
        costoTurista: "120",
        costoEjecutivo:"340",
        costoEquipaje: "30",
        ciudadOrigen: "Buenos Aires",
        ciudadDestino: "Montevideo",
        fechaAlta: "09/08/2024",
        categorias: "América, Temporada"
    },
    "AR1381": {
        nombre: "AR1381",
        aerolinea: "Aerolíneas Argentinas",
        descripcion: "Tiempo estimado de vuelo 55 minutos",
        hora: "09:35",
        costoTurista: "160",
        costoEjecutivo:"400",
        costoEquipaje: "30",
        ciudadOrigen: "Montevideo",
        ciudadDestino: "Buenos Aires",
        fechaAlta: "09/08/2024",
        categorias: "Cortos, América"
    }
};

// Obtener el parámetro de la URL
const urlParams = new URLSearchParams(window.location.search);
const rutaId = urlParams.get('ruta');

// Seleccionar los elementos donde mostrar la información de la ruta
const rutaInfoDiv = document.getElementById('ruta-info');
const rutaTitulo = document.getElementById('ruta-titulo');
const imagen = document.getElementById('imagen');
const user = getCookie("user"); 
// Verificar si la ruta existe
if (rutas[rutaId]) {
    const ruta = rutas[rutaId];
	if (rutaId == "AR1380"){
		imagen.innerHTML=`<img src="../static/img/RV03.jpg" style="max-width:300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);"  alt="foto ruta">`
		
	}else{
		imagen.innerHTML=`<img src="../static/img/RV04.jpg"  style="max-width:300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);"  alt="foto ruta">`
	}
    rutaTitulo.innerHTML = `	<h1 class="flight-title">Ruta ${ruta.nombre}</h1>
								<a href="perfilAerolinea.html">
								<button type="button" class="btn flight-airline">Aerolineas Argentinas</button>
								</a>`;
    rutaInfoDiv.innerHTML = `
        <p class="flight-info"><strong>Aerolinea:</strong> ${ruta.aerolinea}</p>
        <p class="flight-info"><strong>Descripción:</strong> ${ruta.descripcion}</p>
        <p class="flight-info"><strong>Hora:</strong> ${ruta.hora}</p>
        <p class="flight-info"><strong>Costo Turista:</strong> ${ruta.costoTurista}</p>
        <p class="flight-info"><strong>Costo Ejecutivo:</strong> ${ruta.costoEjecutivo}</p>
        <p class="flight-info"><strong>Costo Equipaje Extra:</strong> ${ruta.costoEquipaje}</p>
        <p class="flight-info"><strong>Ciudad Orígen:</strong> ${ruta.ciudadOrigen}</p>
        <p class="flight-info"><strong>Ciudad Destino:</strong> ${ruta.ciudadDestino}</p>
        <p class="flight-info"><strong>Fecha de alta:</strong> ${ruta.fechaAlta}</p>
        <p class="flight-info"><strong>Categorías:</strong> ${ruta.categorias}</p>
    `;
	// agregar el id a el breadcrumb
	const agregar_id_breadcrumb = document.getElementById('nombre-breadcrumb');
	agregar_id_breadcrumb.textContent = rutaId;
} else {
    rutaTitulo.textContent = 'Ruta no encontrada';
    rutaInfoDiv.innerHTML = `<p class="text-danger">La ruta seleccionada no existe.</p>`;
}