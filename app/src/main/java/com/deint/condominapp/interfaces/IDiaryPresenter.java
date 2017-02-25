package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Note;

import java.util.List;

/**
 * Presenter for the notes
 */
public interface IDiaryPresenter {
    void selectNotes();

    int insertNote(Pojo_Note note);

    int updateNote(Pojo_Note note);

    int deleteNote(Pojo_Note note);

    boolean validateNote(Pojo_Note note);

    /**
     * Presenter for the notes list
     */
    interface View {
        void showMessage(int msg, boolean error);

        void refreshElements(List<Pojo_Note> pojo_notes);
    }
}
