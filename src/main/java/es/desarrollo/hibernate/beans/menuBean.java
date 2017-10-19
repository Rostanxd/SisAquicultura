package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.accesoDAO;
import es.desarrollo.hibernate.dao.moduloDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.accesoDetalle;
import es.desarrollo.hibernate.entities.modulo;
import es.desarrollo.hibernate.entities.usuario;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class menuBean {

    private MenuModel model;
    private usuario usuario;
    private String idUsuario;
    private List<modulo> modulos = new ArrayList<>();
    private List<accesoDetalle> accesoDetalles = new ArrayList<>();

    @PostConstruct
    public void init(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        this.idUsuario = (String) sessionMap.get("usuario");

//        Genero la entidad de usuario
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuario = usuarioDAO.buscaUsuarioId(idUsuario);

//        Genero la lista de Accesos del usuario
        accesoDAO accesoDAO = new accesoDAO();
        accesoDetalles = accesoDAO.accesosUsuario(this.usuario);

//        Genero la lista de modulos del usuario, por el tipo de usuario.
        moduloDAO moduloDAO = new moduloDAO();
        this.modulos = moduloDAO.listarModulosAcceso(this.usuario);

        model = new DefaultMenuModel();
        for (modulo m : this.modulos){
            DefaultSubMenu submenu = new DefaultSubMenu(m.getNombre());

            for (accesoDetalle ad : accesoDetalles){
                if (ad.getPrograma().getModulo().getId().equals(m.getId())){
                    DefaultMenuItem item = new DefaultMenuItem(ad.getPrograma().getNombre());
                    item.setUrl(ad.getPrograma().getPagina());
                    item.setIcon("ui-icon-home");
                    submenu.addElement(item);
                }
            }

            model.addElement(submenu);
        }

        /*
        model = new DefaultMenuModel();

        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");

        //First submenu
        DefaultMenuItem item = new DefaultMenuItem("External");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        firstSubmenu.addElement(item);

        model.addElement(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");

        item = new DefaultMenuItem("Save");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{menuBean.save}");
        item.setUpdate("messages");
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Delete");
        item.setIcon("ui-icon-close");
        item.setCommand("#{menuBean.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Redirect");
        item.setIcon("ui-icon-search");
        item.setCommand("#{menuBean.update}");
        secondSubmenu.addElement(item);

        model.addElement(secondSubmenu);
        */
    }

    public MenuModel getModel() {
        return model;
    }

    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
