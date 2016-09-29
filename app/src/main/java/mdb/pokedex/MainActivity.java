package mdb.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Pokedex pokedex = new Pokedex();
        ArrayList<Pokedex.Pokemon> list = pokedex.getPokemon();

        PokeAdapter pokeAdapter = new PokeAdapter(getApplicationContext(), list);

        recyclerView.setAdapter(pokeAdapter);
    }
    
}
