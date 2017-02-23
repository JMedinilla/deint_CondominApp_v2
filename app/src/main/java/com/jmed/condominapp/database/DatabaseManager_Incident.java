package com.jmed.condominapp.database;

import com.jmed.condominapp.pojos.Pojo_Incident;

public class DatabaseManager_Incident {
    private static DatabaseManager_Incident instance;

    public static DatabaseManager_Incident getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Incident();
        }
        return instance;
    }

    public void getIncidents(int community) {
        //
    }

    public Pojo_Incident getIncident(String id) {
        return null;
    }

    public void addIncident(Pojo_Incident incident) {
        //
    }

    public void updateIncident(Pojo_Incident incident) {
        //
    }

    public void deleteIncident(Pojo_Incident incident) {
        //
    }
}
