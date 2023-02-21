package com.example.gedimatapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class RealisationDAO {
    private SQLiteDatabase maBase;
    private RealisationHelper monHelper;
    public RealisationDAO(Context context) {
        monHelper = new RealisationHelper(context);
        maBase = monHelper.getWritableDatabase();
    }



    public void supprimerTous() {
        maBase.delete("realisation",null,null);
    }

    public void ajouterRealisation(Realisation  UneRealisation) {
        //création d'un ContentValues
        ContentValues v = new ContentValues();
        // ajout des propriétés au ContentValues
        v.put("id_realisation", UneRealisation.getId());
        v.put("titre", UneRealisation.getTitre());
        v.put("descr", UneRealisation.getDescr());
        v.put("date_debut", UneRealisation.getDate_debut());
        v.put("date_fin", UneRealisation.getDate_fin());

        // ajout du concurrent dans la table
        maBase.insert("realisation", null, v);
    }



}

