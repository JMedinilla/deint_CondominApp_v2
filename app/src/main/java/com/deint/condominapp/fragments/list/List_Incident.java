package com.deint.condominapp.fragments.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.deint.condominapp.Activity_Home;
import com.deint.condominapp.Activity_ViewImage;
import com.deint.condominapp.R;
import com.deint.condominapp.adapters.Adapter_Incident;
import com.deint.condominapp.interfaces.IIncidentPresenter;
import com.deint.condominapp.pojos.Pojo_Incident;
import com.deint.condominapp.presenters.IncidentPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class List_Incident extends Fragment implements IIncidentPresenter.View {
    private FragmentListIncidentListener homeCallback;

    IncidentPresenterImpl incidentPresenter;
    Adapter_Incident adapter_incident;
    ListView listView;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentListIncidentListener {
        void onManageIncidentOpen();

        void onManageIncidentOpen(Pojo_Incident incident);
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
        incidentPresenter = new IncidentPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_list_incident, container, false);

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fragListIncident_btn);
        listView = (ListView) view.findViewById(R.id.fragListIncident_list);

        listView.setDivider(null);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCallback.onManageIncidentOpen();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pojo_Incident incident = adapter_incident.getItem(i);
                showDetailIncident(incident);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_incident = new Adapter_Incident(getContext());
        listView.setAdapter(adapter_incident);

        incidentPresenter.selectIncidents();
    }

    /**
     * Method that recieves an element from the Activity and insert or update it
     *
     * @param incident Incident to handle
     * @param update   Boolean to know if the element has to be inserted or updated
     */
    public void recieveIncidentFromHome(Pojo_Incident incident, boolean update) {
        if (incidentPresenter.validateIncident(incident)) {
            if (update) {
                incidentPresenter.updateIncident(incident);
            } else {
                incidentPresenter.insertIncident(incident);
            }
        }
    }

    @Override
    /**
     * Method to send a message to the Activity
     */
    public void showMessage(int msg, boolean error) {
        ((Activity_Home) getActivity()).showSnackbar(getString(msg), error);
    }

    /**
     * Method that shows a detailed view of the element
     *
     * @param incident Element to be shown
     */
    private void showDetailIncident(final Pojo_Incident incident) {
        MaterialDialog.Builder dialog = new MaterialDialog.Builder(getActivity())
                .title(incident.getIn_title())
                .customView(R.layout.detail_incident, true)
                .cancelable(true)
                .canceledOnTouchOutside(true)
                .titleColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark))
                .positiveText(R.string.detail_delete)
                .negativeText(R.string.detail_edit)
                .neutralText(R.string.detail_close)
                .autoDismiss(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        incidentPresenter.deleteIncident(incident);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        homeCallback.onManageIncidentOpen(incident);
                    }
                });
        View content = dialog.build().getCustomView();
        if (content != null) {
            final String imageUrl = incident.getIn_photo();

            ImageView img = (ImageView) content.findViewById(R.id.detail_incident_image);
            TextView txtUser = (TextView) content.findViewById(R.id.detail_incident_user);
            TextView txtDate = (TextView) content.findViewById(R.id.detail_incident_date);
            TextView txtDescription = (TextView) content.findViewById(R.id.detail_incident_description);

            if (incident.getIn_photo().isEmpty()) {
                img.setImageResource(R.drawable.image);
            } else {
                Picasso.with(getContext()).load(incident.getIn_photo()).fit().centerCrop().into(img);
            }
            txtUser.setText(incident.getIn_userid());
            txtDate.setText(incident.getIn_date());
            txtDescription.setText(incident.getIn_description());

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!imageUrl.isEmpty()) {
                        Intent intent = new Intent(getContext(), Activity_ViewImage.class);
                        intent.putExtra("image_view", imageUrl);
                        startActivity(intent);
                    }
                }
            });
        }
        dialog.show();
    }

    public void refreshElements(List<Pojo_Incident> pojo_incidents) {
        adapter_incident.updateElements(pojo_incidents);
    }

    @Override
    public void insertResponse(boolean result) {
        if (result) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void updateResponse(boolean result) {
        if (result) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void deleteResponse(boolean result) {
        if (result) {
            getActivity().onBackPressed();
            incidentPresenter.selectIncidents();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListIncidentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeCallback = null;
        adapter_incident = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_incidents, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuIncidents_date:
                adapter_incident.sortIncidents(Pojo_Incident.COMPARATOR_INCIDENT_DATE);
                break;
            case R.id.menuIncidents_title:
                adapter_incident.sortIncidents(Pojo_Incident.COMPARATOR_INCIDENT_TITLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}