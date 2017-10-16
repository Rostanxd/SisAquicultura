package es.desarrollo.hibernate.idao;

import es.desarrollo.hibernate.entities.usuario;

import java.util.List;

public interface IUsuarioDAO {

    boolean registar(usuario usuario);

    boolean actualizar(usuario usuario);

    boolean eliminar(usuario usuario);

    List<usuario> listar();

}
