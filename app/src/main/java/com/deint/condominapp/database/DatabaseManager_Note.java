package com.deint.condominapp.database;

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

    public Pojo_Note getNote(String id) {
        return null;
    }

    public void addNote(Pojo_Note note) {
        //
    }

    public void updateNote(Pojo_Note note) {
        //
    }

    public void deleteNote(Pojo_Note note) {
        //
    }
}
