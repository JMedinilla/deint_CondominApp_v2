package com.jmed.condominapp.repositories;

import com.jmed.condominapp.pojos.Pojo_Point;

import java.util.ArrayList;
import java.util.List;

public class Repository_Point extends ArrayList<Pojo_Point> {
    private static Repository_Point instance;

    public static Repository_Point getInstance() {
        if (instance == null) {
            instance = new Repository_Point();
        }
        return instance;
    }

    private Repository_Point() {
        add(new Pojo_Point(0, "titulo 1", "contenido 1"));
        add(new Pojo_Point(0, "titulo 2", "contenido 2"));
        add(new Pojo_Point(0, "titulo 3", "contenido 3"));
        add(new Pojo_Point(0, "titulo 4", "contenido 4"));
        add(new Pojo_Point(1, "titulo 1", "contenido 1"));
        add(new Pojo_Point(1, "titulo 2", "contenido 2"));
        add(new Pojo_Point(1, "titulo 3", "contenido 3"));
    }

    public List<Pojo_Point> getPoints() {
        return this;
    }
}
