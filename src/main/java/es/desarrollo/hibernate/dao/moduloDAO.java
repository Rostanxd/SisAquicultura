package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.modulo;
import es.desarrollo.hibernate.entities.usuario;
import es.desarrollo.hibernate.idao.IModuloDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class moduloDAO implements IModuloDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    /**
     *
     * @param usuario usuario del cual obtendremos a que modulos tiene acceso
     * @return retorna la lista de modulos que se podran cargar.
     */
    public List<modulo> listarModulosAcceso(usuario usuario){
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT DISTINCT p.modulo FROM accesoDetalle AS ad " +
                "INNER JOIN programa AS p " +
                "ON(p.id = ad.programa.id) " +
                "WHERE ad.acc_codigo = :acc_codigo");
        qry.setParameter("acc_codigo", usuario.getAcceso().getId());
        return (List<modulo>) qry.getResultList();
    }

    @Override
    public boolean registrar(modulo modulo) {
        boolean registrado = false;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(modulo);
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
    public boolean actualizar(modulo modulo) {
        boolean actualizado = false;
        EntityManager em = emf.createEntityManager();
        try{
            Query qry = em.createQuery("SELECT m FROM modulo AS m WHERE m.id =:modCodigo");
            qry.setParameter("modCodigo", modulo.getId());

            modulo moduloUpd = (modulo) qry.getSingleResult();
            moduloUpd.setNombre(modulo.getNombre());
            moduloUpd.setTipo(modulo.getTipo());

            em.getTransaction().begin();
            em.persist(moduloUpd);
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
    public boolean eliminar(modulo modulo) {
        return false;
    }

    @Override
    public List<modulo> listar() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT m FROM modulo AS m");
        return (List<modulo>) qry.getResultList();
    }
}
