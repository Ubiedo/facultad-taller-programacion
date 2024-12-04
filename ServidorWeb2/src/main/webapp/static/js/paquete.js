document.addEventListener("DOMContentLoaded", function () {
        const btnComprar = document.getElementById("btnComprar");
		const mensajeSinRutas = document.getElementById("mensajeSinRutas");
        const mensajeNoDisponible = document.getElementById("mensajeNoDisponible");
		
		if(noEsCliente){
			btnComprar.style.display = "none";
			mensajeNoDisponible.style.display = "none"; 
			mensajeSinRutas.style.display = "none";
		}else if (tieneRutas && noComprado) {
		     btnComprar.disabled = false;
		     mensajeNoDisponible.style.display = "none";  
		     mensajeSinRutas.style.display = "none"; 
		}else if(!noComprado){
			btnComprar.disabled = true;
			mensajeNoDisponible.style.display = "block";  
		}else if(!tieneRutas){
			btnComprar.disabled = true;
			mensajeSinRutas.style.display = "block";
		}
		
});

