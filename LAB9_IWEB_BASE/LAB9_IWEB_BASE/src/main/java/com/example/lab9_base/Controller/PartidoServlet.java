package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();

        Partido p = new Partido();
        p.setNumeroJornada(Integer.parseInt(request.getParameter("jornada")));
        p.setFecha(request.getParameter("fecha"));

        /*
        p.setSeleccionLocal(request.getParameter("local"));
        p.setPhoneNumber(request.getParameter("phone"));
        p.setHireDate(request.getParameter("hire_date"));

        Seleccion selec = new Seleccion();
        selec.setIdSeleccion().setJobId(request.getParameter("job_id"));
        p.setJob(job);

        p.setSalary(new BigDecimal(request.getParameter("salary")));
        p.setCommissionPct(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));

        p manager = new p();
        manager.setpId(Integer.parseInt(request.getParameter("manager_id")));
        p.setManager(manager);

        Department department = new Department();
        department.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
        p.setDepartment(department);
        
        switch(action){
            case "guardar":
                daoPartidos.guardarPa(p);

                response.sendRedirect("pServlet");
                break;

        }*/



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();
        RequestDispatcher view;
        switch (action) {
            case "lista":
                ArrayList<Partido> listaPartidos = daoPartidos.listaDePartidos();
                request.setAttribute("lista", listaPartidos);
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaSelecciones", daoSelecciones.listarSelecciones());
                request.setAttribute("listaArbitros", daoArbitros.listarArbitros());
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);
                break;


        }

    }
}
