package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "acu_empresas")
public class empresa {

    @Id
    @Column(name = "emp_ruc",length = 13)
    private String ruc;

    @Column(name = "emp_nombre", length = 30)
    private String nombre;

    @Column(name = "usr_creacion", length = 10)
    private String usuarioCreacion;

    @Column(name = "fec_creacion")
    private Date fechaCreacion;

    @Column(name = "usr_modificacion", length = 10)
    private String usuarioModificacion;

    @Column(name = "fec_modificacion")
    private Date fechaModificacion;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<aceiteQuemado> listAceiteQuemado;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<empresaUsuario> listEmpresasUsuarios;

//    METODOS
    @Override
    public String toString() {
        return "empresa{" +
                "ruc='" + ruc + '\'' +
                ", nombre='" + nombre + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

    //    CONSTRUCTOR
    public empresa() {
    }

//    GETTER Y SETTER
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
