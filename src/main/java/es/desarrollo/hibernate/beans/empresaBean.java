package es.desarrollo.hibernate.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import es.desarrollo.hibernate.dao.empresaDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.entities.usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class empresaBean {

    private empresa empresa = new empresa();

    private empresa empresaSelected = new empresa();

    private List<empresa> listEmpresas = new ArrayList<>();

    private usuario usuario = new usuario();

    private String btnAccion = "";

    @PostConstruct
    public void init(){
//        Usuario de Sesion
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuario = usuarioDAO.buscaUsuarioId((String) sessionMap.get("usuario"));

        this.listarEmpresas();
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                this.ingresarEmpresa();
                this.limpiar();
                break;
            case "Actualizar":
                this.actualizarEmpresa();
                this.limpiar();
                break;
        }
    }

    private void limpiar() {
        this.empresaSelected.setRuc("");
        this.empresaSelected.setNombre("");
        this.empresaSelected.setUsuarioCreacion(this.usuario.getId());
        this.empresaSelected.setUsuarioModificacion(this.usuario.getId());
        this.empresaSelected.setFechaCreacion(Calendar.getInstance().getTime());
        this.empresaSelected.setFechaModificacion(Calendar.getInstance().getTime());
    }

    private void actualizarEmpresa() {
        empresaDAO empresaDAO = new empresaDAO();
        empresaDAO.actualizar(empresaSelected, this.usuario.getId());
        this.listarEmpresas();
    }

    private void ingresarEmpresa() {
        empresaDAO empresaDAO = new empresaDAO();
        empresaDAO.registrar(empresaSelected, this.usuario.getId());
        this.listarEmpresas();
    }

    private void listarEmpresas(){
        this.listEmpresas.clear();
        empresaDAO empresaDAO = new empresaDAO();
        this.listEmpresas = empresaDAO.listar();
    }

//    GETTER Y SETTER
    public es.desarrollo.hibernate.entities.empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(es.desarrollo.hibernate.entities.empresa empresa) {
        this.empresa = empresa;
    }

    public es.desarrollo.hibernate.entities.empresa getEmpresaSelected() {
        return empresaSelected;
    }

    public void setEmpresaSelected(es.desarrollo.hibernate.entities.empresa empresaSelected) {
        this.empresaSelected = empresaSelected;
    }

    public List<es.desarrollo.hibernate.entities.empresa> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<es.desarrollo.hibernate.entities.empresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(es.desarrollo.hibernate.entities.usuario usuario) {
        this.usuario = usuario;
    }

    public String getBtnAccion() {
        return btnAccion;
    }

    public void setBtnAccion(String btnAccion) {
        this.btnAccion = btnAccion;
        switch (this.btnAccion){
            case "Ingresar":
                this.limpiar();
                break;
            case "Actualizar":
                this.empresaSelected = this.empresa;
                break;
        }
    }

}
