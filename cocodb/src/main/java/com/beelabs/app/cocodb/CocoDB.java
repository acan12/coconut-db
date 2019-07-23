package com.beelabs.app.cocodb;

import android.content.Context;
import android.util.Log;

import app.beelabs.com.utilc.CryptoUtil;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

// database
public class CocoDB {

    // --- realm db config ---
    private static Realm realm;
    private static CocoDB db;

    public CocoDB(Realm realm) {
        this.realm = realm;
    }

    public Realm getRealm() {
        return realm;
    }

    public static CocoDB initDatabase(String databasePackageName, Context context) {
        if (realm == null) db = buildRealm(databasePackageName, context);
        return db;
    }

    private static CocoDB buildRealm(String databasePackageName, Context context) {
        if (context == null) return null;
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(databasePackageName)
                .encryptionKey(CryptoUtil.Companion.encryptedKey64())
                .build();
        // Get a Realm instance for this thread
        realm = Realm.getInstance(realmConfig);

        return new CocoDB(realm);
    }


    public RealmObject saveToRealm(RealmObject object) {
        return saveToRealm(object, null);
    }

    public RealmObject saveToRealm(RealmObject object, TransactionCallback callback) {
        realm.beginTransaction();
        RealmObject obj = realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
        return obj;
    }

    public RealmObject updateRealm(TransactionCallback callback) {
        realm.beginTransaction();

        RealmObject modelUpdated = callback.call();

        realm.copyToRealmOrUpdate(modelUpdated);
        realm.commitTransaction();

        return modelUpdated;
    }

    public RealmResults getCollectionRealm(Class clazz) {
        RealmResults objects = realm.where(clazz).findAll();
        return objects;
    }

    public RealmResults getCollectionByKeyRealm(String key, int value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .equalTo(key, value)
                .endGroup()
                .findAll();
        return items;
    }

    public RealmResults getCollectionByKeyRealm(String key, long value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .equalTo(key, value)
                .endGroup()
                .findAll();
        return items;
    }

    public RealmResults getCollectionByKeyRealm(String key, boolean value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .equalTo(key, value)
                .endGroup()
                .findAll();
        return items;
    }

    public RealmResults getCollectionByKeyRealm(String key, String value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .equalTo(key, value)
                .endGroup()
                .findAll();
        return items;
    }

    public RealmResults findByContain(String key, String value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .like(key, value + "*")
                .endGroup()
                .findAll();
        return items;
    }

    public void closeRealm() {
        if (realm != null)
            realm.close();
    }

    public void deleteRealm(Class clazz) {
        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void deleteRealmBykey(String key, int val, Class clazz) {
        try {
            realm.beginTransaction();
            RealmResults results = realm.where(clazz).equalTo(key, val).findAll();
            results.deleteAllFromRealm();
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public static abstract class TransactionCallback {

        public RealmObject call() {
            return null;
        }
    }
}

