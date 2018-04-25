package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String surname;

    @ManyToMany
    @JoinTable(name = "staff_roles", joinColumns = {@JoinColumn(name = "fk_staff")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role")})
    private Set<StaffRole> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "fk_user_staff")
    private User user;

    public void addRole(StaffRole role){

        this.roles.add(role);
        role.getStaff().add(this);

    }

    public void removeRole(StaffRole role){

        this.roles.remove(role);
        role.getStaff().remove(this);

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<StaffRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<StaffRole> roles) {
        this.roles = roles;
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
}
