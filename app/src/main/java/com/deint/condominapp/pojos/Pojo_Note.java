package com.deint.condominapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class Pojo_Note implements Parcelable {
    private String no_id;
    private int no_community;
    private Date no_date;
    private String no_title;
    private String no_content;
    private boolean no_deleted;

    public Pojo_Note(int no_community, Date no_date, String no_title, String no_content, boolean no_deleted) {
        this.no_id = UUID.randomUUID().toString();
        this.no_community = no_community;
        this.no_date = no_date;
        this.no_title = no_title;
        this.no_content = no_content;
        this.no_deleted = no_deleted;
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public String getNo_id() {
        return no_id;
    }

    public int getNo_community() {
        return no_community;
    }

    public Date getNo_date() {
        return no_date;
    }

    public String getNo_title() {
        return no_title;
    }

    public String getNo_content() {
        return no_content;
    }

    public boolean isNo_deleted() {
        return no_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }

    public void setNo_community(int no_community) {
        this.no_community = no_community;
    }

    public void setNo_date(Date no_date) {
        this.no_date = no_date;
    }

    public void setNo_title(String no_title) {
        this.no_title = no_title;
    }

    public void setNo_content(String no_content) {
        this.no_content = no_content;
    }

    public void setNo_deleted(boolean no_deleted) {
        this.no_deleted = no_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Override methods
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Note: " + no_title + " (" + no_date.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Pojo_Note) {
                Pojo_Note another = (Pojo_Note) obj;
                if (this.no_title.toUpperCase().equals(another.no_title.toUpperCase())) {
                    result = true;
                }
            }
        }
        return result;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region List Comparators
    //---------------------------------------------------------------------------------------------

    public static final Comparator<Pojo_Note> COMPARATOR_NOTE_DATE = new Comparator<Pojo_Note>() {
        @Override
        public int compare(Pojo_Note o1, Pojo_Note o2) {
            return o1.getNo_date().compareTo(o2.getNo_date());
        }
    };

    public static final Comparator<Pojo_Note> COMPARATOR_NOTE_TITLE = new Comparator<Pojo_Note>() {
        @Override
        public int compare(Pojo_Note o1, Pojo_Note o2) {
            return o1.getNo_title().toUpperCase().compareTo(o2.getNo_title().toUpperCase());
        }
    };

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Parcerable implementation
    //---------------------------------------------------------------------------------------------

    private Pojo_Note(Parcel in) {
        no_id = in.readString();
        no_community = in.readInt();
        no_title = in.readString();
        no_content = in.readString();
        no_deleted = in.readByte() != 0;
    }

    public static final Creator<Pojo_Note> CREATOR = new Creator<Pojo_Note>() {
        @Override
        public Pojo_Note createFromParcel(Parcel in) {
            return new Pojo_Note(in);
        }

        @Override
        public Pojo_Note[] newArray(int size) {
            return new Pojo_Note[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(no_id);
        parcel.writeInt(no_community);
        parcel.writeString(no_title);
        parcel.writeString(no_content);
        parcel.writeByte((byte) (no_deleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
