package com.emergentes.dao;

import com.emergentes.modelo.*;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosDAOimpl extends ConexionDB implements UsuariosDAO{
    
    @Override
    public Usuarios getById() throws Exception{
        
        Usuarios usuarios = new Usuarios();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarios.setId(rs.getInt("id"));
                usuarios.setUsuarios(rs.getString("usuario"));
                usuarios.setPassword(rs.getString("password"));
            }
        } catch(Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return usuarios;
    }
}
