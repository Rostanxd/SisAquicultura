package es.desarrollo.hibernate.idao;

import es.desarrollo.hibernate.entities.controlAlimentoPiscina;
import java.util.List;

public interface IControlAlimentoPiscina {

    boolean registrar(controlAlimentoPiscina controlAlimentoPiscina, String usuario);

    boolean actualizar(controlAlimentoPiscina controlAlimentoPiscina, String usuario);

    boolean eliminar(controlAlimentoPiscina controlAlimentoPiscina, String usuario);

    List<controlAlimentoPiscina> listar();

}
