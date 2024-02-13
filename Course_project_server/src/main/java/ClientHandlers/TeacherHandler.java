package ClientHandlers;

import Daos.CourseDao;
import Daos.ReviewDao;
import Daos.StudentDao;
import Daos.TeacherDao;
import Entities.Review;
import Entities.Student;
import Entities.Teacher;
import Entities.User;

import java.io.IOException;
import java.util.List;

public class TeacherHandler {
    public void EditTeacher() throws IOException, ClassNotFoundException {
        String receivedString = (String) MainClientHandler.getOis().readObject();
        System.out.println("String " + receivedString + " received!");
        if(receivedString.equals("changed")) {
            User receivedUser = (Entities.User) MainClientHandler.getOis().readObject();
            Teacher receivedTeacher = (Entities.Teacher) MainClientHandler.getOis().readObject();
            TeacherDao.EditTeacher(receivedTeacher, receivedUser);
            System.out.println("Данные изменены");
        }
    }

    public void ViewMyCourses() throws IOException, ClassNotFoundException {
            Long teacherId = (Long) MainClientHandler.getOis().readObject();
            MainClientHandler.getOos().writeObject(CourseDao.GetTeacherCourses(teacherId));
            MainClientHandler.getOos().flush();
            String receivedString = (String) MainClientHandler.getOis().readObject();
            System.out.println("String " + receivedString + " received!");
            if (receivedString.equals("show_students")) {
                ViewStudentsOfCourse();
            }

    }

    public void ViewReviews() throws IOException {
        List<Review> reviewList = ReviewDao.GetAllReviews();
        MainClientHandler.getOos().writeObject(reviewList);
        MainClientHandler.getOos().flush();
        MainClientHandler.sendStringList(ReviewDao.GetReviewStudentNames(reviewList));
        MainClientHandler.sendStringList(ReviewDao.GetReviewTeacherNames(reviewList));
        MainClientHandler.sendStringList(ReviewDao.GetReviewCourseNames(reviewList));
    }

    public void ViewStudentsOfCourse() throws IOException, ClassNotFoundException {
        Long received_course_id = (Long) MainClientHandler.getOis().readObject();
        List<Student> students = StudentDao.GetAllStudentsOfCourse(received_course_id);
        MainClientHandler.sendStudentList(students);
        String receivedString = (String) MainClientHandler.getOis().readObject();
        System.out.println("String " + receivedString + " received!");
        if (receivedString.equals("changed_progress")) {

        }

    }
}
