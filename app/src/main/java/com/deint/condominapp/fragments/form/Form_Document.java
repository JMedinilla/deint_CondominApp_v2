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
import com.deint.condominapp.pojos.Pojo_Document;
import com.deint.condominapp.preferences.files.Profile;

import java.util.UUID;

public class Form_Document extends Fragment {
    private boolean UPDATE_MODE = false;
    private Pojo_Document update = null;

    private FragmentFormDocumentListener listCallback;

    EditText title;
    EditText description;
    EditText link;
    FloatingActionButton btn;
    Profile profile;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentFormDocumentListener {
        void onAcceptDocument(Pojo_Document document, boolean update);
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
        View view = inflater.inflate(R.layout.fragment_form_document, container, false);

        title = (EditText) view.findViewById(R.id.fragFormDocument_title);
        description = (EditText) view.findViewById(R.id.fragFormDocument_description);
        link = (EditText) view.findViewById(R.id.fragFormDocument_link);
        btn = (FloatingActionButton) view.findViewById(R.id.fragFormDocument_btn);
        profile = new Profile(getContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UPDATE_MODE) {
                    listCallback.onAcceptDocument(update, true);
                } else {
                    Pojo_Document doc = new Pojo_Document(UUID.randomUUID().toString(), profile.getUserCommunity(), title.getText().toString(), description.getText().toString(), link.getText().toString(), false);
                    listCallback.onAcceptDocument(doc, false);
                }
            }
        });

        Pojo_Document pojo_document = getArguments().getParcelable("pojo_document");
        if (pojo_document != null) {
            update = pojo_document;
            UPDATE_MODE = true;
            title.setText(pojo_document.getDo_title());
            description.setText(pojo_document.getDo_description());
            link.setText(pojo_document.getDo_link());
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listCallback = (FragmentFormDocumentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listCallback = null;
    }
}
