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
import cit480.ourstory.adapters.EditFourChoicesAdapter;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmEditFirstthisAdapter;
import cit480.ourstory.adapters.RealmEditFourChoicesAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.PECS;
import cit480.ourstory.models.FourChoices;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.realm.RealmHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class EditFourChoicesFragment extends Fragment {

    private Realm realm;
    private ListView listViewFourChoices;
    private static int id = 1;
    private EditFourChoicesAdapter adapterFourChoices;
    private PECSAdapter adapterPECS;
    private FloatingActionButton floatingActionButtonFourChoices;
    private RecyclerView recycler, recyclerPECS;
    EditText fourChoicesTemplateNameEditText;
    Spinner nameFourChoicesImage1, nameFourChoicesImage2, nameFourChoicesImage3, nameFourChoicesImage4;
    private static final int READ_REQUEST_CODE = 42;
    Button saveFourChoicesBtn, uploadFirstthisBtn1, uploadFirstthisBtn2;
    ImageView fourChoicesImageView1, fourChoicesImageView2, pecsImageView;
    String imagePath1, imagePath2, imagePath3, imagePath4, name1, name2, name3, name4;
    Uri imageUri1, imageUri2 = null;
    ArrayList<String> pecsNames, pecsFilePath;
    ArrayAdapter spinnerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_fourchoices_layout, container, false);

        setHasOptionsMenu(true);


        floatingActionButtonFourChoices = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButtonFourChoices);
        recycler = (RecyclerView) rootView.findViewById(R.id.recycler);

        // Refresh after upload done.
        setupRecycler();


        this.realm = RealmController.with(this).getRealm();
        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getFourChoices());


        floatingActionButtonFourChoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Create Four Choices Template");
                dialog.setContentView(R.layout.input_fourchoices_template_dialog_layout);

                RealmHelper helper = new RealmHelper(realm);
                pecsNames = helper.pecsSpinner();
                pecsFilePath = helper.pecsFilePath();


                // Edit Texts
                fourChoicesTemplateNameEditText = (EditText) dialog.findViewById(R.id.fourChoicesTemplateNameEditText);
                nameFourChoicesImage1 = (Spinner) dialog.findViewById(R.id.nameFourChoicesImage1);
                nameFourChoicesImage2 = (Spinner) dialog.findViewById(R.id.nameFourChoicesImage2);
                nameFourChoicesImage3 = (Spinner) dialog.findViewById(R.id.nameFourChoicesImage3);
                nameFourChoicesImage4 = (Spinner) dialog.findViewById(R.id.nameFourChoicesImage4);

                saveFourChoicesBtn = (Button) dialog.findViewById(R.id.saveFourChoicesBtn);

                spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, pecsNames);
                nameFourChoicesImage1.setAdapter(spinnerAdapter);
                nameFourChoicesImage2.setAdapter(spinnerAdapter);
                nameFourChoicesImage3.setAdapter(spinnerAdapter);
                nameFourChoicesImage4.setAdapter(spinnerAdapter);


                nameFourChoicesImage1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                        name1 = pecsNames.get(position);
                        imagePath1 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                nameFourChoicesImage2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        name2 = pecsNames.get(position);
                        imagePath2 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                nameFourChoicesImage3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        name3 = pecsNames.get(position);
                        imagePath3 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                nameFourChoicesImage4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        name4 = pecsNames.get(position);
                        imagePath4 = pecsFilePath.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                // Save Button
                saveFourChoicesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        // Verify
                        if (name1 != null && name1.length() > 0) {
                            // Set Data
                            FourChoices fourChoices = new FourChoices();
                            fourChoices.setFourChoicesTemplateName(fourChoicesTemplateNameEditText.getText().toString());
                            fourChoices.setImageName1(name1);
                            fourChoices.setImageName2(name2);
                            fourChoices.setImageName3(name3);
                            fourChoices.setImageName4(name4);
                            fourChoices.setImagePath1(imagePath1);
                            fourChoices.setImagePath2(imagePath2);
                            fourChoices.setImagePath3(imagePath3);
                            fourChoices.setImagePath4(imagePath4);
                            // Log.i(TAG, "pecsImagePath: " + pecsImagePath.toString());

                            realm.beginTransaction();
                            realm.copyToRealm(fourChoices);
                            realm.commitTransaction();

                            adapterFourChoices.notifyDataSetChanged();

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


    public void setRealmAdapter(RealmResults<FourChoices> fourChoices) {

        RealmEditFourChoicesAdapter realmAdapter = new RealmEditFourChoicesAdapter(this.getActivity().getApplicationContext(), fourChoices, true);
        adapterFourChoices.setRealmAdapter(realmAdapter);
        adapterFourChoices.notifyDataSetChanged();

    }


    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapterFourChoices = new EditFourChoicesAdapter(getActivity());
        recycler.setAdapter(adapterFourChoices);

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


