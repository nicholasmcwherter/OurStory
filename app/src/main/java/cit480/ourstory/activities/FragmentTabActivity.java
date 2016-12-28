package cit480.ourstory.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import cit480.ourstory.R;
import cit480.ourstory.fragments.ChoicesFragment;
import cit480.ourstory.fragments.EditFirstthisFragment;
import cit480.ourstory.fragments.EditFourChoicesFragment;
import cit480.ourstory.fragments.EditThreeChoicesFragment;
import cit480.ourstory.fragments.EditTwoChoicesFragment;
import cit480.ourstory.fragments.FirstThisFragment;
import cit480.ourstory.fragments.FourChoicesFragment;
import cit480.ourstory.fragments.InfoFragment;
import cit480.ourstory.fragments.PECSFragment;
import cit480.ourstory.fragments.ScheduleFragment;
import cit480.ourstory.fragments.ThreeChoicesFragment;
import cit480.ourstory.fragments.TimerFragment;
import cit480.ourstory.fragments.TwoChoicesFragment;
import cit480.ourstory.models.ThreeChoices;

public class FragmentTabActivity extends AppCompatActivity
        implements AHBottomNavigation.OnTabSelectedListener {


    AHBottomNavigation bottomNavigation;
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**
        // Find the toolbar view inside the activity layout
        Toolbar topToolbar = (Toolbar) findViewById(R.id.topToolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(topToolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

         **/


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

        // Social Story tab is if we can get to it :) Similar to iMovie

        AHBottomNavigationItem firstThisItem = new AHBottomNavigationItem("FIRST THIS", R.drawable.firstthis);
        AHBottomNavigationItem choicesItem = new AHBottomNavigationItem("CHOICES", R.drawable.boards);
    //    AHBottomNavigationItem scheduleItem = new AHBottomNavigationItem("SCHEDULE", R.drawable.schedule);
     //   AHBottomNavigationItem timerItem = new AHBottomNavigationItem("TIMER", R.drawable.timer);
        AHBottomNavigationItem parentModeItem = new AHBottomNavigationItem("PARENT MODE", R.drawable.parentmode);

        // Add to Nav Tab

        // bottomNavigation.addItem(boardsItem);
        bottomNavigation.addItem(firstThisItem);
        bottomNavigation.addItem(choicesItem);
      //  bottomNavigation.addItem(scheduleItem);
      //  bottomNavigation.addItem(timerItem);
        bottomNavigation.addItem(parentModeItem);


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

        // Social Story tab is if we can get to it :) Similar to iMovie

        if (position == 0) {
            FirstThisFragment firstThisFragment = new FirstThisFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, firstThisFragment).addToBackStack(null).commit();
        }
        if (position == 1) {
            TwoChoicesFragment twoChoicesFragment = new TwoChoicesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, twoChoicesFragment).addToBackStack(null).commit();
        }

       // if (position == 2) {
      //      ScheduleFragment scheduleFragment = new ScheduleFragment();
      //      getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, scheduleFragment).addToBackStack(null).commit();
      //  }
      //  if (position == 3) {
      //      TimerFragment timerFragment = new TimerFragment();
      //      getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, timerFragment).addToBackStack(null).commit();
     //   }
        if (position == 2) {
            //ParentModeFragment parentModeFragment = new ParentModeFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, parentModeFragment).addToBackStack(null).commit();
            Intent intent = new Intent(FragmentTabActivity.this, PinRequestActivity.class);
            startActivity(intent);
        }
    }




    // Menu icons are inflated just as they were with actionbar
  //  @Override
 //   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.layout.actionbar_layout, menu);
    //    return true;
  //  }



    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStackImmediate();
        }
    }
}
