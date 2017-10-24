package es.desarrollo.hibernate.idao;

import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.hibernate.entities.usuario;

import java.util.List;

public interface IAceiteQuemadoDAO {

    boolean registrar(aceiteQuemado aceiteQuemado, String usuario);

    boolean actualizar(aceiteQuemado aceiteQuemado, String usuario);

    boolean eliminar(aceiteQuemado aceiteQuemado, String usuario);

    List<aceiteQuemado> listar(usuario usuario);

}
