package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.usuario;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Created by Rostan on 22/08/2017.
 */
@ManagedBean
@ViewScoped
public class usuarioBean {

    private usuario usuario = new usuario();

    //    METODOS
    public String usuarioLogin() {
        String res = "";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;

        usuarioDAO usuarioDAO = new usuarioDAO();
        if (usuarioDAO.validaUsuario(this.usuario) != null) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", usuario.getId());
            res = "home.xhtml";
        }else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Inicio", "Usuario o contraseña incorrecta.");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);

        return res;
    }

    //    GETTER Y SETTER
    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(es.desarrollo.hibernate.entities.usuario usuario) {
        this.usuario = usuario;
    }
}
