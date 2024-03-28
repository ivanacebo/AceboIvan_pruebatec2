package principal.ayuntamiento.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tramite implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean borrado = false;
    
    @OneToMany(mappedBy = "tramite")
    private List<Turno> turnos;

    public Tramite() {
    }

    public Tramite(String nombre, String descripcion, List<Turno> turnos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.turnos = turnos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescipcion() {
        return descripcion;
    }

    public void setDescripcion(String descipcion) {
        this.descripcion = descipcion;
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
    
    

    @Override
    public String toString() {
        return "Tramite{" + "id=" + id + ", nombre=" + nombre + ", descipcion=" + descripcion + ", turnos=" + turnos + '}';
    } 
    
}
