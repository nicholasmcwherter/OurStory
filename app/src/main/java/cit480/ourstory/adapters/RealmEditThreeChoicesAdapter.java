package cit480.ourstory.adapters;


import android.content.Context;

import cit480.ourstory.models.ThreeChoices;
import cit480.ourstory.models.TwoChoices;
import io.realm.RealmResults;

public class RealmEditThreeChoicesAdapter extends RealmModelAdapter<ThreeChoices> {

    public RealmEditThreeChoicesAdapter(Context context, RealmResults<ThreeChoices> realmResults, boolean automaticUpdate) {


        super(context, realmResults, automaticUpdate);

    }
}
