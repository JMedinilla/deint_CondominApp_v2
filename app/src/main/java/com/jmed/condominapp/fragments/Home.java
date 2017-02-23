package com.jmed.condominapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jmed.condominapp.R;

public class Home extends Fragment {
    private FragmentHomeListener homeCallback;

    public interface FragmentHomeListener {
        void onHomeFragmentIncidentsButtonTap();

        void onHomeFragmentDiaryButtonTap();

        void onHomeFragmentBoardButtonTap();

        void onHomeFragmentCBoardButtonTap();

        void onHomeFragmentDocumentsButtonTap();

        void onHomeFragmentMeetingsButtonTap();

        void onHomeFragmentInformationButtonTap();

        void onHomeFragmentUsersButtonTap();

        void onHomeFragmentCommunitiesButtonTap();

        void onHomeFragmentProfileButtonTap();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentHomeListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}