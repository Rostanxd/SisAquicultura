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

    private List<modulo> modulos = new ArrayList<>();

    private String btnAccion = "";

    @PostConstruct
    public void init(){
        moduloDAO moduloDAO = new moduloDAO();
        this.modulos = moduloDAO.listarModulosSistema();
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

    public List<es.desarrollo.hibernate.entities.modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<es.desarrollo.hibernate.entities.modulo> modulos) {
        this.modulos = modulos;
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
    }
}
