package com.Project.SdProject.BusinessLogic.DataTransferObjects;

import java.util.List;

public class StudentDTO {

    private int id;
    private String name;
    private String surname;
    private String university;
    private String username;
    private List<BusPassDTO> busPasses;

    public StudentDTO(int id, String name, String surname, List<BusPassDTO> busPasses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.busPasses = busPasses;
    }

    public StudentDTO() {
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<BusPassDTO> getBusPasses() {
        return busPasses;
    }

    public void setBusPasses(List<BusPassDTO> busPasses) {
        this.busPasses = busPasses;
    }
}
