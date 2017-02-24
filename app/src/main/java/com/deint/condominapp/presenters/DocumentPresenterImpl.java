package com.deint.condominapp.presenters;

import com.deint.condominapp.CondominappApplication;
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
    public List<Pojo_Document> selectDocuments() {
        return DatabaseManager_Document.getInstance().getDocuments(profile.getUserCommunity());
    }

    @Override
    /**
     * Method to get a document
     */
    public Pojo_Document selectDocument(String id) {
        return null;
    }

    @Override
    /**
     * Method to add a document
     */
    public int insertDocument(Pojo_Document document) {
        int result = -1;
        /*
        if (!Repository_Document.getInstance().contains(document)) {
            Repository_Document.getInstance().add(document);
            result = 0;
            view.showMessage(R.string.inserted, false);
        } else {
            view.showMessage(R.string.exists, false);
        }
        */
        return result;
    }

    @Override
    /**
     * Method to update a document
     */
    public int updateDocument(Pojo_Document document) {
        return 0;
    }

    @Override
    /**
     * Method to delete a document
     */
    public int deleteDocument(Pojo_Document document) {
        int result = -1;
        /*
        if (Repository_Document.getInstance().getDocuments().remove(document)) {
            result = 0;
        }
        */
        return result;
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
