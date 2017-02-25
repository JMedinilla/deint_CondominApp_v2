package com.deint.condominapp.presenters;

import android.os.AsyncTask;

import com.deint.condominapp.others.CondominappApplication;
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
    public void selectIncidents() {
        new AsyncTask<Void, Void, List<Pojo_Incident>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Pojo_Incident> doInBackground(Void... voids) {
                return DatabaseManager_Incident.getInstance().getIncidents(profile.getUserCommunity());
            }

            @Override
            protected void onPostExecute(List<Pojo_Incident> pojo_incidents) {
                super.onPostExecute(pojo_incidents);
                view.refreshElements(pojo_incidents);
            }
        }.execute();
    }

    @Override
    /**
     * Method to add an incident
     */
    public void insertIncident(final Pojo_Incident incident) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Long doInBackground(Void... voids) {
                return DatabaseManager_Incident.getInstance().addIncident(incident);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                if (aLong != -1) {
                    view.showMessage(R.string.inserted, false);
                    view.insertResponse(true);
                } else {
                    view.showMessage(R.string.addError, true);
                    view.insertResponse(false);
                }
            }
        }.execute();
    }

    @Override
    /**
     * Method to update an incident
     */
    public void updateIncident(final Pojo_Incident incident) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                return DatabaseManager_Incident.getInstance().updateIncident(incident);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                if (integer > 0) {
                    view.showMessage(R.string.updated, false);
                    view.updateResponse(true);
                } else {
                    view.showMessage(R.string.updateError, true);
                    view.updateResponse(false);
                }
            }
        }.execute();
    }

    @Override
    /**
     * Method to delete an incident
     */
    public void deleteIncident(final Pojo_Incident incident) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                return DatabaseManager_Incident.getInstance().deleteIncident(incident);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                if (integer > 0) {
                    view.showMessage(R.string.deleted, false);
                    view.deleteResponse(true);
                } else {
                    view.showMessage(R.string.deleteError, true);
                    view.deleteResponse(false);
                }
            }
        }.execute();
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
