package br.com.trocaJogos.dao;

import br.com.trocaJogos.model.Genero;
import br.com.trocaJogos.model.Jogo;
import br.com.trocaJogos.model.Plataforma;
import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author lucas
 */
public class JogoDao extends GenericDao<Jogo> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public JogoDao() {
        super(Jogo.class);
    }

    public List<Jogo> buscaPor(Genero genero, Plataforma plataforma, String descricao) {
        Session session = hibernateUtil.getSession();

        StringBuilder hql = new StringBuilder();
        hql.append("SELECT j FROM Jogo j WHERE 1 = 1 ");

        if (genero != null && genero.getId() != null) {
            hql.append("AND j.genero = :genero ");
        }

        if (plataforma != null && plataforma.getId() != null) {
            hql.append("AND j.plataforma = :plataforma ");
        }

        if (descricao != null && !descricao.isEmpty()) {
            hql.append("AND j.descricao LIKE :descricao ");
        }

        Query createQuery = session.createQuery(hql.toString());

        if (genero != null && genero.getId() != null) {
            createQuery.setParameter("genero", genero);
        }

        if (plataforma != null && plataforma.getId() != null) {
            createQuery.setParameter("plataforma", plataforma);
        }

        if (descricao != null && !descricao.isEmpty()) {
            createQuery.setParameter("descricao", "%" + descricao + "%");
        }
        
        return createQuery.list();
    }
}
