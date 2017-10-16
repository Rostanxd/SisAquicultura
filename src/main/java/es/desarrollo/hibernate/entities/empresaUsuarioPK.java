package es.desarrollo.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class empresaUsuarioPK implements Serializable {

    @Id
    @Column(length = 13)
    private String emp_ruc;

    @Id
    @Column(length = 10)
    private String usr_codigo;

    public empresaUsuarioPK() {
    }

    public empresaUsuarioPK(String emp_ruc, String usr_codigo) {
        this.emp_ruc = emp_ruc;
        this.usr_codigo = usr_codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof empresaUsuarioPK)) return false;

        empresaUsuarioPK that = (empresaUsuarioPK) o;

        if (!emp_ruc.equals(that.emp_ruc)) return false;
        return usr_codigo.equals(that.usr_codigo);
    }

    @Override
    public int hashCode() {
        int result = emp_ruc.hashCode();
        result = 31 * result + usr_codigo.hashCode();
        return result;
    }
}
