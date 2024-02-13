package Daos;

import Entities.Student;
import Entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class StudentDao {
    public static void AddStudent(Student s){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(s);
        tx1.commit();
        session.close();
    }

    public static void EditStudent(Student receivedStudent, User receivedUser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User existingUser = session.get(User.class, receivedUser.getId());

        // Найдем студента в БД по ID
        Student existingStudent = session.get(Student.class, receivedStudent.getStudent_id());

        // Проверим, что объекты найдены в БД
        if (existingUser != null && existingStudent != null) {
            // Внесем изменения в объекты
            existingUser.setLogin(receivedUser.getLogin());
            existingUser.setPassword(receivedUser.getPassword());
            existingStudent.setName(receivedStudent.getName());
            existingStudent.setSurname(receivedStudent.getSurname());
            existingStudent.setDate_of_birth(receivedStudent.getDate_of_birth());
            existingStudent.setPhone(receivedStudent.getPhone());
            existingStudent.setEmail(receivedStudent.getEmail());

            // Обновим записи в БД
            session.update(existingUser);
            session.update(existingStudent);

            // Закроем транзакцию
            transaction.commit();
        }
    }
    public static void EditStudent(Student receivedStudent) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Найдем студента в БД по ID
        Student existingStudent = session.get(Student.class, receivedStudent.getStudent_id());

        // Проверим, что объекты найдены в БД
        if (existingStudent != null) {
            existingStudent.setCourse_id(receivedStudent.getCourse_id());
            existingStudent.setCourse_type(receivedStudent.getCourse_type());
            existingStudent.setEnrollment_date(receivedStudent.getEnrollment_date());

            session.update(existingStudent);

            transaction.commit();
        }
    }
    public static Student FindStudentByUserData(User user){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query<Student> query = session.createQuery("FROM Student WHERE user_id = :userId", Student.class);
        query.setParameter("userId", user.getId());

        Student foundStudent = query.uniqueResult();

        transaction.commit();
        return foundStudent;
    }

    public static List<Student> GetAllStudentsOfCourse(Long courseId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("FROM Student WHERE course_id = :courseId", Student.class);
            query.setParameter("courseId", courseId);
            return query.list();
        }
    }

}
