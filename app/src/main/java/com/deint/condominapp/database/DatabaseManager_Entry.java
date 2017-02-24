package com.deint.condominapp.database;

import com.deint.condominapp.pojos.Pojo_Entry;

class DatabaseManager_Entry {
    private static DatabaseManager_Entry instance;

    public static DatabaseManager_Entry getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Entry();
        }
        return instance;
    }

    public void getEntries(int community) {
        //
    }

    public Pojo_Entry get(String id) {
        return null;
    }

    public void addEntry(Pojo_Entry entry) {
        //
    }

    public void updateEntry(Pojo_Entry entry) {
        //
    }

    public void deleteEntry(Pojo_Entry entry) {
        //
    }
}
