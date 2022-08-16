package com.example.swordplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

import static com.example.swordplay.ActivityCompetizione.info;

public class ActivityGirone extends AppCompatActivity {

    public ExtendedFloatingActionButton exFab;
    public ArrayList<NameModel> nameModels;
    private RecyclerView mRecyclerView;
    private ModelAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girone);
        String numGirone=getIntent().getStringExtra("Girone");
        exFab=findViewById(R.id.exFab);
        exFab.setText(getString(R.string.concludi_girone)+" "+ numGirone);

        createExampleList();
        buildRecyclerView();
        onClickGirone(exFab);
    }
    private void createExampleList() {
        int nAssalti=getIntent().getIntExtra("numeroAssalti",0);
        nameModels = new ArrayList<NameModel>(nAssalti);
        for(int i=1; i<=nAssalti; i++){
            nameModels.add(new NameModel(R.drawable.ic_baseline_arrow_forward_ios_24,
                    getString(R.string.assalto) + i,getString(R.string.incontro)));
        }
    }
    private void buildRecyclerView() {
        mRelativeLayout=findViewById(R.id.ll);
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager= new LinearLayoutManager(this);
        mAdapter=new ModelAdapter(nameModels);
        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ModelAdapter.OnItemClickListener() {
            @Override
            public void datiAlertbox(int position,String a) {
                changeItem(position,a);
            }
        });
    }
    public void changeItem(int position,String text){
        nameModels.get(position).changeText(text);
        mAdapter.notifyItemChanged(position);
    }
    public void onClickGirone(ExtendedFloatingActionButton exFab){
        exFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent (view.getContext(),ActivityRiepilogo2.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        for (int i=0;i<info.size();i++){
            info.remove(i);
        }
    }
}