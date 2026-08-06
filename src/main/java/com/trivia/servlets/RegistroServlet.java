/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.trivia.servlets;

import com.trivia.beans.Usuario;
import com.trivia.dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Windows
 */
public class RegistroServlet extends HttpServlet {

    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroServlet</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet RegistroServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Recoger todos los datos del formulario de registro incluyendo usuario y password
        String cedula = request.getParameter("cedula");
        String nombreCompleto = request.getParameter("nombreCompleto"); // <-- Debe decir nombreCompleto
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String correo = request.getParameter("correo");
        String carrera = request.getParameter("carrera");
        String telefono = request.getParameter("telefono");
        
        // Convertir semestre a entero de forma segura
        int semestre = 1;
        try {
            semestre = Integer.parseInt(request.getParameter("semestre"));
        } catch (NumberFormatException e) {
            semestre = 1;
        }

        // Crear objeto Usuario con todos los datos
        Usuario u = new Usuario();
        u.setCedula(cedula);
        u.setNombre(nombreCompleto);
        u.setUsuario(usuario);
        u.setPassword(password);
        u.setCorreo(correo);
        u.setCarrera(carrera);
        u.setTelefono(telefono);
        u.setSemestre(semestre);

        // Guardar en la base de datos mediante el DAO
        boolean registrado = usuarioDAO.registrarUsuario(u);

        if (registrado) {
            // Redirigir al login si se registró con éxito
            response.sendRedirect("vistas/auth/login.jsp?exito=1");
        } else {
            // Redirigir al registro con error
            response.sendRedirect("vistas/auth/registro.jsp?error=1");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
