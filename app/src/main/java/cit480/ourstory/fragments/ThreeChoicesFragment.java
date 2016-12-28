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
import cit480.ourstory.adapters.EditThreeChoicesAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.ThreeChoices;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;


public class ThreeChoicesFragment extends Fragment {


    private PECSAdapter adapter;
    Spinner nameThreeChoicesTemplate;
    private Realm realm;
    private ListView listViewThreeChoices;
    private EditThreeChoicesAdapter adapterThreeChoices;
    private PECSAdapter adapterPECS;
    private RecyclerView recycler, recyclerPECS;
    ImageView threeChoicesImage1, threeChoicesImage2, threeChoicesImage3;
    TextView threeChoicesImageName1, threeChoicesImageName2, threeChoicesImageName3;
    ArrayList<String> threeChoicesTemplateName;
    ArrayAdapter spinnerAdapter;
    String templateName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.threechoices_layout, container, false);

        setHasOptionsMenu(true);

        realm = Realm.getDefaultInstance();
//
//        realm = RealmController.getInstance().getRealm();


        nameThreeChoicesTemplate = (Spinner) rootView.findViewById(R.id.nameThreeChoicesTemplate);
        threeChoicesImage1 = (ImageView) rootView.findViewById(R.id.threeChoicesImage1);
        threeChoicesImage2 = (ImageView) rootView.findViewById(R.id.threeChoicesImage2);
        threeChoicesImage3 = (ImageView) rootView.findViewById(R.id.threeChoicesImage3);
        threeChoicesImageName1 = (TextView) rootView.findViewById(R.id.threeChoicesImage1Name);
        threeChoicesImageName2 = (TextView) rootView.findViewById(R.id.threeChoicesImage2Name);
        threeChoicesImageName3 = (TextView) rootView.findViewById(R.id.threeChoicesImage3Name);



        RealmHelper helper = new RealmHelper(realm);
        threeChoicesTemplateName = helper.threeChoicesTemplateName();

        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, threeChoicesTemplateName);
        nameThreeChoicesTemplate.setAdapter(spinnerAdapter);



        nameThreeChoicesTemplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                final RealmResults<ThreeChoices> results = realm.where(ThreeChoices.class).findAll();
                templateName = threeChoicesTemplateName.get(position);
                threeChoicesImageName1.setText(results.get(position).getImageName1());
                threeChoicesImageName2.setText(results.get(position).getImageName2());

                Picasso.with(getContext())
                        .load(results.get(position).getImagePath1())
                        .resize(180, 180)
                        .into(threeChoicesImage1);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath2())
                        .resize(180, 180)
                        .into(threeChoicesImage2);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath3())
                        .resize(180, 180)
                        .into(threeChoicesImage3);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
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


