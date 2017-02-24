package com.deint.condominapp.fragments.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.deint.condominapp.Activity_Home;
import com.deint.condominapp.R;
import com.deint.condominapp.adapters.Adapter_CBoard;
import com.deint.condominapp.interfaces.ICBoardPresenter;
import com.deint.condominapp.pojos.Pojo_Entry;
import com.deint.condominapp.presenters.CBoardPresenterImpl;

public class List_CBoard extends Fragment implements ICBoardPresenter.View {
    private FragmentListCBoardListener homeCallback;

    CBoardPresenterImpl cBoardPresenter;
    Adapter_CBoard adapter_cBoard;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentListCBoardListener {
        void onManageCBoardOpen();

        void onManageCBoardOpen(Pojo_Entry entry);
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
        cBoardPresenter = new CBoardPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_list_cboard, container, false);

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fragListCBoard_btn);
        ListView listView = (ListView) view.findViewById(R.id.fragListCBoard_list);

        adapter_cBoard = new Adapter_CBoard(getContext(), cBoardPresenter.selectSecondEntries());
        listView.setDivider(null);
        listView.setAdapter(adapter_cBoard);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCallback.onManageCBoardOpen();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pojo_Entry entry = adapter_cBoard.getItem(i);
                showDetailEntry(entry);
            }
        });

        return view;
    }

    /**
     * Method that recieves an element from the Activity and insert or update it
     *
     * @param entry  Entry to handle
     * @param update Boolean to know if the element has to be inserted or updated
     * @return True or False depending on the succes of the operation
     */
    public boolean recieveEntryFromHome(Pojo_Entry entry, boolean update) {
        boolean result = false;
        if (cBoardPresenter.validateSecondEntry(entry)) {
            if (update) {
                result = cBoardPresenter.updateSecondEntry(entry) == 0;
            } else {
                result = cBoardPresenter.insertSecondEntry(entry) == 0;
            }
        }
        return result;
    }

    @Override
    /**
     * Method to send a message to the Activity
     */
    public void showMessage(int msg, boolean error) {
        ((Activity_Home) getActivity()).showSnackbar(getString(msg), error);
    }

    /**
     * Method that shows a detailed view of the element
     *
     * @param entry Element to be shown
     */
    private void showDetailEntry(final Pojo_Entry entry) {
        MaterialDialog.Builder dialog = new MaterialDialog.Builder(getActivity())
                .title(entry.getEn_title())
                .customView(R.layout.detail_entry, true)
                .cancelable(true)
                .canceledOnTouchOutside(true)
                .titleColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark))
                .positiveText(R.string.detail_delete)
                .negativeText(R.string.detail_edit)
                .neutralText(R.string.detail_close)
                .autoDismiss(true)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (cBoardPresenter.deleteSecondEntry(entry) == 0) {
                            showMessage(R.string.deleted, false);
                            adapter_cBoard.notifyDataSetChanged();
                        } else {
                            showMessage(R.string.no_deleted, true);
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        homeCallback.onManageCBoardOpen(entry);
                    }
                });
        View content = dialog.build().getCustomView();
        if (content != null) {
            String month = (String) DateFormat.format("MMM", entry.getEn_date());
            String year = (String) DateFormat.format("yyyy", entry.getEn_date());
            String day = (String) DateFormat.format("dd", entry.getEn_date());

            TextView txtUser = (TextView) content.findViewById(R.id.detail_entry_user);
            TextView txtDate = (TextView) content.findViewById(R.id.detail_entry_date);
            TextView txtDescription = (TextView) content.findViewById(R.id.detail_entry_description);

            txtUser.setText(entry.getEn_userid());
            txtDate.setText(day + " " + month + " " + year);
            txtDescription.setText(entry.getEn_content());
        }
        dialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListCBoardListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeCallback = null;
        adapter_cBoard = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_cboard, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCBoard_date:
                adapter_cBoard.sortSecondEntries(Pojo_Entry.COMPARATOR_ENTRY_DATE);
                break;
            case R.id.menuCBoard_title:
                adapter_cBoard.sortSecondEntries(Pojo_Entry.COMPARATOR_ENTRY_DATE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}