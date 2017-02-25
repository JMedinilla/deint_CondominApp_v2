package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Entry;

import java.util.List;

/**
 * Presenter for the entries
 */
public interface IBoardPresenter {
    void selectFirstEntries();

    int insertFirstEntry(Pojo_Entry entry);

    int updateFirstEntry(Pojo_Entry entry);

    int deleteFirstEntry(Pojo_Entry entry);

    boolean validateFirstEntry(Pojo_Entry entry);

    /**
     * Presenter for the entries list
     */
    interface View {
        void showMessage(int msg, boolean error);

        void refreshElements(List<Pojo_Entry> pojo_entries);
    }
}
