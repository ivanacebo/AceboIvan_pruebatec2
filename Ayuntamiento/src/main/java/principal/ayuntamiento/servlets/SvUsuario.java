package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Usuario;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Usuario> listaUsuarios = controladora.traerUsuarios();

        HttpSession miSession = request.getSession();
        miSession.setAttribute("listaUsuarios", listaUsuarios);

        if (listaUsuarios.isEmpty()) {
            response.sendRedirect("altaUsuario.jsp");
        } else {
            response.sendRedirect("verUsuarios.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String nombre = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        controladora.crearUsuario(nombre, password, rol);

        response.sendRedirect("SvUsuario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
