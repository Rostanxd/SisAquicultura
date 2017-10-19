package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.acceso;
import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.idao.IAceiteQuemadoDAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class aceiteQuemadoDAO implements IAceiteQuemadoDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    public boolean validaMesEmpresa(empresa empresa, Integer mes){
        boolean existe = false;
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT count(a) " +
                "FROM aceiteQuemado AS a " +
                "WHERE a.emp_ruc =:ruc AND a.acq_mes =:mes");
        qry.setParameter("ruc", empresa.getRuc());
        qry.setParameter("mes", mes);
        Long numRegistros = (Long) qry.getSingleResult();
        if (numRegistros > 0){
            existe = true;
        }
        return existe;
    }

//    CRUD
    @Override
    public boolean registrar(aceiteQuemado aceiteQuemado, String usuario) {
        boolean registrado = false;
        aceiteQuemado aqUpd = aceiteQuemado;
        aqUpd.setEmp_ruc(aceiteQuemado.getEmpresa().getRuc());
        aqUpd.setEstado("A");

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aqUpd);
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
        boolean actualizado = false;
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT a FROM  aceiteQuemado AS a " +
                "WHERE a.emp_ruc =:ruc AND a.acq_mes =:mes");
        qry.setParameter("ruc", aceiteQuemado.getEmpresa().getRuc());
        qry.setParameter("mes", aceiteQuemado.getAcq_mes());
        aceiteQuemado aqUpd = (aceiteQuemado) qry.getSingleResult();

        aqUpd.setEstado(aceiteQuemado.getEstado());
        aqUpd.setFichaObservacion(aceiteQuemado.getFichaObservacion());
        aqUpd.setFichaDescripcion(aceiteQuemado.getFichaDescripcion());
        try{
            em.getTransaction().begin();
            em.persist(aqUpd);
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
