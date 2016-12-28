package cit480.ourstory.adapters;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import cit480.ourstory.R;
import cit480.ourstory.models.Firstthis;
import cit480.ourstory.models.PECS;
import cit480.ourstory.realm.RealmController;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class PECSDialogAdapter extends RealmRecyclerViewAdapter<PECS> {

    final Context context;
    private Realm realm;

    public PECSDialogAdapter(Context context) {
        this.context = context;

    }

    // Create New Views
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate a New Card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_template_layout, parent, false);
        return new CardViewHolder(view);

    }

    // Replace what's inside of view

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // Get the object/pecs/name
        final PECS pecs = getItem(position);

        // Cast the default/basic view holder to new view
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // Name of Image
        holder.pecsNameText.setText(pecs.getPecsImage());

        // Load the Image
        if (pecs.getPecsImagePath() != null) {

        }

        Picasso.with(context)
                .load(pecs.getPecsImagePath())
                .resize(180, 180)
                .placeholder(R.drawable.i_want)
                .into(holder.pecsImagePath);


        //Remove single match from Realm
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                RealmResults<PECS> results = realm.where(PECS.class).findAll();

                // Get Name of Image and display with Toast
                PECS p = results.get(position);
                String name = p.getPecsImage();

                // Realm transaction
                realm.beginTransaction();

                // Remove single match
                results.remove(position);
                realm.commitTransaction();


                notifyDataSetChanged();

                Toast.makeText(context, name + " is removed from images.", Toast.LENGTH_SHORT).show();
                return false;

            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<PECS> results = realm.where(PECS.class).findAll();
                RealmResults<Firstthis> results2 = realm.where(Firstthis.class).findAll();

                // Get Name of Image and display with Toast
                PECS p = results.get(position);
                String imageName = p.getPecsImage();
                String imagePath = p.getPecsImagePath();




               // results.get(position).

                Toast.makeText(context, "Image name: " + imageName + "Image path: " + imagePath, Toast.LENGTH_SHORT).show();

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
        public TextView pecsNameText;
        public ImageView pecsImagePath;

        public CardViewHolder(View itemView) {

            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_upload_image);
            pecsNameText = (TextView) itemView.findViewById(R.id.pecsNameText);
            pecsImagePath = (ImageView) itemView.findViewById(R.id.pecsImagePath);
        }
    }
}
