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
import com.deint.condominapp.repositories.Repository_Document;
import com.deint.condominapp.pojos.Pojo_Document;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Adapter_Document extends ArrayAdapter<Pojo_Document> {
    private Context context;

    public Adapter_Document(Context context, List<Pojo_Document> pojo_documents) {
        super(context, R.layout.adapter_document, pojo_documents);
        this.context = context;
    }

    private class DocumentHolder {
        TextView txtTitle;
        TextView txtDescription;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        DocumentHolder documentHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_document, parent, false);
            documentHolder = new DocumentHolder();
            documentHolder.txtTitle = (TextView) view.findViewById(R.id.adapterDocument_txtTitle);
            documentHolder.txtDescription = (TextView) view.findViewById(R.id.adapterDocument_txtContent);

            view.setTag(documentHolder);
        } else {
            documentHolder = (DocumentHolder) view.getTag();
        }

        Pojo_Document document = getItem(position);
        if (document != null) {
            documentHolder.txtTitle.setText(document.getDo_title());
            documentHolder.txtDescription.setText(document.getDo_description());
        }

        return view;
    }

    @Nullable
    @Override
    public Pojo_Document getItem(int position) {
        return super.getItem(position);
    }

    /**
     * Method to sort the list with a given comparator
     *
     * @param comparator Order criterion
     */
    public void sortDocuments(Comparator<Pojo_Document> comparator) {
        Collections.sort(Repository_Document.getInstance(), comparator);
        notifyDataSetChanged();
    }
}
