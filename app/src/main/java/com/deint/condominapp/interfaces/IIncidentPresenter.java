package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Incident;

import java.util.List;

/**
 * Presenter for the incidents
 */
public interface IIncidentPresenter {
    void selectIncidents();

    void insertIncident(Pojo_Incident incident);

    void updateIncident(Pojo_Incident incident);

    void deleteIncident(Pojo_Incident incident);

    boolean validateIncident(Pojo_Incident incident);

    /**
     * Presenter for the incidents list
     */
    interface View {
        void showMessage(int msg, boolean error);

        void refreshElements(List<Pojo_Incident> pojo_incidents);

        void insertResponse(boolean result);

        void updateResponse(boolean result);

        void deleteResponse(boolean result);
    }
}
