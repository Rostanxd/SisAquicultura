package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acu_empresas_usu")
@IdClass(empresaUsuarioPK.class)
public class empresaUsuario {

    @Id
    @Column(length = 13)
    private String emp_ruc;

    @Id
    @Column(length = 10)
    private String usr_codigo;

    @Column(name = "emp_usr_estado")
    private String estado;

    @Column(name = "emp_usr_usr_creacion")
    private String usuarioCreacion;

    @Column(name = "emp_usr_fec_creacion")
    private Date fechaCreacion;

    @Column(name = "emp_usr_usr_modificacion")
    private String usuarioModificacion;

    @Column(name = "emp_usr_fec_modificacion")
    private Date fechaModificacion;

    //    RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_ruc", updatable = false, insertable = false,
            referencedColumnName = "emp_ruc")
    private empresa empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_codigo", updatable = false, insertable = false,
            referencedColumnName = "usr_codigo")
    private usuario usuario;

//    CONSTRUCTOR
    public empresaUsuario() {
    }

//    GETTER Y SETTERS
    public String getEmp_ruc() {
        return emp_ruc;
    }

    public void setEmp_ruc(String emp_ruc) {
        this.emp_ruc = emp_ruc;
    }

    public String getUsr_codigo() {
        return usr_codigo;
    }

    public void setUsr_codigo(String usr_codigo) {
        this.usr_codigo = usr_codigo;
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

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
