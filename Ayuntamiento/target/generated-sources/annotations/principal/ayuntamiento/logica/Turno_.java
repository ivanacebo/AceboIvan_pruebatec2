package principal.ayuntamiento.logica;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import principal.ayuntamiento.logica.Ciudadano;
import principal.ayuntamiento.logica.Funcionario;
import principal.ayuntamiento.logica.Tramite;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-28T16:34:58")
@StaticMetamodel(Turno.class)
public class Turno_ { 

    public static volatile SingularAttribute<Turno, String> estado;
    public static volatile SingularAttribute<Turno, Tramite> tramite;
    public static volatile SingularAttribute<Turno, Boolean> borrado;
    public static volatile SingularAttribute<Turno, Long> id;
    public static volatile SingularAttribute<Turno, Funcionario> funcionario;
    public static volatile SingularAttribute<Turno, LocalDate> fechaTurno;
    public static volatile SingularAttribute<Turno, LocalTime> horaTurno;
    public static volatile SingularAttribute<Turno, Ciudadano> ciudadano;

}