package com.deint.condominapp.database;

import com.deint.condominapp.pojos.Pojo_Meeting;

public class DatabaseManager_Meeting {
    private static DatabaseManager_Meeting instance;

    public static DatabaseManager_Meeting getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Meeting();
        }
        return instance;
    }

    public void getMeetings(int community) {
        //
    }

    public Pojo_Meeting getMeeting(int id) {
        return null;
    }

    public void addMeeting(Pojo_Meeting meeting) {
        //
    }

    public void updateMeeting(Pojo_Meeting meeting) {
        //
    }

    public void deleteMeeting(Pojo_Meeting meeting) {
        //
    }
}
