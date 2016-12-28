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
import cit480.ourstory.adapters.EditThreeChoicesAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmEditFirstthisAdapter;
import cit480.ourstory.adapters.RealmEditThreeChoicesAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.PECS;
import cit480.ourstory.models.ThreeChoices;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class EditThreeChoicesFragment extends Fragment {

    private Realm realm;
    private ListView listViewThreeChoices;
    private static int id = 1;
    private EditThreeChoicesAdapter adapterThreeChoices;
    private PECSAdapter adapterPECS;
    private FloatingActionButton floatingActionButtonThreeChoices;
    private RecyclerView recycler, recyclerPECS;
    EditText threeChoicesTemplateNameEditText;
    Spinner nameThreeChoicesImage1, nameThreeChoicesImage2, nameThreeChoicesImage3;
    private static final int READ_REQUEST_CODE = 42;
    Button saveThreeChoicesBtn, uploadFirstthisBtn1, uploadFirstthisBtn2;
    ImageView threeChoicesImageView1, threeChoicesImageView2, pecsImageView;
    String imagePath1, imagePath2, imagePath3, name1, name2, name3;
    Uri imageUri1, imageUri2 = null;
    ArrayList<String> pecsNames, pecsFilePath;
    ArrayAdapter spinnerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_threechoices_layout, container, false);

        setHasOptionsMenu(true);

        floatingActionButtonThreeChoices = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButtonThreeChoices);
        recycler = (RecyclerView) rootView.findViewById(R.id.recycler);

        // Refresh after upload done.
        setupRecycler();


        this.realm = RealmController.with(this).getRealm();
        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getThreeChoices());


        floatingActionButtonThreeChoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Create Three Choices Template");
                dialog.setContentView(R.layout.input_threechoices_template_dialog_layout);

                RealmHelper helper = new RealmHelper(realm);
                pecsNames = helper.pecsSpinner();
                pecsFilePath = helper.pecsFilePath();


                // Edit Texts
                threeChoicesTemplateNameEditText = (EditText) dialog.findViewById(R.id.threeChoicesTemplateNameEditText);
                nameThreeChoicesImage1 = (Spinner) dialog.findViewById(R.id.nameThreeChoicesImage1);
                nameThreeChoicesImage2 = (Spinner) dialog.findViewById(R.id.nameThreeChoicesImage2);
                nameThreeChoicesImage3 = (Spinner) dialog.findViewById(R.id.nameThreeChoicesImage3);
                saveThreeChoicesBtn = (Button) dialog.findViewById(R.id.saveThreeChoicesBtn);

                spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, pecsNames);
                nameThreeChoicesImage1.setAdapter(spinnerAdapter);
                nameThreeChoicesImage2.setAdapter(spinnerAdapter);
                nameThreeChoicesImage3.setAdapter(spinnerAdapter);

                nameThreeChoicesImage1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                        name1 = pecsNames.get(position);
                        imagePath1 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                nameThreeChoicesImage2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        name2 = pecsNames.get(position);
                        imagePath2 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                nameThreeChoicesImage3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        name3 = pecsNames.get(position);
                        imagePath3 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                // Save Button
                saveThreeChoicesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        // Verify
                        if (name1 != null && name1.length() > 0) {
                            // Set Data
                            ThreeChoices threeChoices = new ThreeChoices();
                            threeChoices.setThreeChoicesTemplateName(threeChoicesTemplateNameEditText.getText().toString());
                            threeChoices.setImageName1(name1);
                            threeChoices.setImageName2(name2);
                            threeChoices.setImageName3(name3);
                            threeChoices.setImagePath1(imagePath1);
                            threeChoices.setImagePath2(imagePath2);
                            threeChoices.setImagePath3(imagePath3);
                            // Log.i(TAG, "pecsImagePath: " + pecsImagePath.toString());

                            realm.beginTransaction();
                            realm.copyToRealm(threeChoices);
                            realm.commitTransaction();

                            adapterThreeChoices.notifyDataSetChanged();

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
        menu.findItem(R.id.editTwoChoicesItem).setVisible(true);
        menu.findItem(R.id.editThreeChoicesItem).setVisible(true);
        menu.findItem(R.id.editFourChoicesItem).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    public void setRealmAdapter(RealmResults<ThreeChoices> threeChoices) {

        RealmEditThreeChoicesAdapter realmAdapter = new RealmEditThreeChoicesAdapter(this.getActivity().getApplicationContext(), threeChoices, true);
        adapterThreeChoices.setRealmAdapter(realmAdapter);
        adapterThreeChoices.notifyDataSetChanged();

    }


    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapterThreeChoices = new EditThreeChoicesAdapter(getActivity());
        recycler.setAdapter(adapterThreeChoices);

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


