
package br.com.trocaJogos.converter;

import br.com.trocaJogos.dao.CidadeDao;
import br.com.trocaJogos.model.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

    private CidadeDao cidadeDao = new CidadeDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().equals("")) {
            try {
                Cidade cidade = cidadeDao.carregar(Integer.parseInt(value));
                if (cidade != null && cidade.getId() != null) {
                    return cidade;
                }
            } catch (NumberFormatException e) {
                return new Cidade();
            }
        }
        return new Cidade();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        return ((value != null && !value.equals("")) && ((Cidade) value).getId() != null) ? ((Cidade) value).getId().toString() : (value != null) ? value.toString() : "";
    }

}
