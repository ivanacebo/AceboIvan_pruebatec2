package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import principal.ayuntamiento.logica.Ciudadano;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Funcionario;
import principal.ayuntamiento.logica.Tramite;
import principal.ayuntamiento.logica.Turno;

@WebServlet(name = "SvEditarTurno", urlPatterns = {"/SvEditarTurno"})
public class SvEditarTurno extends HttpServlet {

    Controladora controladora = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Long id = Long.parseLong(request.getParameter("id"));
        Turno turno = controladora.traerTurno(id);
        
        HttpSession miSession = request.getSession();
        miSession.setAttribute("turnoEditar", turno);

        response.sendRedirect("editarTurno.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String fechaStr = request.getParameter("fecha");
        
        //Fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);

        //Hora
        String horaStr = request.getParameter("hora");
        LocalTime hora = LocalTime.parse(horaStr);

        //Funcionario
        Long funcionarioId = Long.parseLong(request.getParameter("funcionario"));
        Funcionario funcionario = controladora.obtenerFuncionario(funcionarioId);

        //Paciente
        Long ciudadanoId = Long.parseLong(request.getParameter("ciudadano"));
        Ciudadano ciudadano = controladora.obtenerCiudadano(ciudadanoId);
        
        //Tramite
        Long tramiteId = Long.parseLong(request.getParameter("tramite"));
        Tramite tramite = controladora.obtenerTramite(tramiteId);

        //
        Turno turno = (Turno) request.getSession().getAttribute("turnoEditar");
        turno.setFechaTurno(fecha);
        turno.setHoraTurno(hora);
        turno.setFuncionario(funcionario);
        turno.setCiudadano(ciudadano);
        turno.setTramite(tramite);

        controladora.editarTurno(turno);

        response.sendRedirect("SvTurno");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
