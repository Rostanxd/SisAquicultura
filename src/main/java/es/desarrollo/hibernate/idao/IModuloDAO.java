package es.desarrollo.hibernate.idao;

import es.desarrollo.hibernate.entities.modulo;

import java.util.List;

public interface IModuloDAO {

    boolean registrar(modulo modulo);

    boolean actualizar(modulo modulo);

    boolean eliminar(modulo modulo);

    List<modulo> listar();

}
