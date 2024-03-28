package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

@WebServlet(name = "SvCiudadano", urlPatterns = {"/SvCiudadano"})
public class SvCiudadano extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Ciudadano> listaCiudadanos = control.traerCiudadanos();

        HttpSession miSession = request.getSession();
        miSession.setAttribute("listaCiudadanos", listaCiudadanos);

        if (listaCiudadanos.isEmpty()) {
            response.sendRedirect("altaCiudadano.jsp");
        } else {
            response.sendRedirect("verCiudadanos.jsp");
        }

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
        try {
            fecha = fechaFormat.parse(fechaStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvTurno.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Validamos el DNI
        if (dni == null || !dni.matches("\\d{8}[A-HJ-NP-TV-Za-hj-np-tv-z]")) {
            request.setAttribute("errorDNI", "El formato del DNI no es válido.");
            request.getRequestDispatcher("altaCiudadano.jsp").forward(request, response);
            return;
        }

        // Validamos el teléfono
        if (telefono == null || telefono.length() != 9 || !telefono.matches("\\d{9}")) {
            request.setAttribute("errorTelefono", "El número de teléfono debe tener 9 dígitos.");
            request.getRequestDispatcher("altaCiudadano.jsp").forward(request, response);
            return;
        }

        control.crearCiudadano(dni, nombre, apellido, telefono, direccion, fecha);

        response.sendRedirect("SvCiudadano");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
