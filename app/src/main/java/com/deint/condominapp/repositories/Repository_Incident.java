package com.deint.condominapp.repositories;

import com.deint.condominapp.pojos.Pojo_Incident;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Repository_Incident extends ArrayList<Pojo_Incident> {
    private static Repository_Incident instance;

    public static Repository_Incident getInstance() {
        if (instance == null) {
            instance = new Repository_Incident();
        }
        return instance;
    }

    private Repository_Incident() {
        GregorianCalendar calendar_1 = new GregorianCalendar(2016, 0, 2);
        GregorianCalendar calendar_2 = new GregorianCalendar(2016, 1, 14);
        GregorianCalendar calendar_3 = new GregorianCalendar(2016, 2, 27);

        add(new Pojo_Incident("12", 0, new Date(calendar_1.getTimeInMillis()), "Bombilla fundida", "La luz de la bombilla del rellano del tercero no va",
                "http://marketing4ecommerce.net/wp-content/uploads/2016/07/google-amp.jpg", false));
        add(new Pojo_Incident("12", 0, new Date(calendar_2.getTimeInMillis()), "Azulejo roto", "En la escalera del segundo al tercero hay un azulejo roto",
                "http://marketing4ecommerce.net/wp-content/uploads/2016/07/google-amp.jpg", false));
        add(new Pojo_Incident("12", 0, new Date(calendar_3.getTimeInMillis()), "Escalón roto", "A un escalón de la escalera del cuarto al ático le falta un trozo",
                "http://marketing4ecommerce.net/wp-content/uploads/2016/07/google-amp.jpg", false));
    }

    public List<Pojo_Incident> getIncidents() {
        return this;
    }
}
