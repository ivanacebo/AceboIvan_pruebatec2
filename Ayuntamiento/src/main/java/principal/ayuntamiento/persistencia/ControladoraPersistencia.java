package principal.ayuntamiento.persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.ayuntamiento.logica.Ciudadano;
import principal.ayuntamiento.logica.Funcionario;
import principal.ayuntamiento.logica.Tramite;
import principal.ayuntamiento.logica.Turno;
import principal.ayuntamiento.logica.Usuario;
import principal.ayuntamiento.persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();
    FuncionarioJpaController funcionarioJPA = new FuncionarioJpaController();
    TramiteJpaController tramiteJPA = new TramiteJpaController();
    TurnoJpaController turnoJPA = new TurnoJpaController();
    PersonaJpaController personaJPA = new PersonaJpaController();
    HorarioJpaController horarioJPA = new HorarioJpaController();
    UsuarioJpaController usuarioJPA = new UsuarioJpaController();

    /* CIUDADANO */
    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
    }

    public List<Ciudadano> traerCiudadanos() {
        return ciudadanoJPA.findCiudadanoEntities();
    }

    public List<Ciudadano> traerCiudadanosFiltrados() {
        return ciudadanoJPA.encontrarCiudadanosNoBorrados();
    }

    public void eliminarCiudadano(Long id) {
        Ciudadano ciudadano = ciudadanoJPA.findCiudadano(id);
        if (ciudadano != null && !ciudadano.isBorrado()) {
            ciudadano.setBorrado(true);
            editarCiudadano(ciudadano);
        }
    }

    public void editarCiudadano(Ciudadano ciudadano) {
        try {
            ciudadanoJPA.edit(ciudadano);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ciudadano obtenerCiudadano(Long id) {
        Ciudadano ciudadano = ciudadanoJPA.findCiudadano(id);
        if (ciudadano != null && !ciudadano.isBorrado()) {
            return ciudadano;
        } else {
            return null;
        }
    }

    /* ------------------------------------------------------- */

 /* FUNCIONARIO */
    public void crearFuncionario(Funcionario funcionario) {
        funcionarioJPA.create(funcionario);
    }

    public List<Funcionario> traerFuncionarios() {
        return funcionarioJPA.findFuncionarioEntities();
    }

    public List<Funcionario> traerFuncionariosFiltrados() {
        return funcionarioJPA.encontrarFuncionariosNoBorrados();
    }

    public void eliminarFuncionaro(Long id) {
        Funcionario funcionario = funcionarioJPA.findFuncionario(id);
        if (funcionario != null && !funcionario.isBorrado()) {
            funcionario.setBorrado(true);
            editarFuncionario(funcionario);
        }
    }

    public void editarFuncionario(Funcionario funcionario) {
        try {
            funcionarioJPA.edit(funcionario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Funcionario obtenerFuncionario(Long id) {
        Funcionario funcionario = funcionarioJPA.findFuncionario(id);
        if (funcionario != null && !funcionario.isBorrado()) {
            return funcionario;
        } else {
            return null;
        }
    }

    /* ------------------------------------------------------- */

 /* TRAMITE */
    public void crearTramite(Tramite tramite) {
        tramiteJPA.create(tramite);
    }

    public List<Tramite> traerTramites() {
        return tramiteJPA.findTramiteEntities();
    }

    public List<Tramite> traerTramitesFiltrados() {
        return tramiteJPA.encontrarTramitesNoBorrados();
    }

    public void eliminarTramite(Long id) {
        Tramite tramite = tramiteJPA.findTramite(id);
        if (tramite != null && !tramite.isBorrado()) {
            tramite.setBorrado(true);
            editarTramite(tramite);
        }
    }

    public void editarTramite(Tramite tramite) {
        try {
            tramiteJPA.edit(tramite);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tramite obtenerTramite(Long id) {
        Tramite tramite = tramiteJPA.findTramite(id);
        if (tramite != null && !tramite.isBorrado()) {
            return tramite;
        } else {
            return null;
        }
    }

    /* ------------------------------------------------------- */

 /* TURNO */
    public void crearTurno(Turno turno) {
        turnoJPA.create(turno);
    }

    public List<Turno> traerTurnos() {
        return turnoJPA.findTurnoEntities();
    }

    public List<Turno> traerTurnosFiltrados() {
        return turnoJPA.encontrarTurnosNoBorrados();
    }

    public void eliminarTurno(Long id) {
        Turno turno = turnoJPA.findTurno(id);
        if (turno != null && !turno.isBorrado()) {
            turno.setBorrado(true);
            editarTurno(turno);
        }
    }

    public void editarTurno(Turno turno) {
        try {
            turnoJPA.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Turno traerTurno(Long id) {
        Turno turno = turnoJPA.findTurno(id);
        if (turno != null && !turno.isBorrado()) {
            return turno;
        } else {
            return null;
        }
    }

    public Turno atenderSiguienteTurno() {
        return turnoJPA.encontrarTurnoConMenorIdYEstadoEnEspera();
    }

    /* ------------------------------------------------------- */
    
 /* USUARIO */
    public void crearUsuario(Usuario usuario) {
        usuarioJPA.create(usuario);
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJPA.findUsuarioEntities();
    }

    public List<Usuario> traerUsuariosFiltrados() {
        return usuarioJPA.encontrarUsuariosNoBorrados();
    }

    public void borrarUsuario(int id) {
        Usuario usuario = usuarioJPA.findUsuario(id);
        if (usuario != null && !usuario.isBorrado()) {
            usuario.setBorrado(true);
            editarUsuario(usuario);
        }
    }

    public Usuario traerUsuario(int id) {
        Usuario usuario = usuarioJPA.findUsuario(id);
        if (usuario != null && !usuario.isBorrado()) {
            return usuario;
        } else {
            return null;
        }
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuarioJPA.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
