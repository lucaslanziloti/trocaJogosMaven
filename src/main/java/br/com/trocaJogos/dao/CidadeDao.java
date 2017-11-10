package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Cidade;
import br.com.trocaJogos.model.Logradouro;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 * @author lucas
 */
public class CidadeDao extends GenericDao<Cidade> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public CidadeDao() {
        super(Cidade.class);
    }

    public List<Cidade> buscarPorNome(String nome) {
        Session session = hibernateUtil.getSession();

        List<Cidade> resultado = session.createQuery("select c from Cidade c WHERE c.descricao like :nome")
                .setParameter("nome", "%" + nome + "%")
                .list();

        return resultado;
    }
}
