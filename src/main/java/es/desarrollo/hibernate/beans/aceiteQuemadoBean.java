package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.dao.aceiteQuemadoDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.aceiteQuemado;
import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.entities.usuario;

import javax.annotation.PostConstruct;
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

    private Map<Integer, String> meses;

    private Map<String, String> estados;

    private Integer mes;

    @PostConstruct
    public void init(){
//        Usuario de Sesion
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuario = usuarioDAO.buscaUsuarioId((String) sessionMap.get("usuario"));
        this.empresasUsuario();
        this.cargarMeses();
        this.cargarEstados();
    }

//    METODOS
    private void listarAceiteQuemedo(){
        aceiteQuemadoDAO aqd = new aceiteQuemadoDAO();
        this.listAq = aqd.listar();
    }

    private void empresasUsuario(){
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.listEmpresas = usuarioDAO.listarEmpresasUsr(this.usuario);
    }

    private void cargarMeses(){
        meses = new HashMap<Integer, String>();
        meses.put(1, "Enero");
        meses.put(2,"Febrero");
        meses.put(3,"Marzo");
        meses.put(4,"Abril");
        meses.put(5,"Mayo");
        meses.put(6,"Junio");
        meses.put(7,"Julio");
        meses.put(8,"Agosto");
        meses.put(9,"Septiembre");
        meses.put(10,"Octubre");
        meses.put(11,"Noviembre");
        meses.put(12,"Diciembre");
    }

    private void cargarEstados(){
        estados = new HashMap<String, String>();
        estados.put("P", "Pendiente");
        estados.put("A", "Activo");
        estados.put("R", "Revisado");
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

    public Map<Integer, String> getMeses() {
        return meses;
    }

    public void setMeses(Map<Integer, String> meses) {
        this.meses = meses;
    }

    public Map<String, String> getEstados() {
        return estados;
    }

    public void setEstados(Map<String, String> estados) {
        this.estados = estados;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }
}
