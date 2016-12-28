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
import cit480.ourstory.models.FourChoices;
import cit480.ourstory.realm.RealmController;
import io.realm.Realm;
import io.realm.RealmResults;

public class EditFourChoicesAdapter extends RealmRecyclerViewAdapter<FourChoices> {

    final Context context;
    private Realm realm;

    public EditFourChoicesAdapter(Context context) {
        this.context = context;
        //this.pecs = pecs;
    }

    // Create New Views
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate a New Card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fourchoices_template_layout, parent, false);
        return new CardViewHolder(view);

    }

    // Replace what's inside of view

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        final FourChoices fourChoices = getItem(position);

        // Cast the default/basic view holder to new view
        final CardViewHolder holder = (CardViewHolder) viewHolder;


        // Name of Image
        holder.fourChoicesImage1Name.setText(fourChoices.getImageName1());
        holder.fourChoicesImage2Name.setText(fourChoices.getImageName2());
        holder.fourChoicesImage3Name.setText(fourChoices.getImageName3());
        holder.fourChoicesImage4Name.setText(fourChoices.getImageName4());
      //  holder.fourChoicesTemplateName.setText(fourChoices.getFourChoicesTemplateName());


        // Load the Image
        if (fourChoices.getImagePath1() != null) {

        }

        Picasso.with(context)
                .load(fourChoices.getImagePath1())
                .resize(180, 180)
                .into(holder.fourChoicesImage1);



        // Load the Image
        if (fourChoices.getImagePath2() != null) {

        }

        Picasso.with(context)
                .load(fourChoices.getImagePath2())
                .resize(180, 180)
                .into(holder.fourChoicesImage2);


        // Load the Image
        if (fourChoices.getImagePath3() != null) {

        }

        Picasso.with(context)
                .load(fourChoices.getImagePath3())
                .resize(180, 180)
                .into(holder.fourChoicesImage3);

        // Load the Image
        if (fourChoices.getImagePath4() != null) {

        }

        Picasso.with(context)
                .load(fourChoices.getImagePath4())
                .resize(180, 180)
                .into(holder.fourChoicesImage4);


        //Remove single match from Realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                RealmResults<FourChoices> results = realm.where(FourChoices.class).findAll();

                // Get Name of Image and display with Toast
                FourChoices f = results.get(position);
                String name = f.getFourChoicesTemplateName();

                // Realm transaction
                realm.beginTransaction();

                // Remove single match
                results.remove(position);
                realm.commitTransaction();

                notifyDataSetChanged();

                Toast.makeText(context, name + " is removed from four choices templates.", Toast.LENGTH_SHORT).show();
                return false;

            }
        });




        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<FourChoices> results = realm.where(FourChoices.class).findAll();

                // Get Name of Image and display with Toast
                FourChoices f = results.get(position);
                String templateName = f.getFourChoicesTemplateName();

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
    public TextView fourChoicesImage1Name;
    public ImageView fourChoicesImage1;
    public TextView fourChoicesImage2Name;
    public ImageView fourChoicesImage2;
    public TextView fourChoicesImage3Name;
    public ImageView fourChoicesImage3;
    public TextView fourChoicesImage4Name;
    public ImageView fourChoicesImage4;

   // public TextView fourChoicesTemplateName;

    public CardViewHolder(View itemView) {

        super(itemView);

        card = (CardView) itemView.findViewById(R.id.card_fourchoices_template);
        fourChoicesImage1Name = (TextView) itemView.findViewById(R.id.fourChoicesImage1Name);
        fourChoicesImage1 = (ImageView) itemView.findViewById(R.id.fourChoicesImage1);
        fourChoicesImage2Name = (TextView) itemView.findViewById(R.id.fourChoicesImage2Name);
        fourChoicesImage2 = (ImageView) itemView.findViewById(R.id.fourChoicesImage2);
        fourChoicesImage3Name = (TextView) itemView.findViewById(R.id.fourChoicesImage3Name);
        fourChoicesImage3 = (ImageView) itemView.findViewById(R.id.fourChoicesImage3);
        fourChoicesImage4Name = (TextView) itemView.findViewById(R.id.fourChoicesImage4Name);
        fourChoicesImage4 = (ImageView) itemView.findViewById(R.id.fourChoicesImage4);

    }
}
}

