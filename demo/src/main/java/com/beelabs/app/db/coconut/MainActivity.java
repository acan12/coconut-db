package com.beelabs.app.db.coconut;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.beelabs.app.db.coconut.model.Box;

import app.beelabs.com.codebase.base.BaseActivity;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Box box = new Box();
        box.setId(1);
        box.setName("John");
        box.setDesc("Travolta");

        // delete
        App.getCocoDb().deleteRealm(Box.class);
        // getcollection from box model
        RealmResults<Box> boxes = App.getCocoDb().getCollectionRealm(Box.class);
        // save model into Realm
        App.getCocoDb().saveToRealm(box);
        Toast.makeText(this, "prev: " + boxes.get(0).getDesc(), Toast.LENGTH_SHORT).show();

        Box box2 = new Box();
        box2.setId(boxes.get(0).getId());
        box2.setName("John");
        box2.setDesc("Lennon");
        App.getCocoDb().saveToRealm(box2);
        RealmResults<Box> boxes2 = App.getCocoDb().getCollectionRealm(Box.class);
        Toast.makeText(this, "aft: " + boxes2.get(0).getDesc(), Toast.LENGTH_SHORT).show();

        // delete by key value
        App.getCocoDb().deleteRealmBykey("id", 1, Box.class);
        Toast.makeText(this, "deleted: " + boxes.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        App.getCocoDb().closeRealm();
        super.onDestroy();
    }
}
