package es.desarrollo.hibernate.beans;

import es.desarrollo.hibernate.entities.modulo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class accesosBean {

    private List<modulo> modulos = new ArrayList<>();

}
