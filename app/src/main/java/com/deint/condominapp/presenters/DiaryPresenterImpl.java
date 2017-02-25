package com.deint.condominapp.presenters;

import android.os.AsyncTask;

import com.deint.condominapp.others.CondominappApplication;
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
    public void selectNotes() {
        new AsyncTask<Void, Void, List<Pojo_Note>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Pojo_Note> doInBackground(Void... voids) {
                return DatabaseManager_Note.getInstance().getNotes(profile.getUserCommunity());
            }

            @Override
            protected void onPostExecute(List<Pojo_Note> pojo_notes) {
                super.onPostExecute(pojo_notes);
                view.refreshElements(pojo_notes);
            }
        }.execute();
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
