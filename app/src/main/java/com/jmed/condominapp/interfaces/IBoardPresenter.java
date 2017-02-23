package com.jmed.condominapp.interfaces;

import com.jmed.condominapp.pojos.Pojo_Entry;

import java.util.List;

/**
 * Presenter for the entries
 */
public interface IBoardPresenter {
    List<Pojo_Entry> selectFirstEntries();

    Pojo_Entry selectFirstEntry(String id);

    int insertFirstEntry(Pojo_Entry entry);

    int updateFirstEntry(Pojo_Entry entry);

    int deleteFirstEntry(Pojo_Entry entry);

    boolean validateFirstEntry(Pojo_Entry entry);

    /**
     * Presenter for the entries list
     */
    interface View {
        void showMessage(int msg, boolean error);
    }
}
