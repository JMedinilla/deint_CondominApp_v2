package com.deint.condominapp.fragments.form;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deint.condominapp.R;
import com.deint.condominapp.pojos.Pojo_Meeting;

public class Form_Meeting extends Fragment {
    private boolean UPDATE_MODE = false;
    private Pojo_Meeting update = null;

    private FragmentFormMeetingListener listCallback;
    public static final String TAG_FRAGMENT_FORM_MEETING = "fragmentFormMeetingTag";

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentFormMeetingListener {
        void onAcceptMeeting(Pojo_Meeting meeting, boolean update);
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
        View view = inflater.inflate(R.layout.fragment_form_meeting, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listCallback = (FragmentFormMeetingListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listCallback = null;
    }
}
