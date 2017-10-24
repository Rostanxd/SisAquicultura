package es.desarrollo.hibernate.beans;

import es.desarrollo.excels.generaExcelControlAlimentoPiscina;
import es.desarrollo.hibernate.dao.controlAlimentoPiscinaDAO;
import es.desarrollo.hibernate.dao.usuarioDAO;
import es.desarrollo.hibernate.entities.controlAlimentoPiscina;
import es.desarrollo.hibernate.entities.empresa;
import es.desarrollo.hibernate.entities.usuario;
import es.desarrollo.servicio.Utils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class controlAlimentoPiscinaBean {

    private controlAlimentoPiscina cap = new controlAlimentoPiscina();

    private controlAlimentoPiscina capUpd = new controlAlimentoPiscina("E");

    private List<controlAlimentoPiscina> listCap = new ArrayList<>();

    private String btnAccion = "";

    private usuario usuario = new usuario();

    private Map<Integer, String> listMeses;

    private Map<String, String> listEstados;

    private List<empresa> listEmpresas = new ArrayList<>();

    private String estado;

    private String mes;

    @PostConstruct
    public void init(){
//        Usuario de Sesion
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        usuarioDAO usuarioDAO = new usuarioDAO();
        this.usuario = usuarioDAO.buscaUsuarioId((String) sessionMap.get("usuario"));

        this.listarControlAlimentoPiscina();
        this.listEmpresas = usuarioDAO.listarEmpresasUsr(this.usuario);
        this.listMeses = Utils.listarMeses();
        this.listEstados = Utils.listarEstado();
    }

    private void listarControlAlimentoPiscina(){
        controlAlimentoPiscinaDAO capDao = new controlAlimentoPiscinaDAO();
        this.listCap = capDao.listar(this.usuario);
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

    private void limpiar() {
        this.setMes("");
        this.setEstado("Pendiente");

        this.capUpd.setEstado("E");
        this.capUpd.setEmp_ruc("");
        this.capUpd.setFichaPiscina("");
        this.capUpd.setFichaHectareas("");
        this.capUpd.setFichaCorridas("");
        this.capUpd.setFichaTipoSiembra("");
        this.capUpd.setFichaProveedorLarba("");
        this.capUpd.setFichaOrigenNauplio("");
        this.capUpd.setFichaDensidad("");
        this.capUpd.setFechaIngreso(Calendar.getInstance().getTime());
        this.capUpd.setFichaDiasCultivo("");
        this.capUpd.setFichaPesoCamaron("");
        this.capUpd.setFichaTiposBalanceado("");
        this.capUpd.setFichaDosisLibras("");
        this.capUpd.setFichaOtrosInsumosCalCarbonato("");
        this.capUpd.setFichaOtrosInsBacteria("");
        this.capUpd.setFichaOtrosInsDesparacitantes("");
        this.capUpd.setFichaOtrosInsVitamina("");
        this.capUpd.setFichaObservacion("");
    }

    public void operar(){
        switch (btnAccion){
            case "Ingresar":
                if (!this.validaMesEmpresa()){
                    this.ingresarRegistro();
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Listo!", "Registro ingresado con éxito."));
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Mes ya registrado."));
                }
                break;
            case "Revisar":
                this.actualizarRegistro();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Listo!", "Registro actualizado exitosamente!."));
                break;
        }
    }

    private void actualizarRegistro() {
        controlAlimentoPiscinaDAO capDAO = new controlAlimentoPiscinaDAO();
        capUpd.setEstado("R");
        capDAO.actualizar(capUpd, this.usuario.getId());
    }

    private void ingresarRegistro() {
        for (Integer key : listMeses.keySet()){
            if (listMeses.get(key).equals(this.mes)){
                this.capUpd.setAcp_mes(key);
            }
        }

        for (String key : listEstados.keySet()){
            if (listEstados.get(key).equals(this.estado)){
                this.capUpd.setEstado(key);
            }
        }
        controlAlimentoPiscinaDAO capDAO = new controlAlimentoPiscinaDAO();
        capDAO.registrar(capUpd, this.usuario.getId());

        this.listCap.clear();
        this.listarControlAlimentoPiscina();
    }

    private boolean validaMesEmpresa() {
        controlAlimentoPiscinaDAO capDAO = new controlAlimentoPiscinaDAO();
        return capDAO.validaMesEmpresa(this.capUpd.getEmpresa(), Utils.mesInt(this.mes));
    }

    public boolean setDiabledElemento(String elemento){
        boolean disabled = true;
        if (this.usuario.getAcceso().getId().equals("01") && !elemento.equals("observacion")
                && capUpd.getEstado().equals("E")){
            disabled = false;
        }else if (this.usuario.getAcceso().getId().equals("02") && elemento.equals("observacion")
                && capUpd.getEstado().equals("A")){
            disabled = false;
        }
        return disabled;
    }

    public boolean setDisabledBtnAccion() {
        boolean estado;
        estado = this.btnAccion.equals("Ingresar") && this.usuario.getAcceso().getId().equals("01") ||
                this.btnAccion.equals("Revisar") && this.usuario.getAcceso().getId().equals("02");
        return !estado;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            header.getCell(i).setCellStyle(cellStyle);
        }
    }

    public void generarExcel(){
        generaExcelControlAlimentoPiscina.generaExcel(this.capUpd);
    }

//    GETTER Y SETTER
    public controlAlimentoPiscina getCap() {
        return cap;
    }

    public void setCap(controlAlimentoPiscina cap) {
        this.cap = cap;
    }

    public controlAlimentoPiscina getCapUpd() {
        return capUpd;
    }

    public void setCapUpd(controlAlimentoPiscina capUpd) {
        this.capUpd = capUpd;
    }

    public List<controlAlimentoPiscina> getListCap() {
        return listCap;
    }

    public void setListCap(List<controlAlimentoPiscina> listCap) {
        this.listCap = listCap;
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
                this.capUpd = this.cap;
                this.capUpd.setEmpresa(this.cap.getEmpresa());
                this.mes = Utils.mesString(this.cap.getAcp_mes());
                this.estado= Utils.estadoString(this.cap.getEstado());
                break;
            case "Ver":
                this.capUpd = this.cap;
                this.mes = Utils.mesString(this.cap.getAcp_mes());
                this.estado= Utils.estadoString(this.cap.getEstado());
                break;
        }
    }

    public es.desarrollo.hibernate.entities.usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(es.desarrollo.hibernate.entities.usuario usuario) {
        this.usuario = usuario;
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

    public List<empresa> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<empresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

}
