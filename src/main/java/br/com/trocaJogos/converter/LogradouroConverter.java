
package br.com.trocaJogos.converter;

import br.com.trocaJogos.dao.LogradouroDao;
import br.com.trocaJogos.model.Logradouro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter("logradouroConverter")
public class LogradouroConverter implements Converter {

    private LogradouroDao logradouroDao = new LogradouroDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().equals("")) {
            try {
                Logradouro logradouro = logradouroDao.carregar(Integer.parseInt(value));
                if (logradouro != null && logradouro.getId() != null) {
                    return logradouro;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return new Logradouro();
            }
        }
        return new Logradouro();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        return ((value != null && !value.equals("")) && ((Logradouro) value).getId() != null) ? ((Logradouro) value).getId().toString() : (value != null) ? value.toString() : "";
    }
}
