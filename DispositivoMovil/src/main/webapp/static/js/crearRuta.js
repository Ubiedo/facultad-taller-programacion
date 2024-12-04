const form_ruta = document.getElementById('crear-ruta');

function handleFormSubmit(event) {
    'use strict'
    if (!this.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    } else {
		this.submit();
    }
    this.classList.add('was-validated');
}

form_ruta.addEventListener("submit", handleFormSubmit);