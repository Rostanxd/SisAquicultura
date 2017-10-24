package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.usuario;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Map;

/**
 * Created by Rostan on 22/08/2017.
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class loginBean {

    private usuario usuario = new usuario();

    private usuario usuarioUpd = new usuario();

    //    METODOS
    public String usuarioLogin() {
        String res = "";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;

        usuarioDAO usuarioDAO = new usuarioDAO();
        if (usuarioDAO.validaUsuario(this.usuario) != null) {
            loggedIn = true;

            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", "Usuario.");

            this.usuarioUpd = usuarioDAO.buscaUsuarioId(this.usuario.getId());
            if (this.usuarioUpd.getAcceso().getId().equals("02")){
                res = "homeRevisor.xhtml";
            }else {
                res = "home.xhtml";
            }
        }else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Inicio", "Usuario o contraseña incorrecta.");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        if (loggedIn){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("usuario", this.usuario.getId());
        }

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
