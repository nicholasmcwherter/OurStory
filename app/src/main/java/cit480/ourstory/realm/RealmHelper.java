package cit480.ourstory.realm;


import java.lang.reflect.Array;
import java.util.ArrayList;

import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.FourChoices;
import cit480.ourstory.models.PECS;
import cit480.ourstory.models.ThreeChoices;
import cit480.ourstory.models.TwoChoices;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;

    }

    public ArrayList<String> pecsSpinner() {

        ArrayList<String> pecsNames = new ArrayList<>();
        RealmResults<PECS> pecsSpinnerNames = realm.where(PECS.class).findAll();

        for (PECS p: pecsSpinnerNames)
        {
            pecsNames.add(p.getPecsImage());
        }
        return pecsNames;


    }

    public ArrayList<String> pecsFilePath() {
        ArrayList<String> pecsFilePath = new ArrayList<>();
        RealmResults<PECS> pecsFilePathResults = realm.where(PECS.class).findAll();

        for (PECS p: pecsFilePathResults) {
            pecsFilePath.add(p.getPecsImagePath());
        }
        return pecsFilePath;
    }


    public ArrayList<String> firstthisTemplateName() {

        ArrayList<String> firstthisTemplateName = new ArrayList<>();
        RealmResults<Firstthis> firstthisRealmResults = realm.where(Firstthis.class).findAll();

        for (Firstthis ft: firstthisRealmResults) {

            firstthisTemplateName.add(ft.getFirstthisTemplateName());

        }
        return firstthisTemplateName;
    }

    public ArrayList<String> twoChoicesTemplateName() {

        ArrayList<String> twoChoicesTemplateName = new ArrayList<>();
        RealmResults<TwoChoices> twoChoicesRealmResults = realm.where(TwoChoices.class).findAll();


        for (TwoChoices tc: twoChoicesRealmResults) {

            twoChoicesTemplateName.add(tc.getTwoChoicesTemplateName());
        }
        return twoChoicesTemplateName;
    }

    public ArrayList<String> threeChoicesTemplateName() {

        ArrayList<String> threeChoicesTemplateName = new ArrayList<>();
        RealmResults<ThreeChoices> threeChoicesRealmResults = realm.where(ThreeChoices.class).findAll();

        for (ThreeChoices thc: threeChoicesRealmResults) {

            threeChoicesTemplateName.add(thc.getThreeChoicesTemplateName());
        }
        return threeChoicesTemplateName;
    }

    public ArrayList<String> fourChoicesTemplateName() {

        ArrayList<String> fourChoicesTemplateName = new ArrayList<>();
        RealmResults<FourChoices> fourChoicesRealmResults = realm.where(FourChoices.class).findAll();

        for (FourChoices fc: fourChoicesRealmResults) {

            fourChoicesTemplateName.add(fc.getFourChoicesTemplateName());
        }
        return fourChoicesTemplateName;
    }



}
