package com.jmed.condominapp.preferences.application;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.jmed.condominapp.R;

public class Settings extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_settings);
    }
}
