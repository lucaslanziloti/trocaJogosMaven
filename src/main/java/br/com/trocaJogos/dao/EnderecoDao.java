package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Endereco;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class EnderecoDao extends GenericDao<Endereco> {
    
    private HibernateUtil hibernateUtil = new HibernateUtil();

    public EnderecoDao() {
        super(Endereco.class);
    }

}