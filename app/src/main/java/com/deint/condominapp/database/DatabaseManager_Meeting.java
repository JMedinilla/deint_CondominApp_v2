package com.deint.condominapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Meeting;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Meeting {
    private static DatabaseManager_Meeting instance;

    public static DatabaseManager_Meeting getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Meeting();
        }
        return instance;
    }

    public List<Pojo_Meeting> getMeetings(int communityWhere) {
        ArrayList<Pojo_Meeting> meetings = new ArrayList<>();
        Pojo_Meeting meeting;

        String where = DatabaseContract.MEETING_TABLE.COLUMN_COMMUNITY + " =?";
        String[] args = {String.valueOf(communityWhere)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.MEETING_TABLE.TABLE_NAME,
                DatabaseContract.MEETING_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int community = cursor.getInt(1);
                String date = cursor.getString(2);
                boolean deleted = false;
                if (cursor.getInt(3) > 0) {
                    deleted = true;
                }
                meeting = new Pojo_Meeting(id, community, date, deleted);
                if (!meeting.isMe_deleted()) {
                    meetings.add(meeting);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return meetings;
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
