package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.JogoDoUsuario;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author lucas
 */
public class JogoDoUsuarioDao extends GenericDao<JogoDoUsuario> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public JogoDoUsuarioDao() {
        super(JogoDoUsuario.class);
    }

    public List<JogoDoUsuario> jogosDo(Usuario usuario) {
        try {
            Session session = hibernateUtil.getSession();

            return session.createQuery("SELECT j FROM JogoDoUsuario j WHERE j.usuario = :usuario").setParameter("usuario", usuario).list();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
}
