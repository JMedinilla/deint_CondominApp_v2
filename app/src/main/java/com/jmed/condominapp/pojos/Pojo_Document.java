package com.jmed.condominapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.UUID;

public class Pojo_Document implements Parcelable {
    private String do_id;
    private int do_community;
    private String do_title;
    private String do_description;
    private String do_link;
    private boolean do_deleted;

    public Pojo_Document(int do_community, String do_title, String do_description, String do_link, boolean do_deleted) {
        this.do_id = UUID.randomUUID().toString();
        this.do_community = do_community;
        this.do_title = do_title;
        this.do_description = do_description;
        this.do_link = do_link;
        this.do_deleted = do_deleted;
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public String getDo_id() {
        return do_id;
    }

    public int getDo_community() {
        return do_community;
    }

    public String getDo_title() {
        return do_title;
    }

    public String getDo_description() {
        return do_description;
    }

    public String getDo_link() {
        return do_link;
    }

    public boolean isDo_deleted() {
        return do_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setDo_id(String do_id) {
        this.do_id = do_id;
    }

    public void setDo_community(int do_community) {
        this.do_community = do_community;
    }

    public void setDo_title(String do_title) {
        this.do_title = do_title;
    }

    public void setDo_description(String do_description) {
        this.do_description = do_description;
    }

    public void setDo_link(String do_link) {
        this.do_link = do_link;
    }

    public void setDo_deleted(boolean do_deleted) {
        this.do_deleted = do_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Override methods
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Document: " + do_title + " -> " + do_link;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Pojo_Document) {
                Pojo_Document another = (Pojo_Document) obj;
                if (this.do_title.toUpperCase().equals(another.do_title.toUpperCase())
                        && this.do_deleted == another.do_deleted) {
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

    public static final Comparator<Pojo_Document> COMPARATOR_DOCUMENT_TITLE = new Comparator<Pojo_Document>() {
        @Override
        public int compare(Pojo_Document o1, Pojo_Document o2) {
            return o1.getDo_title().toUpperCase().compareTo(o2.getDo_title().toUpperCase());
        }
    };

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Parcerable implementation
    //---------------------------------------------------------------------------------------------

    private Pojo_Document(Parcel in) {
        do_id = in.readString();
        do_community = in.readInt();
        do_title = in.readString();
        do_description = in.readString();
        do_link = in.readString();
        do_deleted = in.readByte() != 0;
    }

    public static final Creator<Pojo_Document> CREATOR = new Creator<Pojo_Document>() {
        @Override
        public Pojo_Document createFromParcel(Parcel in) {
            return new Pojo_Document(in);
        }

        @Override
        public Pojo_Document[] newArray(int size) {
            return new Pojo_Document[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(do_id);
        parcel.writeInt(do_community);
        parcel.writeString(do_title);
        parcel.writeString(do_description);
        parcel.writeString(do_link);
        parcel.writeByte((byte) (do_deleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
