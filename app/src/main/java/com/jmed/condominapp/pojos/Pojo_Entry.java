package com.jmed.condominapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class Pojo_Entry implements Parcelable {
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    private String en_id;
    private String en_userid;
    private int en_usercommunity;
    private String en_title;
    private String en_content;
    private Date en_date;
    private int en_category;
    private boolean en_deleted;

    public Pojo_Entry(String en_userid, int en_usercommunity, String en_title, String en_content,
                      Date en_date, int en_category, boolean en_deleted) {
        this.en_id = UUID.randomUUID().toString();
        this.en_userid = en_userid;
        this.en_usercommunity = en_usercommunity;
        this.en_title = en_title;
        this.en_content = en_content;
        this.en_date = en_date;
        this.en_category = en_category;
        this.en_deleted = en_deleted;
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public String getEn_id() {
        return en_id;
    }

    public String getEn_userid() {
        return en_userid;
    }

    public int getEn_usercommunity() {
        return en_usercommunity;
    }

    public String getEn_title() {
        return en_title;
    }

    public String getEn_content() {
        return en_content;
    }

    public Date getEn_date() {
        return en_date;
    }

    public int getEn_category() {
        return en_category;
    }

    public boolean isEn_deleted() {
        return en_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setEn_id(String en_id) {
        this.en_id = en_id;
    }

    public void setEn_userid(String en_userid) {
        this.en_userid = en_userid;
    }

    public void setEn_usercommunity(int en_usercommunity) {
        this.en_usercommunity = en_usercommunity;
    }

    public void setEn_title(String en_title) {
        this.en_title = en_title;
    }

    public void setEn_content(String en_content) {
        this.en_content = en_content;
    }

    public void setEn_date(Date en_date) {
        this.en_date = en_date;
    }

    public void setEn_category(int en_category) {
        this.en_category = en_category;
    }

    public void setEn_deleted(boolean en_deleted) {
        this.en_deleted = en_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Override methods
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Pojo_Entry: " + en_title + " (" + en_date.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Pojo_Entry) {
                Pojo_Entry another = (Pojo_Entry) obj;
                if (this.en_title.toUpperCase().equals(another.en_title.toUpperCase())
                        && this.en_category == another.en_category
                        && this.en_deleted == another.en_deleted) {
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

    public static final Comparator<Pojo_Entry> COMPARATOR_ENTRY_TITLE = new Comparator<Pojo_Entry>() {
        @Override
        public int compare(Pojo_Entry o1, Pojo_Entry o2) {
            return o1.getEn_title().toUpperCase().compareTo(o2.getEn_title().toUpperCase());
        }
    };
    public static final Comparator<Pojo_Entry> COMPARATOR_ENTRY_DATE = new Comparator<Pojo_Entry>() {
        @Override
        public int compare(Pojo_Entry o1, Pojo_Entry o2) {
            return o1.getEn_date().compareTo(o2.getEn_date());
        }
    };

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Parcerable implementation
    //---------------------------------------------------------------------------------------------

    private Pojo_Entry(Parcel in) {
        en_id = in.readString();
        en_userid = in.readString();
        en_usercommunity = in.readInt();
        en_title = in.readString();
        en_content = in.readString();
        en_category = in.readInt();
        en_deleted = in.readByte() != 0;
    }

    public static final Creator<Pojo_Entry> CREATOR = new Creator<Pojo_Entry>() {
        @Override
        public Pojo_Entry createFromParcel(Parcel in) {
            return new Pojo_Entry(in);
        }

        @Override
        public Pojo_Entry[] newArray(int size) {
            return new Pojo_Entry[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(en_id);
        dest.writeString(en_userid);
        dest.writeInt(en_usercommunity);
        dest.writeString(en_title);
        dest.writeString(en_content);
        dest.writeInt(en_category);
        dest.writeByte((byte) (en_deleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
