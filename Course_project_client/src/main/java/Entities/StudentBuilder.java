package Entities;

import java.time.LocalDate;
import java.util.Date;

public interface StudentBuilder {
    StudentBuilder setName(String name);
    StudentBuilder setSurname(String surname);
    StudentBuilder setDateOfBirth(LocalDate date_of_birth);
    StudentBuilder setPhone(String phone);
    StudentBuilder setEmail(String email);
    Student build();
}
