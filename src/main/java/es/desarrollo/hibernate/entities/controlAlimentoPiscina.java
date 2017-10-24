package es.desarrollo.hibernate.entities;

import es.desarrollo.servicio.Utils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acu_control_alimento_piscina")
@IdClass(controlAlimentoPiscinaPK.class)
public class controlAlimentoPiscina {

    @Id
    @Column(length = 13)
    private String emp_ruc;

    @Id
    @Column(length = 2)
    private Integer acp_mes;

    @Column(name = "acp_piscina", length = 10)
    private String fichaPiscina;

    @Column(name = "acp_hectareas", length = 10)
    private String fichaHectareas;

    @Column(name = "acp_corridas", length = 10)
    private String fichaCorridas;

    @Column(name = "acp_tip_siembra", length = 100)
    private String fichaTipoSiembra;

    @Column(name = "acp_prv_larba", length = 100)
    private String fichaProveedorLarba;

    @Column(name = "acp_ori_nauplio", length = 100)
    private String fichaOrigenNauplio;

    @Column(name = "acp_densidad", length = 50)
    private String fichaDensidad;

    @Column(name = "acp_fec_ingreso")
    private Date fechaIngreso;

    @Column(name = "acp_estado", length = 1)
    private String estado;

    @Column(name = "acp_dia_cultivo", length = 10)
    private String fichaDiasCultivo;

    @Column(name = "acp_pes_camaron", length = 10)
    private String fichaPesoCamaron;

    @Column(name = "acp_tip_balanceado", length = 50)
    private String fichaTiposBalanceado;

    @Column(name = "acp_dos_libras", length = 10)
    private String fichaDosisLibras;

    @Column(name = "acp_otr_carbonato", length = 250)
    private String fichaOtrosInsumosCalCarbonato;

    @Column(name = "acp_otr_bacteria", length = 250)
    private String fichaOtrosInsBacteria;

    @Column(name = "acp_otr_desparacitantes", length = 250)
    private String fichaOtrosInsDesparacitantes;

    @Column(name = "acp_otr_victamina", length = 250)
    private String fichaOtrosInsVitamina;

    @Column(name = "acp_observacion", length = 250)
    private String fichaObservacion;

    @ManyToOne
    @JoinColumn(name = "emp_ruc", updatable = false, insertable = false,
            referencedColumnName = "emp_ruc")
    private empresa empresa;

//    CONSTRUCTOR
    public controlAlimentoPiscina() {
    }

    public controlAlimentoPiscina(String estado) {
        this.estado = estado;
    }

    //    METODOS
    @Override
    public String toString() {
        return "controlAlimentoPiscina{" +
                "emp_ruc='" + emp_ruc + '\'' +
                ", acp_mes=" + acp_mes +
                ", fichaPiscina='" + fichaPiscina + '\'' +
                ", fichaHectareas='" + fichaHectareas + '\'' +
                ", fichaHectareas='" + fichaHectareas + '\'' +
                ", fichaTipoSiembra='" + fichaTipoSiembra + '\'' +
                ", fichaProveedorLarba='" + fichaProveedorLarba + '\'' +
                ", fichaOrigenNauplio='" + fichaOrigenNauplio + '\'' +
                ", fichaDensidad='" + fichaDensidad + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", fichaDiasCultivo='" + fichaDiasCultivo + '\'' +
                ", fichaPesoCamaron='" + fichaPesoCamaron + '\'' +
                ", fichaTiposBalanceado='" + fichaTiposBalanceado + '\'' +
                ", fichaDosisLibras='" + fichaDosisLibras + '\'' +
                ", fichaOtrosInsumosCalCarbonato='" + fichaOtrosInsumosCalCarbonato + '\'' +
                ", fichaOtrosInsBacteria='" + fichaOtrosInsBacteria + '\'' +
                ", fichaOtrosInsDesparacitantes='" + fichaOtrosInsDesparacitantes + '\'' +
                ", fichaOtrosInsVictamina='" + fichaOtrosInsVitamina + '\'' +
                ", fichaObservacion='" + fichaObservacion + '\'' +
                ", empresa=" + empresa +
                '}';
    }

