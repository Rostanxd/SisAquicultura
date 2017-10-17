package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.acceso;
import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.hibernate.idao.IAceiteQuemadoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class aceiteQuemadoDAO implements IAceiteQuemadoDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

//    CRUD
    @Override
    public boolean registrar(aceiteQuemado aceiteQuemado, String usuario) {
        boolean registrado = false;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aceiteQuemado);
            em.getTransaction().commit();
            registrado = true;
        }catch (Exception e){
            em.getTransaction().rollback();
            e.getStackTrace();
        }finally {
            em.close();
        }
        return registrado;
    }

    @Override
    public boolean actualizar(aceiteQuemado aceiteQuemado, String usuario) {
        return false;
    }

    @Override
    public boolean eliminar(aceiteQuemado aceiteQuemado, String usuario) {
        return false;
    }

    @Override
    public List<aceiteQuemado> listar() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT a FROM aceiteQuemado AS a");
        return (List<aceiteQuemado>) qry.getResultList();
    }

}
