package es.desarrollo.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Rostan on 22/08/2017.
 */
@Entity
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
    private String usrModifica;

    @Column(name = "usr_fec_modificacion")
    private Date fechaModificacion;

    //    CONSTRUCTOR
    public usuario() {
    }

    public usuario(String id, String clave) {
        this.id = id;
        this.clave = clave;
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

    public String getUsrModifica() {
        return usrModifica;
    }

    public void setUsrModifica(String usrModifica) {
        this.usrModifica = usrModifica;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
