package com.emergentes.dao;

import com.emergentes.modelo.*;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostsDAOimpl extends ConexionDB implements PostsDAO{
    @Override
    public void insert(Posts posts) throws Exception {
        
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into posts (fecha, titulo, contenido) values (?, ?, ?)");
            ps.setString(1, posts.getFechasql());
            ps.setString(2, posts.getTitulo());
            ps.setString(3, posts.getContenido());
            ps.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
    }    
    
    @Override
    public void update(Posts posts) throws Exception{

        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE posts set fecha = ?, titulo = ?, contenido = ? where id = ?");
            ps.setString(1, posts.getFechasql());
            ps.setString(2, posts.getTitulo());
            ps.setString(3, posts.getContenido());
            ps.setInt(4, posts.getId());
            ps.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
    }    
    
    @Override
    public void delete(int id) throws Exception{
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM posts WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
    }
           
    @Override
    public Posts getById(int id) throws Exception{
        
        Posts posts = new Posts();
        try{
            
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM posts WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                posts.setId(rs.getInt("id"));
                posts.setFechasql(rs.getString("fecha"));
                posts.setTitulo(rs.getString("titulo"));
                posts.setContenido(rs.getString("contenido"));
            }
        } catch(Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return posts;
    }
    
    @Override
    public List<Posts> getAll() throws Exception{
        List<Posts> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM posts ORDER BY fecha DESC");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Posts>();
            while(rs.next()) {
                Posts posts = new Posts();
                posts.setId(rs.getInt("id"));
                posts.setFechaformato(rs.getString("fecha"));
                posts.setFechasql(rs.getString("fecha"));
                posts.setTitulo(rs.getString("titulo"));
                posts.setContenido(rs.getString("contenido"));
                lista.add(posts);
            }
            rs.close();
            ps.close();
        } catch(Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return lista;
    }
}
