package Daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Entities.User;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

public class UserDao {

    public static void AddUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public static void UpdateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public static User FindUserById(long user_id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, user_id);
    }

    public static User Authenticate(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query<User> query = session.createQuery("FROM User WHERE login = :login AND password = :password", User.class);
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());

        User foundUser = query.uniqueResult();

        transaction.commit();
        return foundUser;
    }
}
