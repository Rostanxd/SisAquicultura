package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.accesoDAO;
import es.desarrollo.hibernate.entities.acceso;
import es.desarrollo.hibernate.entities.accesoDetalle;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class accesosBean {

    private acceso acceso = new acceso();

    private acceso accesoSelected = new acceso();

    private List<acceso> listAccesos = new ArrayList<>();

    private String btnAccion = "";

    @PostConstruct
    public void init(){
        this.listarAccesos();
    }

//    METODOS
    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                this.ingresarAcceso();
                this.limpiar();
                break;
            case "Actualizar":
                this.actualizarAcceso();
                this.limpiar();
                break;
        }
    }

    private void limpiar() {
        this.accesoSelected.setId("");
        this.accesoSelected.setNombre("");
        this.accesoSelected.setDetalles(null);
    }

    private void actualizarAcceso() {
        accesoDAO accesoDAO = new accesoDAO();
        accesoDAO.actualizar(this.accesoSelected);

        this.listAccesos.clear();
        this.listarAccesos();
    }

    private void ingresarAcceso() {
        FacesMessage msg = new FacesMessage("Opcion inválida", "Lo sentimo!.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        this.listAccesos.clear();
        this.listarAccesos();
    }

    private void listarAccesos(){
        accesoDAO accesoDAO = new accesoDAO();
        this.listAccesos = accesoDAO.listar();
    }

    public void editarAccProgama(RowEditEvent event){
        FacesMessage msg = new FacesMessage("Acceso editado", ((accesoDetalle) event.getObject()).getPrograma().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void cancelarAccProgama(RowEditEvent event){
        FacesMessage msg = new FacesMessage("No hay cambios.", ((accesoDetalle) event.getObject()).getPrograma().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    GETTERS Y SETTERS
    public es.desarrollo.hibernate.entities.acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(es.desarrollo.hibernate.entities.acceso acceso) {
        this.acceso = acceso;
    }

    public es.desarrollo.hibernate.entities.acceso getAccesoSelected() {
        return accesoSelected;
    }

    public void setAccesoSelected(es.desarrollo.hibernate.entities.acceso accesoSelected) {
        this.accesoSelected = accesoSelected;
    }

    public List<es.desarrollo.hibernate.entities.acceso> getListAccesos() {
        return listAccesos;
    }

    public void setListAccesos(List<es.desarrollo.hibernate.entities.acceso> listAccesos) {
        this.listAccesos = listAccesos;
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
                this.accesoSelected = this.acceso;
                break;
        }
    }
}
