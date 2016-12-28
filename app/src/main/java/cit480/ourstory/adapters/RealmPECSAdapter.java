package cit480.ourstory.adapters;


import android.content.Context;

import cit480.ourstory.models.PECS;
import io.realm.RealmResults;

public class RealmPECSAdapter extends RealmModelAdapter<PECS> {

    public RealmPECSAdapter(Context context, RealmResults<PECS> realmResults, boolean automaticUpdate) {


        super(context, realmResults, automaticUpdate);

    }
}
