package cit480.ourstory.activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import cit480.ourstory.R;
import cit480.ourstory.fragments.EditFirstthisFragment;
import cit480.ourstory.fragments.EditFourChoicesFragment;
import cit480.ourstory.fragments.EditScheduleFragment;
import cit480.ourstory.fragments.EditThreeChoicesFragment;
import cit480.ourstory.fragments.EditTwoChoicesFragment;
import cit480.ourstory.fragments.FourChoicesFragment;
import cit480.ourstory.fragments.InfoFragment;
import cit480.ourstory.fragments.PECSFragment;
import cit480.ourstory.fragments.ThreeChoicesFragment;
import cit480.ourstory.fragments.TwoChoicesFragment;
import cit480.ourstory.utility.PermissionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class FragmentTabActivityParentMode extends AppCompatActivity
        implements AHBottomNavigation.OnTabSelectedListener {

    AHBottomNavigation bottomNavigation;
    Realm realm;
    RealmChangeListener realmChangeListener;
    ListView listView;
    EditText nameEditText,urlEditText;

    private static final int READ_EXTERNAL_STORAGE_REQUEST = 123;


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults)
    {
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_REQUEST:
                boolean accepted;



                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                    accepted = true;
                    //Peform your task here if any
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                    accepted = false;
            }
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_layout);
                if(f instanceof PermissionChangeListener) {
                    ((PermissionChangeListener) f).onPermissionChange(accepted);
                }
                break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottomNav);

        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.custom_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                InfoFragment infoFragment = new InfoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, infoFragment).addToBackStack(null).commit();
                break;
            case R.id.twoChoicesItem:
                TwoChoicesFragment twoChoicesFragment = new TwoChoicesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, twoChoicesFragment).addToBackStack(null).commit();
                break;
            case R.id.threeChoicesItem:
                ThreeChoicesFragment threeChoicesFragment = new ThreeChoicesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, threeChoicesFragment).addToBackStack(null).commit();
                break;
            case R.id.fourChoicesItem:
                FourChoicesFragment fourChoicesFragment = new FourChoicesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fourChoicesFragment).addToBackStack(null).commit();
                break;
            case R.id.editTwoChoicesItem:
                EditTwoChoicesFragment editTwoChoicesFragment1 = new EditTwoChoicesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editTwoChoicesFragment1).addToBackStack(null).commit();
                break;
            case R.id.editThreeChoicesItem:
                EditThreeChoicesFragment editThreeChoicesFragment = new EditThreeChoicesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editThreeChoicesFragment).addToBackStack(null).commit();
                break;
            case R.id.editFourChoicesItem:
                EditFourChoicesFragment editFourChoicesFragment = new EditFourChoicesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editFourChoicesFragment).addToBackStack(null).commit();
                break;

        }


        return super.onOptionsItemSelected(item);
    }


    private void createNavItems()
    {
        // Create Items for Nav Tab

        AHBottomNavigationItem uploadItem = new AHBottomNavigationItem("UPLOAD", R.drawable.upload);
        AHBottomNavigationItem editFirstThisItem = new AHBottomNavigationItem("EDIT FIRST THIS", R.drawable.firstthis);
      //  AHBottomNavigationItem editScheduleItem = new AHBottomNavigationItem("FIRST THIS", R.drawable.firstthis);
        //AHBottomNavigationItem editTimerItem = new AHBottomNavigationItem("EDIT TIMER", R.drawable.timer);
        // AHBottomNavigationItem storyItem = new AHBottomNavigationItem("CREATE STORY", R.drawable.createstory);


        AHBottomNavigationItem editFourChoices = new AHBottomNavigationItem("EDIT CHOICES", R.drawable.boards);
        //AHBottomNavigationItem infoItem = new AHBottomNavigationItem("INFO", R.drawable.info);
        AHBottomNavigationItem exitItem = new AHBottomNavigationItem("LOGOUT", R.drawable.exit);

        // Add to Nav Tab

        bottomNavigation.addItem(uploadItem);
        bottomNavigation.addItem(editFirstThisItem);
       // bottomNavigation.addItem(editScheduleItem);
        //bottomNavigation.addItem(editTimerItem);

        // bottomNavigation.addItem(storyItem);

        bottomNavigation.addItem(editFourChoices);
        //bottomNavigation.addItem(infoItem);
        bottomNavigation.addItem(exitItem);


        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#1e7ec8"));

        // Change Colors

        bottomNavigation.setAccentColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setInactiveColor(Color.parseColor("#8da6bf"));

        // Force the titles to be displayed

        bottomNavigation.setForceTitlesDisplay(true);

        // Default Tab is Map
        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public void onTabSelected(int position, boolean wasSelected)
    {

        if (position == 0) {
            PECSFragment pecsFragment = new PECSFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, pecsFragment).addToBackStack(null).commit();
        }
        if (position == 1) {
            EditFirstthisFragment editFirstThisFragment = new EditFirstthisFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editFirstThisFragment).addToBackStack(null).commit();
        }
      //  if (position == 2) {
      //      EditScheduleFragment editScheduleFragment = new EditScheduleFragment();
      //      getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editScheduleFragment).addToBackStack(null).commit();
      //  }

        /*
        // If we have enough time we can get to the timer :)
        if (position == 3) {
            EditTimerFragment editTimerFragment = new EditTimerFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editTimerFragment).addToBackStack(null).commit();
        }


        // If we can get the social story working this would be the tab :) - Similar to iMovie

        if (position == 1) {
            StoryFragment storyFragment = new StoryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, storyFragment).addToBackStack(null).commit();
        }
        */

        if (position == 2) {
            EditFourChoicesFragment editFourChoicesFragment = new EditFourChoicesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, editFourChoicesFragment).addToBackStack(null).commit();


           // InfoFragment infoFragment = new InfoFragment();
           // getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, infoFragment).addToBackStack(null).commit();
        }

        if (position == 3) {
            //ExitFragment exitFragment = new ExitFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, exitFragment).addToBackStack(null).commit();
            Intent intent = new Intent(FragmentTabActivityParentMode.this, FragmentTabActivity.class);
            startActivity(intent);
        }

    }

    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
