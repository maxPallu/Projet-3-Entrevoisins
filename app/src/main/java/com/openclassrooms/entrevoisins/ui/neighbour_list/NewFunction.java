package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
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
    private ImageButton deleteFavorite;

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
        String neighbourName = intent.getStringExtra("nomVoisins");
        String neighbourAdress = intent.getStringExtra("neighbourAdress");
        String neighbourPhone = intent.getStringExtra("neighbourTel");
        String descriptionNeighbour = intent.getStringExtra("infoNeighbour");
        String UrlAvatar = intent.getStringExtra("avatarURL");
        long neighbourID = intent.getLongExtra("neighbour_id", 0);

        Neighbour favoriteNeighbour = new Neighbour(neighbourID, neighbourName, UrlAvatar, neighbourAdress, neighbourPhone, descriptionNeighbour);

        //Ecrit les données récupérées dans cette activité

        name.setText(neighbourName);
        neighbourMainName.setText(neighbourName);
        adressNeighbour.setText(neighbourAdress);
        phoneNeighbour.setText(neighbourPhone);
        neighbourSite.setText("www.facebook.fr/"+neighbourName);
        describeNeighbour.setText(descriptionNeighbour);
        Picasso.get().load(UrlAvatar).into(neighbourAvatar);

        if(DI.getNeighbourApiService().getFavorites().contains(favoriteNeighbour)) {
            addFavorite.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundFavroite));
            DI.getNeighbourApiService().deleteFavorite(favoriteNeighbour);
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
                DI.getNeighbourApiService().addNeighbourFavorite(favoriteNeighbour);
                addFavorite.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundFavroite));
            }
        });

    }
}