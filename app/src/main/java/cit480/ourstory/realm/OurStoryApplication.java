package cit480.ourstory.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class OurStoryApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Configure Realm for the app
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("ourstory.realm")
                .schemaVersion(1)
                // remove before next version of app is up
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);



    }
}