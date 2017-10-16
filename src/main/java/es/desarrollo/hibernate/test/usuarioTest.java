package es.desarrollo.hibernate.test;

import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.usuario;

import java.util.List;

/**
 * Created by Rostan on 22/08/2017.
 */
public class usuarioTest {

    public static void main(String[] args) {
        listarUsuarios();
    }

    private static void listarUsuarios() {
        usuarioDAO usuarioDAO = new usuarioDAO();
        List<usuario> usuarios = usuarioDAO.listar();
        System.out.println("Usuarios en el sistema...");
        for (usuario u : usuarios) {
            System.out.println("    - " + u.toString());
        }
    }
}
