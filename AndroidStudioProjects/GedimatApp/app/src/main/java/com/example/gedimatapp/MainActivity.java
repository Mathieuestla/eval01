package com.example.gedimatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import cz.msebera.android.httpclient.Header;
public class MainActivity extends AppCompatActivity {

    private Button btnIMPORT = null;
    private Button btnEXPORT = null;
    Realisation bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtention des références sur les vues de l'activité
        btnIMPORT = (Button) findViewById(R.id.boutonImport);
        btnEXPORT = (Button) findViewById(R.id.boutonExport);

        btnIMPORT.setOnClickListener(EcouteurBouton);
        btnEXPORT.setOnClickListener(EcouteurBouton);

        Date dateActuelle = Calendar.getInstance().getTime();
        Date debutVote = new Date(123,2,17);
        Date finInscription = new Date(123,1,10);

        if(dateActuelle.before(debutVote) && dateActuelle.after(finInscription)){
            btnIMPORT.setEnabled(true);
        }
        else{
            btnIMPORT.setEnabled(false);
        }
    }

    public View.OnClickListener EcouteurBouton = new View.OnClickListener() {
        @SuppressLint("Range")
        @Override

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.boutonImport:
                    // Requête HTTP GET
                    String urlI = "http://10.0.2.2/Mathieu/gedimat/APIGedimat/realisations.php";
                    AsyncHttpClient requestI = new AsyncHttpClient();

                    requestI.get(urlI, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            super.onSuccess(statusCode, headers, response);
                            Log.i("donnée",response.toString());
                            // Deserialisation du flux JSON
                            RealisationDAO bdd;

                            bdd = new RealisationDAO(MainActivity.this);
                            bdd.supprimerTous();

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    String id =response.getJSONObject(i).getString("id_realisation");
                                    String titre = response.getJSONObject(i).getString("titre");
                                    String descr = response.getJSONObject(i).getString("descr");
                                    String date_debut = response.getJSONObject(i).getString("date_debut");
                                    String date_fin = response.getJSONObject(i).getString("date_fin");

                                    Realisation R = new Realisation();
                                    R.setId(id);
                                    R.setTitre(titre);
                                    R.setDescr(descr);
                                    R.setDate_debut(date_debut);
                                    R.setDate_fin(date_fin);

                                    Log.i("info", R.toString());
                                    bdd.ajouterRealisation(R);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Toast.makeText(getApplicationContext(), "Imporation terminée", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Log.i("Erreur", String.valueOf(statusCode) + "Erreur = " + responseString);
                            Toast.makeText(getApplicationContext(), "Echec de l'importation", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        }
    };

}