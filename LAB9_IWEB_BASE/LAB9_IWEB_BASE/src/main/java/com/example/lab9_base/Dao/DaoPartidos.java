package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase{
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();

        String sql = "SELECT \n" +
                "    p.*, \n" +
                "    sl.nombre AS sLocal, \n" +
                "    sv.nombre AS sVisitante, \n" +
                "    e.nombre AS eName, \n" +
                "    a.nombre AS nameArbitro, \n" +
                "    e.idEstadio \n" +
                "FROM \n" +
                "    partido p\n" +
                "JOIN \n" +
                "    seleccion sl ON p.seleccionLocal = sl.idSeleccion\n" +
                "JOIN \n" +
                "    seleccion sv ON p.seleccionVisitante = sv.idSeleccion\n" +
                "JOIN \n" +
                "    estadio e ON sl.estadio_idEstadio = e.idEstadio\n" +
                "JOIN \n" +
                "    arbitro a ON p.arbitro = a.idArbitro";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Partido p = fetchPartidoData(rs);
                partidos.add(p);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }

    private Partido fetchPartidoData(ResultSet rs) throws SQLException {
        Partido p = new Partido();
        p.setIdPartido(rs.getInt(1));
        p.setNumeroJornada(rs.getInt("numeroJornada"));
        p.setFecha(rs.getString(6));

        Seleccion s1 = new Seleccion();
        s1.setIdSeleccion(rs.getInt(2));
        s1.setNombre(rs.getString("sVisitante"));
        p.setSeleccionLocal(s1);

        Estadio e = new Estadio();
        e.setIdEstadio(rs.getInt("idEstadio"));
        e.setNombre(rs.getString("eName"));

        Seleccion s2 = new Seleccion();
        s2.setIdSeleccion(rs.getInt(3));
        s2.setNombre(rs.getString("sLocal"));
        s2.setEstadio(e);
        p.setSeleccionVisitante(s2);

        Arbitro ar = new Arbitro();
        ar.setIdArbitro(rs.getInt(4));
        ar.setNombre(rs.getString("nameArbitro"));
        p.setArbitro(ar);

        return p;
    }
}
