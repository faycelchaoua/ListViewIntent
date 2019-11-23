package com.isetkelibia.listviewintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_DE_MON_ACTIVITE = 1;
    private final String EXTRA_TITLE = "title";
    private final String EXTRA_DESCRIPTION = "description";
    private ListView maListViewPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView
        // Récupération de la "ListView" créée dans le fichier activity_main.xml
        maListViewPerso = findViewById(R.id.listviewperso);

        // Création de la "ArrayList" qui nous permettra de remplir la "ListView"
        ArrayList<HashMap<String, String>> listItems = new ArrayList<>();

        // On déclare la "HashMap" qui contiendra les informations pour un item
        HashMap<String, String> item;

        // Titre des items
        String[] title = new String[]{
                getString(R.string.word),
                getString(R.string.excel),
                getString(R.string.powerpoint),
                getString(R.string.outlook)};
        // Description des items
        String[] description = new String[]{
                getString(R.string.word_description),
                getString(R.string.excel_description),
                getString(R.string.powerpoint_description),
                getString(R.string.outlook_description)};
        // Icones (images) des items
        String[] icon = new String[]{
                String.valueOf(R.drawable.word),
                String.valueOf(R.drawable.excel),
                String.valueOf(R.drawable.powerpoint),
                String.valueOf(R.drawable.outlook)};
        // Creation des items de la liste
        for (int i = 0; i < title.length; i++) {
            item = new HashMap<>();
            // Titre
            item.put("title", title[i]);
            // Description
            item.put("description", description[i]);
            // Icone
            item.put("icon", icon[i]);
            listItems.add(item);
        }

        // Creation d l’Adapter
        SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(),
                listItems,
                R.layout.activity_list_item,
                new String[]{"title", "description", "icon"},
                new int[]{R.id.title, R.id.description, R.id.icon});
        // Association de l’adapter à la liste
        maListViewPerso.setAdapter(adapter);

        // Interaction avec les items de la liste
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> item = (HashMap) maListViewPerso.getItemAtPosition(position);

                // On crée un objet Bundle, c'est ce qui va nous permettre d'envoyer des données
                // à l'autre activité
                Bundle oBundle = new Bundle();

                // Cela fonctionne plus ou moins comme une HashMap,
                // on entre une clef et sa valeur en face
                oBundle.putString(EXTRA_TITLE, item.get("title"));
                oBundle.putString(EXTRA_DESCRIPTION, item.get("description"));

                // On crée l'Intent qui va nous permettre d'afficher l'autre activité
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);

                // On affecte à l'Intent le Bundle que l'on a créé
                intent.putExtras(oBundle);

                // On démarre l'autre activité
                startActivityForResult(intent, CODE_DE_MON_ACTIVITE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //  On regarde quelle activité a répondu
        if (requestCode == CODE_DE_MON_ACTIVITE) {
            // On récupère l'élément racine de l'interface graphique de l'activité
            final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                    .findViewById(android.R.id.content)).getChildAt(0);

            // On regarde qu'elle est la réponse envoyée.
            // En fonction de la réponse, on affiche un message différent.
            switch (resultCode) {
                case 1:
                    Snackbar.make(viewGroup, R.string.use_word, Snackbar.LENGTH_LONG).show();
                    break;
                case 2:
                    Snackbar.make(viewGroup, R.string.use_excel, Snackbar.LENGTH_LONG).show();
                    break;
                case 3:
                    Snackbar.make(viewGroup, R.string.use_powerpoint, Snackbar.LENGTH_LONG).show();
                    break;
                case 4:
                    Snackbar.make(viewGroup, R.string.use_outlook, Snackbar.LENGTH_LONG).show();
                    break;
                case 5:
                    Snackbar.make(viewGroup, R.string.not_use_word, Snackbar.LENGTH_LONG).show();
                    break;
                case 6:
                    Snackbar.make(viewGroup, R.string.not_use_excel, Snackbar.LENGTH_LONG).show();
                    break;
                case 7:
                    Snackbar.make(viewGroup, R.string.not_use_powerpoint,
                            Snackbar.LENGTH_LONG).show();
                    break;
                case 8:
                    Snackbar.make(viewGroup, R.string.not_use_outlook, Snackbar.LENGTH_LONG).show();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}