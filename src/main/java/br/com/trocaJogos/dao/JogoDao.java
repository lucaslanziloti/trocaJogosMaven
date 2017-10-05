package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class JogoDao extends GenericDao<Jogo> {
    
    private HibernateUtil hibernateUtil = new HibernateUtil();

    public JogoDao() {
        super(Jogo.class);
    }
}
