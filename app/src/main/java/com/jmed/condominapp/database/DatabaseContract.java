package com.jmed.condominapp.database;

import android.provider.BaseColumns;

class DatabaseContract {
    static class COMMUNITY_TABLE implements BaseColumns {
        static final String TABLE_NAME = "community";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_LOCALITY = "co_locality";
        static final String COLUMN_MUNICIPALITY = "co_municipality";
        static final String COLUMN_ADDRESS = "co_address";
        static final String COLUMN_NUMBER = "co_number";
        static final String COLUMN_BLOCK = "co_block";
        static final String COLUMN_POSTAL = "co_postal";
        static final String COLUMN_APARTMENTS = "co_apartments";
        static final String COLUMN_DELETED = "co_deleted";

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_LOCALITY, COLUMN_MUNICIPALITY, COLUMN_ADDRESS, COLUMN_NUMBER,
                COLUMN_BLOCK, COLUMN_POSTAL, COLUMN_APARTMENTS, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL,"
                                + " %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_LOCALITY, COLUMN_MUNICIPALITY,
                        COLUMN_ADDRESS, COLUMN_NUMBER, COLUMN_BLOCK, COLUMN_POSTAL,
                        COLUMN_APARTMENTS, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class USER_TABLE implements BaseColumns {
        static final String TABLE_NAME = "user";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_COMMUNITY = "us_community";
        static final String COLUMN_FLOOR = "us_floor";
        static final String COLUMN_DOOR = "us_door";
        static final String COLUMN_PHONE = "us_phone";
        static final String COLUMN_MAIL = "us_mail";
        static final String COLUMN_NAME = "us_name";
        static final String COLUMN_CATEGORY = "us_category";
        static final String COLUMN_PHOTO = "us_photo";
        static final String COLUMN_DELETED = "us_deleted";

        static final String REFERENCE_COMMUNITY =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        COMMUNITY_TABLE.TABLE_NAME, COMMUNITY_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_COMMUNITY, COLUMN_FLOOR, COLUMN_DOOR, COLUMN_PHONE,
                COLUMN_MAIL, COLUMN_NAME, COLUMN_CATEGORY, COLUMN_PHOTO, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s INTEGER NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_COMMUNITY, REFERENCE_COMMUNITY, COLUMN_FLOOR,
                        COLUMN_DOOR, COLUMN_PHONE, COLUMN_MAIL, COLUMN_NAME,
                        COLUMN_CATEGORY, COLUMN_PHOTO, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class INCIDENT_TABLE implements BaseColumns {
        static final String TABLE_NAME = "incident";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_USER = "in_user";
        static final String COLUMN_DATE = "in_date";
        static final String COLUMN_TITLE = "in_title";
        static final String COLUMN_DESCRIPTION = "in_description";
        static final String COLUMN_PHOTO = "in_photo";
        static final String COLUMN_DELETED = "in_deleted";

        static final String REFERENCE_USER =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        USER_TABLE.TABLE_NAME, USER_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_USER, COLUMN_DATE, COLUMN_TITLE,
                COLUMN_DESCRIPTION, COLUMN_PHOTO, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s TEXT NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_USER, REFERENCE_USER, COLUMN_DATE,
                        COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_PHOTO, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class ENTRY_TABLE implements BaseColumns {
        static final String TABLE_NAME = "entry";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_USER = "en_user";
        static final String COLUMN_TITLE = "en_title";
        static final String COLUMN_CONTENT = "en_content";
        static final String COLUMN_DATE = "en_date";
        static final String COLUMN_CATEGORY = "en_category";
        static final String COLUMN_DELETED = "en_deleted";

        static final String REFERENCE_USER =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        USER_TABLE.TABLE_NAME, USER_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_USER, COLUMN_TITLE, COLUMN_CONTENT,
                COLUMN_DATE, COLUMN_CATEGORY, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s TEXT NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL,"
                                + " %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_USER, REFERENCE_USER, COLUMN_TITLE,
                        COLUMN_CONTENT, COLUMN_DATE, COLUMN_CATEGORY, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class DOCUMENT_TABLE implements BaseColumns {
        static final String TABLE_NAME = "document";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_COMMUNITY = "do_community";
        static final String COLUMN_TITLE = "do_title";
        static final String COLUMN_DESCRIPTION = "do_description";
        static final String COLUMN_LINK = "do_link";
        static final String COLUMN_DELETED = "do_deleted";

        static final String REFERENCE_COMMUNITY =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        COMMUNITY_TABLE.TABLE_NAME, COMMUNITY_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_COMMUNITY, COLUMN_TITLE,
                COLUMN_DESCRIPTION, COLUMN_LINK, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s INTEGER NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_COMMUNITY, REFERENCE_COMMUNITY, COLUMN_TITLE,
                        COLUMN_DESCRIPTION, COLUMN_LINK, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class NOTE_TABLE implements BaseColumns {
        static final String TABLE_NAME = "note";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_COMMUNITY = "no_community";
        static final String COLUMN_DATE = "no_date";
        static final String COLUMN_TITLE = "no_title";
        static final String COLUMN_CONTENT = "no_content";
        static final String COLUMN_DELETED = "no_deleted";

        static final String REFERENCE_COMMUNITY =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        COMMUNITY_TABLE.TABLE_NAME, COMMUNITY_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_COMMUNITY, COLUMN_DATE,
                COLUMN_TITLE, COLUMN_CONTENT, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s INTEGER NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_COMMUNITY, REFERENCE_COMMUNITY, COLUMN_DATE,
                        COLUMN_TITLE, COLUMN_CONTENT, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class MEETING_TABLE implements BaseColumns {
        static final String TABLE_NAME = "meeting";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_COMMUNITY = "me_community";
        static final String COLUMN_DATE = "me_date";
        static final String COLUMN_DELETED = "me_deleted";

        static final String REFERENCE_COMMUNITY =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        COMMUNITY_TABLE.TABLE_NAME, COMMUNITY_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_COMMUNITY,
                COLUMN_DATE, COLUMN_DELETED
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s INTEGER NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_COMMUNITY, REFERENCE_COMMUNITY,
                        COLUMN_DATE, COLUMN_DELETED);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    static class POINT_TABLE implements BaseColumns {
        static final String TABLE_NAME = "point";
        static final String COLUMN_ID = _ID;
        static final String COLUMN_MEETING = "po_meeting";
        static final String COLUMN_TITLE = "po_title";
        static final String COLUMN_CONTENT = "po_content";

        static final String REFERENCE_MEETING =
                String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                        MEETING_TABLE.TABLE_NAME, MEETING_TABLE._ID);

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_MEETING,
                COLUMN_TITLE, COLUMN_CONTENT
        };

        static final String SQL_CREATION =
                String.format("CREATE TABLE %s ("
                                + "%s TEXT PRIMARY KEY, %s INTEGER NOT NULL %s,"
                                + " %s TEXT NOT NULL, %s TEXT NOT NULL)",
                        TABLE_NAME, COLUMN_ID, COLUMN_MEETING, REFERENCE_MEETING,
                        COLUMN_TITLE, COLUMN_CONTENT);

        static final String SQL_DELETION =
                String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }
}