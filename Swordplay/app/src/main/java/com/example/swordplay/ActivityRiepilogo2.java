package com.example.swordplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static com.example.swordplay.ActivityCompetizione.info;

public class ActivityRiepilogo2 extends AppCompatActivity {

    public TextView vediPDF;
    Bitmap btm, scaledbmp;
    public ExtendedFloatingActionButton exFabPDF;
    private int storagePermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo2);
        vediPDF = findViewById(R.id.textRiepilogo2);
        vediPDF.setMovementMethod(new ScrollingMovementMethod());
        exFabPDF = findViewById(R.id.exFabPDF);
        vediPDF.setText(leggi(info));
        onClickButtonPDF(exFabPDF);
    }
    private void onClickButtonPDF(ExtendedFloatingActionButton exFabPDF) {
        exFabPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    String myFilePath2 = Environment.getExternalStorageDirectory().getPath() + "/SwordplayPDF" + leggi_directory() + ".pdf";
                    File a2 = new File(myFilePath2);
                    createPDF(a2.getName());
                } else {
                    requestStoragePermission();
                }
            }
        });
    }
    public void createPDF(String a) {
        PdfDocument mypdfDocument = new PdfDocument();
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(720, 1280, 1).create();
        PdfDocument.Page mypage = mypdfDocument.startPage(mypageInfo);
        Paint mypaint = new Paint();
        btm = BitmapFactory.decodeResource(getResources(), R.drawable.pdf);
        scaledbmp = Bitmap.createScaledBitmap(btm, 720, 1280, false);
        mypage.getCanvas().drawBitmap(scaledbmp, 0, 0, mypaint);
        //String myString = "questa è la \n nuova stringa";
        String myString = "" + leggi(info);
        mypaint.setTextSize(30);
        int x = 50, y = 200;
        for (String line : myString.split("\n")) {
            mypage.getCanvas().drawText(line, x, y, mypaint);
            y += mypaint.descent() - mypaint.ascent();
        }
        mypdfDocument.finishPage(mypage);
        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + a;
        File myfile = new File(myFilePath);
        try {
            mypdfDocument.writeTo(new FileOutputStream(myfile));
            Toast.makeText(this, R.string.pdf_salvato, Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            vediPDF.setText("ERROR");
        }
        mypdfDocument.close();
    }
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ) {
            new AlertDialog.Builder(this)
                    .setTitle("PERMESSI NEGATI")
                    .setMessage("Consenti l'accesso")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(ActivityRiepilogo2.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, storagePermissionCode);
                        }
                    }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, storagePermissionCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == storagePermissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permessi Garantiti", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permessi Negati", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String leggi(ArrayList a) {
        String b = "";
        for (int i = 0; i < a.size(); i++) {
            if (i <= 1) {
                b += a.get(i).toString() + "\n";
            } else {
                b += a.get(i).toString() + "\n";
            }
        }
        return b;
    }
    public int leggi_directory() {
        int prova = 0;
        File c = new File(Environment.getExternalStorageDirectory() + "/");
        String Arr[] = c.list();
        for (int i = 0; i < Arr.length; i++) {
            if (i == Arr.length - 1) {
                prova += i;
                return prova;
            }
        }
        return prova;
    }
    public void onBackPressed(){
        super.onBackPressed();
        for (int i=1;i<info.size();i++){
                info.remove(i);
        }
    }
}
    /*PdfDocument mypdfDocument = new PdfDocument();
                        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(720, 1280, 1).create();
                        PdfDocument.Page mypage = mypdfDocument.startPage(mypageInfo);
                        Paint mypaint = new Paint();
                        btm = BitmapFactory.decodeResource(getResources(), R.drawable.pdf);
                        scaledbmp = Bitmap.createScaledBitmap(btm, 720, 1280, false);
                        mypage.getCanvas().drawBitmap(scaledbmp, 0, 0, mypaint);
                        //String myString = "questa è la \n nuova stringa";
                        String myString = "" + leggi(info);
                        mypaint.setTextSize(30);
                        int x = 50, y = 200;
                        for (String line : myString.split("\n")) {
                            mypage.getCanvas().drawText(line, x, y, mypaint);
                            y += mypaint.descent() - mypaint.ascent();
                        }
                        mypdfDocument.finishPage(mypage);

                        numPdf++;
                        String nomeFile = "/SwordplayPDF(" + numPdf + ").pdf";
                        //String myFilePath= Environment.getExternalStorageDirectory().getPath()+"/swordplay.pdf";
                        String myFilePath = Environment.getExternalStorageDirectory().getPath() + nomeFile;
                        File myfile = new File(myFilePath);
                        try {
                            if (myfile.exists()) {
                                Toast.makeText(view.getContext(), myfile.getName() + "GIA PRESENTE", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(view.getContext(), myfile.getName() + "NOOOOOOOOO", Toast.LENGTH_LONG).show();
                                mypdfDocument.writeTo(new FileOutputStream(myfile));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            vediPDF.setText("ERROR");
                        }
                        mypdfDocument.close();
                        Toast.makeText(view.getContext(), "swordplay.Pdf salvato", Toast.LENGTH_LONG).show();

*/