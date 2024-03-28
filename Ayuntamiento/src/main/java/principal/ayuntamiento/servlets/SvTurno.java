package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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

@WebServlet(name = "SvTurno", urlPatterns = {"/SvTurno"})
public class SvTurno extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Turno> listaTurnos = controladora.traerTurnos();

        HttpSession miSession = request.getSession();
        miSession.setAttribute("listaTurnos", listaTurnos);

        if (listaTurnos.isEmpty()) {
            response.sendRedirect("altaTurno.jsp");
        } else {
            response.sendRedirect("verTurnos.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {
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

            controladora.crearTurno(fecha, hora, funcionario, ciudadano, tramite);

            response.sendRedirect("SvTurno");
            
        } catch (DateTimeParseException ex) {
            request.setAttribute("errorMensaje", "¡Todos los campos son obligatorios!");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
