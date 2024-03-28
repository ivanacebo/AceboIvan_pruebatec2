
package principal.ayuntamiento.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import principal.ayuntamiento.logica.Controladora;
import principal.ayuntamiento.logica.Turno;

@WebServlet(name = "SvAtenderTurno", urlPatterns = {"/SvAtenderTurno"})
public class SvAtenderTurno extends HttpServlet {
    
    Controladora control = new Controladora();

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
        
        Turno turno = control.atenderSiguienteTurno();

        if (turno != null) {
            turno.setEstado("Ya atendido");
            control.editarTurno(turno);
        }

        response.sendRedirect("atenderTurno.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
