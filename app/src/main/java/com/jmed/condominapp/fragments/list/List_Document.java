package com.jmed.condominapp.fragments.list;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jmed.condominapp.Activity_Home;
import com.jmed.condominapp.R;
import com.jmed.condominapp.adapters.Adapter_Document;
import com.jmed.condominapp.interfaces.IDocumentPresenter;
import com.jmed.condominapp.pojos.Pojo_Document;
import com.jmed.condominapp.presenters.DocumentPresenterImpl;


public class List_Document extends Fragment implements IDocumentPresenter.View {
    private FragmentListDocumentListener homeCallback;

    DocumentPresenterImpl documentPresenter;
    Adapter_Document adapter_document;

    /**
     * Listener from the fragment to the Activity
     */
    public interface FragmentListDocumentListener {
        void onManageDocumentOpen();

        void onManageDocumentOpen(Pojo_Document document);
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
        documentPresenter = new DocumentPresenterImpl(this);
        View view = inflater.inflate(R.layout.fragment_list_document, container, false);

        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.fragListDocument_btn);
        ListView listView = (ListView) view.findViewById(R.id.fragListDocument_list);

        adapter_document = new Adapter_Document(getContext(), documentPresenter.selectDocuments());
        listView.setDivider(null);
        listView.setAdapter(adapter_document);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeCallback.onManageDocumentOpen();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pojo_Document document = adapter_document.getItem(i);
                showDetailDocument(document);
            }
        });

        return view;
    }

    /**
     * Method that recieves an element from the Activity and insert or update it
     *
     * @param document Document to handle
     * @param update Boolean to know if the element has to be inserted or updated
     * @return True or False depending on the succes of the operation
     */
    public boolean recieveDocumentFromHome(Pojo_Document document, boolean update) {
        boolean result = false;
        if (documentPresenter.validateDocument(document)) {
            if (update) {
                result = documentPresenter.updateDocument(document) == 0;
            } else {
                result = documentPresenter.insertDocument(document) == 0;
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
     * @param document Element to be shown
     */
    private void showDetailDocument(final Pojo_Document document) {
        MaterialDialog.Builder dialog = new MaterialDialog.Builder(getActivity())
                .title(document.getDo_title())
                .customView(R.layout.detail_document, true)
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
                        if (documentPresenter.deleteDocument(document) == 0) {
                            showMessage(R.string.deleted, false);
                            adapter_document.notifyDataSetChanged();
                        } else {
                            showMessage(R.string.no_deleted, true);
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        homeCallback.onManageDocumentOpen(document);
                    }
                });
        View content = dialog.build().getCustomView();
        if (content != null) {
            TextView txtDescription = (TextView) content.findViewById(R.id.detail_document_description);
            Button btnOnline = (Button) content.findViewById(R.id.detail_document_btnOnline);
            Button btnDownload = (Button) content.findViewById(R.id.detail_document_btnDownload);

            txtDescription.setText(document.getDo_description());
            btnOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Abrir en internet
                }
            });
            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Descargar
                }
            });
        }
        dialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeCallback = (FragmentListDocumentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homeCallback = null;
        adapter_document = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_documents, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDocuments_title:
                adapter_document.sortDocuments(Pojo_Document.COMPARATOR_DOCUMENT_TITLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}