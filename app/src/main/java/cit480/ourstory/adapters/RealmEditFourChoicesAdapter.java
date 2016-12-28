package cit480.ourstory.adapters;


import android.content.Context;

import cit480.ourstory.models.FourChoices;
import cit480.ourstory.models.TwoChoices;
import io.realm.RealmResults;

public class RealmEditFourChoicesAdapter extends RealmModelAdapter<FourChoices> {

    public RealmEditFourChoicesAdapter(Context context, RealmResults<FourChoices> realmResults, boolean automaticUpdate) {


        super(context, realmResults, automaticUpdate);

    }
}
