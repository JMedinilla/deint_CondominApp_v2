package com.deint.condominapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deint.condominapp.R;
import com.deint.condominapp.pojos.Pojo_Entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Adapter_CBoard extends ArrayAdapter<Pojo_Entry> {
    private Context context;

    public Adapter_CBoard(Context context) {
        super(context, R.layout.adapter_cboard);
        this.context = context;
    }

    public void updateElements(List<Pojo_Entry> pojo_entries) {
        this.clear();
        this.addAll(pojo_entries);
        notifyDataSetChanged();
    }

    private class CBoardHolder {
        TextView txtTitle;
        TextView txtDescription;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        CBoardHolder cBoardHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_cboard, parent, false);
            cBoardHolder = new CBoardHolder();
            cBoardHolder.txtTitle = (TextView) view.findViewById(R.id.adapterCBoard_txtTitle);
            cBoardHolder.txtDescription = (TextView) view.findViewById(R.id.adapterCBoard_txtContent);

            view.setTag(cBoardHolder);
        } else {
            cBoardHolder = (CBoardHolder) view.getTag();
        }

        Pojo_Entry entry = getItem(position);
        if (entry != null) {
            cBoardHolder.txtTitle.setText(entry.getEn_title());
            cBoardHolder.txtDescription.setText(entry.getEn_content());
        }

        return view;
    }

    @Nullable
    @Override
    public Pojo_Entry getItem(int position) {
        return super.getItem(position);
    }

    /**
     * Method to sort the list with a given comparator
     *
     * @param comparator Order criterion
     */
    public void sortSecondEntries(Comparator<Pojo_Entry> comparator) {
        ArrayList<Pojo_Entry> list = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            list.add(getItem(i));
        }
        Collections.sort(list, comparator);
        clear();
        addAll(list);
        notifyDataSetChanged();
    }
}
