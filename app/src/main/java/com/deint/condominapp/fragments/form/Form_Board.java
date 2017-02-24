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
import com.deint.condominapp.pojos.Pojo_Entry;
import com.deint.condominapp.preferences.files.Profile;

import java.util.Calendar;
import java.util.Date;

public class Form_Board extends Fragment {
    private boolean UPDATE_MODE = false;
    private Pojo_Entry update = null;

    private FragmentFormBoardListener listCallback;
    public static final String TAG_FRAGMENT_FORM_BOARD = "fragmentFormBoardTag";

    EditText title;
    EditText description;
    FloatingActionButton btn;
    Profile profile;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentFormBoardListener {
        void onAcceptBoard(Pojo_Entry entry, boolean update);
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
        View view = inflater.inflate(R.layout.fragment_form_board, container, false);

        title = (EditText) view.findViewById(R.id.fragFormBoard_title);
        description = (EditText) view.findViewById(R.id.fragFormBoard_description);
        btn = (FloatingActionButton) view.findViewById(R.id.fragFormBoard_btn);
        profile = new Profile(getContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UPDATE_MODE) {
                    listCallback.onAcceptBoard(update, true);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    Date date = new Date(calendar.getTimeInMillis());
                    Pojo_Entry entry = new Pojo_Entry(profile.getUserId(), profile.getUserCommunity(), title.getText().toString(), description.getText().toString(), date, Pojo_Entry.FIRST, false);
                    listCallback.onAcceptBoard(entry, false);
                }
            }
        });

        Pojo_Entry pojo_entry = getArguments().getParcelable("pojo_entryf");
        if (pojo_entry != null) {
            update = pojo_entry;
            UPDATE_MODE = true;
            title.setText(pojo_entry.getEn_title());
            description.setText(pojo_entry.getEn_content());
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listCallback = (FragmentFormBoardListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listCallback = null;
    }
}
