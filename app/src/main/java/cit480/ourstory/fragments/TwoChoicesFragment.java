package cit480.ourstory.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import cit480.ourstory.R;
import cit480.ourstory.adapters.EditTwoChoicesAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.TwoChoices;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;


public class TwoChoicesFragment extends Fragment {


    private PECSAdapter adapter;
    Spinner nameTwoChoicesTemplate;
    private Realm realm;
    private ListView listViewTwoChoices;
    private EditTwoChoicesAdapter adapterTwoChoices;
    private PECSAdapter adapterPECS;
    private RecyclerView recycler, recyclerPECS;
    ImageView twoChoicesImage1, twoChoicesImage2;
    TextView twoChoicesImageName1, twoChoicesImageName2;
    ArrayList<String> twoChoicesTemplateName;
    ArrayAdapter spinnerAdapter;
    String templateName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.twochoices_layout, container, false);



        setHasOptionsMenu(true);

 //       realm = Realm.getDefaultInstance();

        realm = Realm.getDefaultInstance();

       // realm = RealmController.getInstance().getRealm();


        nameTwoChoicesTemplate = (Spinner) rootView.findViewById(R.id.nameTwoChoicesTemplate);
        twoChoicesImage1 = (ImageView) rootView.findViewById(R.id.twoChoicesImage1);
        twoChoicesImage2 = (ImageView) rootView.findViewById(R.id.twoChoicesImage2);
        twoChoicesImageName1 = (TextView) rootView.findViewById(R.id.twoChoicesImage1Name);
        twoChoicesImageName2 = (TextView) rootView.findViewById(R.id.twoChoicesImage2Name);


        RealmHelper helper = new RealmHelper(realm);
        twoChoicesTemplateName = helper.twoChoicesTemplateName();

        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, twoChoicesTemplateName);
        nameTwoChoicesTemplate.setAdapter(spinnerAdapter);





        nameTwoChoicesTemplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                final RealmResults<TwoChoices> results = realm.where(TwoChoices.class).findAll();
                templateName = twoChoicesTemplateName.get(position);
                twoChoicesImageName1.setText(results.get(position).getImageName1());
                twoChoicesImageName2.setText(results.get(position).getImageName2());

                Picasso.with(getContext())
                        .load(results.get(position).getImagePath1())
                        .resize(180, 180)
                        .into(twoChoicesImage1);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath2())
                        .resize(180, 180)
                        .into(twoChoicesImage2);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
    }

    public void setRealmAdapterPECS(RealmResults<PECS> pecs) {

        RealmPECSAdapter realmAdapter = new RealmPECSAdapter(this.getActivity().getApplicationContext(), pecs, true);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();

    }


    private void setupRecyclerPECS() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapter = new PECSAdapter(getActivity());
        recycler.setAdapter(adapter);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.twoChoicesItem).setVisible(true);
        menu.findItem(R.id.threeChoicesItem).setVisible(true);
        menu.findItem(R.id.fourChoicesItem).setVisible(true);
        menu.findItem(R.id.editTwoChoicesItem).setVisible(false);
        menu.findItem(R.id.editThreeChoicesItem).setVisible(false);
        menu.findItem(R.id.editFourChoicesItem).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

}

