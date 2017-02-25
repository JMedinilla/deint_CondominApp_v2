package com.deint.condominapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.deint.condominapp.R;
import com.deint.condominapp.pojos.Pojo_Incident;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Adapter_Incident extends ArrayAdapter<Pojo_Incident> {
    private Context context;

    public Adapter_Incident(Context context) {
        super(context, R.layout.adapter_incident);
        this.context = context;
    }

    public void updateElements(List<Pojo_Incident> pojo_incidents) {
        this.clear();
        this.addAll(pojo_incidents);
        notifyDataSetChanged();
    }

    private class IncidentHolder {
        ImageView imgPhoto;
        TextView txtTitle;
        TextView txtContent;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        IncidentHolder incidentHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_incident, parent, false);
            incidentHolder = new IncidentHolder();
            incidentHolder.imgPhoto = (ImageView) view.findViewById(R.id.adapterIncident_img);
            incidentHolder.txtTitle = (TextView) view.findViewById(R.id.adapterIncident_txtTitle);
            incidentHolder.txtContent = (TextView) view.findViewById(R.id.adapterIncident_txtContent);

            view.setTag(incidentHolder);
        } else {
            incidentHolder = (IncidentHolder) view.getTag();
        }

        Pojo_Incident incident = getItem(position);
        if (incident != null) {
            if (incident.getIn_photo().isEmpty()) {
                incidentHolder.imgPhoto.setImageResource(R.drawable.image);
            } else {
                Picasso.with(getContext()).load(incident.getIn_photo()).fit().centerCrop().into(incidentHolder.imgPhoto);
            }
            incidentHolder.txtTitle.setText(incident.getIn_title());
            incidentHolder.txtContent.setText(incident.getIn_description());
        }

        return view;
    }

    @Nullable
    @Override
    public Pojo_Incident getItem(int position) {
        return super.getItem(position);
    }

    /**
     * Method to sort the list with a given comparator
     *
     * @param comparator Order criterion
     */
    public void sortIncidents(Comparator<Pojo_Incident> comparator) {
        ArrayList<Pojo_Incident> list = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            list.add(getItem(i));
        }
        Collections.sort(list, comparator);
        clear();
        addAll(list);
        notifyDataSetChanged();
    }
}
