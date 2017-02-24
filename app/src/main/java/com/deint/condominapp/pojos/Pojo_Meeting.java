package com.deint.condominapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Pojo_Meeting implements Parcelable {
    private int me_id;
    private int me_community;
    private String me_date;
    private boolean me_deleted;

    public Pojo_Meeting(int me_id, int me_community, String me_date, boolean me_deleted) {
        this.me_id = me_id;
        this.me_community = me_community;
        this.me_date = me_date;
        this.me_deleted = me_deleted;
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public int getMe_id() {
        return me_id;
    }

    public int getMe_community() {
        return me_community;
    }

    public String getMe_date() {
        return me_date;
    }

    public boolean isMe_deleted() {
        return me_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setMe_id(int me_id) {
        this.me_id = me_id;
    }

    public void setMe_community(int me_community) {
        this.me_community = me_community;
    }

    public void setMe_date(String me_date) {
        this.me_date = me_date;
    }

    public void setMe_deleted(boolean me_deleted) {
        this.me_deleted = me_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Override methods
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Meeting (" + me_date + ")";
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Pojo_Meeting) {
                Pojo_Meeting another = (Pojo_Meeting) obj;
                if (this.me_date.equals(another.me_date)) {
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

    public static final Comparator<Pojo_Meeting> COMPARATOR_MEETING_DATE = new Comparator<Pojo_Meeting>() {
        @Override
        public int compare(Pojo_Meeting o1, Pojo_Meeting o2) {
            return o1.getMe_date().compareTo(o2.getMe_date());
        }
    };

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Parcerable implementation
    //---------------------------------------------------------------------------------------------

    private Pojo_Meeting(Parcel in) {
        me_id = in.readInt();
        me_community = in.readInt();
        me_date = in.readString();
        me_deleted = in.readByte() != 0;
    }

    public static final Creator<Pojo_Meeting> CREATOR = new Creator<Pojo_Meeting>() {
        @Override
        public Pojo_Meeting createFromParcel(Parcel in) {
            return new Pojo_Meeting(in);
        }

        @Override
        public Pojo_Meeting[] newArray(int size) {
            return new Pojo_Meeting[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(me_id);
        dest.writeInt(me_community);
        dest.writeString(me_date);
        dest.writeByte((byte) (me_deleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
