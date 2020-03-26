package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewFunction extends AppCompatActivity {

    private TextView name;
    private ImageView returnButton;
    private TextView adressNeighbour;
    private TextView phoneNeighbour;
    private TextView neighbourSite;
    private TextView describeNeighbour;
    private TextView neighbourMainName;
    private ImageView neighbourAvatar;
    private FloatingActionButton addFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_function);

        name = findViewById(R.id.nomVoisin);
        neighbourMainName = findViewById(R.id.nomVoisin2);
        returnButton = findViewById(R.id.bouton_retour);
        adressNeighbour = findViewById(R.id.adresse_voisin);
        phoneNeighbour = findViewById(R.id.tel_voisin);
        neighbourSite = findViewById(R.id.site_voisin);
        describeNeighbour = findViewById(R.id.info_voisin);
        addFavorite = findViewById(R.id.favoriteButton);
        neighbourAvatar = findViewById(R.id.avatarNeighbour);

        //Récupère les données transmises dans la première activité

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("voisin");


        //Ecrit les données récupérées dans cette activité

        name.setText(neighbour.getName());
        neighbourMainName.setText(neighbour.getName());
        adressNeighbour.setText(neighbour.getAddress());
        phoneNeighbour.setText(neighbour.getPhoneNumber());
        neighbourSite.setText("www.facebook.fr/"+neighbour.getName());
        describeNeighbour.setText(neighbour.getAboutMe());
        Picasso.get().load(neighbour.getAvatarUrl()).into(neighbourAvatar);

        if(DI.getNeighbourApiService().getFavorites().contains(neighbour)) {
            addFavorite.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundFavroite));
        }

        //Permet d'éteindre cette activité en cliquant sur la flèche

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Permet d'ajouter des voisins dans la liste des favoris

        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DI.getNeighbourApiService().addNeighbourFavorite(neighbour);
                addFavorite.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundFavroite));
            }
        });

    }
}