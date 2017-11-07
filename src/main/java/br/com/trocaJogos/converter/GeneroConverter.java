/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.converter;

import br.com.trocaJogos.dao.GeneroDao;
import br.com.trocaJogos.model.Genero;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter("generoConverter")
public class GeneroConverter implements Converter {

    private GeneroDao generoDao = new GeneroDao();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().equals("")) {
            try {
                Genero genero = generoDao.carregar(Integer.parseInt(value));
                if (genero != null && genero.getId() != null) {
                    return genero;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return new Genero();
            }
        }
        return new Genero();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        return ((value != null && !value.equals("")) && ((Genero) value).getId() != null) ? ((Genero) value).getId().toString() : (value != null) ? value.toString() : "";
    }

}
