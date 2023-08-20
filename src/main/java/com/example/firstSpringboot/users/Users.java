package com.example.firstSpringboot.users;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table
public class Users {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Transient
    private int age;
    private String email;
    private String role;
    private LocalDate dob;

    public Users() {
    }

    public Users(String name, String email, String role, LocalDate dob) {
        System.out.println("trigg");
        this.name = name;
        this.email = email;
        this.role = role;
        this.dob = dob;
    }

    public Users(String id, String name, String email, String role, LocalDate dob) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.email = email;
        this.role = role;
        this.dob = dob;
    }


    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + getAge() +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", dob=" + dob +
                '}';
    }


}
