package Entities;
import java.time.LocalDate;

public class StudentBuilderImp implements StudentBuilder {
    Student s = new Student();
    @Override
    public StudentBuilder setName(String name) {
        s.name = name;
        return this;
    }

    @Override
    public StudentBuilder setSurname(String surname) {
        s.surname = surname;
        return this;
    }

    @Override
    public StudentBuilder setDateOfBirth(LocalDate date_of_birth) {
        s.date_of_birth = date_of_birth;
        return this;
    }

    @Override
    public StudentBuilder setPhone(String phone) {
        s.phone = phone;
        return this;
    }

    @Override
    public StudentBuilder setEmail(String email) {
        s.email = email;
        return this;
    }

    @Override
    public Student build() {
        return s;
    }
}
