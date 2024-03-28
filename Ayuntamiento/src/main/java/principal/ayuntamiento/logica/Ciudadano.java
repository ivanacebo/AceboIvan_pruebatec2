package principal.ayuntamiento.logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Ciudadano extends Persona implements Serializable{
    
    @OneToMany(mappedBy = "ciudadano")
    private List<Turno> turnos;
    private boolean borrado = false;
    

    public Ciudadano() {
    }

    public Ciudadano(List<Turno> turnos, String dni, String nombre, String apellido, String telefono, String direccion, Date fechaNacimiento) {
        super(dni, nombre, apellido, telefono, direccion, fechaNacimiento);
        this.turnos = turnos;
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
        return "Ciudadano{" + "turnos=" + turnos + '}';
    }
    
}
