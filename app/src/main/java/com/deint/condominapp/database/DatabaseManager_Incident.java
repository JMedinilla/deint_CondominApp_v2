package com.deint.condominapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Incident;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Incident {
    private static DatabaseManager_Incident instance;

    public static DatabaseManager_Incident getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Incident();
        }
        return instance;
    }

    public List<Pojo_Incident> getIncidents(int communityWhere) {
        ArrayList<Pojo_Incident> incidents = new ArrayList<>();
        Pojo_Incident incident;

        String where = DatabaseContract.INCIDENT_TABLE.COLUMN_COMMUNITY + " =?";
        String[] args = {String.valueOf(communityWhere)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.INCIDENT_TABLE.TABLE_NAME,
                DatabaseContract.INCIDENT_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String user = cursor.getString(1);
                int community = cursor.getInt(2);
                String date = cursor.getString(3);
                String title = cursor.getString(4);
                String description = cursor.getString(5);
                String photo = cursor.getString(6);
                boolean deleted = false;
                if (cursor.getInt(7) > 0) {
                    deleted = true;
                }
                incident = new Pojo_Incident(id, user, community, date, title, description, photo, deleted);
                if (!incident.isIn_deleted()) {
                    incidents.add(incident);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return incidents;
    }

    public Pojo_Incident getIncident(String id) {
        return null;
    }

    public void addIncident(Pojo_Incident incident) {
        //
    }

    public void updateIncident(Pojo_Incident incident) {
        //
    }

    public void deleteIncident(Pojo_Incident incident) {
        //
    }
}
