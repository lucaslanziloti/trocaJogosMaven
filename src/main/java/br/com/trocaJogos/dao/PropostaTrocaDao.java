/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.PropostaTroca;
import br.com.trocaJogos.util.HibernateUtil;

/**
 *
 * @author lucas
 */
public class PropostaTrocaDao extends GenericDao<PropostaTroca> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public PropostaTrocaDao() {
        super(PropostaTroca.class);
    }

}
