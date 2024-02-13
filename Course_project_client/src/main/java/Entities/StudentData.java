package Entities;

import java.time.LocalDate;

public class StudentData {
    protected static Long student_id;
    protected static String name;
    protected static String surname;
    protected static LocalDate date_of_birth;
    protected static String phone;
    protected static String email;
    protected static Long course_id;
    protected static char course_type;
    protected static LocalDate enrollment_date;
    protected static Long progress_id;
    protected static Long user_id;
    public StudentData(){}
    public static void init(Student s){
        student_id = s.student_id;
        name = s.name;
        surname = s.surname;
        date_of_birth = s.date_of_birth;
        phone = s.phone;
        email = s.email;
        course_id = s.course_id;
        course_type = s.course_type;
        enrollment_date = s.enrollment_date;
        progress_id = s.progress_id;
        user_id = s.user_id;
    }

    public static Long getStudent_id() {
        return student_id;
    }

    public static void setStudent_id(Long student_id) {
        StudentData.student_id = student_id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        StudentData.name = name;
    }

    public static String getSurname() {
        return surname;
    }

    public static void setSurname(String surname) {
        StudentData.surname = surname;
    }

    public static LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public static void setDate_of_birth(LocalDate date_of_birth) {
        StudentData.date_of_birth = date_of_birth;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        StudentData.phone = phone;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        StudentData.email = email;
    }

    public static Long getCourse_id() {
        return course_id;
    }

    public static void setCourse_id(Long course_id) {
        StudentData.course_id = course_id;
    }

    public static char getCourse_type() {
        return course_type;
    }

    public static void setCourse_type(char course_type) {
        StudentData.course_type = course_type;
    }

    public static LocalDate getEnrollment_date() {
        return enrollment_date;
    }

    public static void setEnrollment_date(LocalDate enrollment_date) {
        StudentData.enrollment_date = enrollment_date;
    }

    public static Long getProgress_id() {
        return progress_id;
    }

    public static void setProgress_id(Long progress_id) {
        StudentData.progress_id = progress_id;
    }

    public static Long getUser_id() {
        return user_id;
    }

    public static void setUser_id(Long user_id) {
        StudentData.user_id = user_id;
    }

    public static Student toStudent(){
        Student s = new Student();
        s.student_id = StudentData.student_id;
        s.name = StudentData.name;
        s.surname = StudentData.surname;
        s.date_of_birth = StudentData.date_of_birth;
        s.phone = StudentData.phone;
        s.email = StudentData.email;
        s.course_id = StudentData.course_id;
        s.course_type = StudentData.course_type;
        s.enrollment_date = StudentData.enrollment_date;
        s.progress_id = StudentData.progress_id;
        s.user_id = StudentData.user_id;
        return s;
    }
}
