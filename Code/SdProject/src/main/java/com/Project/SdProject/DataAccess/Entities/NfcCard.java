package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;

@Entity
public class NfcCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String password;

    @OneToOne(mappedBy = "nfcCard")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
