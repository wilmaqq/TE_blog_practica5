package com.emergentes.controlador;

import com.emergentes.modelo.Usuarios;
import com.emergentes.dao.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            UsuariosDAO dao = new UsuariosDAOimpl();
            Usuarios usu = dao.getById();
            System.out.println(usu);
            request.setAttribute("usu", usu);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch(Exception ex) {
            System.out.println("Error en Login (doGet): " + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuarioin");
        String usuariosql = request.getParameter("usuariosql");
        String password = request.getParameter("passwordin");
        String passwordsql = request.getParameter("passwordsql");
        
        if (usuario.equals(usuariosql) && password.equals(passwordsql)) {
            System.out.println("Conexion Correcta....");
            response.sendRedirect(request.getContextPath()+"/Inicio");
        } else {
            response.sendRedirect(request.getContextPath()+"/Login");
        }
    }

}
