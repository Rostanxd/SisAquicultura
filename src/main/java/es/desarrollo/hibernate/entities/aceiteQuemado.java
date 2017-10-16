package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acu_aceite_quemado")
@IdClass(aceiteQuemadoPK.class)
public class aceiteQuemado {

    @Id
    @Column(length = 13)
    private String emp_ruc;

    @Id
    @Column(length = 2)
    private Integer acq_mes;

    @Column(name = "acq_fec_ingreso")
    private Date fechaIngreso;

    @Column(name = "acq_estado", length = 1)
    private String estado;

    @Column(name = "acq_descripcion", length = 100)
    private String fichaDescripcion;

    @Column(name = "acq_observacion", length = 100)
    private String fichaObservacion;

    @ManyToOne
    @JoinColumn(name = "emp_ruc", updatable = false, insertable = false,
        referencedColumnName = "emp_ruc")
    private empresa empresa;

//    CONSTRUCTOR
    public aceiteQuemado() {
    }

//    GETTER Y SETTER
    public String getEmp_ruc() {
        return emp_ruc;
    }

    public void setEmp_ruc(String emp_codigo) {
        this.emp_ruc = emp_codigo;
    }

    public Integer getAcq_mes() {
        return acq_mes;
    }

    public void setAcq_mes(Integer mes) {
        this.acq_mes = mes;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFichaDescripcion() {
        return fichaDescripcion;
    }

    public void setFichaDescripcion(String fichaDescripcion) {
        this.fichaDescripcion = fichaDescripcion;
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
}
