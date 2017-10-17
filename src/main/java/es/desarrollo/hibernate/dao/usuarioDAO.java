package es.desarrollo.hibernate.dao;

import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.entities.usuario;
import es.desarrollo.hibernate.idao.IUsuarioDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rostan on 22/08/2017.
 */
public class usuarioDAO implements IUsuarioDAO{

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

    public List<empresa> listarEmpresasUsr(usuario usuario){
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT eu.empresa FROM empresaUsuario as eu " +
                "WHERE eu.usuario.id =:usrCodigo");
        qry.setParameter("usrCodigo", usuario.getId());
        return (List<empresa>) qry.getResultList();
    }

//    CRUD
    @Override
    public boolean registar(usuario usuario) {
        boolean registrado = false;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
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
    public boolean actualizar(usuario usuario) {
        boolean actualizado = false;
        EntityManager em = emf.createEntityManager();
        try{
            Query qry = em.createQuery("SELECT u FROM usuario AS u WHERE u.id = :id");
            qry.setParameter("id", usuario.getId());
            usuario u = (usuario) qry.getSingleResult();

            u.setAcceso(usuario.getAcceso());
            u.setClave(usuario.getClave());
            u.setEstado(usuario.getEstado());
            u.setFechaModificacion(Calendar.getInstance().getTime());
            u.setUsuarioModificacion(usuario.getUsuarioModificacion());

            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            actualizado = true;
        }catch (Exception e){
            em.getTransaction().rollback();
            e.getStackTrace();
        }finally {
            em.close();
        }
        return actualizado;
    }

    @Override
    public boolean eliminar(usuario usuario) {
        boolean eliminado = false;
        EntityManager em = emf.createEntityManager();
        try{
            Query qry = em.createQuery("SELECT u FROM usuario AS u WHERE u.id = :id");
            qry.setParameter("id", usuario.getId());
            usuario u = (usuario) qry.getSingleResult();
            u.setEstado("I");

            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.getStackTrace();
        }finally{
            em.close();
        }
        return eliminado;
    }

    @Override
    public List<usuario> listar() {
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("SELECT u FROM usuario as u");
        return (List<usuario>) qry.getResultList();
    }
}
