package com.company.service;

import com.company.entity.Store;
import com.company.entity.User;

import java.io.*;
import java.util.Scanner;

public class UserService {

    public static User[] users = new User[1000];


    public static int getLastIndex() {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void add(User user) {
        int lastIndex = getLastIndex();
        users[lastIndex] = user;
        user.setId(++lastIndex);
    }

    public void registr() {
        System.out.print("Please Enter number of Users = ");
        int number = new Scanner(System.in).nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Number of User = " + (i + 1) + "\n");

            User user = new User();
            System.out.print("User name = ");
            user.setName(new Scanner(System.in).nextLine());
            System.out.print("User surname = ");
            user.setSurname(new Scanner(System.in).nextLine());
            System.out.print("User age = ");
            user.setAge(new Scanner(System.in).nextInt());
            System.out.print("user Money = ");
            user.setUserMoney(new Scanner(System.in).nextDouble());
            System.out.println("");

            add(user);
        }
        System.out.println("");
        System.out.println("*********************************************");
        System.out.println("       Users successfully registered");
        System.out.println("********************************************* ");
        System.out.println("");
    }

    public void remove(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getId().equals(user.getId())) {
                users[i] = null;
                break;
            }
        }
    }

    public User findById(int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getId() == id) {
                return users[i];
            }
        }
        return null;
    }

    public void delete() {
        System.out.print("Please Enter id of User = ");
        int id = new Scanner(System.in).nextInt();
        User user = findById(id);
        if (user != null) {
            remove(user);
            System.out.println("*********************************************");
            System.out.println("        User successfully deleted");
            System.out.println("********************************************* ");
        } else {
            System.out.println("There is no such User:");
        }
    }

    public void search() {
        System.out.print("Enter user name = ");
        String name = new Scanner(System.in).nextLine();
        System.out.print("Enter user surname = ");
        String surname = new Scanner(System.in).nextLine();

        User user = null;
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) continue;
            if (name.equalsIgnoreCase(users[i].getName()) && surname.equalsIgnoreCase(users[i].getSurname())) {
                user = users[i];
                System.out.println(user);
            }
        }
        if (user == null) {
            System.out.println("There is no such User:");
        }
    }


    public void showAllUser() {
        User user = null;

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) continue;
            if (users[i] != null) {
                user = users[i];
                System.out.println(user);
            }
        }
        if (user == null) {
            System.out.println("There is not user");
        }

    }

    public void update() {
        System.out.print("Please Enter the User id = ");
        int id = new Scanner(System.in).nextInt();
        User user = findById(id);

        if (user == null) {
            System.out.println("There is no such user:");
        } else {
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println(user.toString());
            System.out.println("-----------------------------------------------------------------------------------------------------");

            System.out.print("Which field do you want to update: = ");
            String field=new Scanner(System.in).nextLine();

            if (field.equalsIgnoreCase("name")){
                System.out.print("Please again enter user name = ");
                user.setName(new Scanner(System.in).nextLine());
            } else if (field.equalsIgnoreCase("sunrame")){
                System.out.print("Please again enter user surname = ");
                user.setSurname(new Scanner(System.in).nextLine());
            } else if (field.equalsIgnoreCase("age")){
                System.out.print("Please again enter user age = ");
                user.setAge(new Scanner(System.in).nextInt());
            } else if (field.equalsIgnoreCase("userMoney")){
                System.out.print("Please again enter user money = ");
                user.setUserMoney(new Scanner(System.in).nextDouble());
            }
            else {
                System.out.println("Operation is wrong:");
            }
        }
    }

    public void exit(){
        System.exit(0);
    }

    public static void writeIntoFileIO(Store store)  {

        try {
            File file= new File("Files/UserList.txt");
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(store);

            o.close();
            f.close();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

    }

    public static Store readFromFileIO() {
        try {
            File file = new File("Files/UserList.txt");
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            Store pr1 = (Store) oi.readObject();
            oi.close();
            fi.close();
            return pr1;
        } catch (Exception ex){
            Store s = new Store();
            s.setUsersList(new User[1000]);
            return s;
        }
    }


    public void sendMoney(){
        for (int i= 0;i<users.length;i++){
            if (users[i]==null) continue;
            System.out.println(users[i]);
        }

        User whichFromUser=null;
        User toWhichUser = null;
        System.out.print("Which from User = ");
        String fromUser = new Scanner(System.in).nextLine();
        System.out.print("To Which User = ");
        String toUser = new Scanner(System.in).nextLine();
        System.out.print("How much money? = ");
        double money = new Scanner(System.in).nextDouble();
        for (int i=0;i< users.length;i++){
            if (users[i]==null) continue;
            if (fromUser.equalsIgnoreCase(users[i].getName())){
                whichFromUser=users[i];
            }
        }

        for (int i=0;i< users.length;i++){
            if (users[i]==null) continue;
            if (toUser.equalsIgnoreCase(users[i].getName())){
                toWhichUser=users[i];
            }
        }

        double hesabat1=whichFromUser.getUserMoney()-money;
        whichFromUser.setUserMoney(hesabat1);

        double hesabat2= toWhichUser.getUserMoney()+money;
        toWhichUser.setUserMoney(hesabat2);

        System.out.println(" "+money+" azn money decreased from account of "+ fromUser+" and send to account of "+toUser+" ");

    }


}
