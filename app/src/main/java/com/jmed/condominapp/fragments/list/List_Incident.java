package com.jmed.condominapp.fragments.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
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
import com.jmed.condominapp.Activity_Home;
import com.jmed.condominapp.Activity_ViewImage;
import com.jmed.condominapp.R;
import com.jmed.condominapp.adapters.Adapter_Incident;
import com.jmed.condominapp.interfaces.IIncidentPresenter;
import com.jmed.condominapp.pojos.Pojo_Incident;
import com.jmed.condominapp.presenters.IncidentPresenterImpl;
import com.squareup.picasso.Picasso;

public class List_Incident extends Fragment implements IIncidentPresenter.View {
    private FragmentListIncidentListener homeCallback;

    IncidentPresenterImpl incidentPresenter;
    Adapter_Incident adapter_incident;

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
        ListView listView = (ListView) view.findViewById(R.id.fragListIncident_list);

        adapter_incident = new Adapter_Incident(getContext(), incidentPresenter.selectIncidents());
        listView.setDivider(null);
        listView.setAdapter(adapter_incident);

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

    /**
     * Method that recieves an element from the Activity and insert or update it
     *
     * @param incident Incident to handle
     * @param update   Boolean to know if the element has to be inserted or updated
     * @return True or False depending on the succes of the operation
     */
    public boolean recieveIncidentFromHome(Pojo_Incident incident, boolean update) {
        boolean result = false;
        if (incidentPresenter.validateIncident(incident)) {
            if (update) {
                result = incidentPresenter.updateIncident(incident) == 0;
            } else {
                result = incidentPresenter.insertIncident(incident) == 0;
            }
        }
        return result;
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
                        if (incidentPresenter.deleteIncident(incident) == 0) {
                            showMessage(R.string.deleted, false);
                            adapter_incident.notifyDataSetChanged();
                        } else {
                            showMessage(R.string.no_deleted, true);
                        }
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
            String month = (String) DateFormat.format("MMM", incident.getIn_date());
            String year = (String) DateFormat.format("yyyy", incident.getIn_date());
            String day = (String) DateFormat.format("dd", incident.getIn_date());

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
            txtDate.setText(day + " " + month + " " + year);
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