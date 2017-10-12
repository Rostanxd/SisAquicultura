package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.modulo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class moduloDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    /**
     *
     * @param tipoUsuario {'S', 'U'} resuelve para saber que tipo de usuario loguea.
     * @return retorna la lista de modulos que se podran cargar.
     */
    public List<modulo> listarModulosSistema(String tipoUsuario){
        EntityManager em = emf.createEntityManager();
        Query qry;

        if (tipoUsuario.equals("S")) {
            qry = em.createQuery("SELECT m FROM modulo as m");
        }else{
            qry = em.createQuery("SELECT m FROM modulo as m WHERE m.tipo = 'U'");
        }
        return (List<modulo>) qry.getResultList();
    }
}
