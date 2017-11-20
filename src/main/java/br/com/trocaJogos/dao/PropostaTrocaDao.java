/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.PropostaTroca;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author lucas
 */
public class PropostaTrocaDao extends GenericDao<PropostaTroca> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public PropostaTrocaDao() {
        super(PropostaTroca.class);
    }
    
    public Boolean validaTroca(PropostaTroca propostaTroca){
        List<PropostaTroca> results = hibernateUtil.getSession().createQuery("SELECT p FROM PropostaTroca p WHERE p.jogo = :jogo AND p.usuarioOrigem = :origem AND p.usuarioDestino = :destino")
                .setParameter("jogo", propostaTroca.getJogo())
                .setParameter("origem", propostaTroca.getUsuarioOrigem())
                .setParameter("destino", propostaTroca.getUsuarioDestino())
                .list();
        
        return results == null || results.isEmpty();
    }

}
