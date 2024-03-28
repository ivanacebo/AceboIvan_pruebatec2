package principal.ayuntamiento.logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Funcionario extends Persona implements Serializable{
    
    private String cargo;
    private String departamento;
    
    /*@OneToOne 
    private Usuario Usuario;
    
    @OneToOne
    private Horario Horario;*/
    
    @OneToMany(mappedBy = "funcionario")
    private List<Turno> turnos;
    
    private boolean borrado = false;

    public Funcionario() {
    }

    public Funcionario(String cargo, String departamento, List<Turno> turnos, String dni, String nombre, String apellido, String telefono, String direccion, Date fechaNacimiento) {
        super(dni, nombre, apellido, telefono, direccion, fechaNacimiento);
        this.cargo = cargo;
        this.departamento = departamento;
        this.turnos = turnos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
    
    /*
    Función para poder pintar la fecha según mis intereses
    */
    public String getFechaFormateada(){
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("yyyy-mm-dd");
        return fechaFormateada.format(this.getFechaNacimiento());
    }

    @Override
    public String toString() {
        return "Funcionario{" + "cargo=" + cargo + ", departamento=" + departamento + ", turnos=" + turnos + '}';
    }

}
