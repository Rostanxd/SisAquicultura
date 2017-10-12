package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sis_accesos_det")
@IdClass(accesoPK.class)
public class accesoDetalle implements Serializable{

    @Id
    @Column(length = 2)
    private String acc_codigo;

    @Id
    @Column(length = 3)
    private String prg_codigo;

    @Column(name = "acc_prg_ejecuta")
    private Boolean ejecutar;

    @Column(name = "acc_prg_nuevo")
    private Boolean nuevo;

    @Column(name = "acc_prg_modificar")
    private Boolean modificar;

    @Column(name = "acc_prg_anular")
    private Boolean anular;

    @Column(name = "acc_prg_imprimir")
    private Boolean imprimir;

//    RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_codigo", updatable = false, insertable = false,
            referencedColumnName = "acc_codigo")
    private acceso acceso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prg_codigo", updatable = false, insertable = false,
            referencedColumnName = "prg_codigo")
    private programa programa;

//    CONSTRUCTOR
    public accesoDetalle() {

    }

//    GETTER Y SETTER
    public String getAcc_codigo() {
        return acc_codigo;
    }

    public void setAcc_codigo(String acc_codigo) {
        this.acc_codigo = acc_codigo;
    }

    public String getPrg_codigo() {
        return prg_codigo;
    }

    public void setPrg_codigo(String prg_codigo) {
        this.prg_codigo = prg_codigo;
    }

    public Boolean getEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(Boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Boolean getModificar() {
        return modificar;
    }

    public void setModificar(Boolean modificar) {
        this.modificar = modificar;
    }

    public Boolean getAnular() {
        return anular;
    }

    public void setAnular(Boolean anular) {
        this.anular = anular;
    }

    public Boolean getImprimir() {
        return imprimir;
    }

    public void setImprimir(Boolean imprimir) {
        this.imprimir = imprimir;
    }

    public es.desarrollo.hibernate.entities.acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(es.desarrollo.hibernate.entities.acceso acceso) {
        this.acceso = acceso;
    }

    public es.desarrollo.hibernate.entities.programa getPrograma() {
        return programa;
    }

    public void setPrograma(es.desarrollo.hibernate.entities.programa programa) {
        this.programa = programa;
    }
}
