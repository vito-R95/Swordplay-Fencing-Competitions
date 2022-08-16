package com.example.swordplay;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.jar.Attributes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.swordplay.ActivityCompetizione.info;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {

    private ArrayList<NameModel> mNameModels;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void datiAlertbox(int position, String a);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
        ModelAdapter.ModelViewHolder evh = new ModelAdapter.ModelViewHolder(v, mListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        NameModel currentItem = mNameModels.get(position);
        holder.mAssalto.setText(currentItem.getAssalto());
        holder.mIncontro.setText(currentItem.getmIncontro());
    }
    @Override
    public int getItemCount() {
        return mNameModels.size();
    }

    public static class ModelViewHolder extends RecyclerView.ViewHolder {
        public TextView mAssalto;
        public TextView mIncontro;
        public ImageView mImage;
        public int n=0;
        public int cont1 = 0;
        public int cont2 = 0;
        public String Risultato="";
        public ImageButton conferma, close, bMenoUno, bPiuUno, bMenoDue, bPiuDue;
        public TextView punteggioUno, punteggioDue, ETAtletaUno, ETAtletaDue;

        public ModelViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mAssalto = itemView.findViewById(R.id.textAssalto2);
            mIncontro = itemView.findViewById(R.id.textIncontro);
            itemView.setOnClickListener(new View.OnClickListener() {//al click di una Card faccio aprire un pop up dove inserire nomi giocatori e punteggi
                @Override
                public void onClick(View view) {

                    if (listener != null) {
                        final int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            final Dialog a = new Dialog(view.getContext());
                            a.setContentView(R.layout.dialogbox);
                            conferma = a.findViewById(R.id.ok);
                            close = a.findViewById(R.id.close);
                            bMenoUno = a.findViewById(R.id.bMenoUno);
                            bPiuUno = a.findViewById(R.id.bPiuUno);
                            punteggioUno = a.findViewById(R.id.punteggioUno);
                            punteggioDue = a.findViewById(R.id.punteggioDue);
                            ETAtletaUno = a.findViewById(R.id.ETAtletaUno);
                            ETAtletaDue = a.findViewById(R.id.ETAtletaDue);
                            bMenoDue = a.findViewById(R.id.bMenoDue);
                            bPiuDue = a.findViewById(R.id.bPiuDue);
                            a.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            a.show();

                            bPiuUno.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    cont1++;
                                    punteggioUno.setText(Integer.toString(cont1));
                                }
                            });
                            bMenoUno.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (cont1 == 0) {
                                        Toast.makeText(view.getContext(), R.string.toastAlertBox, Toast.LENGTH_SHORT).show();
                                    } else {
                                        cont1--;
                                        punteggioUno.setText(Integer.toString(cont1));
                                    }
                                }
                            });
                            bPiuDue.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    cont2++;
                                    punteggioDue.setText(Integer.toString(cont2));
                                }
                            });
                            bMenoDue.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (cont2 == 0) {
                                        Toast.makeText(view.getContext(), R.string.toastAlertBox, Toast.LENGTH_SHORT).show();
                                    } else {
                                        cont2--;
                                        punteggioDue.setText(Integer.toString(cont2));
                                    }
                                }
                            });
                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    a.dismiss();
                                }
                            });
                            //alla conferma del popup oltre ad inserire nomi dei giocatori e punteggi inserisco la stringa che contiene giocatori e punteggi
                            // nello stesso arraylist info creato precedentemente, al fine di visualizzarli successivamente
                            conferma.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Risultato= ETAtletaUno.getText().toString()+" " + punteggioUno.getText().toString() +
                                            "  -  " + ETAtletaDue.getText().toString() +" "+ punteggioDue.getText().toString();

                                    info.add("Assalto "+position+": "+Risultato );
                                    listener.datiAlertbox(position, Risultato);
                                    cont1 = 0;
                                    cont2 = 0;
                                    a.dismiss();
                                }

                            });
                        }

                    }
                }
            });
        }
    }
        public ModelAdapter(ArrayList<NameModel> nameModels) {
            mNameModels = nameModels;
        }
}

