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

    private aceiteQuemado aqUpd = new aceiteQuemado();

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
        this.empresasUsuario();
        this.cargarMeses();
        this.cargarEstados();
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

    private void empresasUsuario(){
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.listEmpresas = usuarioDAO.listarEmpresasUsr(this.usuario);
    }

    private void cargarMeses(){
        listMeses = new HashMap<Integer, String>();
        listMeses.put(1, "Enero");
        listMeses.put(2,"Febrero");
        listMeses.put(3,"Marzo");
        listMeses.put(4,"Abril");
        listMeses.put(5,"Mayo");
        listMeses.put(6,"Junio");
        listMeses.put(7,"Julio");
        listMeses.put(8,"Agosto");
        listMeses.put(9,"Septiembre");
        listMeses.put(10,"Octubre");
        listMeses.put(11,"Noviembre");
        listMeses.put(12,"Diciembre");
    }

    private void cargarEstados(){
        listEstados = new HashMap<String, String>();
        listEstados.put("E", "Pendiente");
        listEstados.put("A", "Activo");
        listEstados.put("R", "Revisado");
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                if (!this.validaMesEmpresa()){
                    this.ingresarRegistro();
                    this.limpiar();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Listo!", "Registro ingresado con éxito."));
//                    RequestContext.getCurrentInstance().execute("dialogWidgetVar.hide()");
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Mes ya registrado."));
                }
                break;
            case "Actualizar":
                this.actualizarRegistro();
                this.limpiar();
                break;
        }
    }

    private void limpiar() {
        this.setMes("");
        this.setEstado("Pendiente");

        this.aqUpd.setEmp_ruc("");
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

        System.out.println(this.aqUpd.toString());

        aceiteQuemadoDAO aceiteQuemadoDAO = new aceiteQuemadoDAO();
        aceiteQuemadoDAO.registrar(aqUpd, this.usuario.getId());

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
        switch (this.btnAccion){
            case "Ingresar":
                this.limpiar();
                break;
            case "Actualizar":
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
