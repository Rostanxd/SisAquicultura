package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.aceiteQuemadoDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.aceiteQuemado;
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
public class aceiteQuemadoBean {

    private aceiteQuemado aq = new aceiteQuemado();

    private aceiteQuemado aqUpd = new aceiteQuemado();

    private List<aceiteQuemado> listAq = new ArrayList<>();

    private String btnAccion = "";

    private usuario usuario = new usuario();

    @PostConstruct
    public void init(){
//        Usuario de Sesion
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuario = usuarioDAO.buscaUsuarioId((String) sessionMap.get("usuario"));
    }

//    METODOS
    private void listarAceiteQuemedo(){
        aceiteQuemadoDAO aqd = new aceiteQuemadoDAO();
        this.listAq = aqd.listar();
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                this.ingresarRegistro();
                this.limpiar();
                break;
            case "Actualizar":
                this.actualizarRegistro();
                this.limpiar();
                break;
        }
    }

    private void limpiar() {
        this.aqUpd.setEmp_ruc("");
        this.aqUpd.setAcq_mes(0);
        this.aqUpd.setEstado("E");
        this.aqUpd.setFichaDescripcion("");
        this.aqUpd.setFichaObservacion("");
        this.aqUpd.setFechaIngreso(Calendar.getInstance().getTime());

    }

    private void actualizarRegistro() {
        aceiteQuemadoDAO aqDAO = new aceiteQuemadoDAO();
        aqDAO.actualizar(aqUpd, this.usuario.getId());

        this.listAq.clear();
        this.listarAceiteQuemedo();
    }

    private void ingresarRegistro() {
        this.listAq.clear();
        this.listarAceiteQuemedo();
    }

    //    GETTER Y SETTERS
    public aceiteQuemado getAq() {
        return aq;
    }

    public void setAq(aceiteQuemado aq) {
        this.aq = aq;
    }

    public aceiteQuemado getAqUpd() {
        return aqUpd;
    }

    public void setAqUpd(aceiteQuemado aqUpd) {
        this.aqUpd = aqUpd;
    }

    public List<aceiteQuemado> getListAq() {
        return listAq;
    }

    public void setListAq(List<aceiteQuemado> listAq) {
        this.listAq = listAq;
    }

    public String getBtnAccion() {
        return btnAccion;
    }

    public void setBtnAccion(String btnAccion) {
        this.btnAccion = btnAccion;
    }

    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }
}
