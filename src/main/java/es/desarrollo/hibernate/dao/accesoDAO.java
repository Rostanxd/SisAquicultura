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
    public List<accesoDetalle> accesosUsuario(usuario usuario){
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT a " +
                "FROM accesoDetalle AS a WHERE a.acc_codigo = :acc_codigo");
        qry.setParameter("acc_codigo", usuario.getAcceso().getId());
        return (List<accesoDetalle>) qry.getResultList();
    }

}
