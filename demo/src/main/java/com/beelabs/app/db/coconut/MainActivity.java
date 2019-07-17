package com.beelabs.app.db.coconut;

import android.annotation.SuppressLint;
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
        box.setId(1);
        box.setName("dodoldl");
        box.setDesc("jawa");

        App.getCocoDb().deleteRealm(Box.class);
        App.getCocoDb().saveToRealm(box);
        int sizeBox = App.getCocoDb().getCollectionRealm(Box.class).size();

        App.getCocoDb().deleteRealmBykey("id", 1, Box.class);
        int sizeBox2 = App.getCocoDb().getCollectionRealm(Box.class).size();
        Toast.makeText(this, "prev: " + sizeBox, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "aft: " + sizeBox2, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        App.getCocoDb().closeRealm();
        super.onDestroy();
    }
}
