package com.deint.condominapp.database;

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

    public Pojo_Document getDocument(String id) {
        return null;
    }

    public void addDocument(Pojo_Document document) {
        //
    }

    public void updateDocument(Pojo_Document document) {
        //
    }

    public void deleteDocument(Pojo_Document document) {
        //
    }
}
