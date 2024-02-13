package utils;

import Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static void BuildSessionFactory()
    {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Review.class);
                configuration.addAnnotatedClass(Progress.class);
                configuration.addAnnotatedClass(Payment.class);
                configuration.addAnnotatedClass(Admin.class);
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}