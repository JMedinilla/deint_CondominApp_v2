package com.jmed.condominapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jmed.condominapp.R;
import com.jmed.condominapp.repositories.Repository_Meeting;
import com.jmed.condominapp.pojos.Pojo_Meeting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Adapter_Meeting extends ArrayAdapter<Pojo_Meeting> {
    private Context context;

    public Adapter_Meeting(Context context, List<Pojo_Meeting> pojo_meetings) {
        super(context, R.layout.adapter_meeting, pojo_meetings);
        this.context = context;
    }

    private class MeetingHolder {
        TextView txtTitle;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        MeetingHolder meetingHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_meeting, parent, false);
            meetingHolder = new MeetingHolder();
            meetingHolder.txtTitle = (TextView) view.findViewById(R.id.adapterMeeting_txtTitle);

            view.setTag(meetingHolder);
        } else {
            meetingHolder = (MeetingHolder) view.getTag();
        }

        Pojo_Meeting meeting = getItem(position);
        if (meeting != null) {
            String month = (String) DateFormat.format("MMM", meeting.getMe_date());
            String year = (String) DateFormat.format("yyyy", meeting.getMe_date());
            String day = (String) DateFormat.format("dd", meeting.getMe_date());
            meetingHolder.txtTitle.setText(day + " " + month + " " + year);
        }

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * Method to sort the list with a given comparator
     *
     * @param comparator Order criterion
     */
    public void sortMeetings(Comparator<Pojo_Meeting> comparator) {
        Collections.sort(Repository_Meeting.getInstance(), comparator);
        notifyDataSetChanged();
    }
}
