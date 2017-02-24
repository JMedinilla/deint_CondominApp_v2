package com.deint.condominapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Community;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Community {
    private static DatabaseManager_Community instance;

    public static DatabaseManager_Community getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Community();
        }
        return instance;
    }

    public List<Pojo_Community> getCommunities() {
        ArrayList<Pojo_Community> communities = new ArrayList<>();
        Pojo_Community community;

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.COMMUNITY_TABLE.TABLE_NAME,
                DatabaseContract.COMMUNITY_TABLE.ALL_COLUMNS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String locality = cursor.getString(1);
                String municipality = cursor.getString(2);
                String address = cursor.getString(3);
                String number = cursor.getString(4);
                String block = cursor.getString(5);
                String postal = cursor.getString(6);
                int apartments = cursor.getInt(7);
                boolean deleted = false;
                if (cursor.getInt(8) > 0) {
                    deleted = true;
                }
                community = new Pojo_Community(id, locality, municipality, address,
                        number, block, postal, apartments, deleted);
                if (!community.isCo_deleted()) {
                    communities.add(community);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return communities;
    }

    public Pojo_Community getCommunity(int id) {
        return null;
    }

    public void addCommunity(Pojo_Community community) {
        //
    }

    public void updateCommunity(Pojo_Community community) {
        //
    }

    public void deleteCommunity(Pojo_Community community) {
        //
    }
}
