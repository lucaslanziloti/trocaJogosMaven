package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Plataforma;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class PlataformaDao extends GenericDao<Plataforma> {
    
    private HibernateUtil hibernateUtil = new HibernateUtil();

    public PlataformaDao() {
        super(Plataforma.class);
    }
}
