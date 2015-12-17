package com.carvalho.pedro.trabalhodispositivosmoveis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.carvalho.pedro.trabalhodispositivosmoveis.entities.Livro;
import com.carvalho.pedro.trabalhodispositivosmoveis.persist.LivroPersistence;
import com.carvalho.pedro.trabalhodispositivosmoveis.util.LivroAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListLivrosActivity extends AppCompatActivity {

    private List<Livro> listaDeLivros;
    private ListView listView;
    private LivroAdapter livroListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_livros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaDeLivros = (ArrayList<Livro>) LivroPersistence.getInstancia(getApplicationContext()).getlistaLivro();
        livroListAdapter = new LivroAdapter(this, listaDeLivros);

        listView = (ListView) findViewById(R.id.listView);
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(livroListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent clienteListView = new Intent(ListLivrosActivity.this, AddLivroActivity.class);
                clienteListView.putExtra("id_livro", listaDeLivros.get(position).getId());
                startActivity(clienteListView);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.listar_livros) {
            startActivity(new Intent(getApplicationContext(), ListLivrosActivity.class));
            return true;
        } else if (id == R.id.cadastrar_livro) {
            startActivity(new Intent(getApplicationContext(), AddLivroActivity.class));
            return true;
        } else if (id == R.id.procurar_livro) {
            startActivity(new Intent(getApplicationContext(), PesquisaActivity.class));
            return true;
        } else if (id == R.id.fechar_app) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
