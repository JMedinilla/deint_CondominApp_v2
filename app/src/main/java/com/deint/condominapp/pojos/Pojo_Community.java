package com.deint.condominapp.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Pojo_Community implements Parcelable {
    private int co_id;
    private String co_locality;
    private String co_municipality;
    private String co_address;
    private String co_number;
    private String co_block;
    private String co_postal;
    private int co_apartments;
    private boolean co_deleted;

    public Pojo_Community(int co_id, String co_locality, String co_municipality, String co_address,
                          String co_number, String co_block, String co_postal, int co_apartments, boolean co_deleted) {
        this.co_id = co_id;
        this.co_locality = co_locality;
        this.co_municipality = co_municipality;
        this.co_address = co_address;
        this.co_number = co_number;
        this.co_block = co_block;
        this.co_postal = co_postal;
        this.co_apartments = co_apartments;
        this.co_deleted = co_deleted;
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public int getCo_id() {
        return co_id;
    }

    public String getCo_locality() {
        return co_locality;
    }

    public String getCo_municipality() {
        return co_municipality;
    }

    public String getCo_address() {
        return co_address;
    }

    public String getCo_number() {
        return co_number;
    }

    public String getCo_block() {
        return co_block;
    }

    private String getCo_postal() {
        return co_postal;
    }

    private int getCo_apartments() {
        return co_apartments;
    }

    public boolean isCo_deleted() {
        return co_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setCo_id(int co_id) {
        this.co_id = co_id;
    }

    public void setCo_locality(String co_locality) {
        this.co_locality = co_locality;
    }

    public void setCo_municipality(String co_municipality) {
        this.co_municipality = co_municipality;
    }

    public void setCo_address(String co_address) {
        this.co_address = co_address;
    }

    public void setCo_number(String co_number) {
        this.co_number = co_number;
    }

    public void setCo_block(String co_block) {
        this.co_block = co_block;
    }

    public void setCo_postal(String co_postal) {
        this.co_postal = co_postal;
    }

    public void setCo_apartments(int co_apartments) {
        this.co_apartments = co_apartments;
    }

    public void setCo_deleted(boolean co_deleted) {
        this.co_deleted = co_deleted;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Override methods
    //---------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Community: "
                + co_postal + " (postal), "
                + co_address + " n" + co_number + " (address)";
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Pojo_Community) {
                Pojo_Community another = (Pojo_Community) obj;
                if (this.co_postal.toUpperCase().equals(another.co_postal.toUpperCase())
                        && this.co_address.toUpperCase().equals(another.co_address.toUpperCase())
                        && this.co_number.toUpperCase().equals(another.co_number.toUpperCase())
                        && this.co_deleted == another.co_deleted) {
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

    public static final Comparator<Pojo_Community> COMPARATOR_COMMUNITY_POSTAL = new Comparator<Pojo_Community>() {
        @Override
        public int compare(Pojo_Community o1, Pojo_Community o2) {
            return o1.getCo_postal().toUpperCase().compareTo(o2.getCo_postal().toUpperCase());
        }
    };

    public static final Comparator<Pojo_Community> COMPARATOR_COMMUNITY_APARTMENTS = new Comparator<Pojo_Community>() {
        @Override
        public int compare(Pojo_Community o1, Pojo_Community o2) {
            return o1.getCo_apartments() - o2.getCo_apartments();
        }
    };

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Parcerable implementation
    //---------------------------------------------------------------------------------------------

    private Pojo_Community(Parcel in) {
        co_id = in.readInt();
        co_locality = in.readString();
        co_municipality = in.readString();
        co_address = in.readString();
        co_number = in.readString();
        co_block = in.readString();
        co_postal = in.readString();
        co_apartments = in.readInt();
        co_deleted = in.readByte() != 0;
    }

    public static final Creator<Pojo_Community> CREATOR = new Creator<Pojo_Community>() {
        @Override
        public Pojo_Community createFromParcel(Parcel in) {
            return new Pojo_Community(in);
        }

        @Override
        public Pojo_Community[] newArray(int size) {
            return new Pojo_Community[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(co_id);
        parcel.writeString(co_locality);
        parcel.writeString(co_municipality);
        parcel.writeString(co_address);
        parcel.writeString(co_number);
        parcel.writeString(co_block);
        parcel.writeString(co_postal);
        parcel.writeInt(co_apartments);
        parcel.writeByte((byte) (co_deleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
