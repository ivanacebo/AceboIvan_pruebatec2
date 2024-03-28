package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Tramite;

@WebServlet(name = "SvTramite", urlPatterns = {"/SvTramite"})
public class SvTramite extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        List<Tramite> listaTramites = control.traerTramites();

        HttpSession miSession = request.getSession();
        miSession.setAttribute("listaTramites", listaTramites);

        if (listaTramites.isEmpty()) {
            response.sendRedirect("altaTramite.jsp");
        } else {
            response.sendRedirect("verTramites.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        control.crearTramite(nombre, descripcion);

        response.sendRedirect("SvTramite");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
