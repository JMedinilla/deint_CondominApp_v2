package com.jmed.condominapp.repositories;

import com.jmed.condominapp.pojos.Pojo_Document;

import java.util.ArrayList;
import java.util.List;

public class Repository_Document extends ArrayList<Pojo_Document> {
    private static Repository_Document instance;

    public static Repository_Document getInstance() {
        if (instance == null) {
            instance = new Repository_Document();
        }
        return instance;
    }

    private Repository_Document() {
        add(new Pojo_Document(99, "Estatutos", "Estatutos de la comunidad actualizados", "url 1", false));
        add(new Pojo_Document(99, "Normativa ruidos", "Normativa del ayuntamiento sobre ruidos", "url 2", false));
        add(new Pojo_Document(99, "Acta junta 16/05/15", "Acta de la junta celebrada el 16 de mayo de 2015", "url 3", false));
        add(new Pojo_Document(99, "Acta junta 10/01/16", "Acta de la junta celebrada el 10 de enero de 2016", "url 4", false));
    }

    public List<Pojo_Document> getDocuments() {
        return this;
    }
}
