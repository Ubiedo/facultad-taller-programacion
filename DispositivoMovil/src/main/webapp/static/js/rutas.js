const filtroForm = document.getElementById('filtro-form');

// Mostrar todas las opciones al cargar la página
window.onload = () => {
    filtrar([]); 
	
};

filtroForm.addEventListener('change', () => {
    const selectedFilters = Array.from(filtroForm.querySelectorAll('input:checked')).map(checkbox => checkbox.value);
    filtrar(selectedFilters);
});

function filtrar(filtros) {
    const opciones = document.querySelectorAll('.flight-option');
    
    opciones.forEach(opcion => {
        const filterValue = opcion.getAttribute('data-filter');
        
        // Si alguno de los filtros seleccionados coincide, mostramos la opción
        if (filtros.length === 0 || filtros.includes(filterValue)) {
            opcion.style.display = 'block'; // Mostrar la opción si no hay filtros o si coincide
        } else {
            opcion.style.display = 'none'; // Ocultar la opción si no coincide
        }
    });
}