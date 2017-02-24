package com.deint.condominapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.deint.condominapp.pojos.Pojo_User;

public class Adapter_User extends ArrayAdapter<Pojo_User> {
    public Adapter_User(Context context, int resource) {
        super(context, resource);
    }
}
