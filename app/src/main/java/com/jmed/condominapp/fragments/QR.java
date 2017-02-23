package com.jmed.condominapp.fragments;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.jmed.condominapp.R;

public class QR extends Fragment implements QRCodeReaderView.OnQRCodeReadListener {
    private QRListener qrListener;
    private QRCodeReaderView qrCodeReaderView;
    private Switch flash;

    public interface QRListener {
        void onQRRead(String text);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, container, false);
        qrCodeReaderView = (QRCodeReaderView) view.findViewById(R.id.qrReader);
        flash = (Switch) view.findViewById(R.id.fragment_qr_swtFlash);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_qr_container);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setQRDecodingEnabled(true);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setTorchEnabled(false);
        qrCodeReaderView.setBackCamera();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flash.setChecked(!flash.isChecked());
            }
        });
        flash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                qrCodeReaderView.setTorchEnabled(flash.isChecked());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qrCodeReaderView.startCamera();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrListener.onQRRead(text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        qrListener = (QRListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        qrListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}
