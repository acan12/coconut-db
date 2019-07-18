# coconut DB
Database interface to handle everything related with db transaction base on Realms

## Installation guide :

**1. Add the JitPack repository to your build file**
```allprojects {
   		repositories {
   			...
   			maven { url 'https://jitpack.io' }
   		}
   	}
```

**2. Add the dependency**
```
dependencies {
        ...
		implementation 'com.github.acan12:coconut-db:1.0.1'
		...
        
	}
```

**3. Add initialize into Application**
```
@Override
public class App extends BaseApp {
    private static CocoDB db;
    
    public void onCreate() {
        super.onCreate();
        db = CocoDB.initDatabase(this);
        ...
    }
    
    public static CocoDB getCocoDb(){
        return db;
    }
    
}
    
```

**4. Don't forget close realm if activity destroy**
```aidl

@Override
    protected void onDestroy() {
        App.getCocoDb().closeRealm();
        super.onDestroy();
    }
```

## How to use :
```aidl
    // delete
    App.getCocoDb().deleteRealm(Box.class);
    // getcollection from box model
    RealmResults<Box> boxes = App.getCocoDb().getCollectionRealm(Box.class);
    // save model into Realm
    App.getCocoDb().saveToRealm(box);
    Toast.makeText(this, "prev: " + boxes.size(), Toast.LENGTH_SHORT).show();
    // delete by key value
    App.getCocoDb().deleteRealmBykey("id", 1, Box.class);

```