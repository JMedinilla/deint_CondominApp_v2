package com.jmed.condominapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.jmed.condominapp.pojos.Pojo_Community;

public class Adapter_Community extends ArrayAdapter<Pojo_Community> {
    public Adapter_Community(Context context, int resource) {
        super(context, resource);
    }
}
