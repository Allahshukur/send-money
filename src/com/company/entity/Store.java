package com.company.entity;

import java.io.Serializable;

public class Store implements Serializable {

    public User[] usersList;

    public User[] getUsersList() {
        return usersList;
    }

    public void setUsersList(User[] usersList) {
        this.usersList = usersList;
    }
}
