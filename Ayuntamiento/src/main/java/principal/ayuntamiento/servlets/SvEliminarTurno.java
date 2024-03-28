package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import principal.ayuntamiento.logica.Controladora;

@WebServlet(name = "SvEliminarTurno", urlPatterns = {"/SvEliminarTurno"})
public class SvEliminarTurno extends HttpServlet {
    
    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Long id = Long.parseLong(request.getParameter("id"));
        controladora.eliminarTurno(id);
        
        response.sendRedirect("SvTurno");
    }
    
    public String getServletInfo() {
        return "Short description";
    }

}
