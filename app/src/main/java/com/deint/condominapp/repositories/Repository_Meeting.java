package com.deint.condominapp.repositories;

import com.deint.condominapp.pojos.Pojo_Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Repository_Meeting extends ArrayList<Pojo_Meeting> {
    private static Repository_Meeting instance;

    public static Repository_Meeting getInstance() {
        if (instance == null) {
            instance = new Repository_Meeting();
        }
        return instance;
    }

    private Repository_Meeting() {
        GregorianCalendar calendar_1 = new GregorianCalendar(2016, 4, 4);
        GregorianCalendar calendar_2 = new GregorianCalendar(2015, 5, 16);

        add(new Pojo_Meeting(0, 99, new Date(calendar_1.getTimeInMillis()), false));
        add(new Pojo_Meeting(1, 99, new Date(calendar_2.getTimeInMillis()), false));
    }

    public List<Pojo_Meeting> getMeetings() {
        return this;
    }
}
