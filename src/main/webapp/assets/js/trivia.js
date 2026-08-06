let preguntas = [];
let indiceActual = 0;
let puntaje = 0;

document.addEventListener("DOMContentLoaded", function() {
    // Obtener el contextPath inyectado desde el JSP o usar una ruta relativa segura
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    
    // Cargar preguntas desde el Servlet vía fetch
    fetch(APP_CONTEXT + '/TriviaServlet')
        .then(response => response.json())
        .then(data => {
            preguntas = data;
            if (preguntas.length > 0) {
                mostrarPregunta();
            } else {
                document.getElementById("contenedor-trivia").innerHTML = "<p style='color: #f85149; text-align: center;'>No hay preguntas disponibles en este momento.</p>";
            }
        })
        .catch(error => {
            console.error("Error al cargar las preguntas:", error);
            document.getElementById("contenedor-trivia").innerHTML = "<p style='color: #f85149; text-align: center;'>Error al conectar con el servidor.</p>";
        });
});

function mostrarPregunta() {
    if (indiceActual >= preguntas.length) {
        finalizarTrivia();
        return;
    }

    const p = preguntas[indiceActual];
    
    // Mezclar opciones de manera sencilla para que la correcta no esté siempre en el mismo lugar
    let opciones = [p.opcion1, p.opcion2, p.opcion3, p.opcionCorrecta];
    opciones.sort(() => Math.random() - 0.5);

    let html = `
        <div class="pregunta-texto">Pregunta ${indiceActual + 1} de ${preguntas.length}:<br>${p.pregunta}</div>
        <div class="opciones-grid">
    `;

    opciones.forEach(op => {
        // Escapamos comillas simples para evitar errores en el evento onclick
        let opEscapada = op.replace(/'/g, "\\'");
        let correctaEscapada = p.opcionCorrecta.replace(/'/g, "\\'");
        html += `<button class="opcion-btn" onclick="verificarRespuesta('${opEscapada}', '${correctaEscapada}', ${p.id})">${op}</button>`;
    });

    html += `</div>`;
    document.getElementById("contenedor-trivia").innerHTML = html;
}

function verificarRespuesta(elegida, correcta, idPregunta) {
    const esCorrecta = (elegida === correcta);
    if (esCorrecta) {
        puntaje += 10; // 10 puntos por cada acierto
    }

    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    // Enviar respuesta al backend para guardarla en el historial
    const params = new URLSearchParams();
    params.append('accion', 'guardarRespuesta');
    params.append('idPregunta', idPregunta);
    params.append('respuestaDada', elegida);
    params.append('esCorrecta', esCorrecta);

    fetch(APP_CONTEXT + '/TriviaServlet', {
        method: 'POST',
        body: params
    });

    indiceActual++;
    mostrarPregunta();
}

function finalizarTrivia() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    // Actualizar puntaje total en el backend
    const params = new URLSearchParams();
    params.append('accion', 'actualizarPuntaje');
    params.append('puntaje', puntaje);

    fetch(APP_CONTEXT + '/TriviaServlet', {
        method: 'POST',
        body: params
    }).then(() => {
        document.getElementById("contenedor-trivia").innerHTML = `
            <div style="text-align: center; padding: 20px;">
                <h2 style="color: #58a6ff;">¡Trivia Finalizada!</h2>
                <p style="font-size: 1.2rem; margin: 15px 0;">Tu puntaje total es: <strong>${puntaje}</strong> puntos</p>
                <a href="ranking.jsp" class="btn-siguiente" style="display: inline-block; text-decoration: none; text-align: center; margin-top: 10px;">Ver Ranking Global</a>
            </div>
        `;
    });
}