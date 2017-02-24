package com.deint.condominapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.UUID;

public class Pojo_Point implements Parcelable {
    private String po_id;
    private int po_meeting;
    private String po_title;
    private String po_content;

    public Pojo_Point(String po_id, int po_meeting, String po_title, String po_content) {
        this.po_id = po_id;
        this.po_meeting = po_meeting;
        this.po_title = po_title;
        this.po_content = po_content;
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public String getPo_id() {
        return po_id;
    }

    public int getPo_meeting() {
        return po_meeting;
    }

    private String getPo_title() {
        return po_title;
    }

    public String getPo_content() {
        return po_content;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setPo_id(String po_id) {
        this.po_id = po_id;
    }

    public void setPo_meeting(int po_meeting) {
        this.po_meeting = po_meeting;
    }

    public void setPo_title(String po_title) {
        this.po_title = po_title;
    }

    public void setPo_content(String po_content) {
        this.po_content = po_content;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Override methods
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Point: " + po_title;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Pojo_Point) {
                Pojo_Point another = (Pojo_Point) obj;
                if (this.po_title.toUpperCase().equals(another.po_title.toUpperCase())) {
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

    public static final Comparator<Pojo_Point> COMPARATOR_POINT_TITLE = new Comparator<Pojo_Point>() {
        @Override
        public int compare(Pojo_Point o1, Pojo_Point o2) {
            return o1.getPo_title().toUpperCase().compareTo(o2.getPo_title().toUpperCase());
        }
    };

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Parcerable implementation
    //---------------------------------------------------------------------------------------------

    private Pojo_Point(Parcel in) {
        po_id = in.readString();
        po_meeting = in.readInt();
        po_title = in.readString();
        po_content = in.readString();
    }

    public static final Creator<Pojo_Point> CREATOR = new Creator<Pojo_Point>() {
        @Override
        public Pojo_Point createFromParcel(Parcel in) {
            return new Pojo_Point(in);
        }

        @Override
        public Pojo_Point[] newArray(int size) {
            return new Pojo_Point[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(po_id);
        parcel.writeInt(po_meeting);
        parcel.writeString(po_title);
        parcel.writeString(po_content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
