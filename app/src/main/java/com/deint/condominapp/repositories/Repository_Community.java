package com.deint.condominapp.repositories;

import com.deint.condominapp.pojos.Pojo_Community;

import java.util.ArrayList;
import java.util.List;

public class Repository_Community extends ArrayList<Pojo_Community> {
    private static Repository_Community instance;

    public static Repository_Community getInstance() {
        if (instance == null) {
            instance = new Repository_Community();
        }
        return instance;
    }

    private Repository_Community() {
        add(new Pojo_Community(99, "Málaga", "Málaga", "Calle Falsa", "1", "-", "29114", 12, false));
    }

    public List<Pojo_Community> getCommunities() {
        return this;
    }
}
