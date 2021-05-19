package com.example.sinemapp_users.Activitiler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.Button;
import com.example.sinemapp_users.Adapters.KoltukAdapter;
import com.example.sinemapp_users.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class BiletalActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Button button;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ArrayList<String>koltukList;
    private KoltukAdapter adapter;
    private String ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biletal);
        rv=findViewById(R.id.rvBiletAl);
        button=findViewById(R.id.buttonBiletAl);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        koltukList=new ArrayList<>();
        ad= (String) getIntent().getSerializableExtra("filmAd");
        koltukGetir(ad);
        adapter=new KoltukAdapter(koltukList,BiletalActivity.this,ad);
        rv.setAdapter(adapter);
    }

    public void koltukGetir(String ad){
        reference.child("Koltuklar").child(ad).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                koltukList.add(snapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}