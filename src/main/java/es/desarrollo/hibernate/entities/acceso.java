package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Rostan on 23/08/2017.
 */
@Entity
@Table(name = "sis_accesos")
public class acceso {

    @Id
    @Column(name = "acc_codigo", length = 2)
    private String id;

    @Column(name = "acc_nombre", length = 25)
    private String nombre;

    @Column(name = "acc_usr_creacion", length = 10)
    private String usuarioCreacion;

    @Column(name = "acc_fec_creacion")
    private Date fechaCreacion;

    @Column(name = "acc_usr_modificacion", length = 10)
    private String usuarioModificacion;

    @Column(name = "acc_fec_modificacion")
    private Date fechaModificacion;

//    RELACIONES
    @OneToMany(mappedBy = "acceso", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<accesoDetalle> detalles;

//    CONSTRUCTOR
    public acceso() {}

//    METODOS
    @Override
    public String toString() {
        return "acceso{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
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

    public Set<accesoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<accesoDetalle> detalles) {
        this.detalles = detalles;
    }

}
