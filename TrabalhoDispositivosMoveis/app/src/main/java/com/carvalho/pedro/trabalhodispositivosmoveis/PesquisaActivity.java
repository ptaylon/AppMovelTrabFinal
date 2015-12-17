package com.carvalho.pedro.trabalhodispositivosmoveis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.carvalho.pedro.trabalhodispositivosmoveis.entities.Livro;
import com.carvalho.pedro.trabalhodispositivosmoveis.persist.LivroPersistence;
import com.carvalho.pedro.trabalhodispositivosmoveis.util.LivroAdapter;

import java.util.ArrayList;
import java.util.List;

public class PesquisaActivity extends AppCompatActivity {

    private List<Livro> listaDeLivros;
    private ListView listView;
    private LivroAdapter livroListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.btnPesquisar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textoPesquisa = (EditText) findViewById(R.id.edtPesquisar);
                if (textoPesquisa.getText().toString() != null) {
                    listaDeLivros = (ArrayList<Livro>) LivroPersistence.getInstancia(getApplicationContext()).getLivroPorNome(textoPesquisa.getText().toString());
                    livroListAdapter = new LivroAdapter(v.getContext(), listaDeLivros);
                    listView = (ListView) findViewById(R.id.listView);
                    listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(livroListAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            Intent clienteListView = new Intent(PesquisaActivity.this, AddLivroActivity.class);
                            clienteListView.putExtra("id_livro", listaDeLivros.get(position).getId());
                            startActivity(clienteListView);
                        }

                    });

                } else {
                    listView.setVisibility(View.INVISIBLE);
                    listView.setAdapter(null);
                }
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
