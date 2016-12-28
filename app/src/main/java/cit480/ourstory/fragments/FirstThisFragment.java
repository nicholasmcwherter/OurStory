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
import cit480.ourstory.adapters.EditFirstthisAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;


public class FirstThisFragment extends Fragment {


    private PECSAdapter adapter;
    Spinner nameFirstthisTemplate;
    private Realm realm;
    private ListView listViewFirstthis;
    private EditFirstthisAdapter adapterFirstthis;
    private PECSAdapter adapterPECS;
    private RecyclerView recycler, recyclerPECS;
    ImageView firstthisImage1, firstthisImage2;
    TextView firstthisImageName1, firstthisImageName2, thenTextView;
    ArrayList<String> firstthisTemplateName;
    ArrayAdapter spinnerAdapter;
    String templateName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.firstthis_layout, container, false);

        setHasOptionsMenu(true);
        realm = Realm.getDefaultInstance();
//        realm = RealmController.getInstance().getRealm();

        // Refresh after upload done.
      //  setupRecyclerPECS();

        RealmHelper helper = new RealmHelper(realm);
        firstthisTemplateName = helper.firstthisTemplateName();






        nameFirstthisTemplate = (Spinner) rootView.findViewById(R.id.nameFirstthisTemplate);
        firstthisImage1 = (ImageView) rootView.findViewById(R.id.firstthisImage1);
        firstthisImage2 = (ImageView) rootView.findViewById(R.id.firstthisImage2);
        firstthisImageName1 = (TextView) rootView.findViewById(R.id.firstthisImage1Name);
        firstthisImageName2 = (TextView) rootView.findViewById(R.id.firstthisImage2Name);
        thenTextView = (TextView) rootView.findViewById(R.id.thenTextView);

        // recycler = (RecyclerView) rootView.findViewById(R.id.recycler);





        //  this.realm = RealmController.with(this).getRealm();
        // RealmController.with(this).refresh();
        // setRealmAdapter(RealmController.with(this).getFirstthis());


        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, firstthisTemplateName);
        nameFirstthisTemplate.setAdapter(spinnerAdapter);






        nameFirstthisTemplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                final RealmResults<Firstthis> results = realm.where(Firstthis.class).findAll();
                templateName = firstthisTemplateName.get(position);
                //  twoChoicesImage1.setImageBitmap(BitmapFactory.decodeFile(results.get(position).getImagePath1()));
                //  twoChoicesImage2.setImageBitmap(BitmapFactory.decodeFile(results.get(position).getImagePath2()));
                firstthisImageName1.setText(results.get(position).getImageName1());
                firstthisImageName2.setText(results.get(position).getImageName2());

                // twoChoicesImage1 = results.get(p)
                // twoChoicesImage2.

                Picasso.with(getContext())
                        .load(results.get(position).getImagePath1())
                        .resize(180, 180)
                        .into(firstthisImage1);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath2())
                        .resize(180, 180)
                        .into(firstthisImage2);






            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.twoChoicesItem).setVisible(false);
        menu.findItem(R.id.threeChoicesItem).setVisible(false);
        menu.findItem(R.id.fourChoicesItem).setVisible(false);
        menu.findItem(R.id.editTwoChoicesItem).setVisible(false);
        menu.findItem(R.id.editThreeChoicesItem).setVisible(false);
        menu.findItem(R.id.editFourChoicesItem).setVisible(false);
        super.onPrepareOptionsMenu(menu);
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
