package com.carvalho.pedro.trabalhodispositivosmoveis.entities;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Pedro on 12/12/2015.
 */

@DatabaseTable(tableName = "livro")
public class Livro {

    @DatabaseField(columnName = "id" ,generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "titulo", dataType=DataType.STRING, width = 80)
    private String nome;

    @DatabaseField(columnName = "autor", dataType=DataType.STRING, width = 80)
    private String autor;

    @DatabaseField(columnName = "editora", dataType=DataType.STRING, width = 40)
    private String editora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

}
