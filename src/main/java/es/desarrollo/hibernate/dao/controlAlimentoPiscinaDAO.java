package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.hibernate.entities.controlAlimentoPiscina;
import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.idao.IControlAlimentoPiscina;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class controlAlimentoPiscinaDAO implements IControlAlimentoPiscina {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public boolean validaMesEmpresa(empresa empresa, Integer mes){
        boolean existe = false;
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT count(c) " +
                "FROM controlAlimentoPiscina AS c " +
                "WHERE c.emp_ruc =:ruc AND c.acp_mes =:mes");
        qry.setParameter("ruc", empresa.getRuc());
        qry.setParameter("mes", mes);
        Long numRegistros = (Long) qry.getSingleResult();
        if (numRegistros > 0){
            existe = true;
        }
        return existe;
    }

    @Override
    public boolean registrar(controlAlimentoPiscina cap, String usuario) {
        boolean registrado = false;
        controlAlimentoPiscina capUpd = cap;
        capUpd.setEmp_ruc(cap.getEmpresa().getRuc());
        capUpd.setEstado("A");

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(capUpd);
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
    public boolean actualizar(controlAlimentoPiscina controlAlimentoPiscina, String usuario) {
        boolean actualizado = false;
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT c " +
                "FROM  controlAlimentoPiscina AS c " +
                "WHERE c.emp_ruc =:ruc AND c.acp_mes =:mes");
        qry.setParameter("ruc", controlAlimentoPiscina.getEmpresa().getRuc());
        qry.setParameter("mes", controlAlimentoPiscina.getAcp_mes());
        controlAlimentoPiscina capUpd = (controlAlimentoPiscina) qry.getSingleResult();

        capUpd.setEstado(controlAlimentoPiscina.getEstado());
        capUpd.setFichaObservacion(controlAlimentoPiscina.getFichaObservacion());

        try{
            em.getTransaction().begin();
            em.persist(capUpd);
            em.getTransaction().commit();
            actualizado = true;
        }catch (Exception e){
            em.getTransaction().rollback();
            e.getStackTrace();
            actualizado = false;
        }finally{
            em.close();
        }

        return actualizado;
    }

    @Override
    public boolean eliminar(controlAlimentoPiscina aceiteQuemado, String usuario) {
        return false;
    }

    @Override
    public List<controlAlimentoPiscina> listar() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT c " +
                "FROM controlAlimentoPiscina AS c");
        return (List<controlAlimentoPiscina>) qry.getResultList();
    }
}
