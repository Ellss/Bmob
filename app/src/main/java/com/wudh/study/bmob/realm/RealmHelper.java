package com.wudh.study.bmob.realm;


import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by CoderLengary
 */


public class RealmHelper {
    public static final String DATABASE_NAME = "WanAndroid.realm";

    public static Realm newRealmInstance(){
        return Realm.getInstance(new RealmConfiguration.Builder()
                .name(DATABASE_NAME)
                .deleteRealmIfMigrationNeeded()
                .build());
    }
}
