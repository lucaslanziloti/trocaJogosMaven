package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Genero;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class GeneroDao extends GenericDao<Genero> {
    
    private HibernateUtil hibernateUtil = new HibernateUtil();

    public GeneroDao() {
        super(Genero.class);
    }
}
