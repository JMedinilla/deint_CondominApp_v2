package com.deint.condominapp.interfaces;

import com.deint.condominapp.pojos.Pojo_Document;

import java.util.List;

/**
 * Presenter for the documents
 */
public interface IDocumentPresenter {
    List<Pojo_Document> selectDocuments();

    Pojo_Document selectDocument(String id);

    int insertDocument(Pojo_Document document);

    int updateDocument(Pojo_Document document);

    int deleteDocument(Pojo_Document document);

    boolean validateDocument(Pojo_Document document);

    /**
     * Presenter for the documents list
     */
    interface View {
        void showMessage(int msg, boolean error);
    }
}
