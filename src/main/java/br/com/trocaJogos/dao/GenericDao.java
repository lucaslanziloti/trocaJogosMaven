package br.com.trocaJogos.dao;

import br.com.trocaJogos.util.HibernateUtil;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author lucas
 */
public class GenericDao<T> {

    private HibernateUtil hibernateUtil = new HibernateUtil();

    public void salvar(T entity) {
        Session session = hibernateUtil.getSession();

        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();

        session.close();
    }

    public void alterar(T entity) {
        Session session = hibernateUtil.getSession();

        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();

        session.close();
    }

    public void delete(T entity) {
        Session session = hibernateUtil.getSession();

        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();

        session.close();
    }
    
//    public T carregar(Integer id){
//        Session session = hibernateUtil.getSession();
//        
//        session.
//    }
}
