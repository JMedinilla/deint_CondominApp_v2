package com.deint.condominapp.database;

import com.deint.condominapp.pojos.Pojo_Note;

class DatabaseManager_Note {
    private static DatabaseManager_Note instance;

    public static DatabaseManager_Note getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Note();
        }
        return instance;
    }

    public void getNotes(int community) {
        //
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
