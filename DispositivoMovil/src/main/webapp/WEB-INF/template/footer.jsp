<%@page contentType="text/html" pageEncoding="UTF-8"%>
      <nav id="" class="navbar bg-body-tertiary" style="padding-left: 1em; padding-right: .5em;">
          <span class="navbar-text" style="font-size: .8em; text-align: center; width: 100%;">
            © 2024 volando.uy. Todos los derechos reservados.
          </span>
          <a class="navbar-brand" style="text-align: center; width: 100%;">Volando.uy</a>
      </nav>
      
      <script>
    function checkBodyHeight() {
      // Obtener el tamaño del body
      const bodyHeight = document.body.offsetHeight;
      // Obtener la altura de la ventana
      const windowHeight = window.innerHeight;

      // Verificar si el tamaño del body es menor que la altura de la ventana
      const footer = document.getElementById('footer'); // Asegúrate de que sea el selector correcto
      if (footer) {
        if (bodyHeight < windowHeight) {
          footer.classList.add('fixed-bottom');
        } else {
          footer.classList.remove('fixed-bottom'); // Remover la clase si el body es mayor que 100vh
        }
      }
    }

    // Agregar listeners para onload y resize
    window.onload = checkBodyHeight; // Esto se asegura de que también se ejecute en el evento onload
    window.onresize = checkBodyHeight; // Esto se asegura de que se ejecute al hacer zoom o cambiar el tamaño de la ventana
 	// Usar ResizeObserver para observar cambios en el tamaño del body
    const resizeObserver = new ResizeObserver(() => {
      checkBodyHeight(); // Ejecutar checkBodyHeight cuando cambie el tamaño del body
    });

    // Comenzar a observar el body
    resizeObserver.observe(document.body);
  </script>