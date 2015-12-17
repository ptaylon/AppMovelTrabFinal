package com.carvalho.pedro.trabalhodispositivosmoveis.persist;

import android.content.Context;
import android.util.Log;

import com.carvalho.pedro.trabalhodispositivosmoveis.database.DataBaseHelper;
import com.carvalho.pedro.trabalhodispositivosmoveis.entities.Livro;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pedro on 12/12/2015.
 */

public class LivroPersistence {

    private Context ctx;
    private Dao<Livro,Integer> LivroDAO;

    private static LivroPersistence instancia =null;

    public LivroPersistence(Context ctx) {
        super();
        this.ctx = ctx;
        LivroDAO = DataBaseHelper.getInstancia(ctx).getLivroDAO();
    }

    //Salvar
    public int inserir(Livro Livro){
        try {
            return LivroDAO.createOrUpdate(Livro).getNumLinesChanged();
        } catch (SQLException e) {
            Log.e(LivroPersistence.class.getName(), e.getMessage());
        }
        return 0;
    }

    //Deletar
    public int delete(Livro Livro) {
        try {
            return LivroDAO.delete(Livro);
        } catch (SQLException e) {
            Log.e(LivroPersistence.class.getName(), e.getMessage());
        }
        return 0;
    }

    //Deleta todos
    public void deleteAll() {
        try {
            TableUtils.clearTable(DataBaseHelper.getInstancia(ctx).getConnectionSource(), Livro.class);
        } catch (SQLException e) {
            Log.e(LivroPersistence.class.getName(), e.getMessage());
        }
    }

    //Lista
    public List<Livro> getlistaLivro(){
        try {
            PreparedQuery<Livro> preparedQuery = LivroDAO.queryBuilder().prepare();
            return LivroDAO.query(preparedQuery);
        } catch (SQLException e) {
            Log.e(LivroPersistence.class.getName(), e.getMessage());
        }
        return null;
    }

    //Buscar por nome
    //Lista
    public List<Livro> getLivroPorNome(String nome){
        try {
            PreparedQuery<Livro> preparedQuery = LivroDAO.queryBuilder().where().like("titulo",nome).prepare();
            return LivroDAO.query(preparedQuery);
        } catch (SQLException e) {
            Log.e(LivroPersistence.class.getName(), e.getMessage());
        }
        return null;
    }

    //Buscar por id
    public Livro buscaPorId(int id){
        try {
            return LivroDAO.queryForId(id);
        } catch (SQLException e) {
            Log.e(LivroPersistence.class.getName(), e.getMessage());
        }
        return null;
    }

    //Capturar instancia
    public static LivroPersistence getInstancia(Context ctx){
        if (instancia == null) {
            return instancia = new LivroPersistence(ctx);
        }
        return instancia;
    }

}