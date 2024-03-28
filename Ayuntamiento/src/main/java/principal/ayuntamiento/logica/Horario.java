package principal.ayuntamiento.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String horarioInicio;
    private String horarioFin;
    private boolean borrado = false;

    public Horario() {
    }

    public Horario(String horarioInicio, String horarioFin) {
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(String horarioFin) {
        this.horarioFin = horarioFin;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Horario{" + "id=" + id + ", horarioInicio=" + horarioInicio + ", horarioFin=" + horarioFin + ", borrado=" + borrado + '}';
    }

    
}