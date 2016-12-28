package cit480.ourstory.adapters;


import android.content.Context;

import cit480.ourstory.models.PECS;
import cit480.ourstory.models.TwoChoices;
import io.realm.RealmResults;

public class RealmEditTwoChoicesAdapter extends RealmModelAdapter<TwoChoices> {

    public RealmEditTwoChoicesAdapter(Context context, RealmResults<TwoChoices> realmResults, boolean automaticUpdate) {


        super(context, realmResults, automaticUpdate);

    }
}
