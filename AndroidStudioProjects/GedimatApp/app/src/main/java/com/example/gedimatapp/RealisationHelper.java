package com.example.gedimatapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RealisationHelper extends SQLiteOpenHelper {
    public RealisationHelper(@Nullable Context context) {
        super(context, "realisation.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // création des tables de la base embarquée
        // création de la table realisation
        db.execSQL("CREATE TABLE realisation ("
                + "id_realisation INTEGER NOT NULL PRIMARY KEY ,"
                + "titre TEXT NOT NULL,"
                + "descr TEXT NOT NULL,"
                + "date_debut TEXT NOT NULL,"
                + "date_fin TEXT NOT NULL);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS realisation;");
        onCreate(db);
    }
}
