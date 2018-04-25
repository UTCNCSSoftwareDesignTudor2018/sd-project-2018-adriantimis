package com.Project.SdProject.DataAccess.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StaffRole {

    @Id
    private int id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<Staff> staff = new HashSet<>();

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
