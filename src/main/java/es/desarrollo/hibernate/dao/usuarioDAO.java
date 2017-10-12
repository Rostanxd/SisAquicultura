package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Rostan on 22/08/2017.
 */
public class usuarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public usuario buscaUsuarioId(String id){
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT u FROM usuario AS u WHERE u.id = :idUsuario");
        qry.setParameter("idUsuario", id);
        return (usuario) qry.getSingleResult();
    }

    public usuario validaUsuario(usuario usr) {
        usuario usuario = null;
        try {
            EntityManager em = emf.createEntityManager();
            Query qry = em.createQuery("SELECT u FROM usuario as u WHERE u.id = :id and u.clave = :clave and u.estado = 'A'"); //  Sentencia SQL para obtener el usuario, donde sea igual al código y clave del objeto usuario que entra
            qry.setParameter("id", usr.getId());    //  Seteamos el id del usuario en el where.
            qry.setParameter("clave", usr.getClave());  //  Seteamos la clave del usuario en el where.
            usuario = (usuario) qry.getSingleResult(); //  Casteo el objeto que devuelve el query como un usuario (ya que asumo es lo que devuelve)
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return usuario;
    }

    public List<usuario> listarUsuarios() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT u FROM usuario as u");
        return (List<usuario>) qry.getResultList();
    }
}
