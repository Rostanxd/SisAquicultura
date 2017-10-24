package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.aceiteQuemadoDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.entities.usuario;
import es.desarrollo.servicio.Utils;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean
@ViewScoped
public class aceiteQuemadoBean {

    private aceiteQuemado aq = new aceiteQuemado();

    private aceiteQuemado aqUpd = new aceiteQuemado("E");

    private List<aceiteQuemado> listAq = new ArrayList<>();

    private String btnAccion = "";

    private usuario usuario = new usuario();

    private List<empresa> listEmpresas = new ArrayList<>();

    private Map<Integer, String> listMeses;

    private Map<String, String> listEstados;

    private String estado;

    private String mes;

    @PostConstruct
    public void init(){
//        Usuario de Sesion
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuario = usuarioDAO.buscaUsuarioId((String) sessionMap.get("usuario"));

        this.listarAceiteQuemedo();

        this.listEmpresas = usuarioDAO.listarEmpresasUsr(this.usuario);
        this.listMeses = Utils.listarMeses();
        this.listEstados = Utils.listarEstado();
    }

//    METODOS
    private boolean validaMesEmpresa(){
        aceiteQuemadoDAO aceiteQuemadoDAO = new aceiteQuemadoDAO();
        return aceiteQuemadoDAO.validaMesEmpresa(this.aqUpd.getEmpresa(), Utils.mesInt(this.mes));
    }

    private void listarAceiteQuemedo(){
        aceiteQuemadoDAO aqd = new aceiteQuemadoDAO();
        this.listAq = aqd.listar();
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                if (!this.validaMesEmpresa()){
                    this.ingresarRegistro();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Listo!", "Registro ingresado con éxito."));
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Mes ya registrado."));
                }
                break;
            case "Revisar":
                this.actualizarRegistro();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Listo!", "Registro actualizado exitosamente!."));
                break;
        }
    }

    private void limpiar() {
        this.setMes("");
        this.setEstado("Pendiente");

        this.aqUpd.setEstado("E");
        this.aqUpd.setEmp_ruc("");
        this.aqUpd.setFichaDescripcion("");
        this.aqUpd.setFichaObservacion("");
        this.aqUpd.setFechaIngreso(Calendar.getInstance().getTime());
    }

    private void actualizarRegistro() {
        aceiteQuemadoDAO aqDAO = new aceiteQuemadoDAO();
        aqUpd.setEstado("R");
        aqDAO.actualizar(aqUpd, this.usuario.getId());

        this.listAq.clear();
        this.listarAceiteQuemedo();
    }

    private void ingresarRegistro() {
        for (Integer key : listMeses.keySet()){
            if (listMeses.get(key).equals(this.mes)){
                this.aqUpd.setAcq_mes(key);
            }
        }

        for (String key : listEstados.keySet()){
            if (listEstados.get(key).equals(this.estado)){
                this.aqUpd.setEstado(key);
            }
        }
        aceiteQuemadoDAO aceiteQuemadoDAO = new aceiteQuemadoDAO();
        aceiteQuemadoDAO.registrar(aqUpd, this.usuario.getId());

        this.listAq.clear();
        this.listarAceiteQuemedo();
    }

    public String setDialogAccion(String accion){
        switch (accion){
            case "INS":
                this.setBtnAccion("Ingresar");
                break;
            case "UPD":
                if (this.usuario.getAcceso().getId().equals("02")){
                    this.setBtnAccion("Revisar");
                }else{
                    this.setBtnAccion("Ver");
                }
                break;
        }
        return btnAccion;
    }

    public String setAdminAccion(){
        String accion = "";
        if (this.usuario.getAcceso().getId().equals("02")){
            accion = "Revisar";
        }else{
            accion = "Ver";
        }
        return accion;
    }

    public boolean setDisabledBtnAccion() {
        boolean estado;
        estado = this.btnAccion.equals("Ingresar") && this.usuario.getAcceso().getId().equals("01") ||
                this.btnAccion.equals("Revisar") && this.usuario.getAcceso().getId().equals("02");
        return !estado;
    }

    public boolean setDiabledElemento(String elemento){
        boolean disabled = true;
        if (this.usuario.getAcceso().getId().equals("01") && elemento.equals("descripcion") && aqUpd.getEstado().equals("E")){
            disabled = false;
        }else if (this.usuario.getAcceso().getId().equals("02") && elemento.equals("observacion") && aqUpd.getEstado().equals("A")){
            disabled = false;
        }
        return disabled;
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
        switch (this.btnAccion){
            case "Ingresar":
                this.limpiar();
                break;
            case "Revisar":
                this.aqUpd = this.aq;
                this.aqUpd.setEmpresa(this.aq.getEmpresa());
                this.mes = Utils.mesString(this.aq.getAcq_mes());
                this.estado= Utils.estadoString(this.aq.getEstado());
                break;
            case "Ver":
                this.aqUpd = this.aq;
                this.mes = Utils.mesString(this.aq.getAcq_mes());
                this.estado= Utils.estadoString(this.aq.getEstado());
                break;
        }
    }

    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }

    public List<empresa> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<empresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public Map<Integer, String> getListMeses() {
        return listMeses;
    }

    public void setListMeses(Map<Integer, String> listMeses) {
        this.listMeses = listMeses;
    }

    public Map<String, String> getListEstados() {
        return listEstados;
    }

    public void setListEstados(Map<String, String> listEstados) {
        this.listEstados = listEstados;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
