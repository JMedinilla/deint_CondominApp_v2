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

import com.deint.condominapp.R;
import com.deint.condominapp.pojos.Pojo_Note;
import com.deint.condominapp.preferences.files.Profile;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Form_Diary extends Fragment {
    private boolean UPDATE_MODE = false;
    private Pojo_Note update = null;

    private FragmentFormDiaryListener listCallback;

    EditText title;
    EditText description;
    FloatingActionButton btn;
    Profile profile;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentFormDiaryListener {
        void onAcceptDiary(Pojo_Note note, boolean update);
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
        View view = inflater.inflate(R.layout.fragment_form_diary, container, false);

        title = (EditText) view.findViewById(R.id.fragFormDiary_title);
        description = (EditText) view.findViewById(R.id.fragFormDiary_description);
        btn = (FloatingActionButton) view.findViewById(R.id.fragFormDiary_btn);
        profile = new Profile(getContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UPDATE_MODE) {
                    update.setNo_title(title.getText().toString());
                    update.setNo_content(description.getText().toString());
                    listCallback.onAcceptDiary(update, true);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(calendar.getTimeInMillis()));
                    Pojo_Note note = new Pojo_Note(UUID.randomUUID().toString(), profile.getUserCommunity(), date, title.getText().toString(), description.getText().toString(), false);
                    listCallback.onAcceptDiary(note, false);
                }
            }
        });

        Pojo_Note pojo_note = getArguments().getParcelable("pojo_note");
        if (pojo_note != null) {
            update = pojo_note;
            UPDATE_MODE = true;
            title.setText(pojo_note.getNo_title());
            description.setText(pojo_note.getNo_content());
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listCallback = (FragmentFormDiaryListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listCallback = null;
    }
}
