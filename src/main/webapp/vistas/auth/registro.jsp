<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Trivia de Sistemas - Registro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>
<body>

    <div class="login-box" style="width: 420px;">
        <h1>&gt; Registro_Jugador</h1>
        <p>Completa tus datos académicos para la trivia.</p>
        
        <form action="${pageContext.request.contextPath}/RegistroServlet" method="POST">
            <input type="hidden" name="accion" value="registrar">
            
            <input type="text" name="cedula" class="input-field" placeholder="Cédula (Clave Principal)..." required>
            <input type="text" name="nombreCompleto" class="input-field" placeholder="Nombre completo..." required>
            <input type="email" name="correo" class="input-field" placeholder="Correo electrónico..." required>
            <input type="text" name="carrera" class="input-field" placeholder="Carrera..." required>
            <input type="text" name="telefono" class="input-field" placeholder="Teléfono..." required>
            <input type="text" name="semestre" class="input-field" placeholder="Semestre actual..." required>
            <input type="text" name="usuario" class="input-field" placeholder="Nombre de usuario..." required>
            <input type="password" name="password" class="input-field" placeholder="Contraseña..." required>
            
            <button type="submit" class="btn-start">REGISTRARSE()</button>
        </form>

        <div style="margin-top: 15px; text-align: center;">
            <a href="login.jsp" style="color: #58a6ff; font-size: 12px; text-decoration: none;">¿Ya tienes cuenta? Inicia sesión</a>
        </div>
    </div>

</body>
</html>