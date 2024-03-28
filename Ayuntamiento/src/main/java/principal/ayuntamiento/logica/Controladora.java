package principal.ayuntamiento.logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import principal.ayuntamiento.persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    /* CIUDADANO */
    public void crearCiudadano(String dni, String nombre, String apellido, String telefono, String direccion, Date fechaNacimiento) {
        Ciudadano ciudadano = new Ciudadano();

        ciudadano.setDni(dni);
        ciudadano.setNombre(nombre);
        ciudadano.setApellido(apellido);
        ciudadano.setTelefono(telefono);
        ciudadano.setDireccion(direccion);
        ciudadano.setFechaNacimiento(fechaNacimiento);

        controlPersis.crearCiudadano(ciudadano);
    }

    public List<Ciudadano> traerCiudadanos() {
        return controlPersis.traerCiudadanosFiltrados();
    }

    public void eliminarCiudadano(Long id) {
        controlPersis.eliminarCiudadano(id);
    }

    public void editarCiudadano(Ciudadano ciudadano) {
        controlPersis.editarCiudadano(ciudadano);
    }

    public Ciudadano obtenerCiudadano(Long id) {
        return controlPersis.obtenerCiudadano(id);
    }

    /* ------------------------------------------------------- */

 /* FUNCIONARIO */
    public void crearFuncionario(String dni, String nombre, String apellido, String telefono, String direccion,
            String cargo, String departamento, Date fechaNacimiento) {

        Funcionario funcionario = new Funcionario();

        funcionario.setDni(dni);
        funcionario.setNombre(nombre);
        funcionario.setApellido(apellido);
        funcionario.setTelefono(telefono);
        funcionario.setDireccion(direccion);
        funcionario.setCargo(cargo);
        funcionario.setDepartamento(departamento);
        funcionario.setFechaNacimiento(fechaNacimiento);

        controlPersis.crearFuncionario(funcionario);
    }

    public List<Funcionario> traerFuncionarios() {
        return controlPersis.traerFuncionariosFiltrados();
    }

    public void editarFuncionario(Funcionario funcionario) {
        controlPersis.editarFuncionario(funcionario);
    }

    public void eliminarFuncionario(Long id) {
        controlPersis.eliminarFuncionaro(id);
    }

    public Funcionario obtenerFuncionario(Long id) {
        return controlPersis.obtenerFuncionario(id);
    }

    /* ------------------------------------------------------- */

 /* TRAMITE */
    public void crearTramite(String nombre, String descripcion) {
        Tramite tramite = new Tramite();
        tramite.setNombre(nombre);
        tramite.setDescripcion(descripcion);

        controlPersis.crearTramite(tramite);
    }

    public List<Tramite> traerTramites() {
        return controlPersis.traerTramitesFiltrados();
    }

    public void eliminarTramite(Long id) {
        controlPersis.eliminarTramite(id);
    }

    public void editarTramite(Tramite tramite) {
        controlPersis.editarTramite(tramite);
    }

    public Tramite obtenerTramite(Long id) {
        return controlPersis.obtenerTramite(id);
    }

    /* ------------------------------------------------------- */

 /* TURNO */
    public void crearTurno(LocalDate fechaTurno, LocalTime horaTurno, Funcionario funcionario, Ciudadano ciudadano, Tramite tramite) {
        Turno turno = new Turno();

        turno.setFechaTurno(fechaTurno);
        turno.setHoraTurno(horaTurno);
        turno.setCiudadano(ciudadano);
        turno.setFuncionario(funcionario);
        turno.setTramite(tramite);

        controlPersis.crearTurno(turno);
    }

    public List<Turno> traerTurnos() {
        return controlPersis.traerTurnosFiltrados();
    }

    public void eliminarTurno(Long id) {
        controlPersis.eliminarTurno(id);
    }

    public void editarTurno(Turno turno) {
        controlPersis.editarTurno(turno);
    }

    public Turno traerTurno(Long id) {
        return controlPersis.traerTurno(id);
    }

    public Turno atenderSiguienteTurno() {
        return controlPersis.atenderSiguienteTurno();
    }

    /**
     * Retorna una lista de turnos filtrados por fecha y estado.
     * @param fecha para filtrar los turnos.
     * @param estado para filtrar los turnos.
     * @return lista de objetos Turno ya filtrados.
     */
    public List<Turno> traerTurnosFiltradosEstadoFecha(LocalDate fecha, String estado) {
        List<Turno> traerTurnos = controlPersis.traerTurnos();
        List<Turno> turnosFiltrados = traerTurnos.stream()
                .filter(f -> f.getFechaTurno().equals(fecha))
                .filter(e -> e.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());

        return turnosFiltrados;
    }

    /* ------------------------------------------------------- */

 /* USUARIO */
    public void crearUsuario(String nombreUsuario, String contrasenia, String rol) {

        Usuario usu = new Usuario();
        usu.setNombreUsuario(nombreUsuario);
        usu.setPassword(contrasenia);
        usu.setRol(rol);

        controlPersis.crearUsuario(usu);
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuariosFiltrados();
    }

    public void borrarUsuario(int id) {
        controlPersis.borrarUsuario(id);
    }

    public Usuario traerUsuario(int id) {
        return controlPersis.traerUsuario(id);
    }

    public void editarUsuario(Usuario usu) {
        controlPersis.editarUsuario(usu);
    }

    public boolean comprobarIngreso(String usuario, String password) {
        boolean ingreso = false;

        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios = controlPersis.traerUsuarios();

        for (Usuario usu : listaUsuarios) {
            if (usu.getNombreUsuario().equals(usuario)) {
                if (usu.getPassword().equals(password)) {
                    ingreso = true;
                } else {
                    ingreso = false;
                }
            }
        }

        return ingreso;
    }
    /* ------------------------------------------------------- */
}
