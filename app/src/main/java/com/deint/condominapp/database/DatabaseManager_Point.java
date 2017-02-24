package com.deint.condominapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deint.condominapp.pojos.Pojo_Point;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager_Point {
    private static DatabaseManager_Point instance;

    public static DatabaseManager_Point getInstance() {
        if (instance == null) {
            instance = new DatabaseManager_Point();
        }
        return instance;
    }

    public List<Pojo_Point> getPoints(int meetingWhere) {
        ArrayList<Pojo_Point> points = new ArrayList<>();
        Pojo_Point point;

        String where = DatabaseContract.POINT_TABLE.COLUMN_MEETING + " =?";
        String[] args = {String.valueOf(meetingWhere)};

        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.POINT_TABLE.TABLE_NAME,
                DatabaseContract.POINT_TABLE.ALL_COLUMNS, where, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                int meeting = cursor.getInt(1);
                String title = cursor.getString(2);
                String content = cursor.getString(3);
                point = new Pojo_Point(id, meeting, title, content);
                points.add(point);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseHelper.getInstance().closeDatabase();
        return points;
    }

    public long addPoint(Pojo_Point point) {
        return 0;
    }

    public int updatePoint(Pojo_Point point) {
        return 0;
    }

    public int deletePoint(Pojo_Point point) {
        return 0;
    }
}
