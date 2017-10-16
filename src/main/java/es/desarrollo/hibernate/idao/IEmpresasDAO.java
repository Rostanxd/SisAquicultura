package es.desarrollo.hibernate.idao;

import es.desarrollo.hibernate.entities.empresa;

import java.util.List;

public interface IEmpresasDAO {

    boolean registrar(empresa empresa, String idUsuario);

    boolean actualizar(empresa empresa, String idUsuario);

    boolean eliminar(empresa empresa, String idUsuario);

    List<empresa> listar();
}
