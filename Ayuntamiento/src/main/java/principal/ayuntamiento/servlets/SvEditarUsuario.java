package principal.ayuntamiento.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Usuario;

@WebServlet(name = "SvEditarUsuario", urlPatterns = {"/SvEditarUsuario"})
public class SvEditarUsuario extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usu = controladora.traerUsuario(id);

        HttpSession miSession = request.getSession();
        miSession.setAttribute("usuarioEditar", usu);

        response.sendRedirect("editarUsuario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String nombre = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        Usuario usu = (Usuario) request.getSession().getAttribute("usuarioEditar");
        usu.setNombreUsuario(nombre);
        usu.setPassword(password);
        usu.setRol(rol);

        controladora.editarUsuario(usu);

        response.sendRedirect("SvUsuario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
