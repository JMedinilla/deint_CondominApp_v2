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
import com.deint.condominapp.adapters.Adapter_Diary;
import com.deint.condominapp.interfaces.IDiaryPresenter;
import com.deint.condominapp.pojos.Pojo_Note;
import com.deint.condominapp.presenters.DiaryPresenterImpl;

public class List_Diary extends Fragment implements IDiaryPresenter.View {
    private FragmentListDiaryListener homeCallback;

    DiaryPresenterImpl diaryPresenter;
    Adapter_Diary adapter_diary;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentListDiaryListener {
        void onManageDiaryOpen();

        void onManageDiaryOpen(Pojo_Note note);
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
        diaryPresenter = new DiaryPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_list_diary, container, false);

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fragListDiary_btn);
        ListView listView = (ListView) view.findViewById(R.id.fragListDiary_list);

        adapter_diary = new Adapter_Diary(getContext(), diaryPresenter.selectNotes());
        listView.setDivider(null);
        listView.setAdapter(adapter_diary);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCallback.onManageDiaryOpen();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pojo_Note note = adapter_diary.getItem(i);
                showDetailNote(note);
            }
        });

        return view;
    }

    /**
     * Method that recieves an element from the Activity and insert or update it
     *
     * @param note Note to handle
     * @param update Boolean to know if the element has to be inserted or updated
     * @return True or False depending on the succes of the operation
     */
    public boolean recieveNoteFromHome(Pojo_Note note, boolean update) {
        boolean result = false;
        if (diaryPresenter.validateNote(note)) {
            if (update) {
                result = diaryPresenter.updateNote(note) == 0;
            } else {
                result = diaryPresenter.insertNote(note) == 0;
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
     * @param note Element to be shown
     */
    private void showDetailNote(final Pojo_Note note) {
        MaterialDialog.Builder dialog = new MaterialDialog.Builder(getActivity())
                .title(note.getNo_title())
                .customView(R.layout.detail_note, true)
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
                        if (diaryPresenter.deleteNote(note) == 0) {
                            showMessage(R.string.deleted, false);
                            adapter_diary.notifyDataSetChanged();
                        } else {
                            showMessage(R.string.no_deleted, true);
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        homeCallback.onManageDiaryOpen(note);
                    }
                });
        View content = dialog.build().getCustomView();
        if (content != null) {
            TextView txtDate = (TextView) content.findViewById(R.id.detail_note_date);
            TextView txtDescription = (TextView) content.findViewById(R.id.detail_note_description);

            txtDate.setText(note.getNo_date());
            txtDescription.setText(note.getNo_content());
        }
        dialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListDiaryListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeCallback = null;
        adapter_diary = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_diary, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDiary_title:
                adapter_diary.sortDiaries(Pojo_Note.COMPARATOR_NOTE_TITLE);
                break;
            case R.id.menuDiary_date:
                adapter_diary.sortDiaries(Pojo_Note.COMPARATOR_NOTE_DATE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}