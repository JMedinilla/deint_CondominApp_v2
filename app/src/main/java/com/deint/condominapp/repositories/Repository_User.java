package com.deint.condominapp.repositories;

import com.deint.condominapp.pojos.Pojo_User;

import java.util.ArrayList;
import java.util.List;

public class Repository_User extends ArrayList<Pojo_User> {
    private static Repository_User instance;

    public static Repository_User getInstance() {
        if (instance == null) {
            instance = new Repository_User();
        }
        return instance;
    }

    private Repository_User() {
        add(new Pojo_User("12", 99, "-", "-", "656565656", "eliseo@gmail.com", "Eliseo Moreno", Pojo_User.ADMINISTRATOR, "", false));
        add(new Pojo_User("1234", 99, "1", "A", "959595959", "medinilla@gmail.com", "Javier Medinilla", Pojo_User.PRESIDENT, "", false));
        add(new Pojo_User("123456", 99, "2", "C", "454545454", "amafernan@gmail.com", "Amador Fernández", Pojo_User.NEIGHBOUR, "", false));
    }

    public List<Pojo_User> getUsers() {
        return this;
    }
}
