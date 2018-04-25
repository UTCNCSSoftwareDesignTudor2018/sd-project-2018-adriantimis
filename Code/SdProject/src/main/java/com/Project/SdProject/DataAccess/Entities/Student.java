package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String surname;
    private String university;

    @OneToMany(mappedBy = "student")
    private List<BusPass> busPasses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "fk_nfccard")
    private NfcCard nfcCard;
    @OneToOne
    @JoinColumn(name = "fk_user_student")
    private User user;

    public void addBusPass(BusPass busPass){

        busPasses.add(busPass);
        busPass.setStudent(this);

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NfcCard getNfcCard() {
        return nfcCard;
    }

    public void setNfcCard(NfcCard nfcCard) {
        this.nfcCard = nfcCard;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public List<BusPass> getBusPasses() {
        return busPasses;
    }

    public void setBusPasses(List<BusPass> busPasses) {
        this.busPasses = busPasses;
    }
}
