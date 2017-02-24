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
import com.deint.condominapp.pojos.Pojo_Note;

import java.util.Comparator;
import java.util.List;

public class Adapter_Diary extends ArrayAdapter<Pojo_Note> {
    private Context context;

    public Adapter_Diary(Context context, List<Pojo_Note> pojo_notes) {
        super(context, R.layout.adapter_diary, pojo_notes);
        this.context = context;
    }

    private class DiaryHolder {
        TextView txtTitle;
        TextView txtDescription;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        DiaryHolder diaryHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_diary, parent, false);
            diaryHolder = new DiaryHolder();
            diaryHolder.txtTitle = (TextView) view.findViewById(R.id.adapterDiary_txtTitle);
            diaryHolder.txtDescription = (TextView) view.findViewById(R.id.adapterDiary_txtContent);

            view.setTag(diaryHolder);
        } else {
            diaryHolder = (DiaryHolder) view.getTag();
        }

        Pojo_Note note = getItem(position);
        if (note != null) {
            diaryHolder.txtTitle.setText(note.getNo_title());
            diaryHolder.txtDescription.setText(note.getNo_content());
        }

        return view;
    }

    @Nullable
    @Override
    public Pojo_Note getItem(int position) {
        return super.getItem(position);
    }

    /**
     * Method to sort the list with a given comparator
     *
     * @param comparator Order criterion
     */
    public void sortDiaries(Comparator<Pojo_Note> comparator) {
        this.sort(comparator);
        notifyDataSetChanged();
    }
}
