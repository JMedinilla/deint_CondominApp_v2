package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Entry;

import java.util.List;

/**
 * Presenter for the entries
 */
public interface ICBoardPresenter {
    void selectSecondEntries();

    int insertSecondEntry(Pojo_Entry entry);

    int updateSecondEntry(Pojo_Entry entry);

    int deleteSecondEntry(Pojo_Entry entry);

    boolean validateSecondEntry(Pojo_Entry entry);

    /**
     * Presenter for the entries list
     */
    interface View {
        void showMessage(int msg, boolean error);

        void refreshElements(List<Pojo_Entry> pojo_entries);
    }
}
