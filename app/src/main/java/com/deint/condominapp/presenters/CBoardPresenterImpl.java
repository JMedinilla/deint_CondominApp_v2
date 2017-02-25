package com.deint.condominapp.presenters;

import android.os.AsyncTask;

import com.deint.condominapp.CondominappApplication;
import com.deint.condominapp.R;
import com.deint.condominapp.database.DatabaseManager_Entry;
import com.deint.condominapp.interfaces.ICBoardPresenter;
import com.deint.condominapp.pojos.Pojo_Entry;
import com.deint.condominapp.preferences.files.Profile;

import java.util.List;

public class CBoardPresenterImpl implements ICBoardPresenter {
    private ICBoardPresenter.View view;
    private Profile profile;

    public CBoardPresenterImpl(ICBoardPresenter.View view) {
        this.view = view;
        profile = new Profile(CondominappApplication.getContext());
    }

    @Override
    /**
     * Method to get all entries
     */
    public void selectSecondEntries() {
        new AsyncTask<Void, Void, List<Pojo_Entry>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Pojo_Entry> doInBackground(Void... voids) {
                return DatabaseManager_Entry.getInstance().getSecondEntries(profile.getUserCommunity());
            }

            @Override
            protected void onPostExecute(List<Pojo_Entry> pojo_entries) {
                super.onPostExecute(pojo_entries);
                view.refreshElements(pojo_entries);
            }
        }.execute();
    }

    @Override
    /**
     * Method to add an entry
     */
    public int insertSecondEntry(Pojo_Entry entry) {
        int result = -1;
        if (DatabaseManager_Entry.getInstance().addEntry(entry) >= 0) {
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.addError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to update an entry
     */
    public int updateSecondEntry(Pojo_Entry entry) {
        int result = -1;
        if (DatabaseManager_Entry.getInstance().updateEntry(entry) >= 0) {
            result = 0;
            view.showMessage(R.string.updated, false);
        } else {
            view.showMessage(R.string.updateError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to delete an entry
     */
    public int deleteSecondEntry(Pojo_Entry entry) {
        int result = -1;
        if (DatabaseManager_Entry.getInstance().deleteEntry(entry) >= 0) {
            result = 0;
            view.showMessage(R.string.deleted, false);
        } else {
            view.showMessage(R.string.deleteError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to validate an entry
     */
    public boolean validateSecondEntry(Pojo_Entry entry) {
        boolean result;
        if (entry.getEn_title().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Title, true);
        } else if (entry.getEn_title().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Description, true);
        } else {
            result = true;
        }
        return result;
    }
}
