package Daos;
import Entities.Student;
import Entities.Teacher;
import Entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

public class TeacherDao {
    public static Teacher FindTeacherByUserData(User user){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query<Teacher> query = session.createQuery("FROM Teacher WHERE user_id = :userId", Teacher.class);
        query.setParameter("userId", user.getId());

        Teacher foundTeacher = query.uniqueResult();

        transaction.commit();
        return foundTeacher;
    }

    public static Teacher FindTeacherById(Long t_id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query<Teacher> query = session.createQuery("FROM Teacher WHERE teacher_id = :id", Teacher.class);
        query.setParameter("id", t_id);

        Teacher foundTeacher = query.uniqueResult();

        transaction.commit();
        return foundTeacher;
    }

    public static void EditTeacher(Teacher receivedTeacher, User receivedUser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User existingUser = session.get(User.class, receivedUser.getId());

        // Найдем студента в БД по ID
        Teacher existingTeacher = session.get(Teacher.class, receivedTeacher.getTeacher_id());

        // Проверим, что объекты найдены в БД
        if (existingUser != null && existingTeacher != null) {
            // Внесем изменения в объекты
            existingUser.setLogin(receivedUser.getLogin());
            existingUser.setPassword(receivedUser.getPassword());
            existingTeacher.setName(receivedTeacher.getName());
            existingTeacher.setSurname(receivedTeacher.getSurname());
            existingTeacher.setPhone(receivedTeacher.getPhone());
            existingTeacher.setEmail(receivedTeacher.getEmail());

            // Обновим записи в БД
            session.update(existingUser);
            session.update(existingTeacher);

            // Закроем транзакцию
            transaction.commit();
        }
    }
}
