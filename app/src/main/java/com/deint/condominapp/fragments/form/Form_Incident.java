package com.deint.condominapp.fragments.form;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.deint.condominapp.R;
import com.deint.condominapp.pojos.Pojo_Incident;
import com.deint.condominapp.preferences.files.Profile;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Form_Incident extends Fragment {
    private boolean UPDATE_MODE = false;
    private Pojo_Incident update = null;

    private FragmentFormIncidentListener listCallback;

    ImageView img;
    EditText title;
    EditText description;
    FloatingActionButton btn;
    Profile profile;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentFormIncidentListener {
        void onAcceptIncident(Pojo_Incident incident, boolean update);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_incident, container, false);

        img = (ImageView) view.findViewById(R.id.fragFormIncident_img);
        title = (EditText) view.findViewById(R.id.fragFormIncident_title);
        description = (EditText) view.findViewById(R.id.fragFormIncident_description);
        btn = (FloatingActionButton) view.findViewById(R.id.fragFormIncident_btn);
        profile = new Profile(getContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Subir la imagen y recoger la URL
                if (UPDATE_MODE) {
                    String url = "";
                    update.setIn_photo(url);
                    update.setIn_title(title.getText().toString());
                    update.setIn_description(description.getText().toString());
                    listCallback.onAcceptIncident(update, true);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(calendar.getTimeInMillis()));
                    String url = "";
                    Pojo_Incident incident = new Pojo_Incident(UUID.randomUUID().toString(), profile.getUserId(), profile.getUserCommunity(), date, title.getText().toString(), description.getText().toString(), url, false);
                    listCallback.onAcceptIncident(incident, false);
                }
            }
        });

        Pojo_Incident pojo_incident = getArguments().getParcelable("pojo_incident");
        if (pojo_incident != null) {
            update = pojo_incident;
            UPDATE_MODE = true;
            if (!pojo_incident.getIn_photo().isEmpty()) {
                Picasso.with(getContext()).load(pojo_incident.getIn_photo()).fit().centerCrop().into(img);
            } else {
                img.setImageResource(R.drawable.image);
            }
            title.setText(pojo_incident.getIn_title());
            description.setText(pojo_incident.getIn_description());
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listCallback = (FragmentFormIncidentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listCallback = null;
    }
}
