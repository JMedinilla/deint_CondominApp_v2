package com.jmed.condominapp.database;

import com.jmed.condominapp.pojos.Pojo_Point;

class DatabaseManager_Point {
    private static DatabaseManager_Point instance;

    public static DatabaseManager_Point getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Point();
        }
        return instance;
    }

    public void getPoints(int meeting) {
        //
    }

    public Pojo_Point getPoint(String id) {
        return null;
    }

    public void addPoint(Pojo_Point point) {
        //
    }

    public void updatePoint(Pojo_Point point) {
        //
    }

    public void deletePoint(Pojo_Point point) {
        //
    }
}
