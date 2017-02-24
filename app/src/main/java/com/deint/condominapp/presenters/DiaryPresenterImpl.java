package com.deint.condominapp.presenters;

import com.deint.condominapp.R;
import com.deint.condominapp.interfaces.IDiaryPresenter;
import com.deint.condominapp.pojos.Pojo_Note;
import com.deint.condominapp.repositories.Repository_Note;

import java.util.List;

public class DiaryPresenterImpl implements IDiaryPresenter {
    private IDiaryPresenter.View view;

    public DiaryPresenterImpl(IDiaryPresenter.View view) {
        this.view = view;
    }

    @Override
    /**
     * Method to get all notes
     */
    public List<Pojo_Note> selectNotes() {
        return Repository_Note.getInstance().getNotes();
    }

    @Override
    /**
     * Method to get a note
     */
    public Pojo_Note selectNote(String id) {
        return null;
    }

    @Override
    /**
     * Method to add a note
     */
    public int insertNote(Pojo_Note note) {
        int result = -1;
        if (!Repository_Note.getInstance().contains(note)) {
            Repository_Note.getInstance().add(note);
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.exists, false);
        }
        return result;
    }

    @Override
    /**
     * Method to update a note
     */
    public int updateNote(Pojo_Note note) {
        return 0;
    }

    @Override
    /**
     * Method to delete a note
     */
    public int deleteNote(Pojo_Note note) {
        int result = -1;
        if (Repository_Note.getInstance().getNotes().remove(note)) {
            result = 0;
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
