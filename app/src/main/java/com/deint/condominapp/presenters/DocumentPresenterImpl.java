package com.deint.condominapp.presenters;

import android.os.AsyncTask;

import com.deint.condominapp.others.CondominappApplication;
import com.deint.condominapp.R;
import com.deint.condominapp.database.DatabaseManager_Document;
import com.deint.condominapp.interfaces.IDocumentPresenter;
import com.deint.condominapp.pojos.Pojo_Document;
import com.deint.condominapp.preferences.files.Profile;

import java.util.List;

public class DocumentPresenterImpl implements IDocumentPresenter {
    private IDocumentPresenter.View view;
    private Profile profile;

    public DocumentPresenterImpl(IDocumentPresenter.View view) {
        this.view = view;
        profile = new Profile(CondominappApplication.getContext());
    }

    @Override
    /**
     * Method to get all documents
     */
    public void selectDocuments() {
        new AsyncTask<Void, Void, List<Pojo_Document>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<Pojo_Document> doInBackground(Void... voids) {
                return DatabaseManager_Document.getInstance().getDocuments(profile.getUserCommunity());
            }

            @Override
            protected void onPostExecute(List<Pojo_Document> pojo_documents) {
                super.onPostExecute(pojo_documents);
                view.refreshElements(pojo_documents);
            }
        }.execute();
    }

    @Override
    /**
     * Method to add a document
     */
    public void insertDocument(final Pojo_Document document) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Long doInBackground(Void... voids) {
                return DatabaseManager_Document.getInstance().addDocument(document);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                if (aLong != -1) {
                    view.showMessage(R.string.inserted, false);
                    view.insertResponse(true);
                } else {
                    view.showMessage(R.string.addError, true);
                    view.insertResponse(false);
                }
            }
        }.execute();
    }

    @Override
    /**
     * Method to update a document
     */
    public void updateDocument(final Pojo_Document document) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                return DatabaseManager_Document.getInstance().updateDocument(document);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                if (integer > 0) {
                    view.showMessage(R.string.updated, false);
                    view.updateResponse(true);
                } else {
                    view.showMessage(R.string.updateError, true);
                    view.updateResponse(false);
                }
            }
        }.execute();
    }

    @Override
    /**
     * Method to delete a document
     */
    public void deleteDocument(final Pojo_Document document) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                return DatabaseManager_Document.getInstance().deleteDocument(document);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                if (integer > 0) {
                    view.showMessage(R.string.deleted, false);
                    view.deleteResponse(true);
                } else {
                    view.showMessage(R.string.deleteError, true);
                    view.deleteResponse(false);
                }
            }
        }.execute();
    }

    @Override
    /**
     * Method to validate a document
     */
    public boolean validateDocument(Pojo_Document document) {
        boolean result;
        if (document.getDo_title().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Title, true);
        } else if (document.getDo_description().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Description, true);
        } else if (document.getDo_link().length() == 0) {
            result = false;
            view.showMessage(R.string.error_Link, true);
        } else {
            result = true;
        }
        return result;
    }
}
