package ClientHandlers;

import Daos.CourseDao;
import Daos.ReviewDao;
import Daos.StudentDao;
import Daos.TeacherDao;
import Entities.*;

import java.io.IOException;
import java.util.List;

public class StudentHandler {
    public StudentHandler(){}

    public void EditStudent() throws IOException, ClassNotFoundException {
        String receivedString = (String) MainClientHandler.getOis().readObject();
        System.out.println("String " + receivedString + " received!");
        if(receivedString.equals("changed")) {
            User receivedUser = (Entities.User) MainClientHandler.getOis().readObject();
            Student receivedStudent = (Entities.Student) MainClientHandler.getOis().readObject();
            StudentDao.EditStudent(receivedStudent, receivedUser);
            System.out.println("Данные изменены");
        }
    }
    public void ShowAllCourses() throws IOException, ClassNotFoundException {
        MainClientHandler.getOos().writeObject(CourseDao.GetAllCourses());
        MainClientHandler.getOos().flush();
        String receivedString = (String) MainClientHandler.getOis().readObject();
        System.out.println("String " + receivedString + " received!");
        if (receivedString.equals("enroll")) {
            Student receivedStudent = (Entities.Student) MainClientHandler.getOis().readObject();
            StudentDao.EditStudent(receivedStudent);
        }
    }

    public void ViewMyCourse() throws IOException, ClassNotFoundException {
        String receivedString = (String) MainClientHandler.getOis().readObject();
        System.out.println("String " + receivedString + " received!");
        if(receivedString.equals("course_exist")) {
            Long receivedCourseId = (Long) MainClientHandler.getOis().readObject();
            Course foundCourse = CourseDao.FindCourseById(receivedCourseId);
            MainClientHandler.sendCourse(foundCourse);
            Long receivedTeacherId = (Long) MainClientHandler.getOis().readObject();
            Teacher foundTeacher = TeacherDao.FindTeacherById(receivedTeacherId);
            MainClientHandler.sendString(foundTeacher.getName() + " " + foundTeacher.getSurname());
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
}
