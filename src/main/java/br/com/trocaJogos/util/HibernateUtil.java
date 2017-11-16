package br.com.trocaJogos.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author lucas
 */
public class HibernateUtil {

    private static Session sessionFactory;

    public Session getSession() {
        if (sessionFactory == null) {
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            configuration.configure();

            SessionFactory factory = configuration.buildSessionFactory();
            return sessionFactory = factory.openSession();
        } else {
            return sessionFactory;
        }
    }
}
