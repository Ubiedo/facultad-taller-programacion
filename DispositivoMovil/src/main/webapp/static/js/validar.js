
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