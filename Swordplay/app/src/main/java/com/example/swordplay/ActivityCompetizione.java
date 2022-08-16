package com.example.swordplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class ActivityCompetizione extends AppCompatActivity {

    public ExtendedFloatingActionButton exFab2;
    public TextView textCompetizione;
    public EditText ETnomeArbitro,ETpedana,ETgirone,ETatleti;
    public RadioGroup radioCategoria,radioSesso;
    public RadioButton rCategoria,rSesso;
    public RadioButton radioUomo,radioDonna;
    String risultato="";
    static ArrayList info;
    String competizione;
    int numAtleti;
    int numAssalti=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competizione);
        textCompetizione=findViewById(R.id.textCompetizione);
        ETnomeArbitro=findViewById(R.id.ETnomeArbitro);
        ETpedana=findViewById(R.id.ETpedana);
        ETgirone=findViewById(R.id.ETgirone);
        ETatleti=findViewById(R.id.ETatleti);
        radioCategoria=findViewById(R.id.radioCategoria);
        radioSesso=findViewById(R.id.radioSesso);
        radioUomo=findViewById(R.id.radioUomo);
        radioDonna=findViewById(R.id.radioDonna);
        exFab2=findViewById(R.id.exFab2);
        String i=getIntent().getStringExtra("nomeCompetizione");
        textCompetizione.setText(getString(R.string.informazioni_competizione)+" "+i);
        info= new ArrayList<String>();

        exFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controllo()==false){
                    Toast.makeText(view.getContext(), R.string.ToastCompetizioneUno,Toast.LENGTH_SHORT).show();
                }else{
                    numAtleti= Integer.parseInt(ETatleti.getText().toString());
                    numAssalti=controllonumAssalti(numAtleti);
                    Intent i= new Intent(view.getContext(), ActivityGirone.class);
                    i.putExtra("numeroAssalti",numAssalti);
                    i.putExtra("Girone",ETgirone.getText().toString());
                    startActivity(i);
                }
            }
        });
    }
    public boolean controllo(){
        competizione=getIntent().getStringExtra("nomeCompetizione");
        boolean controllo;
        if (ETatleti.length()==0 || ETnomeArbitro.length()==0 ||  ETgirone.length()==0 || ETpedana.length()==0 ||
                radioSesso.getCheckedRadioButtonId()==-1 || radioCategoria.getCheckedRadioButtonId()==-1){
            controllo=false;
        }else{
            risultato= "Ripilogo della Competizione " + competizione  +
                    "\n\nCATEGORIA: "+rCategoria.getText()+
                    "\nSESSO: "+rSesso.getText().toString()+
                    "\nNUMERO ATLETI: "+ETatleti.getText().toString()+
                    "\nARBITRO: "+ETnomeArbitro.getText().toString()+
                    "\nGIRONE: "+ETgirone.getText().toString()+
                    "\nPEDANA: "+ETpedana.getText().toString();
            controllo=true;
            info.add(risultato);
        }
        return controllo;
    }
    public int controllonumAssalti(int numAtleti){
        int numAssalti=0;
        if (numAtleti<=1){
            Toast.makeText(this, R.string.ToastCompetizioneDue,Toast.LENGTH_SHORT).show();
            return numAssalti;
        }else if (numAtleti==2){
            numAssalti=1;
            return numAssalti;
        }else if (numAtleti>2) {
            numAssalti = (numAtleti * (numAtleti - 1)/2);
            return numAssalti;
        }
        return numAssalti;
    }

    public void radioCategoria(View view) {
        int radioId=radioCategoria.getCheckedRadioButtonId();
        rCategoria =findViewById(radioId);
    }
    public void radioSesso(View view) {
        int radioId=radioSesso.getCheckedRadioButtonId();
        rSesso =findViewById(radioId);
    }

}