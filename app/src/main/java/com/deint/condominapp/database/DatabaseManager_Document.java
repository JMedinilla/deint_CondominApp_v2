package com.deint.condominapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Document;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Document {
    private static DatabaseManager_Document instance;

    public static DatabaseManager_Document getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Document();
        }
        return instance;
    }

    public List<Pojo_Document> getDocuments(int communityWhere) {
        ArrayList<Pojo_Document> documents = new ArrayList<>();
        Pojo_Document document;

        String where = DatabaseContract.DOCUMENT_TABLE.COLUMN_COMMUNITY + " =?";
        String[] args = {String.valueOf(communityWhere)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.DOCUMENT_TABLE.TABLE_NAME,
                DatabaseContract.DOCUMENT_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                int community = cursor.getInt(1);
                String title = cursor.getString(2);
                String description = cursor.getString(3);
                String link = cursor.getString(4);
                boolean deleted = false;
                if (cursor.getInt(5) > 0) {
                    deleted = true;
                }
                document = new Pojo_Document(id, community, title, description, link, deleted);
                if (!document.isDo_deleted()) {
                    documents.add(document);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return documents;
    }

    public long addDocument(Pojo_Document document) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_ID, document.getDo_id());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_COMMUNITY, document.getDo_community());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_TITLE, document.getDo_title());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_DESCRIPTION, document.getDo_description());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_LINK, document.getDo_link());
        if (document.isDo_deleted()) {
            values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_DELETED, 0);
        }
        long result = sqLiteDatabase.insert(DatabaseContract.DOCUMENT_TABLE.TABLE_NAME, null, values);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int updateDocument(Pojo_Document document) {
        ContentValues values = new ContentValues();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_COMMUNITY, document.getDo_community());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_TITLE, document.getDo_title());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_DESCRIPTION, document.getDo_description());
        values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_LINK, document.getDo_link());
        if (document.isDo_deleted()) {
            values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_DELETED, 1);
        } else {
            values.put(DatabaseContract.DOCUMENT_TABLE.COLUMN_DELETED, 0);
        }
        String[] where = {String.valueOf(document.getDo_id())};
        int result = sqLiteDatabase.update(DatabaseContract.DOCUMENT_TABLE.TABLE_NAME, values, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }

    public int deleteDocument(Pojo_Document document) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        String[] where = {String.valueOf(document.getDo_id())};
        int result = sqLiteDatabase.delete(DatabaseContract.DOCUMENT_TABLE.TABLE_NAME, "_id = ?", where);
        DatabaseHelper.getInstance().closeDatabase();
        return result;
    }
}
