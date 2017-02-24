package com.deint.condominapp.presenters;

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
    public List<Pojo_Entry> selectSecondEntries() {
        return DatabaseManager_Entry.getInstance().getFirstEntries(profile.getUserCommunity());
    }

    @Override
    /**
     * Method to get an entry
     */
    public Pojo_Entry selectSecondEntry(String id) {
        return null;
    }

    @Override
    /**
     * Method to add an entry
     */
    public int insertSecondEntry(Pojo_Entry entry) {
        int result = -1;
        /*
        if (!Repository_Entry_Second.getInstance().contains(entry)) {
            Repository_Entry_Second.getInstance().add(entry);
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
     * Method to update an entry
     */
    public int updateSecondEntry(Pojo_Entry entry) {
        return 0;
    }

    @Override
    /**
     * Method to delete an entry
     */
    public int deleteSecondEntry(Pojo_Entry entry) {
        int result = -1;
        /*
        if (Repository_Entry_Second.getInstance().getEntries().remove(entry)) {
            result = 0;
        }
        */
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
