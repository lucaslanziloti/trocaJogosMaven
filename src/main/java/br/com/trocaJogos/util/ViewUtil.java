package br.com.trocaJogos.util;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public final class ViewUtil {

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public static String getRequestParameter(String key) {
        return getFacesContext().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = ViewUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(getFacesContext(), component, theId);
    }

    public static Object getManagedBean(String name, Class model) {
        return getFacesContext().getApplication()
                .evaluateExpressionGet(getFacesContext(), "#{" + name + "}", model);
    }

    public static Object getFromSession(String param) {
        return getFacesContext().getExternalContext().getSessionMap().get(param);
    }

    public static void setInSession(String param, String value) {
        getFacesContext().getExternalContext().getSessionMap().put(param, value);
    }

    public static void setInSession(String param, Object value) {
        getFacesContext().getExternalContext().getSessionMap().put(param, value);
    }

    public static Object removeFromSession(String param) {
        return getFacesContext().getExternalContext().getSessionMap().remove(param);
    }

    public static void adicionarMensagemDeErro(String mensagem) {
        getFacesContext().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
    }

    public static void adicionarMensagemDeSucesso(String mensagem) {
        getFacesContext().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem));
    }

    public static void adicionarMensagemDeAlerta(String mensagem) {
        getFacesContext().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, mensagem));
    }

    public static void adicionarMensagemDeErro(Exception exception) {
        getFacesContext().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), exception.getMessage()));
    }

    public static void adicionarMensagemDeAlerta(Exception exception) {
        getFacesContext().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, exception.getMessage(), exception.getMessage()));
    }

    public static String getContextPath() {
        ExternalContext context = getExternalContext();
        if (context.getRequestServerPort() == 443) {
            return "https://" + context.getRequestServerName() + context.getRequestContextPath();
        } else {
            return "http://" + context.getRequestServerName() + ":" + context.getRequestServerPort() + context.getRequestContextPath();
        }
    }
}
