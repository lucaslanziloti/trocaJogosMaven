/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.JogoDesejado;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author lucas
 */
public class JogoDesejadoDao extends GenericDao<JogoDesejado> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public JogoDesejadoDao() {
        super(JogoDesejado.class);
    }

    public List<JogoDesejado> jogosDo(Usuario usuario) {
        try {
            Session session = hibernateUtil.getSession();

            return session.createQuery("SELECT j FROM JogoDesejado j WHERE j.usuario = :usuario").setParameter("usuario", usuario).list();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
}
