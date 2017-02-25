package com.deint.condominapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deint.condominapp.R;
import com.deint.condominapp.pojos.Pojo_Meeting;

import java.util.Comparator;
import java.util.List;

public class Adapter_Meeting extends ArrayAdapter<Pojo_Meeting> {
    private Context context;

    public Adapter_Meeting(Context context) {
        super(context, R.layout.adapter_meeting);
        this.context = context;
    }

    public void updateElements(List<Pojo_Meeting> pojo_meetings) {
        this.clear();
        this.addAll(pojo_meetings);
        notifyDataSetChanged();
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
            meetingHolder.txtTitle.setText(meeting.getMe_date());
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
        this.sort(comparator);
        notifyDataSetChanged();
    }
}
