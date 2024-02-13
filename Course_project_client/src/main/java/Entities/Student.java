package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected Long student_id;
    protected String name;
    protected String surname;
    protected LocalDate date_of_birth;
    protected String phone;
    protected String email;
    protected Long course_id;
    protected char course_type;
    protected LocalDate enrollment_date;
    protected Long progress_id;
    protected Long user_id;

    public Student(){}

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public char getCourse_type() {
        return course_type;
    }

    public void setCourse_type(char course_type) {
        this.course_type = course_type;
    }

    public LocalDate getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(LocalDate enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public Long getProgress_id() {
        return progress_id;
    }

    public void setProgress_id(Long progress_id) {
        this.progress_id = progress_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
