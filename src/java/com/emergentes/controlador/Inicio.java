package com.emergentes.controlador;

import com.emergentes.dao.PostsDAO;
import com.emergentes.dao.PostsDAOimpl;
import com.emergentes.modelo.Posts;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            PostsDAO dao = new PostsDAOimpl();
            int id;
            Posts posts = new Posts();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch(action){
                case "add":
                    request.setAttribute("posts", posts);
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    posts = dao.getById(id);
                    System.out.println(posts);
                    request.setAttribute("posts", posts);
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/Inicio");
                    break; 
                case "view":
                    List<Posts> lista = dao.getAll();
                    request.setAttribute("posts", lista);
                    request.getRequestDispatcher("posts.jsp").forward(request, response);
                default:
                    break;
            } 
        } catch(Exception ex) {
            System.out.println("Error en doGet: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fecha = request.getParameter("fecha");
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");

        Posts posts = new Posts();
        
        posts.setId(id);
        posts.setFechasql(fecha);
        posts.setTitulo(titulo);
        posts.setContenido(contenido);
        
        if (id == 0) {
            try{
                PostsDAO dao = new PostsDAOimpl();
                dao.insert(posts);
                response.sendRedirect(request.getContextPath()+"/Inicio");
            } catch (Exception ex) {
                System.out.println("Error al crear nuevos datos: " + ex.getMessage());
            }
        } else {
            try{
                PostsDAO dao = new PostsDAOimpl();
                dao.update(posts);
                response.sendRedirect(request.getContextPath()+"/Inicio");
            } catch (Exception ex) {
                System.out.println("Error al editar los datos: " + ex.getMessage());
            }
        }
    }
}
