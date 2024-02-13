package ClientHandlers;

import Entities.Course;
import Entities.Student;
import Entities.Teacher;
import Entities.User;
import Server.StartServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class MainClientHandler implements Runnable {

    private static Socket socket;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    public MainClientHandler(Socket socket) throws IOException {
        MainClientHandler.socket = socket;
    }

    public static void sendString(String string) throws IOException {
        oos.writeObject(string);
        oos.flush();
    }

    public static void sendStudent(Student student) throws IOException {
        oos.writeObject(student);
        oos.flush();
    }

    public static void sendTeacher(Teacher teacher) throws IOException {
        oos.writeObject(teacher);
        oos.flush();
    }

    public static void sendUser(User user) throws IOException {
        oos.writeObject(user);
        oos.flush();
    }

    public static void sendCourse(Course course) throws IOException {
        oos.writeObject(course);
        oos.flush();
    }

    public static void sendStringList(List<String> list) throws IOException {
        oos.writeObject(list);
        oos.flush();
    }

    public static void sendStudentList(List<Student> students) throws IOException {
        oos.writeObject(students);
        oos.flush();
    }

    public static ObjectOutputStream getOos() {
        return oos;
    }

    public static void setOos(ObjectOutputStream oos) {
        MainClientHandler.oos = oos;
    }

    public static ObjectInputStream getOis() {
        return ois;
    }

    public static void setOis(ObjectInputStream ois) {
        MainClientHandler.ois = ois;
    }

    @Override
    public void run() {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            while (socket.isConnected()) {
                String receivedPurpose = (String) ois.readObject();
                System.out.println("String " + receivedPurpose + " received!");
                switch (receivedPurpose) {
                    case "login": {
                        LoginHandler loginHandler = new LoginHandler();
                        loginHandler.CheckUser();
                        break;
                    }
                    case "register": {
                        LoginHandler loginHandler = new LoginHandler();
                        loginHandler.RegisterUser();
                        break;
                    }
                    default:
                        System.out.println("Wrong purpose!");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client " + StartServer.getCounter() + " disconnected: " + socket.getInetAddress() + ":" + socket.getPort());
        } finally {
            try {
                oos.close();
                ois.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace(); // Обработайте исключение в соответствии с вашими потребностями
            }
            StartServer.setCounter(StartServer.getCounter() - 1);
        }
    }
}