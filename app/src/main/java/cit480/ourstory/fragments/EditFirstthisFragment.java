package cit480.ourstory.fragments;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

import cit480.ourstory.R;
import cit480.ourstory.adapters.EditFirstthisAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmEditFirstthisAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;


public class EditFirstthisFragment extends Fragment {

    private FloatingActionButton floatingActionButtonFirstthis;
    private Realm realm;
    private ListView listViewFirstthis;
    private static int id = 1;
    private EditFirstthisAdapter adapterFirstthis;
    private PECSAdapter adapterPECS;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recycler, recyclerPECS;
    EditText firstthisTemplateNameEditText;
    Spinner nameFirstthisImage1, nameFirstthisImage2;
    private static final int READ_REQUEST_CODE = 42;
    Button saveFirstthisBtn, uploadFirstthisBtn1, uploadFirstthisBtn2;
    ImageView firstthisImageView1, firstthisImageView2, pecsImageView;
    String imagePath1, imagePath2, name1, name2;
    Uri imageUri1, imageUri2 = null;
    ArrayList<String> pecsNames, pecsFilePath;
    ArrayAdapter spinnerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_firstthis_layout, container, false);

        setHasOptionsMenu(true);


        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButtonFirstthis);
        recycler = (RecyclerView) rootView.findViewById(R.id.recycler);

        // Refresh after upload done.
        setupRecycler();


        this.realm = RealmController.with(this).getRealm();
        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getFirstthis());


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Create First This Template");
                dialog.setContentView(R.layout.input_firstthis_template_dialog_layout);

                RealmHelper helper = new RealmHelper(realm);
                pecsNames = helper.pecsSpinner();
                pecsFilePath = helper.pecsFilePath();

                final RealmResults<PECS> results = realm.where(PECS.class).findAll();

                final PECS pecs = new PECS();


                // Get Name of Image and display with Toast

                //String imageName = p.getPecsImage();
                //String imagePath = p.getPecsImagePath();




                // Edit Texts
                firstthisTemplateNameEditText = (EditText) dialog.findViewById(R.id.firstthisTemplateNameEditText);
                nameFirstthisImage1 = (Spinner) dialog.findViewById(R.id.nameFirstthisImage1);
                nameFirstthisImage2 = (Spinner) dialog.findViewById(R.id.nameFirstthisImage2);
                //firstthisImageView1 = (ImageView) dialog.findViewById(R.id.firstthisImageView1);
                //firstthisImageView2 = (ImageView) dialog.findViewById(R.id.firstthisImageView2);
                //uploadFirstthisBtn1 = (Button) dialog.findViewById(R.id.uploadFirstthisBtn1);
                //uploadFirstthisBtn2 = (Button) dialog.findViewById(R.id.uploadFirstthisBtn2);
                saveFirstthisBtn = (Button) dialog.findViewById(R.id.saveFirstthisBtn);

                spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, pecsNames);
                nameFirstthisImage1.setAdapter(spinnerAdapter);
                nameFirstthisImage2.setAdapter(spinnerAdapter);

                nameFirstthisImage1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                        name1 = pecsNames.get(position);
                        imagePath1 = pecsFilePath.get(position);


                     //   String stringPECS = pecs.getPecsImagePath();
                        //firstthisImageView1.setImageURI(Uri.parse(stringPECS));

                     //   Integer.toString(positionToast);

                   //     Toast.makeText(getActivity(), positionToast, Toast.LENGTH_SHORT).show();


                       // firstthisImageView1 = results.=
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                nameFirstthisImage2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        name2 = pecsNames.get(position);
                        imagePath2 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                /**

                // Upload Button
                firstthisImageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dialog dialogImage1 = new Dialog(getActivity());
                        dialogImage1.setTitle("Pick First Image!");
                        dialogImage1.setContentView(R.layout.select_pec_editfirstthis_dialog_fragment);

                        //realm.beginTransaction();
                      //  RealmResults<PECS> results = realm.where(PECS.class).findAll();

                        //results.get(position).setPecsImagePath(firstthisImageView1);
                        //results.get(position).setPecsImage(nameFirstthisImage1);

                        recyclerPECS = (RecyclerView) dialogImage1.findViewById(R.id.recyclerPECS);

                        // Refresh after upload done.
                        setupRecyclerPECS();
                        realmUpdatePECS();
                        dialogImage1.show();

                    }

                });


               // firstthisImageView1.setImageURI();

                firstthisImageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialogImage2 = new Dialog(getActivity());
                        dialogImage2.setTitle("Pick Second Image!");
                        dialogImage2.setContentView(R.layout.select_pec_editfirstthis_dialog_fragment);


                        recyclerPECS = (RecyclerView) dialogImage2.findViewById(R.id.recyclerPECS);

                        // Refresh after upload done.
                        setupRecyclerPECS();
                        realmUpdatePECS();

                        dialogImage2.show();

                    }
                });

                 **/

                // Save Button
                saveFirstthisBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Get Data
                        //name1 = nameFirstthisImage1.
                       // name2 = nameFirstthisImage2.toString();
                        //imagePath1 = nameFirstthisImage1;
                        //imagePath2 = pecsFilePath.toString();
                        //String firstthisName = firstthisTemplateNameEditText.getText().toString();
                        //twoChoicesTemplateName = firstthisTemplateNameEditText.toString();


                        // Verify
                        if (name1 != null && name1.length() > 0) {
                            // Set Data
                            Firstthis firstthis = new Firstthis();
                            firstthis.setFirstthisTemplateName(firstthisTemplateNameEditText.getText().toString());
                            firstthis.setImageName1(name1);
                            firstthis.setImageName2(name2);
                            firstthis.setImagePath1(imagePath1);
                            firstthis.setImagePath2(imagePath2);
                           // Log.i(TAG, "pecsImagePath: " + pecsImagePath.toString());

                            realm.beginTransaction();
                            realm.copyToRealm(firstthis);
                            realm.commitTransaction();

                            adapterFirstthis.notifyDataSetChanged();

                            dialog.dismiss();

                        } else {
                            Toast.makeText(getActivity(), "Name must contain at least 1 character", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
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


    public void setRealmAdapter(RealmResults<Firstthis> firstthis) {

        RealmEditFirstthisAdapter realmAdapter = new RealmEditFirstthisAdapter(this.getActivity().getApplicationContext(), firstthis, true);
        adapterFirstthis.setRealmAdapter(realmAdapter);
        adapterFirstthis.notifyDataSetChanged();

    }


    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapterFirstthis = new EditFirstthisAdapter(getActivity());
        recycler.setAdapter(adapterFirstthis);

    }


    public void setRealmAdapterPECS(RealmResults<PECS> pecs) {

        RealmPECSAdapter realmAdapterPECS = new RealmPECSAdapter(this.getActivity().getApplicationContext(), pecs, true);
        adapterPECS.setRealmAdapter(realmAdapterPECS);
        adapterPECS.notifyDataSetChanged();

    }


    private void setupRecyclerPECS() {
        recyclerPECS.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerPECS.setLayoutManager(layoutManager);

        adapterPECS = new PECSAdapter(getActivity());
        recyclerPECS.setAdapter(adapterPECS);

    }

    private void realmUpdatePECS() {
        setupRecyclerPECS();

        this.realm = RealmController.with(this).getRealm();
        RealmController.with(this).refresh();
        setRealmAdapterPECS(RealmController.with(this).getPECS());

    }



    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onStop() {
        super.onStop();
    }


}
