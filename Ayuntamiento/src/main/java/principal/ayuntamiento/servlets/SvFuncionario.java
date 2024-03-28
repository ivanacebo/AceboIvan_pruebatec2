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
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Funcionario;

@WebServlet(name = "SvFuncionario", urlPatterns = {"/SvFuncionario"})
public class SvFuncionario extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Funcionario> listaFuncionarios = control.traerFuncionarios();

        HttpSession miSession = request.getSession();
        miSession.setAttribute("listaFuncionarios", listaFuncionarios);

        if (listaFuncionarios.isEmpty()) {
            response.sendRedirect("altaFuncionario.jsp");
        } else {
            response.sendRedirect("verFuncionarios.jsp");
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
        String cargo = request.getParameter("cargo");
        String departamento = request.getParameter("departamento");

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
            request.getRequestDispatcher("altaFuncionario.jsp").forward(request, response);
            return;
        }

        // Validamos el teléfono
        if (telefono == null || telefono.length() != 9 || !telefono.matches("\\d{9}")) {
            request.setAttribute("errorTelefono", "El número de teléfono debe tener 9 dígitos.");
            request.getRequestDispatcher("altaFuncionario.jsp").forward(request, response);
            return;
        }

        control.crearFuncionario(dni, nombre, apellido, telefono, direccion, cargo, departamento, fecha);

        response.sendRedirect("SvFuncionario");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
