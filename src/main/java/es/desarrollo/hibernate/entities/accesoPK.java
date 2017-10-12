package es.desarrollo.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class accesoPK implements Serializable{

    @Column(length = 2)
    private String acc_codigo;

    @Column(length = 3)
    private String prg_codigo;

//    CONSTRUCTOR
    public accesoPK() {
    }

    public accesoPK(String acc_codigo, String prg_codigo) {
        this.acc_codigo = acc_codigo;
        this.prg_codigo = prg_codigo;
    }

//    METODOS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        accesoPK accesoPK = (accesoPK) o;

        if (!acc_codigo.equals(accesoPK.acc_codigo)) return false;
        return prg_codigo.equals(accesoPK.prg_codigo);
    }

    @Override
    public int hashCode() {
        int result = acc_codigo.hashCode();
        result = 31 * result + prg_codigo.hashCode();
        return result;
    }
}
