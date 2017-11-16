package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * @author lucas
 */
public class UsuarioDao extends GenericDao<Usuario> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public UsuarioDao() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario carregar(Integer id){
        Usuario usuario = super.carregar(id);
        
        if(!Hibernate.isInitialized(usuario.getJogosDesejados())){
            Hibernate.initialize(usuario.getJogosDesejados());
        }
        
        if(!Hibernate.isInitialized(usuario.getJogosDoUsuario())){
            Hibernate.initialize(usuario.getJogosDoUsuario());
        }
        
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

}
