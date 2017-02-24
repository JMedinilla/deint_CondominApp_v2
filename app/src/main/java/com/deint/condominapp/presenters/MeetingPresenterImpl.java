package com.deint.condominapp.presenters;

import com.deint.condominapp.R;
import com.deint.condominapp.interfaces.IMeetingPresenter;
import com.deint.condominapp.pojos.Pojo_Meeting;
import com.deint.condominapp.repositories.Repository_Meeting;

import java.util.List;

public class MeetingPresenterImpl implements IMeetingPresenter {
    private IMeetingPresenter.View view;

    public MeetingPresenterImpl(IMeetingPresenter.View view) {
        this.view = view;
    }

    @Override
    /**
     * Method to get all meetings
     */
    public List<Pojo_Meeting> selectMeetings() {
        return Repository_Meeting.getInstance().getMeetings();
    }

    @Override
    /**
     * Method to get a meeting
     */
    public Pojo_Meeting selectMeeting(int id) {
        return null;
    }

    @Override
    /**
     * Method to add a meeting
     */
    public int insertMeeting(Pojo_Meeting meeting) {
        return 0;
    }

    @Override
    /**
     * Method to update a meeting
     */
    public int updateMeeting(Pojo_Meeting meeting) {
        return 0;
    }

    @Override
    /**
     * Method to delete a meeting
     */
    public int deleteMeeting(Pojo_Meeting meeting) {
        return 0;
    }

    @Override
    /**
     * Method to validate a meeting
     */
    public boolean validateMeeting(Pojo_Meeting meeting) {
        view.showMessage(R.string.app_name, true);
        return false;
    }
}
