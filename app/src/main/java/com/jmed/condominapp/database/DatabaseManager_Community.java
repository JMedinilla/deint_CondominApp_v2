package com.jmed.condominapp.database;

import com.jmed.condominapp.pojos.Pojo_Community;

class DatabaseManager_Community {
    private static DatabaseManager_Community instance;

    public static DatabaseManager_Community getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Community();
        }
        return instance;
    }

    public void getCommunities() {
        //
    }

    public Pojo_Community getCommunity(int id) {
        return null;
    }

    public void addCommunity(Pojo_Community community) {
        //
    }

    public void updateCommunity(Pojo_Community community) {
        //
    }

    public void deleteCommunity(Pojo_Community community) {
        //
    }
}
