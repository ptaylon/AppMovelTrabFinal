package com.carvalho.pedro.trabalhodispositivosmoveis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carvalho.pedro.trabalhodispositivosmoveis.entities.Livro;
import com.carvalho.pedro.trabalhodispositivosmoveis.persist.LivroPersistence;

public class AddLivroActivity extends AppCompatActivity {

    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_livro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Caso editar livro
        livro = (Livro) LivroPersistence.getInstancia(getApplicationContext()).buscaPorId(getIntent().getIntExtra("id_livro",0));
        if (livro != null){
            ((EditText) findViewById(R.id.editText)).setText(livro.getNome());
            ((EditText) findViewById(R.id.editText2)).setText(livro.getEditora());
            ((EditText) findViewById(R.id.editText3)).setText(livro.getAutor());
        }

        //Botao Salvar
        ((Button) findViewById(R.id.button)).setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                saveLivro();
            }
        });

    }

    public void saveLivro() {

        //Criando objeto Livro para persistencia
        livro = new Livro();

        EditText titulo = (EditText) findViewById(R.id.editText);
        EditText autor = (EditText) findViewById((R.id.editText2));
        EditText editora = (EditText) findViewById(R.id.editText3);
        livro.setNome(titulo.getText().toString());
        livro.setAutor(autor.getText().toString());
        livro.setEditora(editora.getText().toString());
        int resultado = LivroPersistence.getInstancia(this).inserir(livro);
        Toast.makeText(this, resultado == 1 ? "Sucesso em cadastrar livro" : "Erro ao cadastrar livro", Toast.LENGTH_LONG).show();

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
