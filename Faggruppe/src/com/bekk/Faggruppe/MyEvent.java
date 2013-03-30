package com.bekk.Faggruppe;

import android.view.View;

class MyEvent {
    private final View view;

    public MyEvent(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}