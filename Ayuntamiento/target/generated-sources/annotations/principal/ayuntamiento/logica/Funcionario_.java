package principal.ayuntamiento.logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import principal.ayuntamiento.logica.Turno;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-28T16:34:58")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ extends Persona_ {

    public static volatile SingularAttribute<Funcionario, Boolean> borrado;
    public static volatile SingularAttribute<Funcionario, String> departamento;
    public static volatile ListAttribute<Funcionario, Turno> turnos;
    public static volatile SingularAttribute<Funcionario, String> cargo;

}