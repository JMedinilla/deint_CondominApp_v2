package com.deint.condominapp.database;

import com.deint.condominapp.pojos.Pojo_Document;

class DatabaseManager_Document {
    private static DatabaseManager_Document instance;

    public static DatabaseManager_Document getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Document();
        }
        return instance;
    }

    public void getDocuments(int community) {
        //
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
