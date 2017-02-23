package com.jmed.condominapp.fragments.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmed.condominapp.R;
import com.jmed.condominapp.pojos.Pojo_Community;

public class List_Community extends Fragment {
    private FragmentListCommunityListener homeCallback;

    public interface FragmentListCommunityListener {
        void onManageCommunityOpen();

        void onManageCommunityOpen(Pojo_Community community);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_community, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListCommunityListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}