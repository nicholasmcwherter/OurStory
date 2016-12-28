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
import cit480.ourstory.models.TwoChoices;
import cit480.ourstory.realm.RealmController;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditTwoChoicesAdapter extends RealmRecyclerViewAdapter<TwoChoices> {

    final Context context;
    private Realm realm;

    public EditTwoChoicesAdapter(Context context) {
        this.context = context;
        //this.pecs = pecs;
    }

    // Create New Views
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate a New Card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.twochoices_template_layout, parent, false);
        return new CardViewHolder(view);

    }

    // Replace what's inside of view

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        final TwoChoices twoChoices = getItem(position);

        // Cast the default/basic view holder to new view
        final CardViewHolder holder = (CardViewHolder) viewHolder;


        // Name of Image
        holder.twoChoicesImage1Name.setText(twoChoices.getImageName1());
        holder.twoChoicesImage2Name.setText(twoChoices.getImageName2());
     //   holder.twoChoicesTemplateName.setText(twoChoices.getTwoChoicesTemplateName());


        // Load the Image
        if (twoChoices.getImagePath1() != null) {

            }

            Picasso.with(context)
                    .load(twoChoices.getImagePath1())
                    .resize(180, 180)
                    .into(holder.twoChoicesImage1);



        // Load the Image
        if (twoChoices.getImagePath2() != null) {

            }

            Picasso.with(context)
                    .load(twoChoices.getImagePath2())
                    .resize(180, 180)
                    .into(holder.twoChoicesImage2);


        //Remove single match from Realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                RealmResults<TwoChoices> results = realm.where(TwoChoices.class).findAll();

                // Get Name of Image and display with Toast
                TwoChoices tc = results.get(position);
                String name = tc.getTwoChoicesTemplateName();

                // Realm transaction
                realm.beginTransaction();

                // Remove single match
                results.remove(position);
                realm.commitTransaction();

                notifyDataSetChanged();

                Toast.makeText(context, name + " is removed from two choices templates.", Toast.LENGTH_SHORT).show();
                return false;

            }
        });




        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<TwoChoices> results = realm.where(TwoChoices.class).findAll();

                // Get Name of Image and display with Toast
                TwoChoices tc = results.get(position);
                String templateName = tc.getTwoChoicesTemplateName();

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
        public TextView twoChoicesImage1Name;
        public ImageView twoChoicesImage1;
        public TextView twoChoicesImage2Name;
        public ImageView twoChoicesImage2;
        public TextView twoChoicesTemplateName;

        public CardViewHolder(View itemView) {

            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_twochoices_template);
            twoChoicesImage1Name = (TextView) itemView.findViewById(R.id.twoChoicesImage1Name);
            twoChoicesImage1 = (ImageView) itemView.findViewById(R.id.twoChoicesImage1);
            twoChoicesImage2Name = (TextView) itemView.findViewById(R.id.twoChoicesImage2Name);
            twoChoicesImage2 = (ImageView) itemView.findViewById(R.id.twoChoicesImage2);
         //   twoChoicesTemplateName = (TextView) itemView.findViewById(R.id.firstthisTemplateName);
        }
    }
}
