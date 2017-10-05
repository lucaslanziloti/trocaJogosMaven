package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Cidade;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class CidadeDao extends GenericDao<Cidade> {
    
    private HibernateUtil hibernateUtil = new HibernateUtil();

    public CidadeDao() {
        super(Cidade.class);
    }
}
