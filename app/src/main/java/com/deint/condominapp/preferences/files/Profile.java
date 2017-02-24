package com.deint.condominapp.preferences.files;

import android.content.Context;
import android.content.SharedPreferences;

import com.deint.condominapp.pojos.Pojo_User;

public class Profile {
    private static final int MODE = Context.MODE_PRIVATE;
    private static final String FILE = "com.deint.condominapp_preferences";

    private static final String USER_ID = "";
    private static final String USER_ACCESS = "accesskey";
    private static final String USER_COMMUNITY = "user_data_community";
    private static final String USER_FLOOR = "user_data_floor";
    private static final String USER_DOOR = "user_data_door";
    private static final String USER_PHONE = "user_data_phone";
    private static final String USER_MAIL = "user_data_mail";
    private static final String USER_NAME = "user_data_name";
    private static final String USER_CATEGORY = "user_data_category";
    private static final String USER_PHOTO = "user_data_photo";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Profile(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE, MODE);
        editor = sharedPreferences.edit();
    }

    //region Getter
    //---------------------------------------------------------------------------------------------

    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "default");
    }

    public String getUserAccess() {
        return sharedPreferences.getString(USER_ACCESS, "default");
    }

    public int getUserCommunity() {
        return sharedPreferences.getInt(USER_COMMUNITY, 999999999);
    }

    public String getUserFloor() {
        return sharedPreferences.getString(USER_FLOOR, "default");
    }

    public String getUserDoor() {
        return sharedPreferences.getString(USER_DOOR, "default");
    }

    public String getUserPhone() {
        return sharedPreferences.getString(USER_PHONE, "default");
    }

    public String getUserMail() {
        return sharedPreferences.getString(USER_MAIL, "default");
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "default");
    }

    public int getUserCategory() {
        return sharedPreferences.getInt(USER_CATEGORY, Pojo_User.NEIGHBOUR);
    }

    public String getUserPhoto() {
        return sharedPreferences.getString(USER_PHOTO, "default");
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Setter
    //---------------------------------------------------------------------------------------------

    public void setUserId(String value) {
        editor.putString(USER_ID, value).commit();
    }

    public void setUserAccess(String value) {
        editor.putString(USER_ACCESS, value).commit();
    }

    public void setUserCommunity(int value) {
        editor.putInt(USER_COMMUNITY, value).commit();
    }

    public void setUserFloor(String value) {
        editor.putString(USER_FLOOR, value).commit();
    }

    public void setUserDoor(String value) {
        editor.putString(USER_DOOR, value).commit();
    }

    public void setUserPhone(String value) {
        editor.putString(USER_PHONE, value).commit();
    }

    public void setUserMail(String value) {
        editor.putString(USER_MAIL, value).commit();
    }

    public void setUserName(String value) {
        editor.putString(USER_NAME, value).commit();
    }

    public void setUserCategory(int value) {
        editor.putInt(USER_CATEGORY, value).commit();
    }

    public void setUserPhoto(String value) {
        editor.putString(USER_PHOTO, value).commit();
    }

    //---------------------------------------------------------------------------------------------
    //endregion
}
