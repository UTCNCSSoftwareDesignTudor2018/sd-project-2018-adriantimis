package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BusPass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
