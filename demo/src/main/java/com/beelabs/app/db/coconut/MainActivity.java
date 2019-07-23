package com.beelabs.app.db.coconut;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.beelabs.app.cocodb.CocoDB;
import com.beelabs.app.db.coconut.model.Box;

import app.beelabs.com.codebase.base.BaseActivity;
import io.realm.RealmObject;
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


        Box box3 = (Box) App.getCocoDb().updateRealm(new CocoDB.TransactionCallback(){
            @Override
            public RealmObject call() {
                RealmResults<Box> boxes2 = App.getCocoDb().getCollectionRealm(Box.class);
                Box box2 = boxes2.get(0);
                box2.setName("John");
                box2.setDesc("Lennon");

                return box2;
            }
        });

        Toast.makeText(this, "aft: " + box3.getDesc(), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "deleted 1: " + boxes.size(), Toast.LENGTH_SHORT).show();
        // delete by key value
        App.getCocoDb().deleteRealmBykey("id", 1, Box.class);
        Toast.makeText(this, "deleted 2: " + boxes.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        App.getCocoDb().closeRealm();
        super.onDestroy();
    }
}
