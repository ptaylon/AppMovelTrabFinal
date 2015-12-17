package com.carvalho.pedro.trabalhodispositivosmoveis.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.carvalho.pedro.trabalhodispositivosmoveis.entities.Livro;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by Pedro on 12/12/2015.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "biblioteca.db";
    private static final int VERSAO = 1;
    private static DataBaseHelper instancia = null;
    private Dao<Livro, Integer> LivroDAO = null;

    //construtor da classe
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSAO);
    }

    //evendo de criacao da classe implementa a criacao da tabela
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connS) {
        try {
            TableUtils.createTable(connS, Livro.class);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), e.getMessage());
        }
    }

    //Metodo de atualizacao da tabela
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connS, int oldV, int newV) {
        try {
            TableUtils.dropTable(connS, Livro.class, true);
            onCreate(db, connS);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), e.getMessage());
        }
    }

    //devolvendo uma unica instancia da classe
    public static DataBaseHelper getInstancia(Context context){
        if (instancia == null){
            return instancia = new DataBaseHelper(context.getApplicationContext());
        }
        return instancia;

    }

    public Dao<Livro,Integer> getLivroDAO() {
        if (LivroDAO == null){
            try {
                return LivroDAO = getDao(Livro.class);
            } catch (SQLException e) {
                Log.e(DataBaseHelper.class.getName(), e.getMessage());
            }
        }
        return LivroDAO;
    }

}
