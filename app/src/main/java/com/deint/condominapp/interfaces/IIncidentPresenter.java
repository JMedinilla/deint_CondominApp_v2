package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Incident;

import java.util.List;

/**
 * Presenter for the incidents
 */
public interface IIncidentPresenter {
    List<Pojo_Incident> selectIncidents();

    int insertIncident(Pojo_Incident incident);

    int updateIncident(Pojo_Incident incident);

    int deleteIncident(Pojo_Incident incident);

    boolean validateIncident(Pojo_Incident incident);

    /**
     * Presenter for the incidents list
     */
    interface View {
        void showMessage(int msg, boolean error);
    }
}
