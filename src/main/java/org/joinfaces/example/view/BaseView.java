package org.joinfaces.example.view;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.FacesEvent;
import java.io.Serializable;
import java.util.Map;

public class BaseView implements Serializable
{
    protected void fatal(String message, String detail)
    {
        message(message, detail, FacesMessage.SEVERITY_FATAL);
    }

    protected void error(String message, String detail)
    {
        message(message, detail, FacesMessage.SEVERITY_ERROR);
    }

    protected void warn(String message, String detail)
    {
        message(message, detail, FacesMessage.SEVERITY_WARN);
    }

    protected void info(String message, String detail)
    {
        message(message, detail, FacesMessage.SEVERITY_INFO);
    }

    protected Flash flash()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }

    @SuppressWarnings("unchecked")
    protected <T> T flashGet(String key)
    {
        return (T) flash().get(key);
    }

    protected void flashPut(String key, Object value)
    {
        flash().put(key, value);
    }

    protected String redirect(String outcome)
    {
        return outcome + "?faces-redirect=true";
    }

    private void message(String message, String detail, Severity severity)
    {
        FacesMessage facesMessage = new FacesMessage(severity, message, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getAttribute(FacesEvent event, String key)
    {
        return (T) event.getComponent().getAttributes().get(key);
    }

    protected String getParam(String key)
    {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get(key);
    }
}
