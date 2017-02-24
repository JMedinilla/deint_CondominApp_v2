package com.deint.condominapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Note;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Note {
    private static DatabaseManager_Note instance;

    public static DatabaseManager_Note getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Note();
        }
        return instance;
    }

    public List<Pojo_Note> getNotes(int communityWhere) {
        ArrayList<Pojo_Note> notes = new ArrayList<>();
        Pojo_Note note;

        String where = DatabaseContract.NOTE_TABLE.COLUMN_COMMUNITY + " =?";
        String[] args = {String.valueOf(communityWhere)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.NOTE_TABLE.TABLE_NAME,
                DatabaseContract.NOTE_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                int community = cursor.getInt(1);
                String date = cursor.getString(2);
                String title = cursor.getString(3);
                String content = cursor.getString(4);
                boolean deleted = false;
                if (cursor.getInt(5) > 0) {
                    deleted = true;
                }
                note = new Pojo_Note(id, community, date, title, content, deleted);
                if (!note.isNo_deleted()) {
                    notes.add(note);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return notes;
    }

    public long addNote(Pojo_Note note) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_ID, note.getNo_id());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_COMMUNITY, note.getNo_community());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_DATE, note.getNo_date());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_TITLE, note.getNo_title());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_CONTENT, note.getNo_content());
        if (note.isNo_deleted()) {
            values.put(DatabaseContract.NOTE_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.NOTE_TABLE.COLUMN_DELETED, 0);
        }
        long result = sqLiteDatabase.insert(DatabaseContract.NOTE_TABLE.TABLE_NAME, null, values);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int updateNote(Pojo_Note note) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_COMMUNITY, note.getNo_community());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_DATE, note.getNo_date());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_TITLE, note.getNo_title());
        values.put(DatabaseContract.NOTE_TABLE.COLUMN_CONTENT, note.getNo_content());
        if (note.isNo_deleted()) {
            values.put(DatabaseContract.NOTE_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.NOTE_TABLE.COLUMN_DELETED, 0);
        }
        String[] where = {String.valueOf(note.getNo_id())};
        int result = sqLiteDatabase.update(DatabaseContract.NOTE_TABLE.TABLE_NAME, values, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int deleteNote(Pojo_Note note) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        String[] where = {String.valueOf(note.getNo_id())};
        int result = sqLiteDatabase.delete(DatabaseContract.NOTE_TABLE.TABLE_NAME, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }
}
