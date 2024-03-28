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
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Funcionario;

@WebServlet(name = "SvEditarFuncionario", urlPatterns = {"/SvEditarFuncionario"})
public class SvEditarFuncionario extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Long id = Long.parseLong(request.getParameter("id"));
        Funcionario funcionario = controladora.obtenerFuncionario(id);

        HttpSession miSession = request.getSession();
        miSession.setAttribute("funcionarioEditar", funcionario);

        response.sendRedirect("editarFuncionario.jsp");
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

        Funcionario funcionario = (Funcionario) request.getSession().getAttribute("funcionarioEditar");

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
            fecha = funcionario.getFechaNacimiento();
        }

        // Validamos el DNI
        if (dni == null || !dni.matches("\\d{8}[A-HJ-NP-TV-Za-hj-np-tv-z]")) {
            request.setAttribute("errorDNI", "El formato del DNI no es válido.");
            request.getRequestDispatcher("editarFuncionario.jsp").forward(request, response);
            return;
        }
        
        // Validamos el teléfono
        if (telefono == null || telefono.length() != 9 || !telefono.matches("\\d{9}")) {
            request.setAttribute("errorTelefono", "El número de teléfono debe tener 9 dígitos.");
            request.getRequestDispatcher("editarFuncionario.jsp").forward(request, response);
            return;
        }

        // Actualiza los datos del funcionario
        funcionario.setDni(dni);
        funcionario.setNombre(nombre);
        funcionario.setApellido(apellido);
        funcionario.setTelefono(telefono);
        funcionario.setDireccion(direccion);
        funcionario.setCargo(cargo);
        funcionario.setDepartamento(departamento);

        if (fecha != null) {
            funcionario.setFechaNacimiento(fecha);
        }

        controladora.editarFuncionario(funcionario);

        response.sendRedirect("SvFuncionario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
