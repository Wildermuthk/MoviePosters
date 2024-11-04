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

        //prepare data

        List<Poster> posterList = new ArrayList<>();

        Poster alice = new Poster();
        alice.image = R.drawable.poster1;
        alice.name = "Alice in Wonderland";
        alice.createdBy = "Disney";
        alice.rating = 5f;
        alice.story = "A girl falls down a rabbit hole into a fantasy world.";
        posterList.add(alice);

        Poster martian = new Poster();
        martian.image = R.drawable.poster2;
        martian.name = "The Martian";
        martian.createdBy = "Ridley Scott";
        martian.rating = 4f;
        martian.story = "A man gets left on Mars and must survive on his own.";
        posterList.add(martian);

        final PosterAdapter postersAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(postersAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);

        }
    }
}