package com.jmed.condominapp;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmed.condominapp.fragments.QR;

public class Activity_QR extends AppCompatActivity implements QR.QRListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        QR qrFragment = new QR();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.qr_fragment, qrFragment, "qr_fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onQRRead(String text) {
        setResult(RESULT_OK, new Intent().putExtra("key_qr_read", text));
        finish();
    }
}
