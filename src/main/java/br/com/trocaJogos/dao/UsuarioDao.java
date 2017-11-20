package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Cidade;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author lucas
 */
public class UsuarioDao extends GenericDao<Usuario> {

    private HibernateUtil hibernateUtil = new HibernateUtil();
    private JogoDesejadoDao jogoDesejadoDao = new JogoDesejadoDao();
    private JogoDoUsuarioDao jogoDoUsuarioDao = new JogoDoUsuarioDao();

    public UsuarioDao() {
        super(Usuario.class);
    }

    @Override
    public Usuario carregar(Integer id) {
        Usuario usuario = super.carregar(id);

        usuario.setJogosDesejados(jogoDesejadoDao.jogosDo(usuario));
        usuario.setJogosDoUsuario(jogoDoUsuarioDao.jogosDo(usuario));

        return usuario;
    }

    public Usuario logar(String email, String senha) {
        Session session = hibernateUtil.getSession();

        List<Usuario> resultado = session.createQuery("select u from Usuario u WHERE u.email = :email AND u.senha = :senha")
                .setParameter("senha", senha)
                .setParameter("email", email)
                .list();

        if (resultado != null && !resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            return new Usuario();
        }
    }

    public List<Usuario> buscarUsuarioQuePossui(Jogo jogo, Usuario usuario, Cidade cidade) {
        Session session = hibernateUtil.getSession();

        StringBuilder hql = new StringBuilder();
        hql.append("select j.usuario FROM JogoDoUsuario j ");
        hql.append("WHERE j.jogo = :jogo ");
        hql.append("AND j.usuario <> :usuario ");

        if (cidade != null && cidade.getId() != null) {
            hql.append("AND j.usuario.endereco.cidade = :cidade ");
        }

        Query query = session.createQuery(hql.toString())
                .setParameter("jogo", jogo)
                .setParameter("usuario", usuario);

        if (cidade != null && cidade.getId() != null) {
            query.setParameter("cidade", cidade);
        }
        
        return query.list();
    }

    public Boolean verificaCpfExiste(String cpf) {
        Session session = hibernateUtil.getSession();

        List<Usuario> resultado = session.createQuery("select u from Usuario u WHERE u.cpf = :cpf")
                .setParameter("cpf", cpf)
                .list();

        return resultado != null && !resultado.isEmpty();
    }

    public Boolean verificaEmailExiste(String email) {
        Session session = hibernateUtil.getSession();

        List<Usuario> resultado = session.createQuery("select u from Usuario u WHERE u.email = :email")
                .setParameter("email", email)
                .list();

        return resultado != null && !resultado.isEmpty();
    }

}
