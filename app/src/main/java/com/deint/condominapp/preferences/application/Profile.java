package com.deint.condominapp.preferences.application;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.deint.condominapp.R;

public class Profile extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_profile);
    }
}
