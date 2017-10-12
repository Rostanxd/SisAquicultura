package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sis_programas")
public class programa {

    @Id
    @Column(name = "prg_codigo", length = 3)
    private String id;

    @Column(name = "prg_nombre", length = 25)
    private String nombre;

    @Column(name = "prg_estado", length = 1)
    private String estado;

    @Column(name = "prg_usr_creacion", length = 10)
    private String usuarioCreacion;

    @Column(name = "prg_fec_creacion")
    private Date fechaCreacion;

    @Column(name = "prg_usr_modificacion", length = 10)
    private String usuarioModificacion;

    @Column(name = "prg_fec_modificacion", length = 10)
    private Date fechaModificacion;

//    RELACIONES
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_codigo", updatable = false, insertable = false,
            referencedColumnName = "mod_codigo")
    private modulo modulo;

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL)
    private Set<accesoDetalle> accesoDetalles;

//    CONSTRUCTOR
    public programa() {
    }

//    METODOS
    @Override
    public String toString() {
        return "programa{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
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

    public es.desarrollo.hibernate.entities.modulo getModulo() {
        return modulo;
    }
}
