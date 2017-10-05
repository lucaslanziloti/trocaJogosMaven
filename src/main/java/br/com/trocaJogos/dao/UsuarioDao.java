package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.HibernateUtil;

/**
 * @author lucas
 */
public class UsuarioDao extends GenericDao<Usuario> {
    
    private HibernateUtil hibernateUtil = new HibernateUtil();

    public UsuarioDao() {
        super(Usuario.class);
    }

}
