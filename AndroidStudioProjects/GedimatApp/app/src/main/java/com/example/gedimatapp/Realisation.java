package com.example.gedimatapp;

public class Realisation
{
    private String id_realisation;
    private String titre;
    private String descr;
    private String date_debut;
    private String date_fin;

    public Realisation()
    {
        this.id_realisation="";
        this.titre="";
        this.descr ="";
        this.date_debut ="";
        this.date_fin="";
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescr() {
        return descr;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getId() {
        return id_realisation;
    }

    public void setId(String id) {
        this.id_realisation = id;
    }

    @Override
    public String toString() {
        return "Realisation{" +
                "id='" + id_realisation + '\'' +
                ", titre='" + titre + '\'' +
                ", descr='" + descr + '\'' +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }

}



