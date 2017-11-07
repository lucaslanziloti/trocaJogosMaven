/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.converter;

import br.com.trocaJogos.dao.PlataformaDao;
import br.com.trocaJogos.model.Plataforma;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter("plataformaConverter")
public class PlataformaConverter  implements Converter {

    private PlataformaDao plataformaDao = new PlataformaDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().equals("")) {
            try {
                Plataforma genero = plataformaDao.carregar(Integer.parseInt(value));
                if (genero != null && genero.getId() != null) {
                    return genero;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return new Plataforma();
            }
        }
        return new Plataforma();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        return ((value != null && !value.equals("")) && ((Plataforma) value).getId() != null) ? ((Plataforma) value).getId().toString() : (value != null) ? value.toString() : "";
    }

    
}
