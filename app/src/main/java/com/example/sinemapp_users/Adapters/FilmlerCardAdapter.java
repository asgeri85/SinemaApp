package com.example.sinemapp_users.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sinemapp_users.Classlar.Film;
import com.example.sinemapp_users.Activitiler.FilmDetayActivity;
import com.example.sinemapp_users.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FilmlerCardAdapter extends RecyclerView.Adapter<FilmlerCardAdapter.FilmAdapterTutucu> {
    private List<Film>filmList;
    private Context mContext;

    public FilmlerCardAdapter(List<Film> filmList, Context mContext) {
        this.filmList = filmList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FilmAdapterTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.filmler_card,parent,false);
        return new FilmAdapterTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapterTutucu holder, int position) {
        Film f=filmList.get(position);
        holder.textView.setText(f.getAd());
        Picasso.get().load(f.getFotoUrl()).resize(250,350).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, FilmDetayActivity.class);
                intent.putExtra("ad",f.getAd());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class FilmAdapterTutucu extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView textView;
        private ImageView imageView;
        public FilmAdapterTutucu(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardHome);
            textView=itemView.findViewById(R.id.textViewCardAd);
            imageView=itemView.findViewById(R.id.imageViewCard);
        }
    }
}
