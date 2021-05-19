package com.example.sinemapp_users.Activitiler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sinemapp_users.Classlar.Film;
import com.example.sinemapp_users.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FilmDetayActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewAd,textViewYonetmen,textViewAciklama,textViewİmdb,textViewTür,textViewYil;
    private ImageButton button;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detay);
        imageView=findViewById(R.id.imageViewDetay);
        textViewAd=findViewById(R.id.textViewDeatyAd);
        textViewAciklama=findViewById(R.id.textViewAciklama);
        textViewİmdb=findViewById(R.id.textViewİmdb);
        textViewTür=findViewById(R.id.textViewTur);
        textViewYil=findViewById(R.id.textViewDetayYil);
        textViewYonetmen=findViewById(R.id.textViewYonetmen);
        button=findViewById(R.id.imageButton);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        ad= (String) getIntent().getSerializableExtra("ad");
        detayGetir(ad);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FilmDetayActivity.this,BiletalActivity.class);
                intent.putExtra("filmAd",ad);
                startActivity(intent);
            }
        });
    }

    public void detayGetir(String ad){
        reference.child("Filmler").child(ad).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Film film=snapshot.getValue(Film.class);
                textViewAd.setText(film.getAd());
                textViewİmdb.setText(film.getImdb()+"/10");
                textViewTür.setText("Tür: "+film.getTur());
                textViewYil.setText("Yıl: "+film.getYil());
                textViewYonetmen.setText("Yönetmen: "+film.getYonetmen());
                textViewAciklama.setText("Açıklama: "+film.getAciklama());
                Picasso.get().load(film.getFotoUrl()).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}