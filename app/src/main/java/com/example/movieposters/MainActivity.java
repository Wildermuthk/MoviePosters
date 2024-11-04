package com.example.movieposters;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener{

    private Button buttonAddToWatchlist;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.
     *                           </i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchList);

        //Poster data.
        List<Poster> posterList = new ArrayList<>();

        //Alice in Wonderland poster
        Poster alice = new Poster();
        alice.image = R.drawable.poster1;
        alice.name = "Alice in Wonderland";
        alice.createdBy = "Tim Burton";
        alice.rating = 5f;
        alice.story = "A girl falls down a rabbit hole into a fantasy world.";
        posterList.add(alice);

        //The Martian poster
        Poster martian = new Poster();
        martian.image = R.drawable.poster2;
        martian.name = "The Martian";
        martian.createdBy = "Ridley Scott";
        martian.rating = 4f;
        martian.story = "A man gets left on Mars and must survive on his own.";
        posterList.add(martian);

        //The Princess Bride poster
        Poster princessBride = new Poster();
        princessBride.image = R.drawable.poster3;
        princessBride.name = "The Princess Bride";
        princessBride.createdBy = "Rob Reiner";
        princessBride.rating = 4.5f;
        princessBride.story = "A young woman and her true love are separated and must find each" +
                " other.";
        posterList.add(princessBride);

        //Pirates of the Caribbean: Dead Man's Chest poster
        Poster pirates = new Poster();
        pirates.image = R.drawable.poster4;
        pirates.name = "Pirates of the Caribbean: Dead Man's Chest";
        pirates.createdBy = "Gore Verbinski";
        pirates.rating = 4.75f;
        pirates.story = "A ghost pirate comes to collect a debt and interrupts a wedding.";
        posterList.add(pirates);

        //Spider-Man: Into the Spider-Verse poster
        Poster spiderman = new Poster();
        spiderman.image = R.drawable.poster5;
        spiderman.name = "Spider-Man: Into the Spider-Verse";
        spiderman.createdBy = "Peter Ramsey, Bob Persichetti, Rodney Rothman";
        spiderman.rating = 3.75f;
        spiderman.story = "A young man discovers he has superpowers and that others have the same" +
                " powers.";
        posterList.add(spiderman);

        //Jurassic Park poster
        Poster jurassicPark = new Poster();
        jurassicPark.image = R.drawable.poster6;
        jurassicPark.name = "Jurassic Park";
        jurassicPark.createdBy = "Steven Spielberg";
        jurassicPark.rating = 5f;
        jurassicPark.story = "A group of people journey to an island with resurrected dinosaurs.";
        posterList.add(jurassicPark);

        //The Lord of the Rings poster
        Poster rings = new Poster();
        rings.image = R.drawable.poster7;
        rings.name = "The Lord of the Rings";
        rings.createdBy = "Peter Jackson";
        rings.rating = 4f;
        rings.story = "A man is joined on a quest by a group of friends to destroy a ring.";
        posterList.add(rings);

        //StarWars: Attack of the Clones poster
        Poster starWars = new Poster();
        starWars.image = R.drawable.poster8;
        starWars.name = "Star Wars: Attack of the Clones";
        starWars.createdBy = "George Lucas";
        starWars.rating = 4f;
        starWars.story = "A man and a woman fall in love secretly and the man's mentor finds an " +
                "army of clones.";
        posterList.add(starWars);

        //The Fifth Element poster
        Poster fifthElement = new Poster();
        fifthElement.image = R.drawable.poster9;
        fifthElement.name = "The Fifth Element";
        fifthElement.createdBy = "Luc Besson";
        fifthElement.rating = 5f;
        fifthElement.story = "A man helps a woman with a language barrier save the world.";
        posterList.add(fifthElement);

        //How to Train Your Dragon poster
        Poster dragon = new Poster();
        dragon.image = R.drawable.poster10;
        dragon.name = "How to Train Your Dragon";
        dragon.createdBy = "Chris Sanders, Dean DeBlois";
        dragon.rating = 5f;
        dragon.story = "A young man befriends a dragon.";
        posterList.add(dragon);

        //The Greatest Showman poster
        Poster showman = new Poster();
        showman.image = R.drawable.poster11;
        showman.name = "The Greatest Showman";
        showman.createdBy = "Michael Gracey";
        showman.rating = 4.5f;
        showman.story = "A man starts a circus featuring people with interesting stories.";
        posterList.add(showman);

        //Up poster
        Poster up = new Poster();
        up.image = R.drawable.poster12;
        up.name = "Up";
        up.createdBy = "Pete Docter";
        up.rating = 5f;
        up.story = "An old man befriends a young boy and a dog and goes on an adventure.";
        posterList.add(up);

        final PosterAdapter postersAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(postersAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                List<Poster> selectPosters = postersAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++){
                    if (i == 0){
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * @param isSelected Variable for whether the poster is selected or not.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);

        }
    }
}