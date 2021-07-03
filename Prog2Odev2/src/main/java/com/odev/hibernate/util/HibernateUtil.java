
package com.odev.hibernate.util;

/**
 *
 * @author did
 */
import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


    private static SessionFactory sessionFactory;
     //veri tabanı ile bağlantımızı kurmamızı sağlayan class
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
               Configuration configuration = new Configuration();
               configuration.configure(new File("src/resources/hibernate.cfg.xml"));
               sessionFactory = configuration.buildSessionFactory();
               
            } catch (HibernateException e) {
                e.printStackTrace();
            }

        }

        return sessionFactory;
    }
    }
