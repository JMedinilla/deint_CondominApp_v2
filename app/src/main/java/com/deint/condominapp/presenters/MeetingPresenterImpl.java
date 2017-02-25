package com.deint.condominapp.presenters;

import android.os.AsyncTask;

import com.deint.condominapp.CondominappApplication;
import com.deint.condominapp.R;
import com.deint.condominapp.database.DatabaseManager_Meeting;
import com.deint.condominapp.interfaces.IMeetingPresenter;
import com.deint.condominapp.pojos.Pojo_Meeting;
import com.deint.condominapp.preferences.files.Profile;

import java.util.List;

public class MeetingPresenterImpl implements IMeetingPresenter {
    private IMeetingPresenter.View view;
    private Profile profile;

    public MeetingPresenterImpl(IMeetingPresenter.View view) {
        this.view = view;
        profile = new Profile(CondominappApplication.getContext());
    }

    @Override
    /**
     * Method to get all meetings
     */
    public void selectMeetings() {
        new AsyncTask<Void, Void, List<Pojo_Meeting>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Pojo_Meeting> doInBackground(Void... voids) {
                return DatabaseManager_Meeting.getInstance().getMeetings(profile.getUserCommunity());
            }

            @Override
            protected void onPostExecute(List<Pojo_Meeting> pojo_meetings) {
                super.onPostExecute(pojo_meetings);
                view.refreshElements(pojo_meetings);
            }
        }.execute();
    }

    @Override
    /**
     * Method to add a meeting
     */
    public int insertMeeting(Pojo_Meeting meeting) {
        int result = -1;
        if (DatabaseManager_Meeting.getInstance().addMeeting(meeting) >= 0) {
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.addError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to update a meeting
     */
    public int updateMeeting(Pojo_Meeting meeting) {
        int result = -1;
        if (DatabaseManager_Meeting.getInstance().updateMeeting(meeting) >= 0) {
            result = 0;
            view.showMessage(R.string.updated, false);
        } else {
            view.showMessage(R.string.updateError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to delete a meeting
     */
    public int deleteMeeting(Pojo_Meeting meeting) {
        int result = -1;
        if (DatabaseManager_Meeting.getInstance().deleteMeeting(meeting) >= 0) {
            result = 0;
            view.showMessage(R.string.deleted, false);
        } else {
            view.showMessage(R.string.deleteError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to validate a meeting
     */
    public boolean validateMeeting(Pojo_Meeting meeting) {
        view.showMessage(R.string.app_name, true);
        return false;
    }
}
