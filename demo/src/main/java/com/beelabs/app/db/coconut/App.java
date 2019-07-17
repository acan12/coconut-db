package com.beelabs.app.db.coconut;

import android.content.Context;

import com.beelabs.app.cocodb.CocoDB;

import app.beelabs.com.codebase.base.BaseApp;
import app.beelabs.com.codebase.di.component.AppComponent;
import app.beelabs.com.codebase.di.component.DaggerAppComponent;
import io.realm.Realm;

public class App extends BaseApp {
    private static Context context;
    private static CocoDB db;

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = CocoDB.initDatabase(this);
        setupBuilder(DaggerAppComponent.builder(), this);
    }

    public static AppComponent getAppComponent() {
        if (context == null) return null;
        return getComponent();
    }

    public static CocoDB getCocoDb(){
        return db;
    }
}
