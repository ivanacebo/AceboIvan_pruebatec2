package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Funcionario;
import principal.ayuntamiento.logica.Tramite;

@WebServlet(name = "SvEditarTramite", urlPatterns = {"/SvEditarTramite"})
public class SvEditarTramite extends HttpServlet {
    
    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Long id = Long.parseLong(request.getParameter("id"));
        Tramite tramite = controladora.obtenerTramite(id);
        
        HttpSession miSession = request.getSession();
        miSession.setAttribute("tramiteEditar", tramite);

        response.sendRedirect("editarTramite.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        
        Tramite tramite = (Tramite) request.getSession().getAttribute("tramiteEditar");
        tramite.setNombre(nombre);
        tramite.setDescripcion(descripcion);

        controladora.editarTramite(tramite);

        response.sendRedirect("SvTramite");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
