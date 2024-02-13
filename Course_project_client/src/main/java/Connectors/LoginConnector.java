package Connectors;

import Entities.Student;
import Entities.Teacher;
import Entities.User;

import java.io.IOException;

public class LoginConnector extends Connector{

    public static void SendEnteredUserData(User user) throws IOException {
        oos.writeObject(user);
        oos.flush();
    }

    public static void SendEnteredStudentData(Student s) throws IOException {
        oos.writeObject(s);
        oos.flush();
    }

    public static void SendEnteredTeacherData(Teacher t) throws IOException {
        oos.writeObject(t);
        oos.flush();
    }
}