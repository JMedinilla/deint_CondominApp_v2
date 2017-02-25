package com.deint.condominapp.fragments.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.deint.condominapp.adapters.Adapter_Board;
import com.deint.condominapp.interfaces.IBoardPresenter;
import com.deint.condominapp.pojos.Pojo_Entry;
import com.deint.condominapp.presenters.BoardPresenterImpl;

import java.util.List;

public class List_Board extends Fragment implements IBoardPresenter.View {
    private FragmentListBoardListener homeCallback;

    BoardPresenterImpl boardPresenter;
    Adapter_Board adapter_board;
    ListView listView;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentListBoardListener {
        void onManageBoardOpen();

        void onManageBoardOpen(Pojo_Entry entry);
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
        boardPresenter = new BoardPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_list_board, container, false);

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fragListBoard_btn);
        listView = (ListView) view.findViewById(R.id.fragListBoard_list);

        listView.setDivider(null);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCallback.onManageBoardOpen();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pojo_Entry entry = adapter_board.getItem(i);
                showDetailEntry(entry);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_board = new Adapter_Board(getContext());
        listView.setAdapter(adapter_board);

        boardPresenter.selectFirstEntries();
    }

    /**
     * Method that recieves an element from the Activity and insert or update it
     *
     * @param entry  Entry to handle
     * @param update Boolean to know if the element has to be inserted or updated
     */
    public void recieveEntryFromHome(Pojo_Entry entry, boolean update) {
        boolean result = false;
        if (boardPresenter.validateFirstEntry(entry)) {
            if (update) {
                result = boardPresenter.updateFirstEntry(entry) == 0;
            } else {
                result = boardPresenter.insertFirstEntry(entry) == 0;
            }
        }
        if (result) {
            getActivity().onBackPressed();
        }
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
                        if (boardPresenter.deleteFirstEntry(entry) == 0) {
                            showMessage(R.string.deleted, false);
                            boardPresenter.selectFirstEntries();
                        } else {
                            showMessage(R.string.deleteError, true);
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        homeCallback.onManageBoardOpen(entry);
                    }
                });
        View content = dialog.build().getCustomView();
        if (content != null) {
            TextView txtUser = (TextView) content.findViewById(R.id.detail_entry_user);
            TextView txtDate = (TextView) content.findViewById(R.id.detail_entry_date);
            TextView txtDescription = (TextView) content.findViewById(R.id.detail_entry_description);

            txtUser.setText(entry.getEn_userid());
            txtDate.setText(entry.getEn_date());
            txtDescription.setText(entry.getEn_content());
        }
        dialog.show();
    }

    public void refreshElements(List<Pojo_Entry> pojo_entries) {
        adapter_board.updateElements(pojo_entries);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListBoardListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeCallback = null;
        adapter_board = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_board, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuBoard_date:
                adapter_board.sortFirstEntries(Pojo_Entry.COMPARATOR_ENTRY_DATE);
                break;
            case R.id.menuBoard_title:
                adapter_board.sortFirstEntries(Pojo_Entry.COMPARATOR_ENTRY_TITLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}