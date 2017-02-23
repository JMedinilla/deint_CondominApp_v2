package com.jmed.condominapp.interfaces;

import com.jmed.condominapp.pojos.Pojo_Entry;

import java.util.List;

/**
 * Presenter for the entries
 */
public interface ICBoardPresenter {
    List<Pojo_Entry> selectSecondEntries();

    Pojo_Entry selectSecondEntry(String id);

    int insertSecondEntry(Pojo_Entry entry);

    int updateSecondEntry(Pojo_Entry entry);

    int deleteSecondEntry(Pojo_Entry entry);

    boolean validateSecondEntry(Pojo_Entry entry);

    /**
     * Presenter for the entries list
     */
    interface View {
        void showMessage(int msg, boolean error);
    }
}
