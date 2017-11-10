package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Logradouro;
import br.com.trocaJogos.model.Usuario;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 * @author lucas
 */
public class LogradouroDao extends GenericDao<Logradouro> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public LogradouroDao() {
        super(Logradouro.class);
    }

    public Logradouro buscarPorCEP(String cep) {
        Session session = hibernateUtil.getSession();

        List<Logradouro> resultado = session.createQuery("select l from Logradouro l WHERE l.cep = :cep")
                .setParameter("cep", cep)
                .list();

        if (resultado != null && !resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            return new Logradouro();
        }
    }
}
