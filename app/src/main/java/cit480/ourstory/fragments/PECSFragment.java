package cit480.ourstory.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cit480.ourstory.R;
import cit480.ourstory.adapters.PECSAdapter;
import cit480.ourstory.adapters.RealmPECSAdapter;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import cit480.ourstory.utility.PermissionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


public class PECSFragment extends Fragment implements PermissionChangeListener {

    private PECSAdapter adapter;
    private Realm realm;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recycler;
    EditText nameEditText, urlEditText;
    private static final int READ_REQUEST_CODE = 42;
    Button uploadBtn;
    ImageView uploadImageView;
    String pecsImagePath;
    Uri imageUri = null;

    private static final int READ_EXTERNAL_STORAGE_REQUEST = 123;








        private void checkPermissions() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {//Can add more as per requirement
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(getActivity(), "This will allow you to utilize the map to improve your experience!",
                        Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST);
        }

        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.upload_layout, container, false);

        setHasOptionsMenu(true);

        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButtonUpload);
        recycler = (RecyclerView) rootView.findViewById(R.id.recycler);

        //realm.where(PECS.class).findAll();
        //RealmResults<PECS> results = realm.where(PECS.class).findAll();


        // Refresh after upload done.
        setupRecycler();



        this.realm = RealmController.with(this).getRealm();
        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getPECS());




        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Upload Image");
                dialog.setContentView(R.layout.input_dialog_template);


                // Edit Texts
                nameEditText = (EditText) dialog.findViewById(R.id.nameEditText);
              //  urlEditText = (EditText) dialog.findViewById(R.id.urlEditText);
                Button saveBtn = (Button) dialog.findViewById(R.id.saveBtn);
                uploadImageView = (ImageView) dialog.findViewById(R.id.uploadImageView);
                uploadBtn = (Button) dialog.findViewById(R.id.uploadBtn);


                // Upload Button
                uploadBtn.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {

                                                     checkPermissions();





                                                     openFileSearch();

                                                 }
                                             });


                // Save Button
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Get Data
                        String name = nameEditText.getText().toString();
                    //    String imageUrl = urlEditText.getText().toString();
                        pecsImagePath = imageUri.toString();


                        // Verify
                        if (name != null && name.length() > 0) {
                            // Set Data
                            PECS pecs = new PECS();
                            pecs.setPecsImage(name);
                           // pecs.setPecsUrl(imageUrl);
                            pecs.setPecsImagePath(pecsImagePath);
                            Log.i(TAG, "pecsImagePath: " + pecsImagePath.toString());

                            realm.beginTransaction();
                            realm.copyToRealm(pecs);
                            realm.commitTransaction();

                            adapter.notifyDataSetChanged();

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


    public void setRealmAdapter(RealmResults<PECS> pecs) {

        RealmPECSAdapter realmAdapter = new RealmPECSAdapter(this.getActivity().getApplicationContext(), pecs, true);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();

    }


    private void setupRecycler() {
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        adapter = new PECSAdapter(getActivity());
        recycler.setAdapter(adapter);

    }


    public void openFileSearch() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {

            if (resultData != null) {
                imageUri = resultData.getData();

                Log.i(TAG, "Uri: " + imageUri.toString());
                uploadImageView.setImageURI(imageUri);
            }
        }
    }


    public void onPermissionChange(boolean permissionOnOff) {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        }

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

     //   if (ContextCompat.checkSelfPermission(getActivity(),
     //           Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)

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