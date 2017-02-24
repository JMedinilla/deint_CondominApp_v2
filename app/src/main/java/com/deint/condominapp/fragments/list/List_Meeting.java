package com.deint.condominapp.fragments.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.deint.condominapp.Activity_Home;
import com.deint.condominapp.R;
import com.deint.condominapp.adapters.Adapter_Meeting;
import com.deint.condominapp.interfaces.IMeetingPresenter;
import com.deint.condominapp.pojos.Pojo_Meeting;
import com.deint.condominapp.presenters.MeetingPresenterImpl;

public class List_Meeting extends Fragment implements IMeetingPresenter.View {
    private FragmentListMeetingListener homeCallback;

    MeetingPresenterImpl meetingPresenter;
    Adapter_Meeting adapter_meeting;
    ListView listView;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentListMeetingListener {
        void onManageMeetingOpen();

        void onManageMeetingOpen(Pojo_Meeting meeting);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        meetingPresenter = new MeetingPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_list_meeting, container, false);

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fragListMeeting_btn);
        listView = (ListView) view.findViewById(R.id.fragListMeeting_list);

        listView.setDivider(null);
        setListAdapter();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCallback.onManageMeetingOpen();
            }
        });

        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Dialog
            }
        });

        return view;
    }

    @Override
    /**
     * Method to send a message to the Activity
     */
    public void showMessage(int msg, boolean error) {
        ((Activity_Home) getActivity()).showSnackbar(getString(msg), error);
    }

    /**
     * Method to set an adapter to the fragment list
     */
    private void setListAdapter() {
        adapter_meeting = new Adapter_Meeting(getContext(), meetingPresenter.selectMeetings());
        listView.setAdapter(adapter_meeting);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListMeetingListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeCallback = null;
        adapter_meeting = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_meetings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuMeetings_date:
                adapter_meeting.sortMeetings(Pojo_Meeting.COMPARATOR_MEETING_DATE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_context_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        Pojo_Meeting meeting = adapter_meeting.getItem(index);

        switch (item.getItemId()) {
            case R.id.menuContext_update:
                homeCallback.onManageMeetingOpen(meeting);
                return true;
            case R.id.menuContext_delete:
                if (meetingPresenter.deleteMeeting(meeting) == 0) {
                    showMessage(R.string.deleted, false);
                    adapter_meeting.notifyDataSetChanged();
                } else {
                    showMessage(R.string.deleteError, true);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}