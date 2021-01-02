package com.company.service;

import com.company.entity.Admin;
import com.company.entity.Store;

import java.util.Scanner;

public class AdminService {

    private static Admin admin = new Admin();
    private static UserService userService = new UserService();

    public static void adminRegistraton() {
        adminMenyu();

        for (int i =1; i < 4; ) {
            if (admin.getAdminName().equalsIgnoreCase("aa") && admin.getPassword().equalsIgnoreCase("11")) {
                userMenyu();
                break;
            } else if (i == 3) {
                System.out.println("");
                System.out.println("****************************************");
                System.out.println("      The System has blocked you ");
                System.out.println("****************************************");
                System.exit(0);
                break;
            } else {
                i++;
                System.out.println("--------------------------");
                adminMenyu();
            }
        }

    }

    public static void adminMenyu() {
        System.out.print("Please enter AdminName = ");
        admin.setAdminName(new Scanner(System.in).nextLine());
        System.out.print("Please enter Password = ");
        admin.setPassword(new Scanner(System.in).nextLine());
    }

    public static void userMenyu() {
        while (true) {
            System.out.println("             Please choose an operation \n" +
                    "1. Registr the User " + "              "+"2. Delete the User \n" +
                    "3. Search the User " + "               "+"4. Show All Users \n" +
                    "5. Update the User " + "               "+"6. Send Money \n" +
                    "7. Logout " + "                        "+"8. Exit \n");

            System.out.print("Please choose an Operation = ");
            int operation = new Scanner(System.in).nextInt();

            if (operation == 1) {
                userService.registr();
            } else if (operation == 2) {
                userService.delete();
            } else if (operation == 3) {
                userService.search();
            } else if (operation == 4) {
                userService.showAllUser();
            } else if (operation == 5) {
                userService.update();
            } else if (operation == 6) {
                userService.sendMoney();
            } else if (operation == 7){

            } else if (operation == 8){
                adminRegistraton();
            } else if (operation ==9){
                userService.exit();
            } else {
                System.out.println("There is no such operation please again enter new Operation");
            }

            Store store = new Store();
            store.setUsersList(UserService.users);
            UserService.writeIntoFileIO(store);
        }
    }
}
