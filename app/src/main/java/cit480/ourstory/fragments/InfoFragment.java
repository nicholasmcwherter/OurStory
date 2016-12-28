package cit480.ourstory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cit480.ourstory.R;

public class InfoFragment extends Fragment {


    ListView listView;

    String[] general_info_names = {
            "National Autism Resources",
            "PECS USA"
    };

    String[] general_info_links = {
            "https://www.nationalautismresources.com/the-picture-exchange-communication-system-pecs/",
            "http://www.pecsusa.com/pecs.php"
    };

    String[] pecs_site_names = {
            "Trainland Tripod"
    };

    String[] pecs_links = {
            "http://trainland.tripod.com/pecs.htm"
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_layout, container, false);

        setHasOptionsMenu(true);

        TextView gen_site1 = (TextView) rootView.findViewById(R.id.gen_site1);
        gen_site1.setClickable(true);
        gen_site1.setMovementMethod(LinkMovementMethod.getInstance());
        String gen1 = "<a href='https://www.nationalautismresources.com/the-picture-exchange-communication-system-pecs/'> National Autism Resources information on PECS </a>";
        gen_site1.setText(Html.fromHtml(gen1));

        TextView gen_site2 = (TextView) rootView.findViewById(R.id.gen_site2);
        gen_site2.setClickable(true);
        gen_site2.setMovementMethod(LinkMovementMethod.getInstance());
        String gen2 = "<a href='http://www.pecsusa.com/pecs.php'> PECS USA </a>";
        gen_site2.setText(Html.fromHtml(gen2));





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

