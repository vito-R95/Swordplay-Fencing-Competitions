package com.example.swordplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public TextView textBenvenuto,textSelezione,textTitolo,textSubtitolo;
    public ExtendedFloatingActionButton exFabSpada,exFabSciabola,exFabFioretto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBenvenuto=findViewById(R.id.textBenvenuto);
        textTitolo=findViewById(R.id.textTitolo);
        textSubtitolo=findViewById(R.id.textSubtitolo);
        textSelezione=findViewById(R.id.textSelezione);
        exFabSpada=findViewById(R.id.exFabSpada);
        exFabSciabola=findViewById(R.id.exFabSciabola);
        exFabFioretto=findViewById(R.id.exFabFioretto);
        exFabSpada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ActivityCompetizione.class);
                i.putExtra("nomeCompetizione","Spada");
                startActivity(i);
            }
        });
        exFabSciabola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ActivityCompetizione.class);
                i.putExtra("nomeCompetizione","Sciabola");
                startActivity(i);
            }
        });
        exFabFioretto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ActivityCompetizione.class);
                i.putExtra("nomeCompetizione","Fioretto");
                startActivity(i);
            }
        });
    }
}