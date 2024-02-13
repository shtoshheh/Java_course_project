package Daos;

import Entities.Course;
import Entities.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CourseDao {
    public static List<Course> GetAllCourses() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Course ", Course.class).list();
        }
    }

    public static List<Course> GetTeacherCourses(Long teacherId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Course WHERE teacher_id = :teacherId", Course.class)
                    .setParameter("teacherId", teacherId)
                    .list();
        }
    }


    public static Course FindCourseById(Long c_id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query<Course> query = session.createQuery("FROM Course WHERE course_id = :id", Course.class);
        query.setParameter("id", c_id);

        Course foundCourse = query.uniqueResult();

        transaction.commit();
        return foundCourse;
    }
}
