document.addEventListener("DOMContentLoaded", function () {
    
    // 1. Validación para el formulario de Login
    const formLogin = document.querySelector("form[action*='LoginServlet']");
    if (formLogin) {
        formLogin.addEventListener("submit", function (event) {
            const usuarioInput = document.querySelector("input[name='usuario']");
            const passwordInput = document.querySelector("input[name='password']");
            
            if (usuarioInput && usuarioInput.value.trim() === "") {
                alert("Por favor, ingresa tu nombre de usuario.");
                usuarioInput.focus();
                event.preventDefault();
                return;
            }

            if (passwordInput && passwordInput.value.trim() === "") {
                alert("Por favor, ingresa tu contraseña.");
                passwordInput.focus();
                event.preventDefault();
                return;
            }
        });
    }

    // 2. Validación para el formulario de Registro
    const formRegistro = document.querySelector("form[action*='RegistroServlet']");
    if (formRegistro) {
        formRegistro.addEventListener("submit", function (event) {
            const cedula = document.querySelector("input[name='cedula']");
            const usuario = document.querySelector("input[name='usuario']");
            const nombre = document.querySelector("input[name='nombre']");
            const correo = document.querySelector("input[name='correo']");
            const carrera = document.querySelector("input[name='carrera']");
            const semestre = document.querySelector("input[name='semestre']");

            if (!cedula || cedula.value.trim() === "" ||
                !usuario || usuario.value.trim() === "" ||
                !nombre || nombre.value.trim() === "" ||
                !correo || correo.value.trim() === "" ||
                !carrera || carrera.value.trim() === "" ||
                !semestre || semestre.value.trim() === "") {
                
                alert("Por favor, completa todos los campos obligatorios del registro.");
                event.preventDefault();
            }
        });
    }
});