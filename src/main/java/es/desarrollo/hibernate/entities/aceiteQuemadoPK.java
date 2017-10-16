package es.desarrollo.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class aceiteQuemadoPK implements Serializable{

    @Column(length = 13)
    private String emp_ruc;

    @Column(length = 2)
    private Integer acq_mes;

//    CONSTRUCTOR
    public aceiteQuemadoPK() {
    }

    public aceiteQuemadoPK(String emp_ruc, Integer acq_mes) {
        this.emp_ruc = emp_ruc;
        this.acq_mes = acq_mes;
    }

//    METODOS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof aceiteQuemadoPK)) return false;

        aceiteQuemadoPK that = (aceiteQuemadoPK) o;

        if (!emp_ruc.equals(that.emp_ruc)) return false;
        return acq_mes.equals(that.acq_mes);
    }

    @Override
    public int hashCode() {
        int result = emp_ruc.hashCode();
        result = 31 * result + acq_mes.hashCode();
        return result;
    }
}
