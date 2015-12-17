package com.carvalho.pedro.trabalhodispositivosmoveis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
            startActivity(new Intent(MainActivity.this, ListLivrosActivity.class));
            return true;
        } else if (id == R.id.cadastrar_livro) {
            startActivity(new Intent(MainActivity.this, AddLivroActivity.class));
            return true;
        } else if (id == R.id.procurar_livro) {
            startActivity(new Intent(MainActivity.this, PesquisaActivity.class));
            return true;
        } else if (id == R.id.fechar_app) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
