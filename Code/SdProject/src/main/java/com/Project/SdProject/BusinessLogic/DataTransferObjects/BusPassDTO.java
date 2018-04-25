package com.Project.SdProject.BusinessLogic.DataTransferObjects;

import java.sql.Date;

public class BusPassDTO {

    private int Id;
    private Date expirationDate;

    public BusPassDTO() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
