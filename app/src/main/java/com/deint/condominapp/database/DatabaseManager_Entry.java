package com.deint.condominapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Entry;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Entry {
    private static DatabaseManager_Entry instance;

    public static DatabaseManager_Entry getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Entry();
        }
        return instance;
    }

    public List<Pojo_Entry> getFirstEntries(int userCommunity) {
        ArrayList<Pojo_Entry> entries = new ArrayList<>();
        Pojo_Entry entry;

        String where = DatabaseContract.ENTRY_TABLE.COLUMN_COMMUNITY + " =? and "
                + DatabaseContract.ENTRY_TABLE.COLUMN_CATEGORY + " =?";
        String[] args = {String.valueOf(userCommunity), String.valueOf(Pojo_Entry.FIRST)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.ENTRY_TABLE.TABLE_NAME,
                DatabaseContract.ENTRY_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String user = cursor.getString(1);
                int community = cursor.getInt(2);
                String title = cursor.getString(3);
                String content = cursor.getString(4);
                String date = cursor.getString(5);
                int category = cursor.getInt(6);
                boolean deleted = false;
                if (cursor.getInt(7) > 0) {
                    deleted = true;
                }
                entry = new Pojo_Entry(id, user, community, title, content, date, category, deleted);
                if (!entry.isEn_deleted()) {
                    entries.add(entry);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return entries;
    }

    public List<Pojo_Entry> getSecondEntries(int userCommunity) {
        ArrayList<Pojo_Entry> entries = new ArrayList<>();
        Pojo_Entry entry;

        String where = DatabaseContract.ENTRY_TABLE.COLUMN_COMMUNITY + " =? and "
                + DatabaseContract.ENTRY_TABLE.COLUMN_CATEGORY + " =?";
        String[] args = {String.valueOf(userCommunity), String.valueOf(Pojo_Entry.SECOND)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.ENTRY_TABLE.TABLE_NAME,
                DatabaseContract.ENTRY_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String user = cursor.getString(1);
                int community = cursor.getInt(2);
                String title = cursor.getString(3);
                String content = cursor.getString(4);
                String date = cursor.getString(5);
                int category = cursor.getInt(6);
                boolean deleted = false;
                if (cursor.getInt(7) > 0) {
                    deleted = true;
                }
                entry = new Pojo_Entry(id, user, community, title, content, date, category, deleted);
                if (!entry.isEn_deleted()) {
                    entries.add(entry);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return entries;
    }

    public long addEntry(Pojo_Entry entry) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_ID, entry.getEn_id());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_USER, entry.getEn_userid());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_COMMUNITY, entry.getEn_usercommunity());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_TITLE, entry.getEn_title());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_CONTENT, entry.getEn_content());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_DATE, entry.getEn_date());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_CATEGORY, entry.getEn_category());
        if (entry.isEn_deleted()) {
            values.put(DatabaseContract.ENTRY_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.ENTRY_TABLE.COLUMN_DELETED, 0);
        }
        long result = sqLiteDatabase.insert(DatabaseContract.ENTRY_TABLE.TABLE_NAME, null, values);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int updateEntry(Pojo_Entry entry) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_USER, entry.getEn_userid());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_COMMUNITY, entry.getEn_usercommunity());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_TITLE, entry.getEn_title());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_CONTENT, entry.getEn_content());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_DATE, entry.getEn_date());
        values.put(DatabaseContract.ENTRY_TABLE.COLUMN_CATEGORY, entry.getEn_category());
        if (entry.isEn_deleted()) {
            values.put(DatabaseContract.ENTRY_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.ENTRY_TABLE.COLUMN_DELETED, 0);
        }
        String[] where = {String.valueOf(entry.getEn_id())};
        int result = sqLiteDatabase.update(DatabaseContract.ENTRY_TABLE.TABLE_NAME, values, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int deleteEntry(Pojo_Entry entry) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        String[] where = {String.valueOf(entry.getEn_id())};
        int result = sqLiteDatabase.delete(DatabaseContract.ENTRY_TABLE.TABLE_NAME, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }
}
