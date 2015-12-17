package com.carvalho.pedro.trabalhodispositivosmoveis.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.carvalho.pedro.trabalhodispositivosmoveis.R;
import com.carvalho.pedro.trabalhodispositivosmoveis.entities.Livro;

import java.util.List;

/**
 * Created by Pedro on 13/12/2015.
 */
public class LivroAdapter extends BaseAdapter {

    private List<Livro> lista;
    private LayoutInflater inflater;

    public LivroAdapter(Context ctx, List<Livro> lista) {
        inflater = LayoutInflater.from(ctx);
        this.lista = lista;
    }

    public int getCount(){
        return this.lista != null ? this.lista.size() : 0 ;
    }

    public Object getItem(int position){
        return this.lista.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.content_item_list, parent, false);
        }

        //Busca o item
        Livro livro = lista.get(position);

        //Seta o nome da Livro
        ((TextView) convertView.findViewById(R.id.nome_livro)).setText(livro.getNome());

        return convertView;

    }
}
