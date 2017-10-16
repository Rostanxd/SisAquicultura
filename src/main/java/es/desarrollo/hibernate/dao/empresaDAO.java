package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.idao.IEmpresasDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class empresaDAO implements IEmpresasDAO{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    @Override
    public boolean registrar(empresa empresa, String idUsuario) {
        return false;
    }

    @Override
    public boolean actualizar(empresa empresa, String idUsuario) {
        return false;
    }

    @Override
    public boolean eliminar(empresa empresa, String idUsuario) {
        return false;
    }

    @Override
    public List<empresa> listar() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT e FROM empresa AS e");
        return (List<empresa>) qry.getResultList();
    }
}
