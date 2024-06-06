package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;

public class DaoArbitros extends DaoBase{
    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> listaArbitro = new ArrayList<>();
        String query = "SELECT * FROM arbitro";


        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()) {
                Arbitro arbitro = fetchArbitroData(rs);
                listaArbitro.add(arbitro);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return listaArbitro;
    }

    public void crearArbitro(Arbitro arbitro) {
        String query = "INSERT INTO arbitro (nombre, pais) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Llamamos al método para asignar los valores del objeto Employee a los parámetros de la sentencia SQL.
            setArbitroParameters(arbitro, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // En caso de que ocurra una excepción SQL, imprimimos la traza de la pila.
            e.printStackTrace();
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();

        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {
        /*
        Inserte su código aquí
        */
    }

    private Arbitro fetchArbitroData( ResultSet rs) throws SQLException{
        Arbitro arbitro = new Arbitro();
        arbitro.setIdArbitro(rs.getInt(1));
        arbitro.setNombre(rs.getString(2));
        arbitro.setPais(rs.getString(3));

        return arbitro;
    }

    private void setArbitroParameters(Arbitro arbitro, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, arbitro.getNombre());
        pstmt.setString(2, arbitro.getPais());

    }
}
