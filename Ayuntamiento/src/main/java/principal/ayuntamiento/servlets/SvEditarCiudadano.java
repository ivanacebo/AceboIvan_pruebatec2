package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet(name = "SvEditarCiudadano", urlPatterns = {"/SvEditarCiudadano"})
public class SvEditarCiudadano extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Long id = Long.parseLong(request.getParameter("id"));
        Ciudadano ciudadano = controladora.obtenerCiudadano(id);

        HttpSession miSession = request.getSession();
        miSession.setAttribute("ciudadanoEditar", ciudadano);

        response.sendRedirect("editarCiudadano.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String fechaStr = request.getParameter("fecha");

        SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date fecha = null;

        Ciudadano ciudadano = (Ciudadano) request.getSession().getAttribute("ciudadanoEditar");

        // Si fechaStr no es nulo ni está vacío, intenta parsear la fecha.
        // De lo contrario, conserva la fecha actual del funcionario.
        if (fechaStr != null && !fechaStr.isEmpty()) {
            try {
                fecha = fechaFormat.parse(fechaStr);
            } catch (ParseException ex) {
                Logger.getLogger(SvTurno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Si fechaStr es nulo o está vacío, conserva la fecha actual del funcionario.
            fecha = ciudadano.getFechaNacimiento();
        }

        // Validamos el DNI
        if (dni == null || !dni.matches("\\d{8}[A-HJ-NP-TV-Za-hj-np-tv-z]")) {
            request.setAttribute("errorDNI", "El formato del DNI no es válido.");
            request.getRequestDispatcher("editarCiudadano.jsp").forward(request, response);
            return;
        }
        
        // Validamos el teléfono
        if (telefono == null || telefono.length() != 9 || !telefono.matches("\\d{9}")) {
            request.setAttribute("errorTelefono", "El número de teléfono debe tener 9 dígitos.");
            request.getRequestDispatcher("editarCiudadano.jsp").forward(request, response);
            return;
        }

        ciudadano.setDni(dni);
        ciudadano.setNombre(nombre);
        ciudadano.setApellido(apellido);
        ciudadano.setTelefono(telefono);
        ciudadano.setDireccion(direccion);

        if (fecha != null) {
            ciudadano.setFechaNacimiento(fecha);
        }

        controladora.editarCiudadano(ciudadano);

        response.sendRedirect("SvCiudadano");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
