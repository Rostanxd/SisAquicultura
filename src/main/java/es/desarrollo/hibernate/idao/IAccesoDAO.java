package es.desarrollo.hibernate.idao;

import es.desarrollo.hibernate.entities.acceso;

import java.util.List;

public interface IAccesoDAO {

    boolean registrar(acceso acceso);

    boolean actualizar(acceso acceso);

    boolean eliminar(acceso acceso);

    List<acceso> listar();

}
