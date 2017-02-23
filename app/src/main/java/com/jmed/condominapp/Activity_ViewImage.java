package com.jmed.condominapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Activity_ViewImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        ImageView imageView = (ImageView) findViewById(R.id.imageView_activity_view);
        String url = getIntent().getExtras().getString("image_view");
        Picasso.with(Activity_ViewImage.this).load(url).into(imageView);
    }
}
