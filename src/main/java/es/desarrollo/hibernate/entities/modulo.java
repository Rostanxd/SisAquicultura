package es.desarrollo.hibernate.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sis_modulos")
public class modulo {

    @Id
    @Column(name = "mod_codigo", length = 2)
    private String id;

    @Column(name = "mod_nombre", length = 25)
    private String nombre;

    @Column(name = "mod_tipo", length = 3)
    private String tipo;

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<programa> programas;

//    CONSTRUCTUR
    public modulo() {
    }

//    METODOS
    @Override
    public String toString() {
        return "modulo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
