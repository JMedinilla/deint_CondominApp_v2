package com.deint.condominapp.presenters;

import com.deint.condominapp.CondominappApplication;
import com.deint.condominapp.R;
import com.deint.condominapp.database.DatabaseManager_Incident;
import com.deint.condominapp.interfaces.IIncidentPresenter;
import com.deint.condominapp.pojos.Pojo_Incident;
import com.deint.condominapp.preferences.files.Profile;

import java.util.List;

public class IncidentPresenterImpl implements IIncidentPresenter {
    private IIncidentPresenter.View view;
    private Profile profile;

    public IncidentPresenterImpl(IIncidentPresenter.View view) {
        this.view = view;
        profile = new Profile(CondominappApplication.getContext());
    }

    @Override
    /**
     * Method to get all incidents
     */
    public List<Pojo_Incident> selectIncidents() {
        return DatabaseManager_Incident.getInstance().getIncidents(profile.getUserCommunity());
    }

    @Override
    /**
     * Method to get an incident
     */
    public Pojo_Incident selectIncident(String id) {
        return null;
    }

    @Override
    /**
     * Method to add an incident
     */
    public int insertIncident(Pojo_Incident incident) {
        int result = -1;
        /*
        if (!Repository_Incident.getInstance().contains(incident)) {
            Repository_Incident.getInstance().add(incident);
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.exists, false);
        }
        */
        return result;
    }

    @Override
    /**
     * Method to update an incident
     */
    public int updateIncident(Pojo_Incident incident) {
        return 0;
    }

    @Override
    /**
     * Method to delete an incident
     */
    public int deleteIncident(Pojo_Incident incident) {
        int result = -1;
        /*
        if (Repository_Incident.getInstance().getIncidents().remove(incident)) {
            result = 0;
        }
        */
        return result;
    }

    @Override
    /**
     * Method to validate an incident
     */
    public boolean validateIncident(Pojo_Incident incident) {
        boolean result;
        if (incident.getIn_title().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Title, true);
        } else if (incident.getIn_description().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Description, true);
        } else {
            result = true;
        }
        return result;
    }
}
