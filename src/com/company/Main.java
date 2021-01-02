package com.company;

import com.company.entity.Admin;
import com.company.entity.Store;
import com.company.entity.User;
import com.company.service.AdminService;
import com.company.service.UserService;

public class Main {

    public static void main(String[] args) {
        Store store = UserService.readFromFileIO();
        UserService.users = store.getUsersList();

        while (true) {
            AdminService.adminRegistraton();

            store.setUsersList(UserService.users);
            UserService.writeIntoFileIO(store);
        }


    }

}
