package com.example.swordplay;

import java.util.ArrayList;

public class NameModel {
    private int mImageResorce;
    private String mAssalto;
    private String mIncontro;

    public NameModel(int imageResorce, String textAssalto, String textIncontro) {
        mImageResorce = imageResorce;
        mAssalto = textAssalto;
        mIncontro = textIncontro;
    }

    public String getAssalto() {
        return mAssalto;
    }

    public String getmIncontro() {
        return mIncontro;
    }

    public int getmImage() {
        return mImageResorce;
    }

    public void changeText(String text) {
        mIncontro = text;
    }
}
