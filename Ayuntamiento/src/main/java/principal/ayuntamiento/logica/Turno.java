package principal.ayuntamiento.logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Turno implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado = "En espera";
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
   
    @ManyToOne
    @JoinColumn(name = "id_ciudadano")
    private Ciudadano ciudadano;
    
    @ManyToOne
    @JoinColumn(name = "id_tramite")
    private Tramite tramite;
    
    private boolean borrado = false;

    public Turno() {
    }

    public Turno(LocalDate fechaTurno, LocalTime horaTurno, Funcionario funcionario, Ciudadano ciudadano, Tramite tramite) {
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.funcionario = funcionario;
        this.ciudadano = ciudadano;
        this.tramite = tramite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
    
    @Override
    public String toString() {
        return "Turno{" + "id=" + id + ", estado=" + estado + ", fechaTurno=" + fechaTurno + ", horaTurno=" + horaTurno + ", funcionario=" + funcionario + ", ciudadano=" + ciudadano + ", tramite=" + tramite + '}';
    }
    
}
