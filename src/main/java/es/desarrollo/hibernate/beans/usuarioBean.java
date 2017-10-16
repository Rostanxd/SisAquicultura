package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.accesoDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.acceso;
import es.desarrollo.hibernate.entities.usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class usuarioBean {

    private usuario usuarioSistema = new usuario();

    private usuario usuario = new usuario();

    private usuario usuarioSelected = new usuario();

    private List<usuario> lstUsuarios = new ArrayList<>();

    private String btnAccion = "";

    private String claveNueva = "";

    private String claveVerifica = "";

    private List<acceso> listAccesos = new ArrayList<>();

//    METODOS
    @PostConstruct
    public void init(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String idUsuario = (String) sessionMap.get("usuario");
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuarioSistema = usuarioDAO.buscaUsuarioId(idUsuario);

        this.lstUsuarios = usuarioDAO.listar();

        accesoDAO accesoDAO = new accesoDAO();
        this.listAccesos = accesoDAO.listar();
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                this.ingresarUsuario();
                this.limpiar();
                break;
            case "Actualizar":
                this.actualizarUsuario();
                this.limpiar();
                break;
        }
    }

    public void setBtnAccion(String btnAccion) {
        this.btnAccion = btnAccion;
        switch (this.btnAccion){
            case "Ingresar":
                this.limpiar();
                break;
            case "Actualizar":
                this.usuarioSelected = this.usuario;
                System.out.println(this.usuarioSelected.toString());
                break;
        }
    }

    private void limpiar(){
        this.claveNueva = "";
        this.claveVerifica = "";

        this.usuarioSelected.setId("");
        this.usuarioSelected.setClave("");
        this.usuarioSelected.setEstado("A");
        this.usuarioSelected.setFechaCreacion(Calendar.getInstance().getTime());
        this.usuarioSelected.setUsuarioCreacion(this.usuarioSistema.getId());
        this.usuarioSelected.setFechaModificacion(Calendar.getInstance().getTime());
        this.usuarioSelected.setUsuarioModificacion(this.usuarioSistema.getId());
        this.usuarioSelected.setAcceso(null);
    }

    private void ingresarUsuario(){
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.registar(usuarioSelected);

        this.lstUsuarios.clear();
        this.lstUsuarios = usuarioDAO.listar();
    }

    private void actualizarUsuario(){
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.actualizar(usuarioSelected);

        this.lstUsuarios.clear();
        this.lstUsuarios = usuarioDAO.listar();
    }

//    GETTERS Y SETTERS
    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(es.desarrollo.hibernate.entities.usuario usuario) {
        this.usuario = usuario;
    }

    public es.desarrollo.hibernate.entities.usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(es.desarrollo.hibernate.entities.usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public List<es.desarrollo.hibernate.entities.usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<es.desarrollo.hibernate.entities.usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public String getBtnAccion() {
        return btnAccion;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveVerifica() {
        return claveVerifica;
    }

    public void setClaveVerifica(String claveVerifica) {
        this.claveVerifica = claveVerifica;
    }

    public List<acceso> getListAccesos() {
        return listAccesos;
    }

    public void setListAccesos(List<acceso> listAccesos) {
        this.listAccesos = listAccesos;
    }
}
