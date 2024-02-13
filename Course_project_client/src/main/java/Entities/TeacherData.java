package Entities;

public class TeacherData {
    protected static Long teacher_id;
    protected static String name;
    protected static String surname;
    protected static String phone;
    protected static String email;
    protected static int work_experience;
    protected static Long user_id;
    public TeacherData(){}

    public static void init(Teacher t){
        teacher_id = t.teacher_id;
        name = t.name;
        surname = t.surname;
        phone = t.phone;
        email = t.email;
        work_experience = t.work_experience;
        user_id = t.user_id;
    }

    public static Long getTeacher_id() {
        return teacher_id;
    }

    public static void setTeacher_id(Long teacher_id) {
        TeacherData.teacher_id = teacher_id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        TeacherData.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        TeacherData.surname = surname;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        TeacherData.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        TeacherData.email = email;
    }

    public static int getWork_experience() {
        return work_experience;
    }

    public static void setWork_experience(int work_experience) {
        TeacherData.work_experience = work_experience;
    }

    public static Long getUser_id() {
        return user_id;
    }

    public static void setUser_id(Long user_id) {
        TeacherData.user_id = user_id;
    }
    public static Teacher toTeacher(){
        Teacher t = new Teacher();
        t.teacher_id = TeacherData.getTeacher_id();
        t.name = TeacherData.getName();
        t.surname = TeacherData.getSurname();
        t.phone = TeacherData.getPhone();
        t.email = TeacherData.getEmail();
        t.work_experience = TeacherData.getWork_experience();
        t.user_id = TeacherData.getUser_id();
        return t;
    }
}
