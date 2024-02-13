package ClientHandlers;

import Daos.StudentDao;
import Daos.TeacherDao;
import Daos.UserDao;
import Entities.Student;
import Entities.User;

import java.io.IOException;

public class LoginHandler {
    public LoginHandler() throws IOException {
        MainClientHandler.sendString("Ok");
    }

    public void CheckUser() throws IOException, ClassNotFoundException {
        User receivedUser = (Entities.User) MainClientHandler.getOis().readObject();
        User found_user = UserDao.Authenticate(receivedUser);
        if ( found_user == null)
            MainClientHandler.sendString("Failed");
        else {
            MainClientHandler.sendString(found_user.getRole());
            MainClientHandler.sendUser(found_user);
            if(found_user.getRole().equals("a")){
                adminMenu(found_user);
            }
            else if (found_user.getRole().equals("t")) {
                teacherMenu(found_user);
            }
            else {
              studentMenu(found_user);
            }

        }

    }

    public void teacherMenu(User found_user) throws IOException, ClassNotFoundException {
        MainClientHandler.sendTeacher(TeacherDao.FindTeacherByUserData(found_user));
        while (true){
            String receivedPurpose = (String) MainClientHandler.getOis().readObject();
            System.out.println("String " + receivedPurpose + " received!");
            TeacherHandler teacherHandler = new TeacherHandler();
            switch (receivedPurpose) {
                case "edit": {
                    teacherHandler.EditTeacher();
                    break;
                }
                case "my_courses": {
                    teacherHandler.ViewMyCourses();
                    break;
                }
                case "reviews": {
                    teacherHandler.ViewReviews();
                    break;
                }
            }
        }
    }

    public void studentMenu(User found_user) throws IOException, ClassNotFoundException {
        MainClientHandler.sendStudent(StudentDao.FindStudentByUserData(found_user));
        while (true){
            String receivedPurpose = (String) MainClientHandler.getOis().readObject();
            System.out.println("String " + receivedPurpose + " received!");
            StudentHandler studentHandler = new StudentHandler();
            switch (receivedPurpose) {
                case "edit": {
                    studentHandler.EditStudent();
                    break;
                }
                case "info_courses": {
                    studentHandler.ShowAllCourses();
                    break;
                }
                case "my_courses": {
                    studentHandler.ViewMyCourse();
                    break;
                }
                case "reviews": {
                    studentHandler.ViewReviews();
                    break;
                }
            }
        }
    }

    public void adminMenu(User found_user){

    }
    public void RegisterUser() throws IOException, ClassNotFoundException {
        User receivedUser = null;
        Student receivedStudent = null;
        receivedUser = (User) MainClientHandler.getOis().readObject();
        if(UserDao.Authenticate(receivedUser) != null)
            MainClientHandler.sendString("Failed");
        else {
            MainClientHandler.sendString("Success");
            receivedStudent = (Student) MainClientHandler.getOis().readObject();
            StudentDao.AddStudent(receivedStudent);
            UserDao.AddUser(receivedUser);
            MainClientHandler.sendString("Success");
        }
    }
}