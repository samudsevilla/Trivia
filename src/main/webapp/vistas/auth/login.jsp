<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Trivia de Sistemas - Iniciar Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>
<body>

    <div class="auth-box">
        <h1>&gt; Login_Sistema</h1>
        <p>Ingresa tus credenciales para acceder.</p>
        
        <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
            <input type="hidden" name="accion" value="login">
            
            <input type="text" name="usuario" class="input-field" placeholder="Nombre de usuario..." required>
            <input type="password" name="password" class="input-field" placeholder="Contraseña..." required>
            
            <button type="submit" class="btn-start">INGRESAR()</button>
        </form>

        <div class="auth-links">
            <a href="registro.jsp">¿No tienes una cuenta? Regístrate aquí</a>
        </div>
    </div>

</body>
</html>