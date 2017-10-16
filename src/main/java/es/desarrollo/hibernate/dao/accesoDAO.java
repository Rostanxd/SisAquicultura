package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.acceso;
import es.desarrollo.hibernate.entities.accesoDetalle;
import es.desarrollo.hibernate.entities.usuario;
import es.desarrollo.hibernate.idao.IAccesoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;

public class accesoDAO implements IAccesoDAO{

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

    //    CRUD
    @Override
    public boolean registrar(acceso acceso) {
        boolean registrado = false;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(acceso);
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
    public boolean actualizar(acceso acceso) {
        boolean actualizado = false;
        EntityManager em = emf.createEntityManager();
        try {
            Query qry = em.createQuery("SELECT a FROM acceso AS a WHERE a.id =:accCodigo");
            qry.setParameter("accCodigo", acceso.getId());

            acceso accesoUpd = (acceso) qry.getSingleResult();
            accesoUpd.setNombre(acceso.getNombre());
            accesoUpd.setFechaModificacion(Calendar.getInstance().getTime());
            accesoUpd.setUsuarioModificacion("SISTEMAS");

            em.getTransaction().begin();
            em.persist(accesoUpd);
            em.getTransaction().commit();

            actualizado = true;
        }catch (Exception e){
            e.getStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        return actualizado;
    }

    @Override
    public boolean eliminar(acceso acceso) {
        return false;
    }

    @Override
    public List<acceso> listar() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT a FROM acceso AS a");
        return (List<acceso>) qry.getResultList();
    }
}
