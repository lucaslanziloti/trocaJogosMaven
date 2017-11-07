package br.com.trocaJogos.dao;

import br.com.trocaJogos.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author lucas
 * @param <T>
 */
public class GenericDao<T> {

    private HibernateUtil hibernateUtil = new HibernateUtil();
    private Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }
    

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
    
    public T carregar(Integer id){
        Session session = hibernateUtil.getSession();
        
        return (T) session.get(this.type, id);
    }
    
    public List<T> listaTodos(){
        Session session = hibernateUtil.getSession();
        
        return session.createQuery("select t from " + this.type.getSimpleName() + " t ").list();
    }
}