    public String fechaIngresoFormatted(){
        return Utils.dateFormat(this.fechaIngreso);
    }

    public String mesString(){
        return Utils.mesString(this.acp_mes);
    }

    public String estadoString(){
        return Utils.estadoString(this.estado);
    }

//    GETTER Y SETTERS
    public String getEmp_ruc() {
        return emp_ruc;
    }

    public void setEmp_ruc(String emp_ruc) {
        this.emp_ruc = emp_ruc;
    }

    public Integer getAcp_mes() {
        return acp_mes;
    }

    public void setAcp_mes(Integer acp_mes) {
        this.acp_mes = acp_mes;
    }

    public String getFichaPiscina() {
        return fichaPiscina;
    }

    public void setFichaPiscina(String fichaPiscina) {
        this.fichaPiscina = fichaPiscina;
    }

    public String getFichaHectareas() {
        return fichaHectareas;
    }

    public void setFichaHectareas(String fichaHectareas) {
        this.fichaHectareas = fichaHectareas;
    }

    public String getFichaTipoSiembra() {
        return fichaTipoSiembra;
    }

    public void setFichaTipoSiembra(String fichaTipoSiembra) {
        this.fichaTipoSiembra = fichaTipoSiembra;
    }

    public String getFichaProveedorLarba() {
        return fichaProveedorLarba;
    }

    public void setFichaProveedorLarba(String fichaProveedorLarba) {
        this.fichaProveedorLarba = fichaProveedorLarba;
    }

    public String getFichaOrigenNauplio() {
        return fichaOrigenNauplio;
    }

    public void setFichaOrigenNauplio(String fichaOrigenNauplio) {
        this.fichaOrigenNauplio = fichaOrigenNauplio;
    }

    public String getFichaDensidad() {
        return fichaDensidad;
    }

    public void setFichaDensidad(String fichaDensidad) {
        this.fichaDensidad = fichaDensidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFichaDiasCultivo() {
        return fichaDiasCultivo;
    }

    public void setFichaDiasCultivo(String fichaDiasCultivo) {
        this.fichaDiasCultivo = fichaDiasCultivo;
    }

    public String getFichaPesoCamaron() {
        return fichaPesoCamaron;
    }

    public void setFichaPesoCamaron(String fichaPesoCamaron) {
        this.fichaPesoCamaron = fichaPesoCamaron;
    }

    public String getFichaTiposBalanceado() {
        return fichaTiposBalanceado;
    }

    public void setFichaTiposBalanceado(String fichaTiposBalanceado) {
        this.fichaTiposBalanceado = fichaTiposBalanceado;
    }

    public String getFichaDosisLibras() {
        return fichaDosisLibras;
    }

    public void setFichaDosisLibras(String fichaDosisLibras) {
        this.fichaDosisLibras = fichaDosisLibras;
    }

    public String getFichaOtrosInsumosCalCarbonato() {
        return fichaOtrosInsumosCalCarbonato;
    }

    public void setFichaOtrosInsumosCalCarbonato(String fichaOtrosInsumosCalCarbonato) {
        this.fichaOtrosInsumosCalCarbonato = fichaOtrosInsumosCalCarbonato;
    }

    public String getFichaOtrosInsBacteria() {
        return fichaOtrosInsBacteria;
    }

    public void setFichaOtrosInsBacteria(String fichaOtrosInsBacteria) {
        this.fichaOtrosInsBacteria = fichaOtrosInsBacteria;
    }

    public String getFichaOtrosInsDesparacitantes() {
        return fichaOtrosInsDesparacitantes;
    }

    public void setFichaOtrosInsDesparacitantes(String fichaOtrosInsDesparacitantes) {
        this.fichaOtrosInsDesparacitantes = fichaOtrosInsDesparacitantes;
    }

    public String getFichaOtrosInsVitamina() {
        return fichaOtrosInsVitamina;
    }

    public void setFichaOtrosInsVitamina(String fichaOtrosInsVictamina) {
        this.fichaOtrosInsVitamina = fichaOtrosInsVictamina;
    }

    public String getFichaObservacion() {
        return fichaObservacion;
    }

    public void setFichaObservacion(String fichaObservacion) {
        this.fichaObservacion = fichaObservacion;
    }

    public es.desarrollo.hibernate.entities.empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(es.desarrollo.hibernate.entities.empresa empresa) {
        this.empresa = empresa;
    }

    public String getFichaCorridas() {
        return fichaCorridas;
    }

    public void setFichaCorridas(String fichaCorridas) {
        this.fichaCorridas = fichaCorridas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    EQUALS AND HASCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof controlAlimentoPiscina)) return false;

