package com.deint.condominapp.repositories;

import com.deint.condominapp.pojos.Pojo_Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Repository_Note extends ArrayList<Pojo_Note> {
    private static Repository_Note instance;

    public static Repository_Note getInstance() {
        if (instance == null) {
            instance = new Repository_Note();
        }
        return instance;
    }

    private Repository_Note() {
        GregorianCalendar calendar_1 = new GregorianCalendar(2016, 3, 1);
        GregorianCalendar calendar_2 = new GregorianCalendar(2016, 3, 5);
        GregorianCalendar calendar_3 = new GregorianCalendar(2016, 3, 13);

        add(new Pojo_Note(99, new Date(calendar_1.getTimeInMillis()), "Corte de agua", "El martes 21 hay un corte de agua desde las 4.00 hasta las 8.00", false));
        add(new Pojo_Note(99, new Date(calendar_2.getTimeInMillis()), "Revisión ascensor", "El día 14 de diciembre viene el revisor a ver el ascensor", false));
        add(new Pojo_Note(99, new Date(calendar_3.getTimeInMillis()), "Revisión extintores", "El 15 de enero toca revisión de los extintores de la comunidad", false));
    }

    public List<Pojo_Note> getNotes() {
        return this;
    }
}
