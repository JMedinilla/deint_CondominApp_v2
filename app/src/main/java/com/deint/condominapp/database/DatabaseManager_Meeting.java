package com.deint.condominapp.database;

import android.content.ContentValues;
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

    public long addMeeting(Pojo_Meeting meeting) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.MEETING_TABLE.COLUMN_ID, meeting.getMe_id());
        values.put(DatabaseContract.MEETING_TABLE.COLUMN_COMMUNITY, meeting.getMe_community());
        values.put(DatabaseContract.MEETING_TABLE.COLUMN_DATE, meeting.getMe_date());
        if (meeting.isMe_deleted()) {
            values.put(DatabaseContract.MEETING_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.MEETING_TABLE.COLUMN_DELETED, 0);
        }
        long result = sqLiteDatabase.insert(DatabaseContract.MEETING_TABLE.TABLE_NAME, null, values);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int updateMeeting(Pojo_Meeting meeting) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.MEETING_TABLE.COLUMN_COMMUNITY, meeting.getMe_community());
        values.put(DatabaseContract.MEETING_TABLE.COLUMN_DATE, meeting.getMe_date());
        if (meeting.isMe_deleted()) {
            values.put(DatabaseContract.MEETING_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.MEETING_TABLE.COLUMN_DELETED, 0);
        }
        String[] where = {String.valueOf(meeting.getMe_id())};
        int result = sqLiteDatabase.update(DatabaseContract.MEETING_TABLE.TABLE_NAME, values, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int deleteMeeting(Pojo_Meeting meeting) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        String[] where = {String.valueOf(meeting.getMe_id())};
        int result = sqLiteDatabase.delete(DatabaseContract.MEETING_TABLE.TABLE_NAME, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }
}
