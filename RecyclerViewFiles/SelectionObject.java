package com.haris.budget4u.presentationutil.ui.objectutil;

import android.view.View;


public class SelectionObject {
    private int position;
    private String action;
    private View view;


    public int getPosition() {
        return position;
    }

    public SelectionObject setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getAction() {
        return action;
    }

    public SelectionObject setAction(String action) {
        this.action = action;
        return this;
    }

    public View getView() {
        return view;
    }

    public SelectionObject setView(View view) {
        this.view = view;
        return this;
    }


}
