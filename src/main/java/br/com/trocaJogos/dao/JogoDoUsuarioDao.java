
package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.JogoDoUsuario;
import br.com.trocaJogos.util.HibernateUtil;

/**
 *
 * @author lucas
 */
public class JogoDoUsuarioDao extends GenericDao<JogoDoUsuario> {
    

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public JogoDoUsuarioDao() {
        super(JogoDoUsuario.class);
    }
}
