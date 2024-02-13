package Entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Course implements Serializable {
    private static final long serialVersionUID = 5L;
    protected Long course_id;
    protected String course_name;
    protected String language_level;
    protected LocalDate start_date;
    protected LocalDate end_date;
    protected float price_group;
    protected float price_individual;
    protected Long teacher_id;
    protected String description;
    public Course(){}

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLanguage_level() {
        return language_level;
    }

    public void setLanguage_level(String language_level) {
        this.language_level = language_level;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public float getPrice_group() {
        return price_group;
    }

    public void setPrice_group(float price_group) {
        this.price_group = price_group;
    }

    public float getPrice_individual() {
        return price_individual;
    }

    public void setPrice_individual(float price_individual) {
        this.price_individual = price_individual;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
