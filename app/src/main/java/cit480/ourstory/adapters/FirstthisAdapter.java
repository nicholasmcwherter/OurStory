package cit480.ourstory.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cit480.ourstory.R;
import cit480.ourstory.models.Firstthis;
import cit480.ourstory.realm.RealmController;
import io.realm.Realm;

public class FirstthisAdapter extends RealmRecyclerViewAdapter<Firstthis> {

    final Context context;
    private Realm realm;

    public FirstthisAdapter(Context context) {
        this.context = context;
        //this.pecs = pecs;
    }

    // Create New Views
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate a New Card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.firstthis_template_layout, parent, false);
        return new CardViewHolder(view);

    }

    // Replace what's inside of view

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        final Firstthis firstthis = getItem(position);

        // Cast the default/basic view holder to new view
        final CardViewHolder holder = (CardViewHolder) viewHolder;


        // Name of Image
        holder.firstthisImage1Name.setText(firstthis.getImageName1());
        holder.firstthisImage2Name.setText(firstthis.getImageName2());
        holder.thenTextView.setText("Then");
        //holder.firstthisTemplateName.setText(firstthis.getFirstthisTemplateName());


        // Load the Image
        if (firstthis.getImagePath1() != null) {

            }

            Picasso.with(context)
                    .load(firstthis.getImagePath1())
                    .resize(180, 180)
                    .into(holder.firstthisImage1);



        // Load the Image
        if (firstthis.getImagePath2() != null) {

            }

            Picasso.with(context)
                    .load(firstthis.getImagePath2())
                    .resize(180, 180)
                    .into(holder.firstthisImage2);

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
        public TextView firstthisImage1Name;
        public ImageView firstthisImage1;
        public TextView firstthisImage2Name;
        public ImageView firstthisImage2;
        //public TextView firstthisTemplateName;
        public TextView thenTextView;

        public CardViewHolder(View itemView) {

            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_upload_image);
            firstthisImage1Name = (TextView) itemView.findViewById(R.id.firstthisImage1Name);
            firstthisImage1 = (ImageView) itemView.findViewById(R.id.firstthisImage1);
            firstthisImage2Name = (TextView) itemView.findViewById(R.id.firstthisImage2Name);
            firstthisImage2 = (ImageView) itemView.findViewById(R.id.firstthisImage2);
            thenTextView = (TextView) itemView.findViewById(R.id.thenTextView);
          //  firstthisTemplateName = (TextView) itemView.findViewById(R.id.firstthisTemplateName);
        }
    }
}
