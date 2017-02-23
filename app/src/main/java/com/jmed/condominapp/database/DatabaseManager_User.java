package com.jmed.condominapp.database;

import com.jmed.condominapp.pojos.Pojo_User;

class DatabaseManager_User {
    private static DatabaseManager_User instance;

    public static DatabaseManager_User getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_User();
        }
        return instance;
    }

    public void getUsers(int community) {
        //
    }

    public Pojo_User getUser(String id) {
        return null;
    }

    public void addUser(Pojo_User user) {
        //
    }

    public void updateUser(Pojo_User user) {
        //
    }

    public void deleteUser(Pojo_User user) {
        //
    }
}
