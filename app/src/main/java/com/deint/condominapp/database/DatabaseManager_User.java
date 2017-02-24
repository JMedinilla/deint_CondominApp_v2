package com.deint.condominapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_User {
    private static DatabaseManager_User instance;

    public static DatabaseManager_User getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_User();
        }
        return instance;
    }

    public List<Pojo_User> getUsers() {
        ArrayList<Pojo_User> users = new ArrayList<>();
        Pojo_User user;

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.USER_TABLE.TABLE_NAME,
                DatabaseContract.USER_TABLE.ALL_COLUMNS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                int community = cursor.getInt(1);
                String floor = cursor.getString(2);
                String door = cursor.getString(3);
                String phone = cursor.getString(4);
                String mail = cursor.getString(5);
                String name = cursor.getString(6);
                int category = cursor.getInt(7);
                String photo = cursor.getString(8);
                boolean deleted = false;
                if (cursor.getInt(9) > 0) {
                    deleted = true;
                }
                user = new Pojo_User(id, community, floor, door, phone, mail, name, category, photo, deleted);
                if (!user.isUs_deleted()) {
                    users.add(user);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return users;
    }

    public List<Pojo_User> getUsers(int communityWhere) {
        ArrayList<Pojo_User> users = new ArrayList<>();
        Pojo_User user;

        String where = DatabaseContract.USER_TABLE.COLUMN_COMMUNITY + " =?";
        String[] args = {String.valueOf(communityWhere)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.USER_TABLE.TABLE_NAME,
                DatabaseContract.USER_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                int community = cursor.getInt(1);
                String floor = cursor.getString(2);
                String door = cursor.getString(3);
                String phone = cursor.getString(4);
                String mail = cursor.getString(5);
                String name = cursor.getString(6);
                int category = cursor.getInt(7);
                String photo = cursor.getString(8);
                boolean deleted = false;
                if (cursor.getInt(9) > 0) {
                    deleted = true;
                }
                user = new Pojo_User(id, community, floor, door, phone, mail, name, category, photo, deleted);
                if (!user.isUs_deleted()) {
                    users.add(user);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return users;
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
