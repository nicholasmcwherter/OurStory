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
import cit480.ourstory.adapters.EditFourChoicesAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.FourChoices;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmResults;


public class FourChoicesFragment extends Fragment {


    private PECSAdapter adapter;
    Spinner nameFourChoicesTemplate;
    private Realm realm;
    private ListView listViewFourChoices;
    private EditFourChoicesAdapter adapterFourChoices;
    private PECSAdapter adapterPECS;
    private RecyclerView recycler, recyclerPECS;
    ImageView fourChoicesImage1, fourChoicesImage2, fourChoicesImage3, fourChoicesImage4;
    TextView fourChoicesImageName1, fourChoicesImageName2, fourChoicesImageName3, fourChoicesImageName4;
    ArrayList<String> fourChoicesTemplateName;
    ArrayAdapter spinnerAdapter;
    String templateName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fourchoices_layout, container, false);

        setHasOptionsMenu(true);

        realm = Realm.getDefaultInstance();

        //realm = RealmController.getInstance().getRealm();


        nameFourChoicesTemplate = (Spinner) rootView.findViewById(R.id.nameFourChoicesTemplate);
        fourChoicesImage1 = (ImageView) rootView.findViewById(R.id.fourChoicesImage1);
        fourChoicesImage2 = (ImageView) rootView.findViewById(R.id.fourChoicesImage2);
        fourChoicesImage3 = (ImageView) rootView.findViewById(R.id.fourChoicesImage3);
        fourChoicesImage4 = (ImageView) rootView.findViewById(R.id.fourChoicesImage4);
        fourChoicesImageName1 = (TextView) rootView.findViewById(R.id.fourChoicesImage1Name);
        fourChoicesImageName2 = (TextView) rootView.findViewById(R.id.fourChoicesImage2Name);
        fourChoicesImageName3 = (TextView) rootView.findViewById(R.id.fourChoicesImage3Name);
        fourChoicesImageName4 = (TextView) rootView.findViewById(R.id.fourChoicesImage4Name);



        RealmHelper helper = new RealmHelper(realm);
        fourChoicesTemplateName = helper.fourChoicesTemplateName();

        spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, fourChoicesTemplateName);
        nameFourChoicesTemplate.setAdapter(spinnerAdapter);



        nameFourChoicesTemplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                final RealmResults<FourChoices> results = realm.where(FourChoices.class).findAll();
                templateName = fourChoicesTemplateName.get(position);
                fourChoicesImageName1.setText(results.get(position).getImageName1());
                fourChoicesImageName2.setText(results.get(position).getImageName2());

                Picasso.with(getContext())
                        .load(results.get(position).getImagePath1())
                        .resize(180, 180)
                        .into(fourChoicesImage1);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath2())
                        .resize(180, 180)
                        .into(fourChoicesImage2);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath3())
                        .resize(180, 180)
                        .into(fourChoicesImage3);
                Picasso.with(getContext())
                        .load(results.get(position).getImagePath3())
                        .resize(180, 180)
                        .into(fourChoicesImage4);


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



