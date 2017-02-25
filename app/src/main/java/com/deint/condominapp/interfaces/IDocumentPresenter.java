package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Document;

import java.util.List;

/**
 * Presenter for the documents
 */
public interface IDocumentPresenter {
    void selectDocuments();

    void insertDocument(Pojo_Document document);

    void updateDocument(Pojo_Document document);

    void deleteDocument(Pojo_Document document);

    boolean validateDocument(Pojo_Document document);

    /**
     * Presenter for the documents list
     */
    interface View {
        void showMessage(int msg, boolean error);

        void refreshElements(List<Pojo_Document> pojo_documents);

        void insertResponse(boolean result);

        void updateResponse(boolean result);

        void deleteResponse(boolean result);
    }
}
