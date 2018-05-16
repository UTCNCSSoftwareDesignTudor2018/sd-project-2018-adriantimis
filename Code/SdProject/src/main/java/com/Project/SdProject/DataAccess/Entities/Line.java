package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int lineNumber;

    @ManyToMany(mappedBy = "lines")
    private Set<BusPass> busPasses = new HashSet<>();

    public Set<BusPass> getBusPasses() {
        return busPasses;
    }

    public void setBusPasses(Set<BusPass> busPasses) {
        this.busPasses = busPasses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