        controlAlimentoPiscina that = (controlAlimentoPiscina) o;

        if (!getEmp_ruc().equals(that.getEmp_ruc())) return false;
        if (!getAcp_mes().equals(that.getAcp_mes())) return false;
        if (!getFichaPiscina().equals(that.getFichaPiscina())) return false;
        if (!getFichaHectareas().equals(that.getFichaHectareas())) return false;
        if (!getFichaCorridas().equals(that.getFichaCorridas())) return false;
        if (!getFichaTipoSiembra().equals(that.getFichaTipoSiembra())) return false;
        if (!getFichaProveedorLarba().equals(that.getFichaProveedorLarba())) return false;
        if (!getFichaOrigenNauplio().equals(that.getFichaOrigenNauplio())) return false;
        if (!getFichaDensidad().equals(that.getFichaDensidad())) return false;
        if (!getFechaIngreso().equals(that.getFechaIngreso())) return false;
        if (!getEstado().equals(that.getEstado())) return false;
        if (!getFichaDiasCultivo().equals(that.getFichaDiasCultivo())) return false;
        if (!getFichaPesoCamaron().equals(that.getFichaPesoCamaron())) return false;
        if (!getFichaTiposBalanceado().equals(that.getFichaTiposBalanceado())) return false;
        if (!getFichaDosisLibras().equals(that.getFichaDosisLibras())) return false;
        if (!getFichaOtrosInsumosCalCarbonato().equals(that.getFichaOtrosInsumosCalCarbonato())) return false;
        if (!getFichaOtrosInsBacteria().equals(that.getFichaOtrosInsBacteria())) return false;
        if (!getFichaOtrosInsDesparacitantes().equals(that.getFichaOtrosInsDesparacitantes())) return false;
        if (!getFichaOtrosInsVitamina().equals(that.getFichaOtrosInsVitamina())) return false;
        if (!getFichaObservacion().equals(that.getFichaObservacion())) return false;
        return getEmpresa() != null ? getEmpresa().equals(that.getEmpresa()) : that.getEmpresa() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmp_ruc().hashCode();
        result = 31 * result + getAcp_mes().hashCode();
        result = 31 * result + getFichaPiscina().hashCode();
        result = 31 * result + getFichaHectareas().hashCode();
        result = 31 * result + getFichaCorridas().hashCode();
        result = 31 * result + getFichaTipoSiembra().hashCode();
        result = 31 * result + getFichaProveedorLarba().hashCode();
        result = 31 * result + getFichaOrigenNauplio().hashCode();
        result = 31 * result + getFichaDensidad().hashCode();
        result = 31 * result + getFechaIngreso().hashCode();
        result = 31 * result + getEstado().hashCode();
        result = 31 * result + getFichaDiasCultivo().hashCode();
        result = 31 * result + getFichaPesoCamaron().hashCode();
        result = 31 * result + getFichaTiposBalanceado().hashCode();
        result = 31 * result + getFichaDosisLibras().hashCode();
        result = 31 * result + getFichaOtrosInsumosCalCarbonato().hashCode();
        result = 31 * result + getFichaOtrosInsBacteria().hashCode();
        result = 31 * result + getFichaOtrosInsDesparacitantes().hashCode();
        result = 31 * result + getFichaOtrosInsVitamina().hashCode();
        result = 31 * result + getFichaObservacion().hashCode();
        return result;
    }
}
