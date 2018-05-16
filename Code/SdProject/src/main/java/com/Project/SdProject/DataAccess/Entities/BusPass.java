package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BusPass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date expirationDate;

    @ManyToMany
    @JoinTable(name = "bus_lines", joinColumns = {@JoinColumn(name = "fk_busPass")},
    inverseJoinColumns = {@JoinColumn(name = "fk_line")})
    private Set<Line> lines = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;

    public void addBusLine(Line line){
        this.lines.add(line);
        line.getBusPasses().add(this);
    }

    public void removeBusLine(Line line){
        this.lines.remove(line);
        line.getBusPasses().remove(this);
    }

    public Set<Line> getLines() {
        return lines;
    }

    public void setLines(Set<Line> lines) {
        this.lines = lines;
    }

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
