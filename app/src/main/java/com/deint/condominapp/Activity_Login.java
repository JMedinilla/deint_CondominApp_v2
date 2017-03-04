package com.deint.condominapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.deint.condominapp.database.DatabaseManager_User;
import com.deint.condominapp.pojos.Pojo_User;
import com.deint.condominapp.preferences.files.Profile;
import com.deint.condominapp.preferences.files.Settings;

import java.util.List;

public class Activity_Login extends AppCompatActivity {
    private EditText activity_login_edtKey;
    private CheckBox activity_login_chbRemember;
    private CheckBox activity_login_chbStay;
    private Profile preferences_profile;
    private Settings preferences_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences_settings = new Settings(Activity_Login.this);
        preferences_profile = new Profile(Activity_Login.this);

        initializeComponents();

        if (preferences_settings.getRemember()) {
            activity_login_edtKey.setText(preferences_profile.getUserId());
        } else {
            preferences_settings.setStay(false);
        }
        if (preferences_settings.getStay()) {
            startActivity(new Intent(Activity_Login.this, Activity_Home.class));
            finish();
        }
        if (activity_login_chbRemember.isChecked()) {
            activity_login_chbStay.setEnabled(true);
        }

        activity_login_edtKey.clearFocus();
    }

    /**
     * Method that initializes the layout components, their values and listeners
     */
    private void initializeComponents() {
        activity_login_edtKey = (EditText) findViewById(R.id.activity_login_edtKey);
        activity_login_chbRemember = (CheckBox) findViewById(R.id.activity_login_chbRemember);
        activity_login_chbStay = (CheckBox) findViewById(R.id.activity_login_chbStay);

        activity_login_chbRemember.setChecked(preferences_settings.getRemember());
        activity_login_chbStay.setChecked(preferences_settings.getStay());

        activity_login_chbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences_settings.setRemember(isChecked);
                if (isChecked) {
                    activity_login_chbStay.setEnabled(true);
                } else {
                    activity_login_chbStay.setChecked(false);
                    activity_login_chbStay.setEnabled(false);
                }
            }
        });
        activity_login_chbStay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences_settings.setStay(isChecked);
            }
        });
    }

    /**
     * OnClick method for login buttons
     *
     * @param view Button
     */
    public void getOnClickLoginMain(View view) {
        switch (view.getId()) {
            case R.id.activity_login_btnScan:
                qrCodeScan();
                break;
            case R.id.activity_login_btnJoin:
                neighbourLogin(activity_login_edtKey.getText().toString());
                break;
        }
    }

    /**
     * Method that find an user with the given key
     * It will start a different Activity if the user is an admin or not
     *
     * @param keyP User login key
     */
    private void neighbourLogin(String keyP) {
        boolean result = false;
        Pojo_User tmpUser;
        List<Pojo_User> tmpUsers = DatabaseManager_User.getInstance().getUsers();

        for (int i = 0; i < tmpUsers.size(); i++) {
            if (keyP.equals(tmpUsers.get(i).getUs_id())) {
                tmpUser = new Pojo_User(
                        tmpUsers.get(i).getUs_id(),
                        tmpUsers.get(i).getUs_community(),
                        tmpUsers.get(i).getUs_floor(),
                        tmpUsers.get(i).getUs_door(),
                        tmpUsers.get(i).getUs_phone(),
                        tmpUsers.get(i).getUs_mail(),
                        tmpUsers.get(i).getUs_name(),
                        tmpUsers.get(i).getUs_category(),
                        tmpUsers.get(i).getUs_photo(),
                        tmpUsers.get(i).isUs_deleted());

                preferences_profile.setUserId(tmpUser.getUs_id());
                preferences_profile.setUserCommunity(tmpUser.getUs_community());
                preferences_profile.setUserFloor(tmpUser.getUs_floor());
                preferences_profile.setUserDoor(tmpUser.getUs_door());
                preferences_profile.setUserPhone(tmpUser.getUs_phone());
                preferences_profile.setUserMail(tmpUser.getUs_mail());
                preferences_profile.setUserName(tmpUser.getUs_name());
                preferences_profile.setUserCategory(tmpUser.getUs_category());

                result = true;
                if (preferences_profile.getUserCategory() == Pojo_User.ADMINISTRATOR) {
                    startActivity(new Intent(Activity_Login.this, Activity_Home.class));
                } else {
                    startActivity(new Intent(Activity_Login.this, Activity_Home.class));
                }
                finish();
            }
        }
        if (!result) {
            Pojo_User us = new Pojo_User("", 999999, "", "", "", "", "", Pojo_User.NEIGHBOUR, "", false);
            preferences_profile.setUserId(us.getUs_id());
            preferences_profile.setUserCommunity(us.getUs_community());
            preferences_profile.setUserFloor(us.getUs_floor());
            preferences_profile.setUserDoor(us.getUs_door());
            preferences_profile.setUserPhone(us.getUs_phone());
            preferences_profile.setUserMail(us.getUs_mail());
            preferences_profile.setUserName(us.getUs_name());
            preferences_profile.setUserCategory(us.getUs_category());
            Snackbar.make(findViewById(R.id.activity_login), R.string.no_user, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Method that executes a QR scan
     */
    private void qrCodeScan() {
        Toast.makeText(Activity_Login.this, "Todavía no :C", Toast.LENGTH_SHORT).show();
        //startActivityForResult(new Intent(Activity_Login.this, Activity_QR.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                activity_login_edtKey.setText(data.getStringExtra("key_qr_read"));
            }
        }
    }
}