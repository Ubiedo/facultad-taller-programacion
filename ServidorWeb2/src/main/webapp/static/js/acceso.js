const show_log = document.getElementById('show-log');
const show_sign = document.getElementById('show-sign');
const show_cliente = document.getElementById('show-r-cliente');
const show_aerolinea = document.getElementById('show-r-aerolinea');
const form_log = document.getElementById('l-usuario');
const form_cliente = document.getElementById('r-cliente');
const form_aerolinea = document.getElementById('r-aerolinea');
const log_in = document.getElementById('log-in');

// Inicializa el botón en estado activo
show_log.classList.add('active');

show_log.addEventListener('click', () => {
    if (show_sign.classList.contains('active')) {
        show_sign.classList.remove('active');
        form_aerolinea.style.display = 'none';
        form_aerolinea.reset();
        if (form_aerolinea.classList.contains('was-validated')) {
            form_aerolinea.classList.remove('was-validated');
            form_aerolinea.classList.add('needs-validation');
        }
        if (form_cliente.classList.contains('was-validated')) {
            form_cliente.classList.remove('was-validated');
            form_cliente.classList.add('needs-validation');
        }
        form_cliente.style.display = 'none';
        form_cliente.reset();
    }
    show_log.classList.add('active');
    form_log.style.display = 'block';
});

show_cliente.addEventListener('click', () => {
    if (show_log.classList.contains('active')) {
        show_log.classList.remove('active');
        form_log.style.display = 'none';
        form_log.reset();
        if (form_log.classList.contains('was-validated')) {
            form_log.classList.remove('was-validated');
            form_log.classList.add('needs-validation');
        }
    }
    if (form_aerolinea.classList.contains('was-validated')) {
        form_aerolinea.classList.remove('was-validated');
        form_aerolinea.classList.add('needs-validation');
    }
    show_sign.classList.add('active');
    form_aerolinea.style.display = 'none';
    form_aerolinea.reset();
    form_cliente.style.display = 'block';
});

show_aerolinea.addEventListener('click', () => {
    if (show_log.classList.contains('active')) {
        show_log.classList.remove('active');
        form_log.style.display = 'none';
        form_log.reset();
        if (form_log.classList.contains('was-validated')) {
            form_log.classList.remove('was-validated');
            form_log.classList.add('needs-validation');
        }
    }
    if (form_cliente.classList.contains('was-validated')) {
        form_cliente.classList.remove('was-validated');
        form_cliente.classList.add('needs-validation');
    }
    show_sign.classList.add('active');
    form_cliente.style.display = 'none';
    form_aerolinea.style.display = 'block';
    form_cliente.reset();
});

function handleFormSubmit(event) {
    'use strict'
    if (!this.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    } else {
        event.preventDefault();
		if(show_sign.classList.contains('active')){
			if(form_aerolinea.style.display === 'none'){
				const password 		= document.getElementById('password-c');
				const confirmation 	= document.getElementById('password-confirm-c');
				if(password.value != confirmation.value){
					this.classList.add('was-validated');
					confirmation.classList.remove('is-valid');
					confirmation.classList.add('is-invalid');
					document.getElementById('password-confirm-c').scrollIntoView({ behavior: 'smooth' });
					return;
				}
			} else {
				const password 		= document.getElementById('password-a');
				const confirmation 	= document.getElementById('password-confirm-a');
				if(password.value != confirmation.value){
					confirmation.classList.remove('is-valid');
					confirmation.classList.add('is-invalid');
					document.getElementById('password-confirm-a').scrollIntoView({ behavior: 'smooth' });
					return;
				}
			}
		}
		this.submit();
    }
    this.classList.add('was-validated');
}

form_cliente.addEventListener("submit", handleFormSubmit);
form_log.addEventListener("submit", handleFormSubmit);
form_aerolinea.addEventListener("submit", handleFormSubmit);
