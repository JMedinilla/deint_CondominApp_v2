package com.deint.condominapp.repositories;

import com.deint.condominapp.pojos.Pojo_Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Repository_Entry_First extends ArrayList<Pojo_Entry> {
    private static Repository_Entry_First instance;

    public static Repository_Entry_First getInstance() {
        if (instance == null) {
            instance = new Repository_Entry_First();
        }
        return instance;
    }

    private Repository_Entry_First() {
        GregorianCalendar calendar_1 = new GregorianCalendar(2016, 3, 2);
        GregorianCalendar calendar_2 = new GregorianCalendar(2016, 3, 14);

        add(new Pojo_Entry("12", 0, "Ruidos nocturnos",
                "Se recuerda a los vecinos que el ayuntamiento prohibe el ruido a partir de las 21.00",
                new Date(calendar_1.getTimeInMillis()), Pojo_Entry.FIRST, false));
        add(new Pojo_Entry("12", 0, "Ascensor",
                "El ascensor está estropeado dessde el martes pasado. El revisor vendrá el mes que viene",
                new Date(calendar_2.getTimeInMillis()), Pojo_Entry.FIRST, false));
    }

    public List<Pojo_Entry> getEntries() {
        return this;
    }
}
