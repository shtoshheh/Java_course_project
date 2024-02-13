package Daos;

import Entities.Course;
import Entities.Review;
import Entities.Student;
import Entities.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public static List<Review> GetAllReviews() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Review ", Review.class).list();
        }
    }

    public static List<String> GetReviewStudentNames(List<Review> reviewList) {
        List<String> studentNames = new ArrayList<>();

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            for (Review review : reviewList) {
                Long studentId = review.getStudent_id();
                if (studentId != null) {
                    // Запрос к базе данных для получения информации о студенте по studentId
                    Student student = session.get(Student.class, studentId);
                    if (student != null) {
                        // Формирование строки из фамилии и имени студента
                        String studentName = student.getSurname() + " " + student.getName();
                        studentNames.add(studentName);
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentNames;
    }

    public static List<String> GetReviewCourseNames(List<Review> reviewList) {
        List<String> courseNames = new ArrayList<>();

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            for (Review review : reviewList) {
                Long courseId = review.getCourse_id();
                if (courseId != null) {
                    // Запрос к базе данных для получения информации о курсе по courseId
                    Course course = session.get(Course.class, courseId);
                    if (course != null) {
                        String courseName = course.getCourse_name() + " " + course.getLanguage_level();
                        courseNames.add(courseName);
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseNames;
    }

    public static List<String> GetReviewTeacherNames(List<Review> reviewList) {
        List<String> teacherNames = new ArrayList<>();

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            for (Review review : reviewList) {
                Long courseId = review.getCourse_id();
                if (courseId != null) {
                    // Запрос к базе данных для получения информации о курсе по courseId
                    Course course = session.get(Course.class, courseId);
                    if (course != null) {
                        Long teacherId = course.getTeacher_id();
                        if (teacherId != null) {
                            // Запрос к базе данных для получения информации о учителе по teacherId
                            Teacher teacher = session.get(Teacher.class, teacherId);
                            if (teacher != null) {
                                // Формирование строки с именем и фамилией учителя и добавление в список
                                String teacherName = teacher.getName() + " " + teacher.getSurname();
                                teacherNames.add(teacherName);
                            }
                        }
                    }
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacherNames;
    }
}
