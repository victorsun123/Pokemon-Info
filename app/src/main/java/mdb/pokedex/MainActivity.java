package mdb.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PokeAdapter pokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Pokedex pokedex = new Pokedex();
        final ArrayList<Pokedex.Pokemon> list = pokedex.getPokemon();

        final ArrayList<Pokedex.Pokemon> displayed = new ArrayList<Pokedex.Pokemon>();
        final ArrayList<Pokedex.Pokemon> low = new ArrayList<Pokedex.Pokemon>();
        final ArrayList<Pokedex.Pokemon> medium = new ArrayList<Pokedex.Pokemon>();
        final ArrayList<Pokedex.Pokemon> high = new ArrayList<Pokedex.Pokemon>();


        for(int i=0; i<list.size();i++){
            displayed.add(list.get(i));
        }

        for(int i=0;i<list.size();i++){
            if(Integer.parseInt(list.get(i).hp)<50){
                low.add(list.get(i));
            }
            else if(Integer.parseInt(list.get(i).hp)>=50 && Integer.parseInt(list.get(i).hp)<=100){
                medium.add(list.get(i));
            }
            else{
                high.add(list.get(i));
            }
        }


        for(int i = 0; i<low.size();i++){
            System.out.println(low.get(i).name);
        }

        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setChecked(true);
        toggle.setText("Low");
        toggle.setTextOn("Low");
        toggle.setTextOff("Low");

        final ToggleButton toggle2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggle2.setChecked(true);
        toggle2.setText("Medium");
        toggle2.setTextOn("Medium");
        toggle2.setTextOff("Medium");

        final ToggleButton toggle3 = (ToggleButton) findViewById(R.id.toggleButton3);
        toggle3.setChecked(true);
        toggle3.setText("High");
        toggle3.setTextOn("High");
        toggle3.setTextOff("High");


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for(int i=0; i<low.size();i++){
                        for(int j=0;j<displayed.size();j++){
                            if(low.get(i).name.compareTo(displayed.get(j).name)<0) {            //adds pokemon back to displayed list alphabetically
                                displayed.add(j,low.get(i));
                                j=displayed.size();
                            }
                        }
                    }


                } else {
                    for(int i=0;i<displayed.size();i++){
                        if(Integer.parseInt(displayed.get(i).hp)<50){
                            displayed.remove(i);
                            i--;
                        }
                    }
                }
                pokeAdapter.pokemonArrayList = displayed;
                pokeAdapter.notifyDataSetChanged();
            }
        });



        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for(int i=0; i<medium.size();i++){
                        for(int j=0;j<displayed.size();j++){
                            if(medium.get(i).name.compareTo(displayed.get(j).name)<0) {            //adds pokemon back to displayed list alphabetically
                                displayed.add(j,medium.get(i));
                                j=displayed.size();
                            }
                        }
                    }


                } else {
                    for(int i=0;i<displayed.size();i++){
                        if(Integer.parseInt(displayed.get(i).hp)>=50 && Integer.parseInt(displayed.get(i).hp)<=100){
                            displayed.remove(i);
                            i--;
                        }
                    }
                }
                pokeAdapter.pokemonArrayList = displayed;
                pokeAdapter.notifyDataSetChanged();
            }
        });

        toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for(int i=0; i<high.size();i++){
                        for(int j=0;j<displayed.size();j++){
                            if(high.get(i).name.compareTo(displayed.get(j).name)<0) {            //adds pokemon back to displayed list alphabetically
                                displayed.add(j,high.get(i));
                                j=displayed.size();
                            }
                        }
                    }


                } else {
                    for(int i=0;i<displayed.size();i++){
                        if(Integer.parseInt(displayed.get(i).hp)>100){
                            displayed.remove(i);
                            i--;
                        }
                    }
                }
                pokeAdapter.pokemonArrayList = displayed;
                pokeAdapter.notifyDataSetChanged();
            }
        });



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        pokeAdapter = new PokeAdapter(getApplicationContext(), displayed);

        recyclerView.setAdapter(pokeAdapter);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
    
}
