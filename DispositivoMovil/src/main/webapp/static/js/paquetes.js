//para que no se exceda de palabras la descripcion
document.querySelectorAll('.card-text').forEach(function (descripcion) {
    const maxCaracteres = 100;
    if (descripcion.textContent.length > maxCaracteres) {
        descripcion.textContent = descripcion.textContent.slice(0, maxCaracteres) + '...';
    }
});