package cit480.ourstory.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cit480.ourstory.R;
import cit480.ourstory.models.ThreeChoices;
import cit480.ourstory.realm.RealmController;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditThreeChoicesAdapter extends RealmRecyclerViewAdapter<ThreeChoices> {

    final Context context;
    private Realm realm;

    public EditThreeChoicesAdapter(Context context) {
        this.context = context;
        //this.pecs = pecs;
    }

    // Create New Views
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate a New Card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.threechoices_template_layout, parent, false);
        return new CardViewHolder(view);

    }

    // Replace what's inside of view

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        final ThreeChoices threeChoices = getItem(position);

        // Cast the default/basic view holder to new view
        final CardViewHolder holder = (CardViewHolder) viewHolder;


        // Name of Image
        holder.threeChoicesImage1Name.setText(threeChoices.getImageName1());
        holder.threeChoicesImage2Name.setText(threeChoices.getImageName2());
        holder.threeChoicesImage3Name.setText(threeChoices.getImageName3());
//        holder.threeChoicesTemplateName.setText(threeChoices.getThreeChoicesTemplateName());


        // Load the Image
        if (threeChoices.getImagePath1() != null) {

        }

        Picasso.with(context)
                .load(threeChoices.getImagePath1())
                .resize(180, 180)
                .into(holder.threeChoicesImage1);



        // Load the Image
        if (threeChoices.getImagePath2() != null) {

        }

        Picasso.with(context)
                .load(threeChoices.getImagePath2())
                .resize(180, 180)
                .into(holder.threeChoicesImage2);


        // Load the Image
        if (threeChoices.getImagePath3() != null) {

        }

        Picasso.with(context)
                .load(threeChoices.getImagePath3())
                .resize(180, 180)
                .into(holder.threeChoicesImage3);


        //Remove single match from Realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                RealmResults<ThreeChoices> results = realm.where(ThreeChoices.class).findAll();

                // Get Name of Image and display with Toast
                ThreeChoices thc = results.get(position);
                String name = thc.getThreeChoicesTemplateName();

                // Realm transaction
                realm.beginTransaction();

                // Remove single match
                results.remove(position);
                realm.commitTransaction();

                notifyDataSetChanged();

                Toast.makeText(context, name + " is removed from three choices templates.", Toast.LENGTH_SHORT).show();
                return false;

            }
        });




        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<ThreeChoices> results = realm.where(ThreeChoices.class).findAll();

                // Get Name of Image and display with Toast
                ThreeChoices thc = results.get(position);
                String templateName = thc.getThreeChoicesTemplateName();

                Toast.makeText(context, "Template name: " + templateName, Toast.LENGTH_SHORT).show();

            }
        });


    }





    // Return size of data set
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }

        return 0;

    }




public static class CardViewHolder extends RecyclerView.ViewHolder {

    public CardView card;
    public TextView threeChoicesImage1Name;
    public ImageView threeChoicesImage1;
    public TextView threeChoicesImage2Name;
    public ImageView threeChoicesImage2;
    public TextView threeChoicesImage3Name;
    public ImageView threeChoicesImage3;
    public TextView threeChoicesTemplateName;

    public CardViewHolder(View itemView) {

        super(itemView);

        card = (CardView) itemView.findViewById(R.id.card_threechoices_template);
        threeChoicesImage1Name = (TextView) itemView.findViewById(R.id.threeChoicesImage1Name);
        threeChoicesImage1 = (ImageView) itemView.findViewById(R.id.threeChoicesImage1);
        threeChoicesImage2Name = (TextView) itemView.findViewById(R.id.threeChoicesImage2Name);
        threeChoicesImage2 = (ImageView) itemView.findViewById(R.id.threeChoicesImage2);
        threeChoicesImage3Name = (TextView) itemView.findViewById(R.id.threeChoicesImage3Name);
        threeChoicesImage3 = (ImageView) itemView.findViewById(R.id.threeChoicesImage3);

    }

}

}

