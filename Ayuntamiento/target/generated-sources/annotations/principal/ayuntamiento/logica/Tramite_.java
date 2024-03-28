package principal.ayuntamiento.logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import principal.ayuntamiento.logica.Turno;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-28T16:34:58")
@StaticMetamodel(Tramite.class)
public class Tramite_ { 

    public static volatile SingularAttribute<Tramite, String> descripcion;
    public static volatile SingularAttribute<Tramite, Boolean> borrado;
    public static volatile ListAttribute<Tramite, Turno> turnos;
    public static volatile SingularAttribute<Tramite, Long> id;
    public static volatile SingularAttribute<Tramite, String> nombre;

}