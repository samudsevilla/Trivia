<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.trivia.beans.Usuario" %>
<%@ page import="com.trivia.dao.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<%
    Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
    if (user == null) {
        response.sendRedirect("/vistas/auth/login.jsp");
        return;
    }

    UsuarioDAO dao = new UsuarioDAO();
    List<Usuario> ranking = dao.obtenerRanking();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ranking de Jugadores - Trivia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/trivia.css">
</head>
<body>

    <div class="trivia-container" style="max-width: 750px;">
        <h1>&gt; Ranking_de_Jugadores</h1>
        <p>Tabla de posiciones global ordenada por puntaje.</p>
        
        <hr style="border: 0; border-top: 1px solid #30363d; margin: 15px 0;">

        <table class="ranking-table">
            <thead>
                <tr>
                    <th style="width: 10%;">#</th>
                    <th style="width: 35%;">Nombre</th>
                    <th style="width: 30%;">Carrera</th>
                    <th style="width: 10%; text-align: center;">Sem.</th>
                    <th style="width: 15%; text-align: right;">Puntaje</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int puesto = 1;
                    if (ranking != null && !ranking.isEmpty()) {
                        for (Usuario u : ranking) {
                %>
                <tr>
                    <td class="posicion"><%= puesto++ %></td>
                    <td><%= u.getNombre() %></td>
                    <td style="color: #8b949e;"><%= u.getCarrera() %></td>
                    <td style="text-align: center;"><%= u.getSemestre() %></td>
                    <td style="text-align: right; font-weight: bold; color: #58a6ff;"><%= u.getPuntaje() %> pts</td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" style="text-align: center; color: #8b949e; padding: 20px;">No hay jugadores registrados aún.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div class="trivia-footer">
            <a href="trivia.jsp">&larr; Volver a la Trivia</a>
            <a href="${pageContext.request.contextPath}/LoginServlet?accion=logout" style="color: #f85149;">Cerrar Sesión</a>
        </div>
    </div>

</body>
</html>