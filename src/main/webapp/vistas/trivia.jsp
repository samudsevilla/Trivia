<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.trivia.beans.Usuario" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
    if (user == null) {
        response.sendRedirect("/vistas/auth/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Trivia Interactiva - Sistemas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/trivia.css">
</head>
<body>

    <div class="trivia-container">
        <h1>&gt; Trivia_Interactiva</h1>
        <p>Bienvenido, <strong><%= user.getNombre() %></strong> (<%= user.getCarrera() %>)</p>
        
        <hr style="border: 0; border-top: 1px solid #30363d; margin: 15px 0;">

        <!-- Contenedor dinámico de la trivia -->
        <div id="contenedor-trivia">
            <p style="text-align: center; color: #8b949e;">Cargando preguntas técnicas...</p>
        </div>

        <div class="trivia-footer">
            <a href="ranking.jsp">Ver Ranking de Jugadores</a>
            <a href="${pageContext.request.contextPath}/LoginServlet?accion=logout" style="color: #f85149;">Cerrar Sesión</a>
        </div>
    </div>
    <!-- Definimos el path global de la app directamente desde JSP -->
    <script>
        const APP_CONTEXT = '${pageContext.request.contextPath}';
    </script>    
    <!-- Enlace al archivo JS externo -->
    <script src="${pageContext.request.contextPath}/assets/js/trivia.js"></script>
</body>
</html>