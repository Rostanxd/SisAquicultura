package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.acceso;
import es.desarrollo.hibernate.entities.accesoDetalle;
import es.desarrollo.hibernate.entities.usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class accesoDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    /**
     *
     * @param usuario usuario que esta logueando en el sistema
     * @return retorna el acceso del mismo
     */
    public acceso accesosUsuario(usuario usuario){
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT a FROM acceso AS a WHERE a.id = :acc_usuario");
        qry.setParameter("acc_usuario", usuario.getAcceso());
        return (acceso) qry.getSingleResult();
    }

}
