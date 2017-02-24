package com.deint.condominapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.deint.condominapp.CondominappApplication;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "condominapp.db";

    private static volatile DatabaseHelper instance;
    private AtomicInteger openCounter;
    private SQLiteDatabase database;

    public synchronized static DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    private DatabaseHelper() {
        super(CondominappApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        this.openCounter = new AtomicInteger();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(DatabaseContract.COMMUNITY_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.USER_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.INCIDENT_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.ENTRY_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.DOCUMENT_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.NOTE_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.MEETING_TABLE.SQL_CREATION);
            sqLiteDatabase.execSQL(DatabaseContract.POINT_TABLE.SQL_CREATION);

            sqLiteDatabase.execSQL(DatabaseContract.COMMUNITY_TABLE.COMMUNITY_INSERT);
            sqLiteDatabase.execSQL(DatabaseContract.USER_TABLE.USER_INSERT);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(DatabaseContract.POINT_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.MEETING_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.NOTE_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.DOCUMENT_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.ENTRY_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.INCIDENT_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.USER_TABLE.SQL_DELETION);
            sqLiteDatabase.execSQL(DatabaseContract.COMMUNITY_TABLE.SQL_DELETION);
            onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    /**
     * Method to open the access to the database at the application launch
     *
     * @return Access to the database
     */
    public SQLiteDatabase open() {
        return getWritableDatabase();
    }

    /**
     * Method that returns the database access to use it
     *
     * @return Database access
     */
    synchronized SQLiteDatabase openDatabase() {
        if (openCounter.incrementAndGet() == 1) {
            database = getWritableDatabase();
        }
        return database;
    }

    /**
     * Method that close the database access
     */
    synchronized void closeDatabase() {
        if (openCounter.decrementAndGet() == 0) {
            database.close();
        }
    }
}
