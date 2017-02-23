package com.jmed.condominapp.presenters;

import com.jmed.condominapp.R;
import com.jmed.condominapp.interfaces.ICBoardPresenter;
import com.jmed.condominapp.pojos.Pojo_Entry;
import com.jmed.condominapp.repositories.Repository_Entry_Second;

import java.util.List;

public class CBoardPresenterImpl implements ICBoardPresenter {
    private ICBoardPresenter.View view;

    public CBoardPresenterImpl(ICBoardPresenter.View view) {
        this.view = view;
    }

    @Override
    /**
     * Method to get all entries
     */
    public List<Pojo_Entry> selectSecondEntries() {
        return Repository_Entry_Second.getInstance().getEntries();
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
        if (!Repository_Entry_Second.getInstance().contains(entry)) {
            Repository_Entry_Second.getInstance().add(entry);
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.exists, false);
        }
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
        if (Repository_Entry_Second.getInstance().getEntries().remove(entry)) {
            result = 0;
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
