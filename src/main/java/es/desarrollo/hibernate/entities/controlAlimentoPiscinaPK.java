package es.desarrollo.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class controlAlimentoPiscinaPK implements Serializable{

    @Column(length = 13)
    private String emp_ruc;

    @Column(length = 2)
    private Integer acp_mes;

    public controlAlimentoPiscinaPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof controlAlimentoPiscinaPK)) return false;

        controlAlimentoPiscinaPK that = (controlAlimentoPiscinaPK) o;

        if (!emp_ruc.equals(that.emp_ruc)) return false;
        return acp_mes.equals(that.acp_mes);
    }

    @Override
    public int hashCode() {
        int result = emp_ruc.hashCode();
        result = 31 * result + acp_mes.hashCode();
        return result;
    }
}
