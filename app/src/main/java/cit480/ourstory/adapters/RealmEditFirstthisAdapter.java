package cit480.ourstory.adapters;


import android.content.Context;

import cit480.ourstory.models.Firstthis;
import io.realm.RealmResults;

public class RealmEditFirstthisAdapter extends RealmModelAdapter<Firstthis> {

    public RealmEditFirstthisAdapter(Context context, RealmResults<Firstthis> realmResults, boolean automaticUpdate) {


        super(context, realmResults, automaticUpdate);

    }
}
