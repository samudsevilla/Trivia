/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.trivia.servlets;

import com.google.gson.Gson; // Asegúrate de tener la librería Gson en tu proyecto
import com.trivia.beans.Pregunta;
import com.trivia.beans.Usuario;
import com.trivia.dao.PreguntaDAO;
import com.trivia.dao.UsuarioDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Windows
 */
public class TriviaServlet extends HttpServlet {

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
            out.println("<title>Servlet TriviaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet TriviaServlet at " + request.getContextPath() + "</h1>");
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
        // Petición GET para cargar las preguntas aleatorias en formato JSON
        response.setContentType("application/json;charset=UTF-8");
        
        PreguntaDAO preguntaDAO = new PreguntaDAO();
        // Asegúrate de que el método en tu PreguntaDAO devuelva las preguntas (mínimo 10)
        List<Pregunta> listaPreguntas = preguntaDAO.obtenerPreguntasAleatorias(); 
        
        Gson gson = new Gson();
        String jsonPreguntas = gson.toJson(listaPreguntas);
        
        PrintWriter out = response.getWriter();
        out.print(jsonPreguntas);
        out.flush();
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
        // Petición para procesar la respuesta enviada por JavaScript
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuarioLogueado");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String accion = request.getParameter("accion");

        if ("guardarRespuesta".equals(accion)) {
            int idPregunta = Integer.parseInt(request.getParameter("idPregunta"));
            String respuestaDada = request.getParameter("respuestaDada");
            boolean esCorrecta = Boolean.parseBoolean(request.getParameter("esCorrecta"));

            PreguntaDAO dao = new PreguntaDAO();
            boolean guardado = dao.guardarRespuesta(user.getCedula(), idPregunta, respuestaDada, esCorrecta);

            PrintWriter out = response.getWriter();
            out.print("{\"success\":" + guardado + "}");
        } 
        else if ("actualizarPuntaje".equals(accion)) {
            int puntajeTotal = Integer.parseInt(request.getParameter("puntaje"));
            
            // Actualizamos el puntaje en la tabla de usuarios
            UsuarioDAO userDAO = new UsuarioDAO();
            boolean actualizado = userDAO.actualizarPuntaje(user.getCedula(), puntajeTotal);

            // Actualizamos también el objeto en sesión para que refleje el nuevo puntaje si se requiere
            user.setPuntaje(puntajeTotal);
            session.setAttribute("usuarioLogueado", user);

            PrintWriter out = response.getWriter();
            out.print("{\"success\":" + actualizado + "}");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para la gestión de la trivia interactiva";
    }// </editor-fold>

}
