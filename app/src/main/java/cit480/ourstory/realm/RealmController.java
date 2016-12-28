package cit480.ourstory.realm;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.FourChoices;
import cit480.ourstory.models.PECS;
import cit480.ourstory.models.ThreeChoices;
import cit480.ourstory.models.TwoChoices;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {

        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    // Refresh the realm instance
    public void refresh() {

        realm.refresh();
    }

    // Clear all objects from PECS.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(PECS.class);
        realm.commitTransaction();
    }

    // Find all Objects in the PECS.class
    public RealmResults<PECS> getPECS() {
        return realm.where(PECS.class).findAll();
    }

    // Find all Objects in the Firstthis.class
    public RealmResults<Firstthis> getFirstthis() {
        return realm.where(Firstthis.class).findAll();
    }

    // Find all Objects in the TwoChoices.class
    public RealmResults<TwoChoices> getTwoChoices() {
        return realm.where(TwoChoices.class).findAll();
    }

    // Find all Objects in the ThreeChoices.class
    public RealmResults<ThreeChoices> getThreeChoices() {
        return realm.where(ThreeChoices.class).findAll();
    }

    // Find all Objects in the TwoChoices.class
    public RealmResults<FourChoices> getFourChoices() {
        return realm.where(FourChoices.class).findAll();
    }







}
