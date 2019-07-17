package com.beelabs.app.db.coconut;

import android.annotation.SuppressLint;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Toast;

import com.beelabs.app.db.coconut.model.Box;

import app.beelabs.com.codebase.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Box box = new Box();
        box.setId(Calendar.getInstance().getTimeInMillis());
        box.setName("dodoldl");
        box.setDesc("jawa");


        App.getCocoDb().saveToRealm(box);
        int sizeCollection = App.getCocoDb().getCollectionRealm(Box.class).size();
        int size = App.getCocoDb().findByContain("name", "dodo", Box.class).size();
        Toast.makeText(this, size + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        App.getCocoDb().closeRealm();
        super.onDestroy();
    }
}
