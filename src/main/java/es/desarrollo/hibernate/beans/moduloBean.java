package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.moduloDAO;
import es.desarrollo.hibernate.entities.modulo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class moduloBean {

    private modulo modulo = new modulo();

    private modulo moduloSelected = new modulo();

    private List<modulo> listModulos = new ArrayList<>();

    private String btnAccion = "";

    @PostConstruct
    public void init(){
        this.listarModulos();
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                this.ingresarModulo();
                this.limpiar();
                break;
            case "Actualizar":
                this.actualizarModulo();
                this.limpiar();
                break;
        }
    }

    private void actualizarModulo() {
        moduloDAO moduloDAO = new moduloDAO();
        moduloDAO.actualizar(moduloSelected);

        this.listModulos.clear();
        this.listarModulos();
    }

    private void ingresarModulo() {
        moduloDAO moduloDAO = new moduloDAO();
        moduloDAO.registrar(moduloSelected);

        this.listModulos.clear();
        this.listarModulos();
    }

    private void listarModulos(){
        moduloDAO moduloDAO = new moduloDAO();
        this.listModulos = moduloDAO.listar();
    }

    //    GETTER Y SETTER
    public es.desarrollo.hibernate.entities.modulo getModulo() {
        return modulo;
    }

    public void setModulo(es.desarrollo.hibernate.entities.modulo modulo) {
        this.modulo = modulo;
    }

    public es.desarrollo.hibernate.entities.modulo getModuloSelected() {
        return moduloSelected;
    }

    public void setModuloSelected(es.desarrollo.hibernate.entities.modulo moduloSelected) {
        this.moduloSelected = moduloSelected;
    }

    public List<es.desarrollo.hibernate.entities.modulo> getListModulos() {
        return listModulos;
    }

    public void setListModulos(List<es.desarrollo.hibernate.entities.modulo> listModulos) {
        this.listModulos = listModulos;
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
                this.moduloSelected = this.modulo;
                break;
        }
    }

    private void limpiar() {
        this.moduloSelected.setId("");
        this.moduloSelected.setNombre("");
        this.moduloSelected.setTipo("");
    }
}
