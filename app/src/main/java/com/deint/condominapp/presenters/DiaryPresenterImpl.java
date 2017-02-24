package com.deint.condominapp.presenters;

import com.deint.condominapp.CondominappApplication;
import com.deint.condominapp.R;
import com.deint.condominapp.database.DatabaseManager_Note;
import com.deint.condominapp.interfaces.IDiaryPresenter;
import com.deint.condominapp.pojos.Pojo_Note;
import com.deint.condominapp.preferences.files.Profile;

import java.util.List;

public class DiaryPresenterImpl implements IDiaryPresenter {
    private IDiaryPresenter.View view;
    private Profile profile;

    public DiaryPresenterImpl(IDiaryPresenter.View view) {
        this.view = view;
        profile = new Profile(CondominappApplication.getContext());
    }

    @Override
    /**
     * Method to get all notes
     */
    public List<Pojo_Note> selectNotes() {
        return DatabaseManager_Note.getInstance().getNotes(profile.getUserCommunity());
    }

    @Override
    /**
     * Method to add a note
     */
    public int insertNote(Pojo_Note note) {
        int result = -1;
        if (DatabaseManager_Note.getInstance().addNote(note) >= 0) {
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.addError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to update a note
     */
    public int updateNote(Pojo_Note note) {
        int result = -1;
        if (DatabaseManager_Note.getInstance().updateNote(note) >= 0) {
            result = 0;
            view.showMessage(R.string.updated, false);
        } else {
            view.showMessage(R.string.updateError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to delete a note
     */
    public int deleteNote(Pojo_Note note) {
        int result = -1;
        if (DatabaseManager_Note.getInstance().deleteNote(note) >= 0) {
            result = 0;
            view.showMessage(R.string.deleted, false);
        } else {
            view.showMessage(R.string.deleteError, false);
        }
        return result;
    }

    @Override
    /**
     * Method to validate a note
     */
    public boolean validateNote(Pojo_Note note) {
        boolean result;
        if (note.getNo_title().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Title, true);
        } else if (note.getNo_content().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Description, true);
        } else {
            result = true;
        }
        return result;
    }
}
