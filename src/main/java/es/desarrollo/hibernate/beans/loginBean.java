package es.desarrollo.hibernate.beans;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Created by Rostan on 22/08/2017.
 */
@ManagedBean
public class loginBean {

    private String username;

    private String password;

    //    METODOS
    public String login(ActionEvent event) {
        String res = "";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean flag = false;

        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
            flag = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            res = "home.xhtml";
        } else {
            flag = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", flag);

        return res;
    }

    //    GETTER Y SETTER
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
