package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rostan on 22/08/2017.
 */
@Entity
@Table(name = "sis_usuarios")
public class usuario {

    @Id
    @Column(name = "usr_codigo", length = 10)
    private String id;

    @Column(name = "usr_clave", length = 25)
    private String clave;

    @Column(name = "usr_estado", length = 1)
    private String estado;

    @Column(name = "usr_usr_creacion", length = 10)
    private String usuarioCreacion;

    @Column(name = "usr_fec_creacion")
    private Date fechaCreacion;

    @Column(name = "usr_usr_modificacion", length = 10)
    private String usrModificacion;

    @Column(name = "usr_fec_modificacion")
    private Date fechaModificacion;

    @OneToOne
    @JoinColumn(name = "acc_codigo")
    private acceso acceso;

    //    CONSTRUCTOR
    public usuario() {
    }

    public usuario(String id, String clave, acceso acceso) {
        this.id = id;
        this.clave = clave;
        this.acceso = acceso;
    }

    //    METODOS
    @Override
    public String toString() {
        return "usuario{" +
                "id='" + id + '\'' +
                ", clave='" + clave + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usrModificacion='" + usrModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

    //    GETTER Y SETTER
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsrModificacion() {
        return usrModificacion;
    }

    public void setUsrModificacion(String usrModificacion) {
        this.usrModificacion = usrModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public es.desarrollo.hibernate.entities.acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(es.desarrollo.hibernate.entities.acceso acceso) {
        this.acceso = acceso;
    }
}
