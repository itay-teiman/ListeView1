package com.example.listeview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> planets;
    TTSManager ttsManager=null;

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

        // איתור רכיבים
        ListView list = findViewById(R.id.listView);
        TextView t1 = findViewById(R.id.textView);
        ttsManager = new TTSManager(this);

        // יצירת רשימה של כוכבי לכת
        planets = new ArrayList<>();
        planets.add("Jupiter");
        planets.add("Mars");
        planets.add("Mercury");
        planets.add("Saturn");
        planets.add("Venus");
        planets.add("Earth");
        planets.add("Uranus");
        planets.add("Neptune");

        // יצירת מתאם
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, planets);
        list.setAdapter(adapter);

        // הגדרת המאזין לאירועים
        list.setOnItemClickListener(this);
    }

    @Override

    protected void onDestroy() {

        super.onDestroy();

        ttsManager.shutdown();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String selectedPlanet = planets.get(position);
        ttsManager.speak(selectedPlanet,true);
        ////////////
    }
}
