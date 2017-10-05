package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Logradouro;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class LogradouroDao extends GenericDao<Logradouro> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public LogradouroDao() {
        super(Logradouro.class);
    }

}
