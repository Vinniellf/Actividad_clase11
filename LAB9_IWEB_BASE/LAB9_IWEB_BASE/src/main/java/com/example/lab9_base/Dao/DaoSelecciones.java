package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase{
    public ArrayList<Seleccion> listarSelecciones() {

        ArrayList<Seleccion> selecciones = new ArrayList<>();
        String sql = "select s.*, e.nombre from seleccion s inner join estadio e ON s.estadio_idEstadio = e.idEstadio";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Seleccion s = new Seleccion();
                Estadio e = new Estadio();
                s.setIdSeleccion(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setTecnico(rs.getString(3));
                e.setIdEstadio(rs.getInt(4));
                e.setNombre(rs.getString(5));
                s.setEstadio(e);
                selecciones.add(s);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return selecciones;
    }

    public Seleccion obtenerSeleccion(int id){
        Seleccion s2 = new Seleccion();
        String sql2 = "select *from seleccion where idSeleccion = ?";
        try (Connection conn = this.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql2)) {
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    s2.setIdSeleccion(rs.getInt(1));
                    s2.setNombre(rs.getString(2));
                }
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return s2;

    }

}
