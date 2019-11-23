package com.isetkelibia.listviewintent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    final String EXTRA_TITLE = "title";
    final String EXTRA_DESCRIPTION = "description";
    String title = "";
    String description = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On lui associe le layout afficahgequestionactivity.xml
        setContentView(R.layout.activity_question);

        // On récupère tous les éléments
        TextView textView = findViewById(R.id.question);
        Button buttonYes = findViewById(R.id.buttonYes);
        Button buttonNo = findViewById(R.id.buttonNo);

        // On récupère l'objet Bundle envoyé par l'autre activité
        Bundle oBundle = this.getIntent().getExtras();
        // On récupère les données du Bundle
        if (oBundle != null && oBundle.containsKey("title")
                && oBundle.containsKey("description")) {
            // title = (String) oBundle.get("title");
            title = getIntent().getStringExtra(EXTRA_TITLE);
            description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        } else {
            // Erreur
            title = "Error";
            description = "Error";
        }

        // On crée notre question
        assert description != null;
        description = description.toLowerCase();
        String message = getString(R.string.use) + " " + title + " " + getString(R.string.like)
                + " " + description + " ?";
        textView.setText(message);
        // On crée un écouteur d'évènement pour chaque bouton
        buttonYes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (title) {
                    // Si le titre est égale à "Word"
                    case "Word":
                        // On renvoie la valeur 1 à l'activité principale
                        setResult(1);
                        break;
                    // Si le titre est égale à "Excel"
                    case "Excel":
                        // On renvoie la valeur 2 l'activité principale
                        setResult(2);
                        break;
                    // Si le titre est égale à "PowerPoint"
                    case "PowerPoint":
                        // On renvoie la valeur 3 l'activité principale
                        setResult(3);
                        break;
                    // Si le titre est égale à "Outlook"
                    case "Outlook":
                        // On renvoie la valeur 4 l'activité principale
                        setResult(4);
                        break;
                }
                finish();
            }
        });

        buttonNo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (title) {
                    case "Word":
                        // On renvoie 5 à l'activité principale
                        setResult(5);
                        break;
                    case "Excel":
                        // On renvoie 6 à l'activité principale
                        setResult(6);
                        break;
                    case "PowerPoint":
                        // On renvoie la valeur 7 l'activité principale
                        setResult(7);
                        break;
                    case "Outlook":
                        // On renvoie la valeur 8 l'activité principale
                        setResult(8);
                        break;
                }
                finish();
            }
        });
    }
}