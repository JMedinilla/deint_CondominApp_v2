package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Meeting;

import java.util.List;

/**
 * Presenter for the meetings
 */
public interface IMeetingPresenter {
    List<Pojo_Meeting> selectMeetings();

    Pojo_Meeting selectMeeting(int id);

    int insertMeeting(Pojo_Meeting meeting);

    int updateMeeting(Pojo_Meeting meeting);

    int deleteMeeting(Pojo_Meeting meeting);

    boolean validateMeeting(Pojo_Meeting meeting);

    /**
     * Presenter for the meetings list
     */
    interface View {
        void showMessage(int msg, boolean error);
    }
}
