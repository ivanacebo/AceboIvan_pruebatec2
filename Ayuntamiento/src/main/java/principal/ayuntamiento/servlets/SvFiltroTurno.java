package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Turno;

@WebServlet(name = "SvFiltroTurno", urlPatterns = {"/SvFiltroTurno"})
public class SvFiltroTurno extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {

            String fechaStr = request.getParameter("fecha");
            String estado = request.getParameter("estado");
            
            //Fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

            List<Turno> listaTurnosFiltrados = control.traerTurnosFiltradosEstadoFecha(fecha, estado);

            HttpSession misesion = request.getSession();
            misesion.setAttribute("listadoTurnosFiltrados", listaTurnosFiltrados);

            response.sendRedirect("verTurnosFiltrados.jsp");

        } catch (DateTimeParseException ex) {
            request.setAttribute("errorMensaje", "¡Todos los campos son obligatorios!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
